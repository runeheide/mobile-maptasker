package com.med8.ilocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        
        Button addObjectButton = (Button)findViewById(R.id.addObjectButton);
        Button mapsButton = (Button)findViewById(R.id.mapsButton);
        Button managementButton = (Button)findViewById(R.id.managementButton);
        Button quitButton = (Button)findViewById(R.id.quitButton);
        
       addObjectButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent addObjectIntent = new Intent(view.getContext(), ObjectsActivity.class);
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