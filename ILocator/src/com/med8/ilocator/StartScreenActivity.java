package com.med8.ilocator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.med8.support.*;
public class StartScreenActivity extends Activity {

	Context inContext = this;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);

		final EditText name = (EditText)findViewById(R.id.EditText_Name);
		final EditText password = (EditText)findViewById(R.id.EditText_Password);
		Button signupButton = (Button)findViewById(R.id.signUpButton);	


		signupButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent signupIntent = new Intent(view.getContext(), SignupActivity.class);
				startActivityForResult(signupIntent,0);				
			}
		});


		//		if (txtReader.compareStrings(name.getText().toString()) && txtReader.compareStrings(password.getText().toString()))
		//		{
		//			signupButton.setOnClickListener(new View.OnClickListener() {
		//				public void onClick(View view) {
		//					Intent signupIntent = new Intent(view.getContext(), SignupActivity.class);
		//					startActivityForResult(signupIntent,0);				
		//				}
		//			});
		//		}		
		Button loginButton = (Button)findViewById(R.id.loginButton);
// EMIL ER EN G�GLER
		loginButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				TxtReader txtReader = new TxtReader();
				//System.out.println(name.getText().toString() + ", " + password.getText().toString());
				if (txtReader.getContent2(inContext , name.getText().toString(), "Password", password.getText().toString()))
						{
							System.out.println("2: " + name.getText().toString() + ", " + password.getText().toString());
							Intent dashboardIntent = new Intent(view.getContext(), DashboardActivity.class);
							startActivityForResult(dashboardIntent,0);
						}
			}
		});

	}	
}