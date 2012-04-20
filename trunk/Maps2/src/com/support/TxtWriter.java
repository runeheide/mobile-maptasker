package com.support;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import android.content.Context;


public class TxtWriter{

	TxtReader textReader = new TxtReader();

	public void writeFile(Context inContext, String Name, String Category, String ObjectType, String Latitude, String Longitude){

		String[] line = textReader.getAllLines(inContext);

		FileWriter fWriter;

		
		try{
			fWriter = new FileWriter("/sdcard/data/filename.txt");

			String newLine = System.getProperty("line.separator");
			
			//Read all lines in txt file until strikes empty line and writes on that line

			FileInputStream fstream = new FileInputStream("filename.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			//Read File Line By Line
			while ((strLine = br.readLine()) = = null)   {
				// Print the content on the console
				System.out.println (strLine);
				fWriter.write("penis" + newLine);
			}

			//System.out.println("if"+i);
			fWriter.write(Name + ":" + Category + ":" + ObjectType + ":" + Latitude + ":" + Longitude + ";");
			fWriter.flush();
			fWriter.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}