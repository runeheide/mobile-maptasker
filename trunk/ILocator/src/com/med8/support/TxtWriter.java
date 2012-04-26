package com.med8.support;

import java.io.FileWriter;

public class TxtWriter{
	

	
	public void writeFileAddObject(String Name, String Category, String ObjectType, String EventStatus, String latitude, String longitude, String altitude){
    	//TO DO: Add latitude + longitude information
        FileWriter fWriter;
        try{
             fWriter = new FileWriter("/sdcard/iLocator/filename.txt");
             fWriter.write(Name + ":" + Category + ":" + ObjectType + ":" + EventStatus + ":" + latitude + ":" + longitude + ":" + altitude + ";");
             fWriter.flush();
             fWriter.close();
         }
        	catch(Exception e){
                  e.printStackTrace();
         }
    
    }
    public void writeFileSignUp(String UserName, String Email, String Password){
    	 
        FileWriter fWriter;
        try{
             fWriter = new FileWriter("/sdcard/iLocator/user.txt");
             fWriter.write(UserName + ":" + Email + ":" + Password + ";");
             fWriter.flush();
             fWriter.close();
         }
        	catch(Exception e){
                  e.printStackTrace();
         }  
    }    
}