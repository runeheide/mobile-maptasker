package com.med8.ilocator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.med8.ilocator.R;
import com.med8.support.*;

public class StartScreenActivity extends ILocatorActivity {

	Context inContext = this;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);

		final EditText name = (EditText)findViewById(R.id.EditText_Name);
		final EditText password = (EditText)findViewById(R.id.EditText_Password);

		ImageButton signupButton = (ImageButton)findViewById(R.id.signUpButton);	
		signupButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent signupIntent = new Intent(view.getContext(), SignupActivity.class);
				startActivityForResult(signupIntent,0);				
			}
		});

		ImageButton loginButton = (ImageButton)findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				TxtReader txtReader = new TxtReader();
				//System.out.println(name.getText().toString() + ", " + password.getText().toString());
				if (txtReader.checkUser(name.getText().toString(), "Password", password.getText().toString()))
				{
					System.out.println("2: " + name.getText().toString() + ", " + password.getText().toString());
					Intent dashboardIntent = new Intent(view.getContext(), DashboardActivity.class);
					startActivityForResult(dashboardIntent,0);
				}
				else
					System.out.println(txtReader.checkUser(name.getText().toString(), "Password", password.getText().toString()));
			}
		});
	}	
}