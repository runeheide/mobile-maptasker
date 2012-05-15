package com.med8.ilocator;

import android.app.Activity;
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
import com.med8.ilocator.maps.ItemOverlayNonePress;
import com.med8.support.TxtReader;
import com.med8.support.TxtWriter;

public class UpdateObjectPositionActivity extends MapActivity
{    
	MapController mapController;
	AlternativeMyLocationOverlay mylocation;
	MapView mapview; 

	public static GeoPoint userlocation = new GeoPoint(0,0);

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updatelocation);

		mapview = (MapView)findViewById(R.id.mapview);
		mapview.setBuiltInZoomControls(true);
		mapController = mapview.getController();
		mapController.setZoom(16);

		mapview.setBuiltInZoomControls(true);

		mylocation = new AlternativeMyLocationOverlay(this, mapview);
		mapview.getOverlays().add(mylocation);
		mylocation.enableMyLocation();

		TxtReader txtReader = new TxtReader();

		if (txtReader.getNameOfPressedButton()!=null){
			String objectName = txtReader.getNameOfPressedButton();

			if (objectName!=null){

				if (!(txtReader.getObject(objectName, "Name").equals("Failed to locate object")))
				{
					Drawable marker = getResources().getDrawable(R.drawable.firehydrantgreen);

					GeoPoint point = new GeoPoint(Integer.parseInt(txtReader.getObject(objectName, "Latitude")),
							Integer.parseInt(txtReader.getObject(objectName, "Longitude")));

					String eventStatus = txtReader.getObject(objectName, "EventStatus");

					if (eventStatus.equalsIgnoreCase("OK"))
					{
						marker = getResources().getDrawable(R.drawable.firehydrantgreenopa);
						ItemOverlayNonePress itemizedOverlay = new ItemOverlayNonePress(marker, this);
						mapview.getOverlays().add(itemizedOverlay);
						OverlayItem overlayitem = new OverlayItem(point, txtReader.getObject(objectName, "Name"), "I'm in Mexico City!");
						itemizedOverlay.addOverlay(overlayitem);
					}
					else if (eventStatus.equalsIgnoreCase("Needs Attention"))
					{
						marker = getResources().getDrawable(R.drawable.firehydrantyellowopa);
						ItemOverlayNonePress itemizedOverlay = new ItemOverlayNonePress(marker, this);
						mapview.getOverlays().add(itemizedOverlay);
						OverlayItem overlayitem = new OverlayItem(point, txtReader.getObject(objectName, "Name"), "I'm in Mexico City!");
						itemizedOverlay.addOverlay(overlayitem);
					}
					else if (eventStatus.equalsIgnoreCase("Broken Down"))
					{
						marker = getResources().getDrawable(R.drawable.firehydrantredopa);
						ItemOverlayNonePress itemizedOverlay = new ItemOverlayNonePress(marker, this);
						mapview.getOverlays().add(itemizedOverlay);
						OverlayItem overlayitem = new OverlayItem(point, txtReader.getObject(objectName, "Name"), "I'm in Mexico City!");
						itemizedOverlay.addOverlay(overlayitem);	
					}
				}
			}
		}

		//If this activity is started from pressing "Show me the object" in an object activity, navigate to the selected object.
		try {
			if (txtReader.getNameOfPressedButton()!=null){
				GeoPoint objectSelected = new GeoPoint(Integer.parseInt(txtReader.getObject(txtReader.getNameOfPressedButton(), "Latitude")),
						Integer.parseInt(txtReader.getObject(txtReader.getNameOfPressedButton(), "Longitude")));
				mapController.animateTo(objectSelected);
			}
		}
		catch (Exception e)
		{
			System.out.println("Failure in navigating to object");
		}

		final ImageButton button = (ImageButton) findViewById(R.id.mapShiftButton);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				mapview.setSatellite(!mapview.isSatellite());
			}
		});

		final ImageButton myLocation = (ImageButton) findViewById(R.id.MyLocation);
		myLocation.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				if (!(mylocation.getMyLocation()==null)){
					mapController.animateTo(mylocation.getMyLocation());
				}
			}
		});

		final ImageButton done = (ImageButton) findViewById(R.id.Done);
		done.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click

				GeoPoint location = mapview.getMapCenter();

				String lat = Integer.toString(location.getLatitudeE6());
				String lon = Integer.toString(location.getLongitudeE6());

				TxtWriter txtWriter = new TxtWriter();
				TxtReader txtReader = new TxtReader();

				txtWriter.writeLocationSelected(location);
				txtWriter.writeEditObject(txtReader.getNameOfPressedButton(), "Latitude", lat);
				txtWriter.writeEditObject(txtReader.getNameOfPressedButton(), "Longitude", lon);

				Intent data = new Intent();

				if (getParent() == null) {
					setResult(Activity.RESULT_OK, data);
				} else {
					getParent().setResult(Activity.RESULT_OK, data);
				}
				finish();

			}
		});
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