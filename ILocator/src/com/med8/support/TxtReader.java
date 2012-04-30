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

import android.content.Context;

public class TxtReader {

	//static InputStream inputStream;

	public String getSimpleName()
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
	

	/**The function is called by: "getContentAt(Context, String, String)"
	 *Context can be replaced by "this" to input the class from where the function is called
	 *String is the name of the object you would like to locate in the file
	 *reqContent is Content that will be returned and can be "Name", "Category", "ObjectType", "Latitude" or "Longitude"
	 * @return 
	 */

	public Boolean checkUser(String inString, String reqContent, String inputPassword)
	{
		String outString = "outstring pee";
		String returnString = "Function Fail";

		int contentId = -1;

		if (reqContent.equals("Name"))
			contentId = 0;
		else if (reqContent.equals("Email"))
			contentId = 1;
		else if (reqContent.equals("Password"))
			contentId = 2;
		else
			contentId = -1;

		String myString = new String();
		myString = readRawTextFile();

		String[] separated = myString.split(";");

		if (contentId != -1){
			System.out.println("if1");

			for (int i = 0; i < separated.length; i++)
			{
				String stringToSplit = "";
				stringToSplit = stringToSplit + separated[i].toString();

				String [] separatedSplitInfo;
				separatedSplitInfo = stringToSplit.split(":");

				String hallo = separatedSplitInfo[0];
				System.out.println(hallo.compareTo(inString));

				//If the two strings are similar the result is "0" 
				//(otherwise negative number) and the if-sentence will run
				if (hallo.equals(inString)) {
					outString = separatedSplitInfo[contentId];
					returnString = outString;
					System.out.println("hallo");
				}
			}	
		}

		System.out.println(returnString + inputPassword);

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
		else if (reqContent.equals("Latitude"))
			contentId = 3;
		else if (reqContent.equals("Longitude"))
			contentId = 4;
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
	
	
	//Denne funktion skal fjernes 
	public String getObject(String reqContent)
	{
		String outString = "outstring pee";
		String returnString = "Function Fail";

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

		String myString = new String();
		myString = readRawObjectsTextFile();

		String[] separated = myString.split(";");

		if (contentId != -1){

			for (int i = 0; i < separated.length; i++)
			{
				String stringToSplit = "";
				stringToSplit = stringToSplit + separated[i].toString();

				String [] separatedSplitInfo;
				separatedSplitInfo = stringToSplit.split(":");

				//If the two strings are similar the result is "0" 
				//(otherwise negative number) and the if-sentence will run
				//				if (hallo.equals(inString)) {
				outString = separatedSplitInfo[contentId];
				//					System.out.println("outString: " + outString);
				returnString = outString;
				//					System.out.println("hallo");

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
				int i = 0;
				while ((strLine = br.readLine()) != null){

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



	/**The function is called by: "getContentAt(Context, String)"
	 *inContext can be replaced by "this" to input the class wherefrom the function is called \newLine
	 *inString is the name of the object you would like to locate in the file \newLine
	 *fileName is the name (without extension) on the .txt file in the project that you would like to read from (only .txt is validated!)
	 */ 

	private static String readRawTextFile()
	{
		String output = "";
		FileReader fileReader;
		
		try {
			fileReader = new FileReader("/sdcard/iLocator/user.txt");
			//InputStreamReader inputreader = new InputStreamReader(inputStream);
			BufferedReader buffreader = new BufferedReader(fileReader);
			//BufferedReader buffreader = new BufferedReader(inputreader);
			String line;
			StringBuilder text = new StringBuilder();

			try {
				while (( line = buffreader.readLine()) != null) {
					text.append(line);
					// text.append('\n');
				}
			} 
			catch (IOException e) {
				return null;
			}
			output = text.toString();
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return output;
	}

	private static String readRawObjectsTextFile()
	{
		String output = "";
		FileReader fileReader;
		
		try {
			fileReader = new FileReader("/sdcard/iLocator/filename.txt");
			//InputStreamReader inputreader = new InputStreamReader(inputStream);
			BufferedReader buffreader = new BufferedReader(fileReader);
			//BufferedReader buffreader = new BufferedReader(inputreader);
			String line;
			StringBuilder text = new StringBuilder();

			try {
				while (( line = buffreader.readLine()) != null) {
					text.append(line);
					// text.append('\n');
				}
			} 
			catch (IOException e) {
				return null;
			}
			output = text.toString();
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return output;
	}
}
