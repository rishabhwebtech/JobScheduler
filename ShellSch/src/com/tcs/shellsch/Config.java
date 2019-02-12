/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.shellsch;

/**
 *
 * @author 1299792
 */
public class Config {
    
    private String userName;
    private String password;
    private String ip;
    private int port;
    private String knownHost;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getKnownHost() {
        return knownHost;
    }

    public void setKnownHost(String knownHost) {
        this.knownHost = knownHost;
    }
    
    
    
}
