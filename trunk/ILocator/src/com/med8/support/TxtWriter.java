package com.med8.support;

import java.io.FileWriter;

public class TxtWriter{
	    
    public void writeFile(String Name, String Category, String ObjectType, String Latitude, String Longitude){
 
        FileWriter fWriter;
        try{
             fWriter = new FileWriter("/sdcard/data/filename.txt");
             fWriter.write(Name + ":" + Category + ":" + ObjectType + ":" + Latitude + ":" + Longitude + ";");
             fWriter.flush();
             fWriter.close();
         }
        	catch(Exception e){
                  e.printStackTrace();
         }
    
    }
    public void writeFile2(String UserName, String Email, String Password){
    	 
        FileWriter fWriter;
        try{
             fWriter = new FileWriter("/sdcard/data/user.txt");
             fWriter.write(UserName + ":" + Email + ":" + Password + ";");
             fWriter.flush();
             fWriter.close();
         }
        	catch(Exception e){
                  e.printStackTrace();
         }
    
    }
    
}