package com.med8.ilocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.med8.support.TxtWriter;

public class MapsActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps);

		ImageButton gpsButton = (ImageButton)findViewById(R.id.gpsButton);
		ImageButton arButton = (ImageButton)findViewById(R.id.arButton);
		ImageButton heatmapButton = (ImageButton)findViewById(R.id.heatmapButton);
		ImageButton backButton = (ImageButton)findViewById(R.id.backButton);

		gpsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				TxtWriter txtWriter = new TxtWriter();
				txtWriter.writeButtonPressed("");
				Intent gpsIntent = new Intent(view.getContext(), MapViewActivity.class);
				startActivityForResult(gpsIntent,0);
			}
		});

		arButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent arIntent = new Intent(view.getContext(), ARApplicationActivity.class);
				startActivity(arIntent);
				//startActivityForResult(arIntent,0);
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
