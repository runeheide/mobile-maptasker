package com.support;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TxtReader2 {

	public String getObject(String objectName, String reqContent){

		String returnString = "Failed to locate object";

		int contentId = -1;

		if (reqContent.equals("Name"))
			contentId = 0;
		else if (reqContent.equals("Category"))
			contentId = 1;
		else if (reqContent.equals("ObjectType"))
			contentId = 2;
		else if (reqContent.equals("Latitude"))
			contentId = 3;
		else if (reqContent.equals("Longitude"))
			contentId = 4;
		else
			contentId = -1;

		if (!(contentId==-1)){
			try{
				// Open the file that is the first command line parameter
				FileInputStream fstream = new FileInputStream("/sdcard/data/filename.txt");
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;

				//Read File Line By Line
				if (in!=null){
					while ((strLine = br.readLine()) != null)   {

						//System.out.println(strLine);

						String [] separatedInfo;
						separatedInfo = strLine.split(":");

						String nameCheck = separatedInfo[0];

						if (nameCheck.equals(objectName)) {
							returnString = separatedInfo[contentId];
							System.out.println("nameCheck");
						}
					}
				}
				//Close the input stream
				in.close();
			}
			//Catch exception if any
			catch (Exception e){
				System.err.println("Error: " + e.getMessage());
			}
		}
		return returnString;
	}

	public List<String> returnObjects(){
		
		List<String> strings = new ArrayList<String>();
		
		try{
			// Open the file that is the first command line parameter
			FileInputStream fstream = new FileInputStream("/sdcard/data/filename.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			
			//Read File Line By Line
			if (in!=null){
				int i = 0;
				while ((strLine = br.readLine()) != null){
//					System.out.println(strings);
//					System.out.println(i);

					String [] separatedInfo;
					separatedInfo = strLine.split(":");

					Collections.addAll(strings, separatedInfo[0]);
					i++;
				}
			}
			//Close the input stream
			in.close();
		}
		//Catch exception if any
		catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return strings;
	}






	String[] strArray = {,};

	public String[] returnAll(String reqContent) {

		String[] returnArray = {,};

		try{
			// Open the file that is the first 
			// command line parameter
			FileInputStream fstream = new FileInputStream("/sdcard/data/filename.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			int i = 0;
			//Read File Line By Line
			if (in!=null){
				while ((strLine = br.readLine()) != null)   {
					// Print the content on the console
					//System.out.println (strLine);
					strArray[i] = strLine;
					i++;
				}
			}

			//Close the input stream
			in.close();
		}catch (Exception e){//Catch exception if any
			//System.err.println("TISISISISI");
			System.err.println("Error: " + e.getMessage());
		}
		returnArray = strArray;

		return returnArray;
	}

}