package com.med8.ilocator;

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

		mylocation = new AlternativeMyLocationOverlay(this, mapview);
		mapview.getOverlays().add(mylocation);
		mylocation.enableMyLocation();
//		mylocation.onTap(userlocation, mapview);
		
		Drawable marker = getResources().getDrawable(R.drawable.androidmarker);
		ItemOverlay itemizedOverlay = new ItemOverlay(marker, this);
		mapview.getOverlays().add(itemizedOverlay);
		
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



		//While-loop ?

		GeoPoint point = new GeoPoint(19240000,-99120000);
		OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");

		GeoPoint point2 = new GeoPoint(35410000, 139460000);
		OverlayItem overlayitem2 = new OverlayItem(point2, "Sekai, konichiwa!", "I'm in Japan!");


		GeoPoint point3 = new GeoPoint(57029262, 9979329);
		OverlayItem overlayitem3 = new OverlayItem(point3, "1", "2");
		
		itemizedOverlay.addOverlay(overlayitem);
		itemizedOverlay.addOverlay(overlayitem2);


		itemizedOverlay.addOverlay(overlayitem3);		
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
		mylocation.enableMyLocation();
		super.onResume();
	}

	@Override
	protected void onPause() {
		mylocation.disableMyLocation();
		super.onPause();
	}
}