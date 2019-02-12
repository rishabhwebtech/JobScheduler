/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;

import com.tcs.jpath.modal.Branch;
import com.tcs.jpath.modal.JConfig;
import com.tcs.regrato.event.TableHandlerEvent;
import com.tcs.regrato.gui.BranchEditor;
import com.tcs.regrato.gui.BranchRenderer;
import com.tcs.regrato.gui.ButtonEditor;
import com.tcs.regrato.gui.ButtonRenderer;
import com.tcs.regrato.gui.CommandEditor;
import com.tcs.regrato.modal.CommandEditorModal;
import com.tcs.regrato.modal.CommandEditorTableModel;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.util.Comman;
import com.tcs.regrato.xml.TraceReader;
import com.tcs.rtestingframework.tcl.parser.TCLParser;
import com.tcs.rtestingframework.tcl.tag.Click;
import com.tcs.rtestingframework.tcl.tag.Field;
import com.tcs.rtestingframework.tcl.tag.Put;
import com.tcs.rtestingframework.tcl.tag.Run;
import com.tcs.rtestingframework.tcl.tag.Select;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.w3c.dom.Element;

/**
 *
 * @author 1299792
 */
public class CommandEditorThread extends SwingWorker<String,List<JTable>>{

    private JTabbedPane pane = null;
    private JLabel commandFileName ;
    String traceFileName ;
    private JTextField desc;
    private CommandEditorModal com;
    private org.apache.log4j.Logger log;
    public CommandEditorThread(JTabbedPane pane,JLabel commandFileName){
      this.pane = pane;
      this.commandFileName = commandFileName;
      this.com=CommandEditorModal.getCommandEditorModal();
      desc = this.com.getDescription();
      this.log= new Comman().getLogger();
    }
    
    
    @Override
    protected String doInBackground() throws Exception {
        JFileChooser choose = new JFileChooser();
        List<JTable> t = new ArrayList<>();
        String desc="";
        File traceFile = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Trace File", "xml");
        choose.setFileFilter(filter);
        if(commandFileName.getText().equals("New Command")){
            traceFile  = new File("data"+File.separator+"newCommandEdit.xml");
        } else{
        String tracePath = new Path().getPath(Path.TRACED_DATA);
        choose.setCurrentDirectory(new File(tracePath));
        int retValue=choose.showOpenDialog(new CommandEditor());
        if(retValue==JFileChooser.OPEN_DIALOG){
          traceFile  = choose.getSelectedFile();
        }
       }
        if(traceFile!=null){
            System.out.println("Trace File Details "+traceFile.getPath());
            CommandEditorModal.getCommandEditorModal().setFilePath(traceFile.getPath());
            this.traceFileName = traceFile.getName();
//            TCLParser parser = TCLParser.getTCLParserInstance(traceFile.getPath());
            TCLParser parser = new TCLParser(traceFile.getPath());
            Element rootElement = parser.getRootElemement();
            desc=rootElement.getAttribute("name");
            t=processXml(rootElement);
            publish(t);
        }else{
          
          return null;
        }
        
        
//        return t;
              return desc;
    }
 
    
    private List<JTable> processXml(Element rootElement){
//    private void processXml(Element rootElement){
          List<JTable> t = new ArrayList<>();

          TraceReader reader = new TraceReader(rootElement);
          System.out.println("Variables Loaded");  
          String []testCasesName = reader.getTestCasesName();
          HashMap<String,List<Object>> testCases = reader.getTraceData();
          int testCasesLength = testCasesName.length;
//          final String []columnData = {"Variable","Command","Value","Location","By","Map Name","Action"};
          final String []columnData = {"Variable","Command","Value","Location","By","Map Name","Status"};
          for(int i =0;i<testCasesLength;i++){
              String testCase = testCasesName[i];
              List<Object> tags =  testCases.get(testCase);

              CommandEditorTableModel modal = new CommandEditorTableModel();
              JTable table = new JTable(modal);
             
              table.setName(testCase);
              for(String s : columnData){
               modal.addColumn(s);
               
              }
              int lengthOfTags = tags.size();
             for(int j=0;j<lengthOfTags;j++){
               Object object =   tags.get(j);
               Class classNameTemp = object.getClass();
               String className = classNameTemp.getSimpleName();
               
               String var = "";
               String location = "";
               String value    = "";
               String command = "";
               
               switch(className){
                   case "Click":
                       Click click = (Click)object;
                       var  = click.getVariable();
                       location = click.getLocation();
                       command = "click";
                       break;
                       
                   case "Put":
                       Put put = (Put)object;
                       location = put.getLocation();
                       value = put.getValue();
                       var = put.getVariable();
                       command = "put";
                      break;
                   case "Select":
                       Select select =  (Select)object;
                       location = select.getLocation();
                       value = select.getValue();
                       var = select.getVariable();
                       command = "select";
                       break;
                   case "Run" :
                       Run run = (Run)object;
                       location = run.getLocation();
                       value = run.getValue();
                       var = run.getVar();
                       command = "run";
                       break;
               
               }
                 Field field =  reader.getField(testCase,var);
                 if(field!=null){
                  String by = field.getBy();
                  String name = field.getName();
                  boolean isMandatory = field.isMandatory();
                  String map = field.getMap();
                  String variableName = field.getName();
//                  String []row = {variableName,command,value,location,by,map,"Delete"};
                  String []row = {variableName,command,value,location,by,map,"No Action"};
                  modal.addRow(row);
                  
                 }
               
             }

               t.add(table);
          }
         
          return t;


    }



