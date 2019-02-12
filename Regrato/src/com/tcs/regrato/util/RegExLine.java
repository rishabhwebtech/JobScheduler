/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.util;

/**
 *
 * @author 1299792
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.regex.Pattern;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;

public class RegExLine 
{
    
    
    public String forAccountCard(String fileName) throws IOException
    {
     
        Pattern whitespace = Pattern.compile("^[\\s]{8,8}(.)*");
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicReference<String> ss = new AtomicReference<>();
        String accountDetails = new String();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) 
        {
            stream.forEach(s -> 
            {
                
              
                atomicInteger.getAndIncrement();
                Matcher match = whitespace.matcher(s);
                if(match.find())
                { 
                   
                      String temp = match.group();
                      ss.set(temp);
//                    System.out.print("line "+ atomicInteger);
                }
            });
        } 
        accountDetails = ss.get();
//        System.out.println(accountDetails);
        return accountDetails;   
    }
}