/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.jpath.modal;

/**
 *
 * @author 1299792
 */
public class DBConnection {
    
    private String dbIP;
    private String dbUserName;
    private String dbPassword;
    private int    dbPort;
    private String dbSchema;
    public boolean isEmpty(){
     boolean flag=true;
     if((dbIP!=null)&&(dbUserName!=null)&&(dbPassword!=null)&&(dbPort!=0) && dbSchema!=null){
         if(dbSchema.equals("")==false && dbIP.equals("")==false && dbUserName.equals("")==false && dbPassword.equals("")==false && dbPort!=0 ){
         flag = false;
         }
     }
     
     return flag;
    }
    
    public String getDbIP() {
        return dbIP;
    }

    public void setDbIP(String dbIP) {
        this.dbIP = dbIP;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public int getDbPort() {
        return dbPort;
    }

    public void setDbPort(int dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbSchema() {
        return dbSchema;
    }

    public void setDbSchema(String dbSchema) {
        this.dbSchema = dbSchema;
    }
    
    
    
    
}