    @Override
    protected void process(List<List<JTable>> chunks) {
       System.out.println("Process");
       Comman commanFunction = new Comman();
       Branch branch = new JConfig().getBranches();
       ArrayList<String> list = branch.getBranches();  
       
       
       System.out.println("Command File Name "+ this.traceFileName);
//       this.commandFileName.setText(this.traceFileName);
       List<JTable> t = chunks.get(0);
       int length  = t.size();
       for(int i =0;i<length;i++){
        JTable t1 = t.get(i);
        t1.getModel().addTableModelListener(new TableHandlerEvent());
        t1.setRowHeight(30);
        commanFunction.addingRightClickMenu(t1);
        JCheckBox b = new JCheckBox();
           JComboBox<String> branchb= new JComboBox();
           
        Branch branch1 = new JConfig().getBranches();
        ArrayList<String> list1 = branch1.getBranches();  
        for(String s:list1){
          branchb.addItem(s);
         }     
           
        ButtonEditor editor = new ButtonEditor(b);
        BranchEditor branchEditor = new BranchEditor(branchb);
        BranchRenderer branchR = new BranchRenderer(list1.stream().toArray(String[]::new));
        ButtonRenderer renderer = new ButtonRenderer();
    
//        t1.getColumn("Action").setCellRenderer(renderer);
//        t1.getColumn("Action").setCellEditor(editor);
         t1.getColumn("Status").setCellRenderer(renderer);
         t1.getColumn("Status").setCellEditor(editor);
        t1.getColumn("Location").setCellRenderer(branchR);
        t1.getColumn("Location").setCellEditor(branchEditor);
        JScrollPane scrolPane = new JScrollPane(t1);
        String nameOfTab = "";
        String action=this.commandFileName.getText();
        if(action.equals("New Command")){
         nameOfTab=t1.getName();
         int tabCount = this.pane.getTabCount();
         nameOfTab=nameOfTab+"_"+(tabCount+1);
        }else{
          nameOfTab=t1.getName();
        }
        this.pane.addTab(nameOfTab,scrolPane);
        this.commandFileName.setText(this.traceFileName);
        CommandEditorModal.getCommandEditorModal().setCommandFileLabel(commandFileName);
 
       }
    }

    @Override
    protected void done() {
        try {
            String desc1= get();
            if(desc1.equals("")==false){
                 desc.setText(desc1);
                 this.com.setDescription(desc);
            }
           com.setMainTabedPaneOfCommandEditor(pane);
        } catch (InterruptedException ex) {
            Logger.getLogger(CommandEditorThread.class.getName()).log(Level.SEVERE, null, ex);
              this.log.error(ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(CommandEditorThread.class.getName()).log(Level.SEVERE, null, ex);
              this.log.error(ex);
        }catch(NullPointerException ex){
          this.log.error(ex);
        }
    }

     
}
