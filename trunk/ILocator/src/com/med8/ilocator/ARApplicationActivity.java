package com.med8.ilocator;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.med8.ilocator.R;
import com.med8.ilocator.augmentedreality.activity.AugmentedReality;
import com.med8.ilocator.augmentedreality.data.ARData;
import com.med8.ilocator.augmentedreality.data.BuzzDataSource;
import com.med8.ilocator.augmentedreality.data.LocalDataSource;
import com.med8.ilocator.augmentedreality.data.NetworkDataSource;
import com.med8.ilocator.augmentedreality.data.TwitterDataSource;
import com.med8.ilocator.augmentedreality.data.WikipediaDataSource;
import com.med8.ilocator.augmentedreality.ui.Marker;
import com.med8.support.TxtReader;
import com.med8.support.TxtWriter;


/**
 * This class extends the AugmentedReality and is designed to be an example on how to extends the AugmentedReality
 * class to show multiple data sources.
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class ARApplicationActivity extends AugmentedReality {
	Context thisContext;
	private static final String TAG = "ARApplication";
	private static final String locale = Locale.getDefault().getLanguage();
	private static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1);
	private static final ThreadPoolExecutor exeService = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, queue);
	private static final Map<String,NetworkDataSource> sources = new ConcurrentHashMap<String,NetworkDataSource>();    
	//private AlertDialog.Builder builder;

	private LocalDataSource localData;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
//		setContentView(R.layout.arview);

		localData = new LocalDataSource(this.getResources());
		ARData.addMarkers(localData.getMarkers());

    }

	@Override
	public void onStart() {
		super.onStart();
		//ARData.removeMarkers();
		ARData.addMarkers(localData.getMarkers());
		//       LocalDataSource localData = new LocalDataSource(this.getResources());
	

		Location last = ARData.getCurrentLocation();
		updateData(last.getLatitude(),last.getLongitude(),last.getAltitude());
	}

	//   @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.v(TAG, "onOptionsItemSelected() item="+item);
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
	protected void markerTouched(final Marker marker) {
		final AlertDialog builder = new AlertDialog.Builder(this).create();
		builder.setTitle(marker.getName());

		builder.setMessage("Hydrant: type 1");
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setButton("Done", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				//...
			}
		});
		builder.setButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				TxtWriter txtWriter = new TxtWriter();
				TxtReader txtReader = new TxtReader();
				txtWriter.writeFileAddObject(marker.getName(), txtReader.getObject(thisContext, "Category"), txtReader.getObject(thisContext, "ObjectType"), 
						"OK", txtReader.getObject(thisContext, "Latitude"), txtReader.getObject(thisContext, "Longitude"), txtReader.getObject(thisContext, "Altitude"));
				//ARData.removeMarkers();
				updateDataOnClick();
			}
		});
		builder.setButton2("Broken down", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				TxtWriter txtWriter = new TxtWriter();
				TxtReader txtReader = new TxtReader();
				txtWriter.writeFileAddObject(marker.getName(), txtReader.getObject(thisContext, "Category"), txtReader.getObject(thisContext, "ObjectType"), 
						"Broken Down", txtReader.getObject(thisContext, "Latitude"), txtReader.getObject(thisContext, "Longitude"), txtReader.getObject(thisContext, "Altitude"));
				//ARData.removeMarkers();
				updateDataOnClick();

			}
		});
		builder.setButton3("Needs attention", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				TxtWriter txtWriter = new TxtWriter();
				TxtReader txtReader = new TxtReader();
				txtWriter.writeFileAddObject(marker.getName(), txtReader.getObject(thisContext, "Category"), txtReader.getObject(thisContext, "ObjectType"), 
						"Needs Attention", txtReader.getObject(thisContext, "Latitude"), txtReader.getObject(thisContext, "Longitude"), txtReader.getObject(thisContext, "Altitude"));
				//ARData.removeMarkers();
				updateDataOnClick();
			}

		});
		
		/*		
		builder.setOnCancelListener(new OnCancelListener() {

			   public void onCancel(DialogInterface dialog) {
			    // TODO Auto-generated method stub
			    TextView txt=(TextView)findViewById(R.id.txt);
			    txt.setText(txt.getText()+" the cancel listner invoked");
			   }
			  });
		 */
		builder.show();
		//      Toast t = Toast.makeText(getApplicationContext(), marker.getName(), Toast.LENGTH_SHORT);
		//      t.setGravity(Gravity.CENTER, 0, 0);
		//      t.show();
	}

	/**
	 * {@inheritDoc}
	 */
	private void updateDataOnClick()
	{
		ARData.removeMarkers();
		ARData.addMarkers(localData.getMarkers());

		//this.onCreate(null);
		
//		Intent intent = new Intent();
//		setResult(RESULT_OK, intent);
//		finish();
		
	//	Intent intent = new android.content.Intent();
	//	intent.setClass(this, this.getClass());  
	
		Intent intent = this.getIntent();
		this.startActivity(intent);
		this.finish();
 
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
			exeService.execute(
					new Runnable() {
						public void run() {
							for (NetworkDataSource source : sources.values())
								download(source, lat, lon, alt);
						}
					}
					);
		} catch (RejectedExecutionException rej) {
			Log.w(TAG, "Not running new download Runnable, queue is full.");
		} catch (Exception e) {
			Log.e(TAG, "Exception running download Runnable.",e);
		}
	}

	private static boolean download(NetworkDataSource source, double lat, double lon, double alt) {
		if (source==null) return false;

		String url = null;
		try {
			url = source.createRequestURL(lat, lon, alt, ARData.getRadius(), locale);    	
		} catch (NullPointerException e) {
			return false;
		}

		List<Marker> markers = null;
		try {
			markers = source.parse(url);
		} catch (NullPointerException e) {
			return false;
		}

		ARData.addMarkers(markers);
		return true;
	}

}
