package com.maps;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.support.TxtReader;
import com.support.TxtReader2;
import com.support.TxtWriter;
import com.support.TxtWriter2;

public class Maps2Activity extends MapActivity
{    
	MapController mapController;
	AlternativeMyLocationOverlay mylocation;
	MapView mapview; 
	
	public static GeoPoint userlocation = new GeoPoint(0,0);

	//GeoPoint usergeopoint2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mapview = (MapView)findViewById(R.id.mapview);
		mapview.setBuiltInZoomControls(true);
		mapController = mapview.getController();
		mapController.setZoom(16);
		
		mylocation = new AlternativeMyLocationOverlay(this, mapview);
		mapview.getOverlays().add(mylocation);
		mylocation.enableMyLocation();
		
		
		android.os.SystemClock.sleep(1000);
		
		final Button button = (Button) findViewById(R.id.ChangeView);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				// Rune: Call new class instead of new view
    			//mapController.animateTo(usergeopoint);
    			mapview.setSatellite(!mapview.isSatellite());
			}
		});
		/*
		System.out.println(mylocation.getMyLocation());
		if(mylocation.runOnFirstFix(null)){
			mapController.animateTo(mylocation.getMyLocation());	
		}*/
		
		
		
// TILFØJ DETTE NYE STYKKE TIL iLOCATOR PROJEKTET
// Husk main.xml og string.xml
		
		final Button myLocation = (Button) findViewById(R.id.MyLocation);
		myLocation.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				// Rune: Call new class instead of new view
    			mapController.animateTo(mylocation.getMyLocation());
    			System.out.println(mylocation.getMyLocation());
			}
		});
		
		final Button createNewObject = (Button) findViewById(R.id.NewObject);
		createNewObject.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				// Rune: Call new class instead of new view
    			//mapController.animateTo(usergeopoint);
    			
				System.out.println(mapview.getMapCenter());
			}
		});
		
//HER SLUTTER DET STYKKE DER SKAL MED I iLOCATOR PROJEKTET
		
		
		
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
						
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location provider.
				
				makeUseOfNewLocation(location);
			}

			public void onStatusChanged(String provider, int status, Bundle extras) {}

			public void onProviderEnabled(String provider) {}

			public void onProviderDisabled(String provider) {}
		};

		// Register the listener with the Location Manager to receive location updates
		
		//locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

/*		GeoPoint point = new GeoPoint(19240000,-99120000);
		OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");

		GeoPoint point2 = new GeoPoint(35410000, 139460000);
		OverlayItem overlayitem2 = new OverlayItem(point2, "Sekai, konichiwa!", "I'm in Japan!");


		GeoPoint point3 = new GeoPoint(57029262, 9979329);
		OverlayItem overlayitem3 = new OverlayItem(point3, "1", "2");
*/
		
		
		
//		itemizedoverlay.addOverlay(overlayitem);
//		itemizedoverlay.addOverlay(overlayitem2);

		
//		itemizedoverlay.addOverlay(overlayitem3);
		
//		itemizedoverlay.addOverlay(overlayuserposition);
//		mapOverlays.add(itemizedoverlay);		
		
		
		TxtWriter2 textWriter = new TxtWriter2();
		textWriter.writeFile("Name","Category","ObjectType","Latitude","Longitude");
		
		textWriter.writeFile("Name2","Category2","ObjectType2","Latitude2","Longitude2");
		
		
		//TxtReader2 textReader = new TxtReader2();
		//System.out.println(textReader.returnAll());
	
		//System.out.println(mylocation.getMyLocation());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		System.out.println("pee1");
//		
//    	TxtReader2 txtReader2 = new TxtReader2();
//    	System.out.println(txtReader2.returnObjects());
//    	List<String> arrayList = txtReader2.returnObjects();
//    	
//    	
//    	int i = 0;
//    	
//    	System.out.println("pee");
//    	
//    	while (i<arrayList.size())
//    	{	
//    		System.out.println(arrayList.get(i));
//    		i++;
//    	}
	}
		
	
	protected void makeUseOfNewLocation(Location location) {
		
		int longitue = (int) (location.getLongitude()*1000000);
		int latitute = (int) (location.getLatitude()*1000000); 

		Maps2Activity.userlocation = new GeoPoint(latitute, longitue);
		
		//implement some "if-button-is-pressed" function to navigate to user location
		//mapController.animateTo(userlocation);
		
		//Set the zoom level of the map to level 3
		mapController.setZoom(18);
		//Remove when the "button-pressed" function above is implemented
		//Navigate the map view to a specific location (the user's)
		mapController.animateTo(userlocation);
		
		OverlayItem overlayuserposition = new OverlayItem(userlocation, "You are here","l");
		
		Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		
		
		ItemOverlay itemizedoverlay = new ItemOverlay(drawable, this);
		itemizedoverlay.addOverlay(overlayuserposition);
		
		List<Overlay> mapOverlays = mapview.getOverlays();
		mapOverlays.add(itemizedoverlay);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void onResume() {
		mylocation.enableMyLocation();
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		mylocation.disableMyLocation();
		super.onPause();
	}
}