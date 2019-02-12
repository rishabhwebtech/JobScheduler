/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.jpath.function;

/**
 *
 * @author 1299792
 */
public final class ScriptText {
    
//    public final static String IE_DOM_REMOVE_EVENT = "window.doEventRandomNameRemovejpath3434 = new Function(\""+
//                               "if()\");";
    
    public  final static  String IE_DOM_EVENT = "window.domEventRandomName4567810jpath = new Function(\""+
                                                "eventAttacher\",\""+
                                              
//                                                "eventAttacher.detachEvent(\'onclick\',function(){var caller =  eventAttacher.parentWindow.event.target || eventAttacher.parentWindow.event.srcElement; eventFunctionRandomName45678jpath(caller)  },false) }"+
                                                "if(eventAttacher.attachEvent){"+
                                                 "eventAttacher.attachEvent(\'onclick\',function(){var caller =  eventAttacher.parentWindow.event.target || eventAttacher.parentWindow.event.srcElement; var returnValue = eventFunctionRandomName45678jpath(caller) ; returnValue = jsonStringyfyFunctionRandomCreate42343(returnValue); fileWriteIEFunctionRandomCreated22343(returnValue); },false)}else{"
                                                 +"eventAttacher.addEventListener(\'click\',function(event){eventFunctionRandomName45678jpath(event)},false)}"+
                                                 "\");";
     
    
                                                        
    public final static String FIREFOX_DOM_EVENT= "window.domEventRandomName4567810jpath = new Function(\""+
                                                   "eventAttacher\",\""+
                                                   "eventAttacher.addEventListener(\'click\',function(event){"+
                                                   "  eventFunctionRandomName45678jpath(event)},false);"+
                                                   "\");";
    
    public final static String GLOBAL_VAR = "window.iframeTreeStruct=[];";
    
    public final static String CSS_SELECTOR = "window.getUniqueSelectorRandomName = new Function(\""+ 
                                               "element\",\""+
                                           
                                               
                                               "\");";
    
    public final static String GENRIC_GET_IFRAME = "window.getElementLocationInsideIframeRandomCreated = new Function(\""+
                                                "element\",\""+
                                                "var rootNode;"+
                                                "if(element.ownerDocument){"+
                                                 " rootNode = element.ownerDocument;"+
                                                "}else if(element.getRootNode){"+
                                                " rootNode=element.getRootNode();"+
                                                "}"+
                                               
                                                "var frameDetails ;"+
                                                "if(rootNode.defaultView){"+
                                                "frameDetails = rootNode.defaultView.frameElement;"+
                                                "}else if(rootNode.parentWindow){"+
                                                "frameDetails = rootNode.parentWindow.window.frameElement;"+
                                                "}"+                                              
                                                "if(frameDetails == null ){"+
                                                " return;"+
                                                "}"+
                                                "var id   = frameDetails.id;"+
                                                "if ( typeof id != \'undefined\' || id != \'\' ){"+
                                                " id = frameDetails.id;"+
                                                "}else{"+
                                                " id = frameDetails.name;"+
                                                "}"+
                                                "window.iframeTreeStruct.push(id);"+
                                                "return window.getElementLocationInsideIframeRandomCreated(frameDetails);"+
                                                "\");";
    
    public final static String IE_FILE_WRITE  = "window.fileWriteIEFunctionRandomCreated22343 =  new Function(\""+
                               "fileContent\",\""+
                               "try { "+
                               "var fso = new ActiveXObject(\'Scripting.FileSystemObject\');"+
                               "var filePath = fso.GetSpecialFolder(2)+\'/temp_jpath_012.txt\';  "+
                               "var file = fso.OpenTextFile(filePath,8, true);"+
                               "file.WriteLine(fileContent); "+
                               "file.Close();"+
                               "} catch (e) {"+
                                "alert(\'JPath Lib Write Error\');"+
                               "}\");";
    
    public final static String IE_EVENT_FUNCTION = "window.eventFunctionRandomName45678jpath = new Function(\""+
            "element\",\""+
            "var returnStringValue={};"+
            "var id=\'\';"+
            "var valueOfElement=\'\';"+
            "var className=\'\';"+
            "var xpath=\'\';"+
            "var tagName=\'\';"+
            "var elementLocationInIframe=[];"+
            "id = element.id;"+
            "valueOfElement=element.value;"+
            "window.getElementLocationInsideIframeRandomCreated(element);"+
          
            "elementLocationInIframe=window.iframeTreeStruct.join(\'>\');"+
            "window.iframeTreeStruct=[];"+
            "className = element.className;"+
            "tagName = element.nodeName || element.tagName;"+
            "xpath = window.getPathRandomCreate35435Jpath(element);"+
            "returnStringValue[\'id\']=id;"+
            "returnStringValue[\'className\']=className;"+
            "returnStringValue[\'xpath\']=xpath;"+
            "returnStringValue[\'tagName\']=tagName;"+
            "returnStringValue[\'iframe\']=elementLocationInIframe;"+
            "returnStringValue[\'value\']=valueOfElement;"+
            "returnStringValue[\'variable\']=\' \';"+
            "returnStringValue[\'cssSelector\']=\' \';"+
            "return returnStringValue;"
            +"\");";
    
