package com.med8.ilocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DashboardActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

		ImageButton objectsButton = (ImageButton)findViewById(R.id.addObjectButton);
		ImageButton mapsButton = (ImageButton)findViewById(R.id.mapsButton);
		ImageButton managementButton = (ImageButton)findViewById(R.id.managementButton);
		ImageButton quitButton = (ImageButton)findViewById(R.id.quitButton);

		objectsButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent addObjectIntent = new Intent(view.getContext(), ObjectsListActivity.class);
				startActivityForResult(addObjectIntent,0);
			}
		});

		mapsButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent mapsIntent = new Intent(view.getContext(), MapsActivity.class);
				startActivityForResult(mapsIntent,0);
			}
		});

		managementButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent managementIntent = new Intent(view.getContext(), ManagementActivity.class);
				startActivityForResult(managementIntent,0);
			}
		});

		quitButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent launchIntent = new Intent(view.getContext(), LaunchActivity.class);
				startActivityForResult(launchIntent,0);
			}
		});
	}

}