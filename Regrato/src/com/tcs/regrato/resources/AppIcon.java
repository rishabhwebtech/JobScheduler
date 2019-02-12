/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.resources;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author 1299792
 */
public class AppIcon {
    public static final String APP_NAME = "Regato";
    public Image getAppIcon(){
       ImageIcon icon = null;
       String appIcon = Path.APP_ICON+File.separator + "regrato2.png";
       icon = new ImageIcon(appIcon);
       
       return icon.getImage();
    } 
}
