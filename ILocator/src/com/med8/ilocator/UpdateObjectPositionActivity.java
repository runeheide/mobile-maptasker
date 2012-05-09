package com.med8.ilocator;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.med8.ilocator.maps.AlternativeMyLocationOverlay;
import com.med8.ilocator.maps.ItemOverlay;
import com.med8.support.TxtReader;
import com.med8.support.TxtWriter;

public class UpdateObjectPositionActivity extends MapActivity
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
		ImageView updateLocationImageView = (ImageView) findViewById(R.layout.updatelocation);
		setContentView(updateLocationImageView);
		updateLocationImageView.setClickable(false);
		updateLocationImageView.setVisibility(0);
		

		mapview = (MapView)findViewById(R.id.mapview);
		mapview.setBuiltInZoomControls(true);
		mapController = mapview.getController();
		mapController.setZoom(16);

		mapview.setBuiltInZoomControls(true);

		mylocation = new AlternativeMyLocationOverlay(this, mapview);
		mapview.getOverlays().add(mylocation);
		mylocation.enableMyLocation();
		//		mylocation.onTap(userlocation, mapview);

		TxtReader txtReader = new TxtReader();

		//If this activity is started from pressing "Show me the object" in an object activity, navigate to the selected object.
		if (txtReader.getNameOfPressedButton()!=null){
			GeoPoint objectSelected = new GeoPoint(Integer.parseInt(txtReader.getObject(txtReader.getNameOfPressedButton(), "Latitude")),
					Integer.parseInt(txtReader.getObject(txtReader.getNameOfPressedButton(), "Longitude")));
			mapController.animateTo(objectSelected);
		}

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
				// Perform action on click
				// Rune: Call new class instead of new view
				if (!(mylocation.getMyLocation()==null)){
				mapController.animateTo(mylocation.getMyLocation());
				}
				if (!(mylocation.getMyLocation()==null)){
					mapController.animateTo(mylocation.getMyLocation());
				}
			}
		});

		final ImageButton done = (ImageButton) findViewById(R.id.Done);
		done.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				// Rune: Call new class instead of new view
				//mapController.animateTo(usergeopoint);
				//System.out.println(mapview.getMapCenter());

				GeoPoint location = mapview.getMapCenter();
				TxtWriter txtWriter = new TxtWriter();
				txtWriter.writeLocationSelected(location);

				//System.out.println(location);

				System.out.println("UpdateObject1");

				Intent data = new Intent();

				System.out.println("UpdateObject2");


				if (getParent() == null) {
					setResult(Activity.RESULT_OK, data);
				} else {
					getParent().setResult(Activity.RESULT_OK, data);
				}
				finish();

				System.out.println("UpdateObject3");

			}

		});}
	/*
		TxtReader txtReader = new TxtReader();
		List<String> arrayList = txtReader.returnObjects();
		List<GeoPoint> points = new ArrayList<GeoPoint>();

		if (arrayList.size()>0) {
			Drawable marker = getResources().getDrawable(R.drawable.firehydrantgreen);
			//ItemOverlay itemizedOverlay = new ItemOverlay(marker, this);
			//mapview.getOverlays().add(itemizedOverlay);

			for (int i = 0; i < arrayList.size(); i++) {	
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
					OverlayItem overlayitem = new OverlayItem(points.get(i), "Hola", "I'm in Mexico City!");
					itemizedOverlay.addOverlay(overlayitem);

				}
				else if (eventStatus.equalsIgnoreCase("Broken Down"))
				{
					marker = getResources().getDrawable(R.drawable.firehydrantred);
					ItemOverlay itemizedOverlay = new ItemOverlay(marker, this);
					mapview.getOverlays().add(itemizedOverlay);
					OverlayItem overlayitem = new OverlayItem(points.get(i), "Yo", "I'm in Mexico City!");
					itemizedOverlay.addOverlay(overlayitem);
				}
			}
			//System.out.println("POINTS: " + points);
		}		
	}
	 */
	public void makeUseOfNewLocation(Location location) {

		int longitue = (int) (location.getLongitude()*1000000);
		int latitute = (int) (location.getLatitude()*1000000); 

		UpdateObjectPositionActivity.userlocation = new GeoPoint(latitute, longitue);

		//Set the zoom level of the map to level 18
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
