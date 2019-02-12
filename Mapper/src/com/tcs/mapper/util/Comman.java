/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.mapper.util;

/**
 *
 * @author 1299792
 */
public class Comman {
    
    public int sumOfArray(int []args){
      int sum=0;
      int len = args.length;
      for(int i=0;i<len;i++){
        sum = sum + args[i];
      }
      
      return sum;
    
    }
    
}
