package com.med8.support;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TxtReader {

	public String getNameOfPressedButton()
	{
		String strLine = "getSimpleName failed miserably :(";
		FileInputStream fstream;
		try {
			fstream = new FileInputStream("/sdcard/iLocator/justpressed.txt");

			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			strLine = br.readLine();
			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strLine;
	}

	public String getLocation(String whatTude)
	{
		String strLine = "getLocation failed miserably :(";
		FileInputStream fstream;
		try {
			fstream = new FileInputStream("/sdcard/iLocator/locationselected.txt");

			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			strLine = br.readLine();
			br.close();

			String [] separatedSplitInfo;
			separatedSplitInfo = strLine.split(":");

			if (whatTude.equals("Latitude")) {strLine = separatedSplitInfo[0];}
			else if (whatTude.equals("Longitude")) {strLine = separatedSplitInfo[1];}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strLine;
	}


	/**The function is called by: "getContentAt(Context, String, String)"
	 *Context can be replaced by "this" to input the class from where the function is called
	 *String is the name of the object you would like to locate in the file
	 *reqContent is Content that will be returned and can be "Name", "Category", "ObjectType", "Latitude" or "Longitude"
	 * @return 
	 */

	public Boolean checkUser(String inString, String inputPassword)
	{
		String returnString = "Check User Function Fail";

		String myString = "";
		FileReader fileReader;

		try {
			fileReader = new FileReader("/sdcard/iLocator/users.txt");
			BufferedReader buffreader = new BufferedReader(fileReader);
			String line;
			StringBuilder text = new StringBuilder();

			try {
				while (( line = buffreader.readLine()) != null) {
					text.append(line);
				}
			} 
			catch (IOException e) {
			}

			myString = text.toString();
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String[] separated = myString.split(";");

		for (int i = 0; i < separated.length; i++)
		{
			String stringToSplit = "";
			stringToSplit = stringToSplit + separated[i].toString();

			String [] separatedSplitInfo;
			separatedSplitInfo = stringToSplit.split(":");

			String userName = separatedSplitInfo[0];

			//If the two strings are similar the result is "0" 
			//(otherwise negative number) and the if-sentence will run
			if (userName.equals(inString)) {
				returnString = separatedSplitInfo[2];
			}
		}	

		if (returnString.equals(inputPassword))
			return true;	
		else
			return false;
	}


	public String getObject(String objectName, String reqContent){

		String returnString = "Failed to locate object";

		int contentId = -1;

		if (reqContent.equals("Name"))
			contentId = 0;
		else if (reqContent.equals("Category"))
			contentId = 1;
		else if (reqContent.equals("ObjectType"))
			contentId = 2;
		else if (reqContent.equals("EventStatus"))
			contentId = 3;
		else if (reqContent.equals("Latitude"))
			contentId = 4;
		else if (reqContent.equals("Longitude"))
			contentId = 5;
		else if (reqContent.equals("Altitude"))
			contentId = 6;
		else
			contentId = -1;

		if (!(contentId==-1)){
			try{
				// Open the file that is the first command line parameter
				FileInputStream fstream = new FileInputStream("/sdcard/iLocator/filename.txt");
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;

				//Read File Line By Line
				if (in!=null){
					while ((strLine = br.readLine()) != null)   {

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
			FileInputStream fstream = new FileInputStream("/sdcard/iLocator/filename.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			//Read File Line By Line
			if (in!=null){
				while ((strLine = br.readLine()) != null){

					String [] separatedInfo;
					separatedInfo = strLine.split(":");

					Collections.addAll(strings, separatedInfo[0]);
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
}