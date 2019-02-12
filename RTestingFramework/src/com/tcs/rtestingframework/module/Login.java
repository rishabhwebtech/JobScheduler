/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.module;

import com.tcs.rtestingframework.exception.InvalidAttributeValueException;
import com.tcs.rtestingframework.util.Comman;
import com.tcs.rtestingframework.util.XmlUtil;
import com.tcs.rtestingframework.wrapper.LoginModule;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author 1299792
 */
public class Login {
    WebDriver driver  ; 
    Comman commanFunction;
    public Login(WebDriver driver){
      this.driver = driver;
      this.commanFunction = new Comman();
    }
    
    public void doLogin() throws InvalidAttributeValueException,JavascriptException{
        XmlUtil util = new XmlUtil();
        LoginModule mod =   util.getLoginDetails();
        String brType   = mod.getBranchType();
        String inType   = mod.getInstitutionType();
        String userType = mod.getUserNameType();
        String passType = mod.getPasswordType();
        
        String brValue   = mod.getBranchValue();
        String inValue   = mod.getInstitutionValue();
        String userValue = mod.getUserNameValue();
        String passValue = mod.getPasswordValue();
        
        
        String userNameContent   = mod.getUserNameContent();
        String passwordContent   = mod.getPasswordContent();
        String branchContent     = mod.getBranchContent();
        String institutionContent= mod.getInstitutionContent();
        
        
        
        WebElement  branchElement = null;
        WebElement  institutionElement = null;
        WebElement  userElement = null;
        WebElement  passwordElement = null;
        WebElement  loginButton = null;
        
        if(brType.equals("id")){
          branchElement =  driver.findElement(By.id(brValue));
        }else if(brType.equals("name")){
          branchElement = driver.findElement(By.name(brValue));
        }
        
        
        if(inType.equals("id")){
            institutionElement = driver.findElement(By.id(inValue));
        }else if(brType.equals("name")){
          institutionElement = driver.findElement(By.name(inValue));
        }
          
        if(userType.equals("id")){
          userElement = driver.findElement(By.id(userValue));
        }else if(brType.equals("name")){
          userElement = driver.findElement(By.name(userValue));
        } 
        
        if(passType.equals("id")){
          passwordElement = driver.findElement(By.id(passValue));
        }else if(brType.equals("name")){
           passwordElement = driver.findElement(By.name(passValue));
        }
        
       
        userElement.sendKeys(userNameContent);
        passwordElement.sendKeys(passwordContent);
        branchElement.sendKeys(branchContent);
        institutionElement.sendKeys(institutionContent);
        loginButton = driver.findElement(By.id("Login"));
         loginButton.sendKeys(Keys.ENTER);
      
      
//        commanFunction.pageWait(driver, 60);

    }
    
}
