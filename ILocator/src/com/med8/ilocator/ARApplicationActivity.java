package com.med8.ilocator;

import java.util.concurrent.RejectedExecutionException;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import com.med8.ilocator.R;
import com.med8.ilocator.augmentedreality.activity.AugmentedReality;
import com.med8.ilocator.augmentedreality.data.ARData;
import com.med8.ilocator.augmentedreality.data.LocalDataSource;
import com.med8.ilocator.augmentedreality.ui.Marker;
import com.med8.support.TxtWriter;


/**
 * This class extends the AugmentedReality and is designed to be an example on how to extends the AugmentedReality
 * class to show multiple data sources.
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class ARApplicationActivity extends AugmentedReality {

	private static final String TAG = "ARApplication";
	private LocalDataSource localData;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    }

	@Override
	public void onStart() {
		super.onStart();
		Location last = ARData.getCurrentLocation();
		
		updateData(last.getLatitude(),last.getLongitude(),last.getAltitude());
		
	}

	//   @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//Log.v(TAG, "onOptionsItemSelected() item="+item);
		switch (item.getItemId()) {
		case R.id.showRadar:
			showRadar = !showRadar;
			item.setTitle(((showRadar)? "Hide" : "Show")+" Radar");
			break;
		case R.id.showZoomBar:
			showZoomBar = !showZoomBar;
			item.setTitle(((showZoomBar)? "Hide" : "Show")+" Zoom Bar");
			zoomLayout.setVisibility((showZoomBar)?LinearLayout.VISIBLE:LinearLayout.GONE);
			break;
		case R.id.exit:
			finish();
			break;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onLocationChanged(Location location) {
		super.onLocationChanged(location);
		
		updateData(location.getLatitude(),location.getLongitude(),location.getAltitude());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void markerTouched(Marker marker) {
		
		TxtWriter txtWriter = new TxtWriter();
		txtWriter.writeButtonPressed(marker.getName());
		
		Intent intent = new Intent();
		intent.setClass(this, AlertDialogViewActivity.class);
		this.startActivity(intent);	
	}
	
	protected void updateDataOnZoom() {
		super.updateDataOnZoom();
		Location last = ARData.getCurrentLocation();
		updateData(last.getLatitude(),last.getLongitude(),last.getAltitude());
	}

	private void updateData(final double lat, final double lon, final double alt) {
		try {
			ARData.removeMarkers();
			ARData.addMarkers(localData.getMarkers());
		
		} catch (RejectedExecutionException rej) {
			Log.w(TAG, "Not running new download Runnable, queue is full.");
		} catch (Exception e) {
			Log.e(TAG, "Exception running download Runnable.",e);
		}
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		System.out.println("resume");
		localData = new LocalDataSource(this.getResources());
		
		ARData.addMarkers(localData.getMarkers());
	
	}
	@Override
	public void onPause()
	{
		localData.getMarkers().clear();
		super.onPause();
	}
}
