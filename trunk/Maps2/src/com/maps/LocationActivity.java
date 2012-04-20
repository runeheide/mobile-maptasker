package com.maps;
import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;


public class LocationActivity extends MapActivity implements LocationListener { //<1>

	private static final String TAG = "LocationActivity";

	LocationManager locationManager; //<2>
	Geocoder geocoder; //<3>
	TextView locationText;
	MapView map;  
	MapController mapController; //<4>

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);

		locationText = (TextView)this.findViewById(R.id.lblLocationInfo);
		map = (MapView)this.findViewById(R.id.mapview);
		map.setBuiltInZoomControls(true);

		mapController = map.getController(); //<4>
		mapController.setZoom(16);

		locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE); //<2>

		geocoder = new Geocoder(this); //<3>

		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER); //<5>
		if (location != null) {
			Log.d(TAG, location.toString());
			this.onLocationChanged(location); //<6>
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this); //<7>
	}

	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this); //<8>
	}

	@Override
	public void onLocationChanged(Location location) { //<9>
		Log.d(TAG, "onLocationChanged with location " + location.toString());
		String text = String.format("Lat:\t %f\nLong:\t %f\nAlt:\t %f\nBearing:\t %f", location.getLatitude(), 
				location.getLongitude(), location.getAltitude(), location.getBearing());
		this.locationText.setText(text);

		try {
			List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10); //<10>
			for (Address address : addresses) {
				this.locationText.append("\n" + address.getAddressLine(0));
			}

			int latitude = (int)(location.getLatitude() * 1000000);
			int longitude = (int)(location.getLongitude() * 1000000);

			GeoPoint point = new GeoPoint(latitude,longitude);
			mapController.animateTo(point); //<11>

		} catch (IOException e) {
			Log.e("LocateMe", "Could not get Geocoder data", e);
		}
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}