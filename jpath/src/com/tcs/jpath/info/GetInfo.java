/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.jpath.info;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tcs.jpath.util.Comman;
import java.io.InputStream;

/**
 *
 * @author 1299792
 */
public class GetInfo {
    
    public JsonObject getProductInfo(){
      Comman commanFunction = new Comman();
      JsonObject object = new JsonObject();
      InputStream stream = GetInfo.class.getResourceAsStream("/com/tcs/jpath/info/info.json");
      String string = commanFunction.convertStreamToString(stream);
      JsonParser parse = new JsonParser();
      object =   (JsonObject)parse.parse(string);
      
      return object;
    }
      public JsonObject getHeaderInfo(){
      Comman commanFunction = new Comman();
      JsonObject object = new JsonObject();
      InputStream stream = GetInfo.class.getResourceAsStream("/com/tcs/jpath/info/JPathHeaderInfo.json");
      String string = commanFunction.convertStreamToString(stream);
      JsonParser parse = new JsonParser();
      object =   (JsonObject)parse.parse(string);
      
      return object;
    } 
}
