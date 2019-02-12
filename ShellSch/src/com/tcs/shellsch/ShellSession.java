/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.shellsch;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.tcs.jpath.modal.Host;
import com.tcs.jpath.modal.JConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author 1299792
 */
public class ShellSession {
    public Session getSession(Config config) throws FileNotFoundException, JSchException{
       
//        JConfig jConfig = new JConfig();
    
       Session session =null;
//       String sshKnownHostFile =   getClass().getResource("git_server_known_host.txt").getFile();
        String sshKnownHostFile = config.getKnownHost();
        JSch  jsch = new JSch();
     
        jsch.setKnownHosts(new FileInputStream(new File(sshKnownHostFile)));
        session= jsch.getSession(config.getUserName(), config.getIp(), config.getPort());
        session.setPassword(config.getPassword());
        
        session.connect();
       return session;
    
    }
    
     public Session getSession(Config config,int timoutSeconds) throws FileNotFoundException, JSchException{
//       Config config = new Config();
       Session session =null;
       String sshKnownHostFile =   getClass().getResource("/git_server_known_host.txt").getFile();
        
        JSch  jsch = new JSch();
        jsch.setKnownHosts(new FileInputStream(new File(sshKnownHostFile)));
        session= jsch.getSession(config.getUserName(), config.getIp(), config.getPort());
        session.setPassword(config.getPassword());
        session.connect(timoutSeconds);
       return session;
    
    }
    
    
}
