package com.med8.support;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class TxtWriter{

	public void writeButtonPressed(String Name){

		try{
			// Delete file to make room for the new file (Not the most optimal method)
			File file = new File("/sdcard/iLocator/justpressed.txt");
			file.delete();
			
			// Create buffered writer which writes file on flush()
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sdcard/iLocator/justpressed.txt"));
			
			bufferedWriter.write(Name);
			bufferedWriter.flush();
			bufferedWriter.close();
		}
		//Catch exception if any
		catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	
	public void writeFile(String Name, String Category, String ObjectType, String Latitude, String Longitude){

		try{
			// Open the file that is the first command line parameter
			FileInputStream fstream = new FileInputStream("/sdcard/iLocator/filename.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			// Delete file to make room for the new file (Not the most optimal method)
			File file = new File("/sdcard/iLocator/filename.txt");
			file.delete();
			
			// Create buffered writer which writes file on flush()
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sdcard/iLocator/filename.txt"));
			
			//Read File Line By Line
			System.out.println("tis1");
			
			while ((strLine = br.readLine()) != null)   {
				bufferedWriter.write(strLine);
				bufferedWriter.newLine();
			}
			bufferedWriter.write(Name + ":" + Category + ":" + ObjectType + ":" + Latitude + ":" + Longitude + ";");
			bufferedWriter.flush();
			bufferedWriter.close();
		}
		//Catch exception if any
		catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	
	
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