//package com.maps;
//
//import java.util.List;
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import com.google.android.maps.GeoPoint;
//import com.google.android.maps.MapActivity;
//import com.google.android.maps.MapController;
//import com.google.android.maps.MapView;
//import com.google.android.maps.Overlay;
//import com.google.android.maps.OverlayItem;
//
//public class SetObjectLocationActivity extends MapActivity
//{    
//	MapController mapController;
//	AlternativeMyLocationOverlay mylocation;
//	MapView mapview; 
//	
//	public static GeoPoint userlocation = new GeoPoint(0,0);
//
//	//GeoPoint usergeopoint2;
//
//	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle savedInstanceState) 
//	{
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.main2);
//
//		mapview = (MapView)findViewById(R.id.mapview);
//		mapview.setBuiltInZoomControls(true);
//		mapController = mapview.getController();
//		mapController.setZoom(16);
//		
//		
//		mapview.setBuiltInZoomControls(true);
//		
//		mylocation = new AlternativeMyLocationOverlay(this, mapview);
//		mapview.getOverlays().add(mylocation);
//		mylocation.enableMyLocation();
//		//mapController.animateTo(mylocation.getMyLocation());
//		
//			android.os.SystemClock.sleep(20);
//			
//
//		
//		
//		final Button button = (Button) findViewById(R.id.ChangeView);
//		button.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				// Perform action on click
//				// Rune: Call new class instead of new view
//    			//mapController.animateTo(usergeopoint);
//    			mapview.setSatellite(!mapview.isSatellite());
//			}
//		});
//		
//		
//		
//		
//		
//// TILFØJ DETTE NYE STYKKE TIL iLOCATOR PROJEKTET
//// Husk main.xml og string.xml
//		
//		final Button myLocation = (Button) findViewById(R.id.MyLocation);
//		myLocation.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				// Perform action on click
//				// Rune: Call new class instead of new view
//    			mapController.animateTo(mylocation.getMyLocation());
//    			
//			}
//		});
//		
//		final Button createNewObject = (Button) findViewById(R.id.Done);
//		createNewObject.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				// Perform action on click
//				// Rune: Call new class instead of new view
//    			//mapController.animateTo(usergeopoint);
//				System.out.println(mapview.getMapCenter());
//			}
//		});
//	}
//		
//	@Override
//	protected boolean isRouteDisplayed() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
//	@Override
//	protected void onResume() {
//		mylocation.enableMyLocation();
//		super.onResume();
//	}
//	
//	@Override
//	protected void onPause() {
//		mylocation.disableMyLocation();
//		super.onPause();
//	}
//}