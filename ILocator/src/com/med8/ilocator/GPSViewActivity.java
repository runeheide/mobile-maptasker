package com.med8.ilocator;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.med8.ilocator.R;
import com.med8.ilocator.R.drawable;
import com.med8.ilocator.R.id;
import com.med8.ilocator.R.layout;
import com.med8.ilocator.maps.AlternativeMyLocationOverlay;
import com.med8.ilocator.maps.ItemOverlay;
import com.med8.support.TxtReader;
import com.med8.support.TxtWriter;

public class GPSViewActivity extends MapActivity
{    
	Context mContext = this;
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
		setContentView(R.layout.gpsview);
		
		mapview = (MapView)findViewById(R.id.mapview);
		mapview.setBuiltInZoomControls(true);
		mapController = mapview.getController();
		mapController.setZoom(16);

		mapview.setBuiltInZoomControls(true);


	}

	protected void makeUseOfNewLocation(Location location) {

		int longitue = (int) (location.getLongitude()*1000000);
		int latitute = (int) (location.getLatitude()*1000000); 

		GPSViewActivity.userlocation = new GeoPoint(latitute, longitue);

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

		mylocation = new AlternativeMyLocationOverlay(this, mapview);
		mapview.getOverlays().add(mylocation);
		mylocation.enableMyLocation();
		//		mylocation.onTap(userlocation, mapview);

		final Button button = (Button) findViewById(R.id.mapShiftButton);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				// Rune: Call new class instead of new view
				//mapController.animateTo(usergeopoint);
				mapview.setSatellite(!mapview.isSatellite());
			}
		});

		final Button myLocation = (Button) findViewById(R.id.MyLocation);
		myLocation.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				// Rune: Call new class instead of new view
				mapController.animateTo(mylocation.getMyLocation());
				//System.out.println(mylocation.getMyLocation());
			}
		});

		final Button createNewObject = (Button) findViewById(R.id.NewObject);
		createNewObject.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				// Rune: Call new class instead of new view
				//mapController.animateTo(usergeopoint);
				
			}
		});



		TxtReader txtReader = new TxtReader();

		List<String> arrayList = txtReader.returnObjects();

		List<GeoPoint> points = new ArrayList<GeoPoint>();

		int longitude = -99120000;
		
		if (arrayList.size()>0)
		{
			
			Drawable marker = getResources().getDrawable(R.drawable.firehydrantgreen);
			//ItemOverlay itemizedOverlay = new ItemOverlay(marker, this);
			//mapview.getOverlays().add(itemizedOverlay);

			for (int i = 0; i < arrayList.size(); i++)
			{	

				//		System.out.println("LAT: " + txtReader.getObject(arrayList.get(i), "Latitude"));
				//		System.out.println("LONG: " + txtReader.getObject(arrayList.get(i), "Longitude"));

				GeoPoint point = new GeoPoint(Integer.parseInt(txtReader.getObject(arrayList.get(i), "Latitude")),
												Integer.parseInt(txtReader.getObject(arrayList.get(i), "Longitude")));
				points.add(point);

				String eventStatus = txtReader.getObject(arrayList.get(i), "EventStatus");

				if (eventStatus.equalsIgnoreCase("OK"))
				{
					marker = getResources().getDrawable(R.drawable.firehydrantgreen);
					ItemOverlay itemizedOverlay = new ItemOverlay(marker, this);
					mapview.getOverlays().add(itemizedOverlay);
					OverlayItem overlayitem = new OverlayItem(points.get(i), txtReader.getObject(arrayList.get(i).toString(), "Name"), "I'm in Mexico City!");
					itemizedOverlay.addOverlay(overlayitem);

				}
				else if (eventStatus.equalsIgnoreCase("Needs Attention"))
				{
					marker = getResources().getDrawable(R.drawable.firehydrantyellow);
					ItemOverlay itemizedOverlay = new ItemOverlay(marker, this);
					mapview.getOverlays().add(itemizedOverlay);
					OverlayItem overlayitem = new OverlayItem(points.get(i), txtReader.getObject(arrayList.get(i).toString(), "Name"), "I'm in Mexico City!");
					itemizedOverlay.addOverlay(overlayitem);

				}
				else if (eventStatus.equalsIgnoreCase("Broken Down"))
				{
					marker = getResources().getDrawable(R.drawable.firehydrantred);
					ItemOverlay itemizedOverlay = new ItemOverlay(marker, this);
					mapview.getOverlays().add(itemizedOverlay);
					OverlayItem overlayitem = new OverlayItem(points.get(i), txtReader.getObject(arrayList.get(i).toString(), "Name"), "I'm in Mexico City!");
					itemizedOverlay.addOverlay(overlayitem);
					
				}
				

			}
			//System.out.println("POINTS: " + points);
		}
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		mapview.getOverlays().clear();
		mylocation.disableMyLocation();
		super.onPause();
	}
}