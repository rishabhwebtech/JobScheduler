/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.resources;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.tcs.jpath.modal.Host;
import com.tcs.jpath.modal.JConfig;
import com.tcs.regrato.util.Comman;
import com.tcs.shellsch.Config;
import com.tcs.shellsch.ShellSession;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections.map.MultiKeyMap;


/**
 *
 * @author 1299792
 */
public class SSHSessionFactory {
    
//    private static SSHSessionFactory instance = null;
//    private static HashMap<String, Session> sessionPool=null ;
   
    private static MultiKeyMap sessionPool = null;
    private static org.apache.log4j.Logger log;
    public static Session  getSSHSession(String branch,String mode){
        Session retSession =null;
        log = new Comman().getLogger();
        if(sessionPool==null){
//           sessionPool = new HashMap<>();
             sessionPool = new MultiKeyMap();
          }
           Session sshSession=(Session)sessionPool.get(branch,mode);
           if(sshSession==null){
           ShellSession session = new ShellSession();
           Config configShell = new Config();
           JConfig config = new JConfig();
           Host host = config.getHost();
           String ip = "";
           String hostKnown = "";
           String hostPass = "";
           String userName = "";
        
           switch (mode){
                case "D":
                   ip=host.getDayHostIp();
                   hostKnown=host.getDayHostKnonwHost();
                   userName=host.getDayHostUserName();
                   hostPass = host.getDayHostPassword();
                 break;
                 case "N":
                    ip=host.getNightHostIp();
                    hostKnown=host.getNightHostKnownHost();
                    userName=host.getNightHostUserName();
                    hostPass = host.getNightHostPassword();
                    break;
                 case "C":
                 default:
                   ip=host.getHostIp();
                   hostKnown=host.getHostKnownHost();
                   hostPass=host.getHostPassword();
                   userName=host.getHostUserName();                  
              
              }
              
              
              configShell.setIp(ip);
              configShell.setPassword(hostPass);
              configShell.setPort(22);
              configShell.setUserName(userName);
              configShell.setKnownHost(hostKnown);        
            try {
                Session sessionShell= session.getSession(configShell);
                sessionPool.put(branch,mode, sessionShell);
            } catch (FileNotFoundException ex) {
                log.error(SSHSessionFactory.class.getName()+ ex);
                Logger.getLogger(SSHSessionFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSchException ex) {
                log.error(SSHSessionFactory.class.getName()+ ex);
                Logger.getLogger(SSHSessionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        
           }
          
           retSession=(Session)sessionPool.get(branch,mode);
         return  retSession;
    }
    public static void releaseAllSHHSession(){
        if(sessionPool!=null){

           ArrayList<Session> s = new ArrayList<Session>(sessionPool.values());
           for(int i =0;i<s.size();i++){
               s.get(i).disconnect();
           }
          sessionPool=null;
        }
        
    
    }
}
