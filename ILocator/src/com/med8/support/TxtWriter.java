package com.med8.support;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import com.google.android.maps.GeoPoint;

public class TxtWriter{

	/**fileName must NOT contain file extension, it assumes it is an .txt file*/
	public void deleteFile(String fileName){
		try{
			File file = new File("/sdcard/iLocator/"+fileName+".txt");
			file.delete();
		}
		catch (Exception e){
			System.err.println("Error in deleteFile: " + e.getMessage());
		}
	}

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

	public void writeLocationSelected(GeoPoint location){

		try{
			// Delete file to make room for the new file (Not the most optimal method)
			File file = new File("/sdcard/iLocator/locationselected.txt");
			file.delete();

			// Create buffered writer which writes file on flush()
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sdcard/iLocator/locationselected.txt"));

			System.out.println(location);

			String lat = Integer.toString(location.getLatitudeE6());
			String lon = Integer.toString(location.getLongitudeE6());

			System.out.println(lat+","+lon);

			bufferedWriter.write(lat + ":" + lon);
			bufferedWriter.flush();
			bufferedWriter.close();
		}
		//Catch exception if any
		catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}


	public void writeFileAddObject(String Name, String Category, String ObjectType, String EventStatus, String latitude, String longitude, String altitude){

		try{
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

				while ((strLine = br.readLine()) != null)   {
					bufferedWriter.write(strLine);
					bufferedWriter.newLine();
				}
				bufferedWriter.write(Name + ":" + Category + ":" + ObjectType + ":" + EventStatus + ":" + latitude + ":" + longitude + ":" + altitude + ";");
				bufferedWriter.flush();
				bufferedWriter.close();
			}
			catch (Exception e){
				System.err.println("Missing File: " + e.getMessage());
				// Create buffered writer which writes file on flush()
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sdcard/iLocator/filename.txt"));

				bufferedWriter.write(Name + ":" + Category + ":" + ObjectType + ":" + EventStatus + ":" + latitude + ":" + longitude + ":" + altitude + ";");
				bufferedWriter.flush();
				bufferedWriter.close();
			}
		}
		//Catch exception if any
		catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}

	public String writeEditObject(String objectName, String replacePosition, String replacingText)
	{
		try{
			FileInputStream fstream = new FileInputStream("/sdcard/iLocator/filename.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			if (in!=null){
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sdcard/iLocator/filename2.txt"));

				while ((strLine = br.readLine()) != null)   {

					bufferedWriter.write(strLine);
					bufferedWriter.newLine();
				}
				bufferedWriter.flush();
				bufferedWriter.close();
			}
			in.close();
		}
		catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}

		String returnString = "Failed to edit object";
		int contentId = -1;

		System.out.println("write1");

		if (replacePosition.equals("Name")) {contentId = 0;}
		else if (replacePosition.equals("Category")) {contentId = 1;}
		else if (replacePosition.equals("ObjectType")) {contentId = 2;}
		else if (replacePosition.equals("EventStatus")) {contentId = 3;}
		else if (replacePosition.equals("Latitude")) {contentId = 4;}
		else if (replacePosition.equals("Longitude")) {contentId = 5;}
		else if (replacePosition.equals("Altitude")) {contentId = 6;}
		else {contentId = -1;}

		if (!(contentId==-1)){
			try{
				// Open the file that is the first command line parameter
				FileInputStream fstream = new FileInputStream("/sdcard/iLocator/filename2.txt");
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;

				//Read File Line By Line
				if (in!=null){

					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sdcard/iLocator/filename.txt"));

					while ((strLine = br.readLine()) != null)   {

						System.out.println("write2");

						String [] separatedInfo;
						separatedInfo = strLine.split(":");

						String nameCheck = separatedInfo[0];

						if (nameCheck.equals(objectName)) {
							//returnString = separatedInfo[contentId];
							System.out.println("nameCheck");
							System.out.println(separatedInfo[6]);
							
							String pattern = ";";
							separatedInfo[6] = separatedInfo[6].replaceAll(pattern, "");

							separatedInfo[contentId] = replacingText;

							bufferedWriter.write(separatedInfo[0] + ":" + separatedInfo[1] + ":" + separatedInfo[2] + ":" + separatedInfo[3] + ":" + separatedInfo[4] + ":" + separatedInfo[5] + ":" + separatedInfo[6] + ";");
							bufferedWriter.newLine();
						}

						else{
							bufferedWriter.write(strLine);
							bufferedWriter.newLine();
						}
					}
					System.out.println("write3");
					bufferedWriter.flush();
					bufferedWriter.close();
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


	public void writeFileSignUp(String UserName, String Email, String Password){

		try{
			try{
				// Open the file that is the first command line parameter
				FileInputStream fstream = new FileInputStream("/sdcard/iLocator/users.txt");
				// Get the object of DataInputStream
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;

				// Delete file to make room for the new file (Not the most optimal method)
				File file = new File("/sdcard/iLocator/users.txt");
				file.delete();
				// Create buffered writer which writes file on flush()
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sdcard/iLocator/users.txt"));

				while ((strLine = br.readLine()) != null)   {
					bufferedWriter.write(strLine);
					bufferedWriter.newLine();
				}
				bufferedWriter.write(UserName + ":" + Email + ":" + Password + ";");
				bufferedWriter.flush();
				bufferedWriter.close();
			}
			catch (Exception e){
				System.err.println("Missing File: " + e.getMessage());
				// Create buffered writer which writes file on flush()
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sdcard/iLocator/users.txt"));

				bufferedWriter.write(UserName + ":" + Email + ":" + Password + ";");
				bufferedWriter.flush();
				bufferedWriter.close();
			}
		}
		//Catch exception if any
		catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}   	 
}