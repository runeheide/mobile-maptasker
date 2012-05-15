package com.med8.ilocator;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.med8.ilocator.maps.AlternativeMyLocationOverlay;
import com.med8.ilocator.maps.ItemOverlay;
import com.med8.support.TxtReader;

public class GPSViewActivity extends MapActivity
{    
	Context mContext = this;
	MapController mapController;
	AlternativeMyLocationOverlay mylocation;
	MapView mapview; 

	public static GeoPoint userlocation = new GeoPoint(0,0);


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
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onResume() {

		final ImageButton button = (ImageButton) findViewById(R.id.mapShiftButton);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				// Rune: Call new class instead of new view
				//mapController.animateTo(usergeopoint);
				mapview.setSatellite(!mapview.isSatellite());
			}
		});
		
		final ImageButton myLocation = (ImageButton) findViewById(R.id.MyLocation);
		myLocation.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		//		myLocation.setClickable(hja); 
				//Perform action on click
				// Rune: Call new class instead of new view
				if (!(mylocation.getMyLocation()==null)){
				mapController.animateTo(mylocation.getMyLocation());
				}
			}
		});

		final ImageButton createNewObject = (ImageButton) findViewById(R.id.NewObject);
		createNewObject.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent addObjectIntent = new Intent(v.getContext(), AddObjectActivity.class);
				startActivity(addObjectIntent);
				
			}
		});

		TxtReader txtReader = new TxtReader();

		List<String> arrayList = txtReader.returnObjects();

		List<GeoPoint> points = new ArrayList<GeoPoint>();
		
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
		}
		
		mylocation = new AlternativeMyLocationOverlay(this, mapview);
		mapview.getOverlays().add(mylocation);
		mylocation.enableMyLocation();
		
		//If this activity is started from pressing "Show me the object" in an object activity, navigate to the selected object.
		if (txtReader.getNameOfPressedButton()!=null){
			GeoPoint objectSelected = new GeoPoint(Integer.parseInt(txtReader.getObject(txtReader.getNameOfPressedButton(), "Latitude")),
					Integer.parseInt(txtReader.getObject(txtReader.getNameOfPressedButton(), "Longitude")));
			mapController.animateTo(objectSelected);
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