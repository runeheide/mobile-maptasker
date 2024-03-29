package com.support;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.content.Context;

import com.maps.R;

public class TxtReader {

	/**The function is called by: "getContentAt(Context, String, String)"
	 *Context can be replaced by "this" to input the class wherefrom the function is called
	 *String is the name of the object you would like to locate in the file
	 *reqContent is Content that will be returned and can be "Name", "Category", "ObjectType", "Latitude" or "Longitude"
	 * @return 
	 */
	public String getContent(String inString, String reqContent)
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
		else if (reqContent.equals("Latitude"))
			contentId = 3;
		else if (reqContent.equals("Longitude"))
			contentId = 4;
		else
			contentId = -1;

		String myString = new String();
		myString = readRawTextFile();

		String[] separated = myString.split(";");

		if (contentId != -1){

			for (int i = 0; i < separated.length; i++)
			{
				String stringToSplit = "";
				stringToSplit = stringToSplit + separated[i].toString();

				String [] separatedSplitInfo;
				separatedSplitInfo = stringToSplit.split(":");

				//System.out.println(separatedSplitInfo);

				//System.out.println(separatedSplitInfo[0]);

				String hallo = separatedSplitInfo[0];

				//System.out.println(separatedSplitInfo[0]);
				//System.out.println(hallo.compareTo(inString));

				//If the two strings are similar the result is "0" 
				//(otherwise negative number) and the if-sentence will run
				if (hallo.equals(inString)) {
					outString = separatedSplitInfo[contentId];
					returnString = outString;
					System.out.println("hallo");
				}

				//returnString = outString;
			}	
		}

		return returnString;	
	}

	/**Extended getContent() function is called by: "getContentAt(Context, String, String, String)"
	 *Context can be replaced by "this" to input the class wherefrom the function is called
	 *String is the name of the object you would like to locate in the file
	 *fileName is the name (without extension) on the .txt file in the project that you would like to read from (only .txt is validated!)
	 */
	/*	public String getContent(Context inContext, String inString, String reqContent, String fileName)
	{
		String outString = "outstring pee";
		String returnString = "Function Fail";

		//PROBLEM HERE!!!
		String resIdString = "R.raw."+fileName;
		int resId = Integer.parseInt(resIdString);

		System.out.println(resId);

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

		String myString = new String();
		myString = readRawTextFile(inContext, resId);

		String[] separated = myString.split(";");

		if (contentId != -1){

			for (int i = 0; i < separated.length; i++)
			{
				String stringToSplit = "";
				stringToSplit = stringToSplit + separated[i].toString();

				String [] separatedSplitInfo;
				separatedSplitInfo = stringToSplit.split(":");

				System.out.println(separatedSplitInfo);

				//System.out.println(separatedSplitInfo[0]);

				String hallo = separatedSplitInfo[0];

				System.out.println(separatedSplitInfo[0]);
				System.out.println(hallo.compareTo(inString));

				//If the two strings are similar the result is "0" 
				//(otherwise negative number) and the if-sentence will run
				if (hallo.equals(inString)) {
					outString = separatedSplitInfo[contentId];
					returnString = outString;
					System.out.println("hallo");
				}

				//returnString = outString;
			}	
		}

		try {
			inputStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return returnString;	
	}
	 */


	/**The function is called by: "getContentAt(Context, String)"
	 *inContext can be replaced by "this" to input the class wherefrom the function is called \newLine
	 *inString is the name of the object you would like to locate in the file \newLine
	 *fileName is the name (without extension) on the .txt file in the project that you would like to read from (only .txt is validated!)
	 */ 
	//	public String getContentAtFile(Context inContext, String inString, String fileName)
	//	{
	//		String outString = null;
	//		int resId = Integer.parseInt("R.raw"+fileName);
	//		
	//		System.out.println(resId);
	//		
	//		String myString = new String();
	//		myString = readRawTextFile(inContext, resId);
	//
	//		String[] separated = myString.split(";");
	//		if (separated[0].equals(inString)) {
	//			System.out.println("YAY");
	//		}
	//		else {
	//			System.out.println("TIS");
	//		}	
	//		return outString;
	//	}

	private static String readRawTextFile()
	{
		String output = "";

		FileReader fileReader;
		try {
			fileReader = new FileReader("/sdcard/data/filename.txt");
			//InputStreamReader inputreader = new InputStreamReader(inputStream);
			BufferedReader buffreader = new BufferedReader(fileReader);
			//BufferedReader buffreader = new BufferedReader(inputreader);
			String line;
			StringBuilder text = new StringBuilder();

			try {
				while (( line = buffreader.readLine()) != null) {
					text.append(line);
					//text.append('\n');
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
