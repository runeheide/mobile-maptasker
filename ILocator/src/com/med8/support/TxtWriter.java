package com.med8.support;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class TxtWriter{

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