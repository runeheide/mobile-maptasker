package com.med8.ilocator;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.med8.ilocator.augmentedreality.data.ARData;
import com.med8.support.TxtReader;
import com.med8.support.TxtWriter;

public class AddObjectActivity extends ILocatorActivity {

	boolean locationButtonPressed = false;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.addobject);

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
				System.out.println("LORTELORT1");
				Intent updateObjectPositionIntent = new Intent(view.getContext(), UpdateObjectPositionActivity.class);
				startActivityForResult(updateObjectPositionIntent, 0);
				//				txtWriter.writeEditObject(_objectName.getText().toString(), "Latitude", txtReader.getLocation("Latitude"));
				//				txtWriter.writeEditObject(_objectName.getText().toString(), "Longitude", txtReader.getLocation("Longitude"));
				locationButtonPressed = true;
			}
		});

		createButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				String objName = _objectName.getText().toString();  				
				int selectedCategory = _category.getSelectedItemPosition();
				String category = _category.getItemAtPosition(selectedCategory).toString();
				int selectedObjectType = _objectType.getSelectedItemPosition();
				String objectType = _objectType.getItemAtPosition(selectedObjectType).toString();
				int selectedEventStatus = _eventStatus.getSelectedItemPosition();
				String eventStatus = _eventStatus.getItemAtPosition(selectedEventStatus).toString();

				Location current = ARData.getCurrentLocation();
				double _latitude = current.getLatitude();
				double _longitude = current.getLongitude();

				//		        String latitude = Double.toString(_latitude);				
				//				String longitude = Double.toString(_longitude);

				//				System.out.println("Lat: " + latitude + ", Long: " + longitude);


				String latitude = null;
				String longitude = null;

				if (locationButtonPressed == true){
					System.out.println("LORTELORT2");
					TxtReader txtReader = new TxtReader();
					latitude = txtReader.getLocation("Latitude");
					longitude = txtReader.getLocation("Longitude");
				}
				else if (locationButtonPressed == false){
					latitude = getMyLocation("Latitude");
					longitude = getMyLocation("Longitude");
				}
				
				System.out.println(latitude);
				System.out.println(longitude);

				//				String latitude = "57.0124965";
				//				String longitude = "9.9892814";

				//				System.out.println("Lat: " + latitude + ", Long: " + longitude);

				//		        int latitudeint = (int) (current.getLatitude()*1000000);
				//		        int longitudeint = (int) (current.getLongitude()*1000000);
				//
				//				String latitude = Integer.toString(latitudeint);
				//				String longitude = Integer.toString(longitudeint);

				String altitude = "0.0";

				TxtWriter txtWriter = new TxtWriter();
				txtWriter.writeFileAddObject(objName, category, objectType, eventStatus, latitude, longitude, altitude);

				finish();
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

	public String getMyLocation(String whatTude){ 
		Location myLocation = null;
		String myLocationString = null;
		Location networkLocation = getLocationByProvider(LocationManager.NETWORK_PROVIDER);
		Location gpsLocation = getLocationByProvider(LocationManager.GPS_PROVIDER);
		
		if (gpsLocation!=null) {myLocation = gpsLocation;}
		else if (networkLocation!=null) {myLocation = networkLocation;}
		
		if (whatTude=="Latitude"){
			int latitude = (int) (myLocation.getLatitude()*1000000);
			myLocationString = Integer.toString(latitude);
		}
		else if (whatTude=="Longitude"){
			int longitude = (int) (myLocation.getLongitude()*1000000);
			myLocationString = Integer.toString(longitude);
		}
		
		return myLocationString;
	}

	private Location getLocationByProvider(String provider) {
		Location location = null;
		LocationManager locationManager = (LocationManager) getApplicationContext()
				.getSystemService(Context.LOCATION_SERVICE);

		try {
			if (locationManager.isProviderEnabled(provider)) {
				location = locationManager.getLastKnownLocation(provider);
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Cannot acces Provider " + provider);
		}
		return location;
	}
}