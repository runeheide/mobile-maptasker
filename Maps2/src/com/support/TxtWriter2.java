package com.support;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;


public class TxtWriter2{

	TxtReader textReader = new TxtReader();

	public void writeFile(String Name, String Category, String ObjectType, String Latitude, String Longitude){

		try{
			// Open the file that is the first command line parameter
			FileInputStream fstream = new FileInputStream("/sdcard/data/filename.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			// Delete file to make room for the new file (Not the most optimal method)
			File file = new File("/sdcard/data/filename.txt");
			file.delete();
			
			// Create buffered writer which writes file on flush()
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sdcard/data/filename.txt"));
			
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
}