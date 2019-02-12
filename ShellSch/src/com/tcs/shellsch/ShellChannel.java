/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.shellsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 *
 * @author 1299792
 */
public class ShellChannel {
    
    
    private String command ;
    
    
    public ChannelExec openExecChannel(Session session) throws JSchException{
        ChannelExec  channelExec=null;
        channelExec = (ChannelExec)session.openChannel("exec");
        
        channelExec.setPty(true);
        return channelExec;
    }
  
    public ChannelSftp  openSftpChannel(Session session) throws JSchException{
           ChannelSftp sftp=null;
       
            Channel channel= session.openChannel("sftp");
           channel.connect();
           sftp    =(ChannelSftp)channel;
           
        
       
       return sftp;
    }
    
    
        public ChannelSftp  openSftpChannel(Session session, int timeout) throws JSchException{
           ChannelSftp sftp=null;
       
           Channel channel= session.openChannel("sftp");
           channel.connect(timeout);
           sftp    =(ChannelSftp)channel;
           sftp.setPty(true);
         return sftp;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
 
}
