package com.med8.ilocator;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
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


public class RunesHeatMapActivity extends MapActivity {

	Context mContext = this;
	MapController mapController;
	AlternativeMyLocationOverlay mylocation;
	MapView mapview;
	Boolean itemsDrawn = false;

	TxtReader txtReader = new TxtReader();
	List<String> arrayList = txtReader.returnObjects();
	List<GeoPoint> points = new ArrayList<GeoPoint>();

	List<String> arrayList1 = txtReader.returnObjects();
	List<GeoPoint> points1 = new ArrayList<GeoPoint>();

	public static GeoPoint userlocation = new GeoPoint(0,0);

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.runesheatmap);

		mapview = (MapView)findViewById(R.id.heatmapview);
		mapview.setBuiltInZoomControls(true);
		mapController = mapview.getController();
		mapController.setZoom(16);

		mylocation = new AlternativeMyLocationOverlay(this, mapview);
		mapview.getOverlays().add(mylocation);
		mylocation.enableMyLocation();

		



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

		final ImageButton done = (ImageButton) findViewById(R.id.NewObject);
		done.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click

				if(itemsDrawn==false){

					if (arrayList.size()>0)
					{
						Drawable marker = getResources().getDrawable(R.drawable.firehydrantgreen);

						for (int i = 0; i < arrayList.size(); i++)
						{	
							GeoPoint point = new GeoPoint(Integer.parseInt(txtReader.getObject(arrayList.get(i), "Latitude")),
									Integer.parseInt(txtReader.getObject(arrayList.get(i), "Longitude")));
							points.add(point);

							String eventStatus = txtReader.getObject(arrayList.get(i), "EventStatus");

							if (eventStatus.equalsIgnoreCase("OK")){
								marker = getResources().getDrawable(R.drawable.firehydrantgreen);
								ItemOverlay itemizedOverlay = new ItemOverlay(marker, mContext);
								mapview.getOverlays().add(itemizedOverlay);
								OverlayItem overlayitem = new OverlayItem(points.get(i), txtReader.getObject(arrayList.get(i).toString(), "Name"), "I'm in Mexico City!");
								itemizedOverlay.addOverlay(overlayitem);
							}
							else if (eventStatus.equalsIgnoreCase("Needs Attention")){
								marker = getResources().getDrawable(R.drawable.firehydrantyellow);
								ItemOverlay itemizedOverlay = new ItemOverlay(marker, mContext);
								mapview.getOverlays().add(itemizedOverlay);
								OverlayItem overlayitem = new OverlayItem(points.get(i), txtReader.getObject(arrayList.get(i).toString(), "Name"), "I'm in Mexico City!");
								itemizedOverlay.addOverlay(overlayitem);
							}
							else if (eventStatus.equalsIgnoreCase("Broken Down")){
								marker = getResources().getDrawable(R.drawable.firehydrantred);
								ItemOverlay itemizedOverlay = new ItemOverlay(marker, mContext);
								mapview.getOverlays().add(itemizedOverlay);
								OverlayItem overlayitem = new OverlayItem(points.get(i), txtReader.getObject(arrayList.get(i).toString(), "Name"), "I'm in Mexico City!");
								itemizedOverlay.addOverlay(overlayitem);
							}
						}
						itemsDrawn = true;
						mapview.getOverlays().add(mylocation);
						mapview.postInvalidate();
					}
				}
				else{
					mapview.getOverlays().clear();
					mapview.postInvalidate();
					itemsDrawn = false;
				}

			}
		});





		if (arrayList.size()>0)
		{
			Drawable marker = getResources().getDrawable(R.drawable.firehydrantgreen);
			
			for (int i = 0; i < arrayList.size(); i++)
			{	
				GeoPoint point = new GeoPoint(Integer.parseInt(txtReader.getObject(arrayList.get(i), "Latitude")),
						Integer.parseInt(txtReader.getObject(arrayList.get(i), "Longitude")));
				points.add(point);

				String eventStatus = txtReader.getObject(arrayList.get(i), "EventStatus");

				if (eventStatus.equalsIgnoreCase("OK")){
					int latitude = Integer.parseInt(txtReader.getObject(arrayList.get(i).toString(), "Latitude"));
					int longitude = Integer.parseInt(txtReader.getObject(arrayList.get(i).toString(), "Longitude"));
					for(int j = latitude-30 ; j<latitude+10; j=j+30){
						for(int k = longitude -30; k<longitude+10; k=k+30){

							marker = getResources().getDrawable(R.drawable.greenpixel);
							marker.setAlpha(100);
							
							ItemOverlay itemizedOverlay = new ItemOverlay(marker, mContext);
							mapview.getOverlays().add(itemizedOverlay);
							OverlayItem overlayitem = new OverlayItem(points.get(i), txtReader.getObject(arrayList.get(i).toString(), "Name"), "I'm in Mexico City!");
							itemizedOverlay.addOverlay(overlayitem);
						}
					}
				}
				else if (eventStatus.equalsIgnoreCase("Needs Attention")){
					marker = getResources().getDrawable(R.drawable.yellowpixel);
					ItemOverlay itemizedOverlay = new ItemOverlay(marker, mContext);
					mapview.getOverlays().add(itemizedOverlay);
					OverlayItem overlayitem = new OverlayItem(points.get(i), txtReader.getObject(arrayList.get(i).toString(), "Name"), "I'm in Mexico City!");
					itemizedOverlay.addOverlay(overlayitem);
				}
				else if (eventStatus.equalsIgnoreCase("Broken Down")){
					marker = getResources().getDrawable(R.drawable.redpixel);
					ItemOverlay itemizedOverlay = new ItemOverlay(marker, mContext);
					mapview.getOverlays().add(itemizedOverlay);
					OverlayItem overlayitem = new OverlayItem(points.get(i), txtReader.getObject(arrayList.get(i).toString(), "Name"), "I'm in Mexico City!");
					itemizedOverlay.addOverlay(overlayitem);
				}
			}
			itemsDrawn = true;
			mapview.getOverlays().add(mylocation);
			mapview.postInvalidate();
		}

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