package com.med8.ilocator;

import com.med8.ilocator.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MapsActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);
		
		Button gpsButton = (Button)findViewById(R.id.gpsButton);
		Button arButton = (Button)findViewById(R.id.arButton);
		Button heatmapButton = (Button)findViewById(R.id.heatmapButton);
		Button backButton = (Button)findViewById(R.id.backButton);
		
		gpsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent gpsIntent = new Intent(view.getContext(), GPSViewActivity.class);
				startActivityForResult(gpsIntent,0);
			}
		});
		
		arButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent arIntent = new Intent(view.getContext(), ARApplicationActivity.class);
				startActivityForResult(arIntent,0);
			}
		});
		
		heatmapButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent heatmapIntent = new Intent(view.getContext(), HeatmapViewActivity.class);
				startActivityForResult(heatmapIntent,0);
			}
		});
		
		backButton.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}	
}
