/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.wrapper;

/**
 *
 * @author 1299792
 */
public class DriverProperty {
    private String type;
    private String path;
    private String loginPage;
    
    
    
    public DriverProperty(String type,String path){
      this.type=type;
      this.path = path;
    }
    public DriverProperty(){}
    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
    
    public boolean isEmpty(){
      boolean flag = false;
      try{
      if((type.isEmpty() && path.isEmpty()) || (type=="" && path=="") ){
        flag = true;
      }
      }catch(NullPointerException ex){
        flag = true;
      }
      return flag;
    }
    
}