    public final static String IE_JSON_STRINGYFY_FUNCTION = "window.jsonStringyfyFunctionRandomCreate42343 = new Function(\'"+
                              "jsonData\',\'"+
                              "var JsonStringify=\"\";"+
                              "var count=0;"+
                              "for(var i in jsonData){ "+
                              "var key = i;"+
                              "var val = jsonData[i];"+
                              "if(count==0){ "+
                              "JsonStringify=JsonStringify+\"{\";"+
                              "}"+
                              "if(val.length==0){"+
                              "val=\" \"; }"+
//                              "JsonStringify=JsonStringify+ key +\':\'+val+\',\';  "+
                              "JsonStringify=JsonStringify + \"@@\" + key + \"@@\" +\":\" + \"@@\" +val+ \"@@\" + \",\";  "+
//                              "JsonStringify=JsonStringify + \"'\" + key +\"'\"+ \":\" + \"'\"+val+\"'\"+\",\";"+
                              "count = count + 1; "+
                              "}"+
                              "JsonStringify=JsonStringify.replace(/.$/,\"\")+\"}\";"+
                              "return JsonStringify;\');";
    
    public static final String IE_EVENT_CALL = "window.domEventRandomName4567810jpath(document);";
    public static final String searchForMyKadAppletFunctionVer2 ="window.searchForMyKadAppletVersion2= new Function(\'nodeFrame\',\'var allFrame=[];"+
               "for(var i =0;i<nodeFrame.length;i++){"+
               "var tempIFrame = nodeFrame[i];"+
               "var innerDoc=tempIFrame.contentDocument || tempIFrame.contentWindow.document;"+
               "var innerApplet=innerDoc.getElementsByTagName(\"applet\")[0];"+
               "var innerFrame = innerDoc.getElementsByTagName(\"iframe\");"+
               "for(var j=0;j<innerFrame.length;j++){"+
               "allFrame.push(innerFrame[j]);"+
               "}"+
               "if(innerApplet!=null){"+
//               "parent.isAppletFound=true; innerApplet.setAttribute(\"id\",\"MyKadAppletDisable\");"+
               "domEventRandomName4567810jpath(innerDoc); "+
               "}else{domEventRandomName4567810jpath(innerDoc);}"+
               "}"+
               "return allFrame;"+
               " \')";
    public static final String  callBackFunctionToAttachEventOnIframe="window.callbackFunction34567RandomCreated = new Function(\""+
                 "var framesByTag = document.getElementsByTagName(\'iframe\');while((framesByTag=searchForMyKadAppletVersion2(framesByTag)).length!=0){}"+
                 "\");"+
                 "window.overlay345678RandomCreated=document.getElementById(\"overlay12\");"+
                 "if(window.overlay345678RandomCreated.addEventListener)"+
                 "{window.overlay345678RandomCreated.addEventListener(\"DOMAttrModified\",window.callbackFunction34567RandomCreated)"+
                 "}else{"+
                   "window.overlay345678RandomCreated.attachEvent(\"onpropertychange\",window.callbackFunction34567RandomCreated);"+
                 "}";
   public static final String IE_XPATH_FINDER_FUNCTION_VER_1 = "window.getPathRandomCreate35435Jpath = new Function(\""+
                              "element\",\""+
                              "if (element.tagName == 'HTML')"+
                              "return '/HTML[1]';"+
                              "if (element===document.body)"+
                              "return '/HTML[1]/BODY[1]';"+
                              "var ix= 0;"+
                              "var siblings= element.parentNode.childNodes;"+
                              "for (var i= 0; i<siblings.length; i++) {"+
                              "var sibling= siblings[i];"+
                              "if (sibling===element)"+
                              "return getPathRandomCreate35435Jpath(element.parentNode)+'/'+element.tagName+'['+(ix+1)+']';"+
                              "if (sibling.nodeType===1 && sibling.tagName===element.tagName)"+
                              " ix++;"+
                              " }"+
                              "\");";
 
  public static final String FIREFOX_XPATH_FINDER_FUCNTION_VER_2 =  "window.getPathRandomCreate35435Jpath= new Function(\""+
                 "element\",\""+
                 "if (!element) {return null;}"+
                 "if (element.id) {"+
                 "return `//*[@id=${element.id}]`;"+
                 "} else if (element.tagName === 'BODY') {"+
                 "return '/html/body';"+
                 "} else { "+
                 "const sameTagSiblings = Array.from(element.parentNode.childNodes)"+
                 ".filter(e => e.nodeName === element.nodeName)"+
                 "const idx = sameTagSiblings.indexOf(element)"+
                 "return getElementXPath(element.parentNode) +"+
                 "'/' +"+
                 "element.tagName.toLowerCase() +"+
                 "sameTagSiblings.length > 1 ? `[${idx + 1}]` : '')"+
                 "}"+
                 "}"+
                 "\");";
    
   public static final String XPATH_FINDER_FUCNTION_VER_1 = "window.getPathRandomCreate35435Jpath= new Function(\""+
                 "elem\",\""+
                 "var pathElements = [];"+
                 "var returnJsonObject={};"+
                 "var index = 0;"+
                 "var siblings = elem.parentNode.getElementsByTagName(elem.tagName);"+
                 "for (var i=0, imax=siblings.length; i<imax; i++) {"+
                 "if (elem === siblings[i]) {"+
                 "index = i+1; "+
                 "}"+
                 "}"+
                 "while (elem.tagName.toLowerCase() != \'html\') {"+
                 "pathElements.unshift(elem.tagName);"+
                 "elem = elem.parentNode;"+
                 "}"+
                 "var xpath  = pathElements.join(\'/\') + \'[\' + index + \']\';"+
                 "returnJsonObject[\'xpath\']=xpath;"+
                 "returnJsonObject['\'id\'']=elem;"+
                 "return returnJsonObject;"+
                 "\");";
   
   
   public final static String CSS_PATH_CALULATOR_FUNCTION_IE_7 = ";";
}
