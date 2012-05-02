//Rune: I guess this class is not used at all!!!

package com.med8.ilocator;

import com.med8.ilocator.R;
import com.med8.ilocator.augmentedreality.data.ARData;
import com.med8.support.TxtWriter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class UpdateObjectActivity extends ILocatorActivity {
	
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updateobject);
		
		final EditText _objectName = (EditText)findViewById(R.id.EditText_Name);
		final Spinner _category = (Spinner)findViewById(R.id.Spinner_Category);
		final Spinner _objectType = (Spinner)findViewById(R.id.Spinner_ObjectType);
		final Spinner _eventStatus = (Spinner)findViewById(R.id.Spinner_EventStatus);
		
		Button addPhotoButton = (Button)findViewById(R.id.addPhotoButton);
		Button locationButton = (Button)findViewById(R.id.locationButton);
		Button createButton = (Button)findViewById(R.id.createButton);
		Button backButton = (Button)findViewById(R.id.backButton);
		
		addPhotoButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent addPhotoIntent = new Intent(view.getContext(), AddPhotoActivity.class);
				startActivityForResult(addPhotoIntent, 0);
			}
		});
		
		locationButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent locationIntent = new Intent(view.getContext(), LocationActivity.class);
				startActivityForResult(locationIntent, 0);
			}
		});
		
		createButton.setOnClickListener(new View.OnClickListener() {
						
			public void onClick(View view) {
				String objName = _objectName.getText().toString();
				String category = _category.toString();
				String objectType = _objectType.toString();
				String eventStatus = _eventStatus.toString();
				
				Location current = ARData.getCurrentLocation();
		        double _latitude = current.getLatitude();
		        double _longitude = current.getLongitude();
		 
				
//		        String latitude = Double.toString(_latitude);				
//				String longitude = Double.toString(_longitude);
				
//				System.out.println("Lat: " + latitude + ", Long: " + longitude);
				
		        
				String latitude = "57.0124965";
				String longitude = "9.9892818";
				String altitude = "0.0";
				
				TxtWriter txtWriter = new TxtWriter();
    			txtWriter.writeFileAddObject(objName, category, objectType, eventStatus, latitude, longitude, altitude);
				    			
				Intent dashboardIntent = new Intent(view.getContext(), DashboardActivity.class);
				startActivityForResult(dashboardIntent,0);
			}
		});
		
		backButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}	
}
