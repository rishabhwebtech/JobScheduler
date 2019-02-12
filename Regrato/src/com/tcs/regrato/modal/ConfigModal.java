/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.modal;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 *
 * @author 1299792
 */
public class ConfigModal {
    private static ConfigModal instance = null;
    private ConfigModal(){}
    
    public static ConfigModal getConfigModal(){
       if(instance==null){
         instance = new ConfigModal();
       }
       return instance;
    }
    
    private JTextField webpage;
    private JTextField driver;
    private JComboBox<String> type;
    private JDialog mainDialog;
    private JTextField userName;
    private JTextField password;
    private JTextField branch;
    private JTextField inst;
    // Control Region
    private JTextField hostPassword;
    private JTextField userNameHost;
    private JTextField ipAddressHost;
    private JTextField knownHost;
    
    // Day
    private JTextField dayIpAddress;
    private JTextField dayUserName;
    private JTextField dayPassword;
    private JTextField dayKnownFile;
    
    // Night
    
    private JTextField nightIpAddress;
    private JTextField nightUserName;
    private JTextField nightPassword;
    private JTextField nightKnownFile; 
    
    // Branch
    
    private JTextField branchNameToSave;
    private JList      allBranches;
    
    // DBConnection;
    
    private JTextField dbPassword;
    private JTextField dbUserName;
    private JTextField dbIP;
    private JTextField dbPort;
    private JTextField dbSchema;
    
    public JTextField getWebpage() {
        return webpage;
    }

    public void setWebpage(JTextField webpage) {
        this.webpage = webpage;
    }

    public JTextField getDriver() {
        return driver;
    }

    public void setDriver(JTextField driver) {
        this.driver = driver;
    }

    public JComboBox<String> getType() {
        return type;
    }

    public void setType(JComboBox<String> type) {
        this.type = type;
    }

    public JDialog getMainDialog() {
        return mainDialog;
    }

    public void setMainDialog(JDialog mainDialog) {
        this.mainDialog = mainDialog;
    }

    public JTextField getUserName() {
        return userName;
    }

    public void setUserName(JTextField userName) {
        this.userName = userName;
    }

    public JTextField getPassword() {
        return password;
    }

    public void setPassword(JTextField password) {
        this.password = password;
    }

    public JTextField getBranch() {
        return branch;
    }

    public void setBranch(JTextField branch) {
        this.branch = branch;
    }

    public JTextField getInst() {
        return inst;
    }

    public void setInst(JTextField inst) {
        this.inst = inst;
    }

    public JTextField getHostPassword() {
        return hostPassword;
    }

    public void setHostPassword(JTextField hostPassword) {
        this.hostPassword = hostPassword;
    }

    public JTextField getUserNameHost() {
        return userNameHost;
    }

    public void setUserNameHost(JTextField userNameHost) {
        this.userNameHost = userNameHost;
    }

    public JTextField getIpAddressHost() {
        return ipAddressHost;
    }

    public void setIpAddressHost(JTextField ipAddressHost) {
        this.ipAddressHost = ipAddressHost;
    }

    public JTextField getKnownHost() {
        return knownHost;
    }

    public void setKnownHost(JTextField knownHost) {
        this.knownHost = knownHost;
    }

    public JTextField getDayIpAddress() {
        return dayIpAddress;
    }

    public void setDayIpAddress(JTextField dayIpAddress) {
        this.dayIpAddress = dayIpAddress;
    }

    public JTextField getDayUserName() {
        return dayUserName;
    }

    public void setDayUserName(JTextField dayUserName) {
        this.dayUserName = dayUserName;
    }

    public JTextField getDayPassword() {
        return dayPassword;
    }

    public void setDayPassword(JTextField dayPassword) {
        this.dayPassword = dayPassword;
    }

    public JTextField getDayKnownFile() {
        return dayKnownFile;
    }

    public void setDayKnownFile(JTextField dayKnownFile) {
        this.dayKnownFile = dayKnownFile;
    }

    public JTextField getNightIpAddress() {
        return nightIpAddress;
    }

    public void setNightIpAddress(JTextField nightIpAddress) {
        this.nightIpAddress = nightIpAddress;
    }

    public JTextField getNightUserName() {
        return nightUserName;
    }

    public void setNightUserName(JTextField nightUserName) {
        this.nightUserName = nightUserName;
    }

    public JTextField getNightPassword() {
        return nightPassword;
    }

    public void setNightPassword(JTextField nightPassword) {
        this.nightPassword = nightPassword;
    }

    public JTextField getNightKnownFile() {
        return nightKnownFile;
    }

    public void setNightKnownFile(JTextField nightKnownFile) {
        this.nightKnownFile = nightKnownFile;
    }

    public JTextField getBranchNameToSave() {
        return branchNameToSave;
    }

    public void setBranchNameToSave(JTextField branchNameToSave) {
        this.branchNameToSave = branchNameToSave;
    }

    public JList getAllBranches() {
        return allBranches;
    }

    public void setAllBranches(JList allBranches) {
        this.allBranches = allBranches;
    }

    public static ConfigModal getInstance() {
        return instance;
    }

    public static void setInstance(ConfigModal instance) {
        ConfigModal.instance = instance;
    }

    public JTextField getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(JTextField dbPassword) {
        this.dbPassword = dbPassword;
    }

    public JTextField getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(JTextField dbUserName) {
        this.dbUserName = dbUserName;
    }

    public JTextField getDbIP() {
        return dbIP;
    }

    public void setDbIP(JTextField dbIP) {
        this.dbIP = dbIP;
    }

    public JTextField getDbPort() {
        return dbPort;
    }

    public void setDbPort(JTextField dbPort) {
        this.dbPort = dbPort;
    }

    public JTextField getDbSchema() {
        return dbSchema;
    }

    public void setDbSchema(JTextField dbSchema) {
        this.dbSchema = dbSchema;
    }
    
}
