///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package shellsch;
//
//import com.jcraft.jsch.ChannelExec;
//import com.jcraft.jsch.ChannelShell;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
//import com.tcs.shellsch.Config;
//import com.tcs.shellsch.ShellChannel;
//import com.tcs.shellsch.ShellSession;
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintStream;
//import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author 1299792
// */
//public class ShellSch {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//      Config c = new Config();
//      c.setUserName("fnsonla1");
//      c.setPassword("Ambank@123");
//      c.setIp("10.54.237.40");
//      c.setPort(22);
//      c.setKnownHost("D:\\Users\\1299792\\Documents\\NetBeansProjects\\Regrato\\git_server_known_host.txt");
//        ShellSession session = new ShellSession();
//        try {
//            Session shellSession =   session.getSession(c);
//             
////            ChannelExec ex = channel.openExecChannel(shellSession);
//            ChannelShell shell = (ChannelShell)shellSession.openChannel("shell");
////            ex.setCommand("bash --login -c  ambd1000 <<< '${YES}'");
////            ex.setCommand("echo $data");
//               shell.setAgentForwarding(true);
//          try {
////              ex.connect();
//              OutputStream s =  shell.getOutputStream();
//              InputStream in = shell.getInputStream();
//              shell.setOutputStream(s,true);
//              shell.setInputStream(in,true);
//               BufferedOutputStream out = new BufferedOutputStream(s);
//              shell.connect();
//              out.write(new String("echo $data").getBytes());
//              System.out.println(shell.isConnected());
//              byte []b = new byte[1024];
//              BufferedInputStream bin = new BufferedInputStream(in);
//              int a = 0;
//              while((a=bin.read(b, 0, a))>0){
//                String s1 = new String(b);
//                System.out.println(s1);
//              }
//              in.close();
//              bin.close();
//              out.flush();
//              out.close();
////              InputStream stream = ex.getInputStream();
////              InputStream error  = ex.getErrStream();
////              
////              Scanner in = new Scanner(stream);
////              while(in.hasNext()){
////                System.out.println(in.nextLine());
////                
////              }
////              in.close();
////              s.close();
////              int retValue = ex.getExitStatus();
////              System.out.println(retValue);            
////              
//          } catch (IOException ex1) {
//              Logger.getLogger(ShellSch.class.getName()).log(Level.SEVERE, null, ex1);
//          }
//           
//            shell.disconnect();
//            shellSession.disconnect();
//           
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ShellSch.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (JSchException ex) {
//            Logger.getLogger(ShellSch.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//}
