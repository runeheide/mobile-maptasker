package com.med8.ilocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreenActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		
		Button signupButton = (Button)findViewById(R.id.signUpButton);	
		signupButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent signupIntent = new Intent(view.getContext(), SignupActivity.class);
				startActivityForResult(signupIntent,0);				
			}
		});
				
		Button loginButton = (Button)findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent dashboardIntent = new Intent(view.getContext(), DashboardActivity.class);
				startActivityForResult(dashboardIntent,0);
			}
		});
	}	
}