package com.med8.ilocator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.med8.support.TxtWriter;

public class SignupActivity extends ILocatorActivity {

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);

		final EditText username = (EditText)findViewById(R.id.EditText_Username);
		final EditText email = (EditText)findViewById(R.id.EditText_Email);
		final EditText pass1 = (EditText)findViewById(R.id.EditText_Password);
		final EditText pass2 = (EditText)findViewById(R.id.EditText_Password2);
		final TextView error = (TextView)findViewById(R.id.error);

		ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
		final ImageButton signupButton = (ImageButton)findViewById(R.id.signUpButton);

		pass2.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {

				error.setText(R.string.passwordsnotequal);
				String strPass1 = pass1.getText().toString();
				String strPass2 = pass2.getText().toString();

				if (strPass2.equals(strPass1))
				{
					error.setText(R.string.passwordsequal);
					error.setTextColor(Color.GREEN);
					signupButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							TxtWriter txtWriter = new TxtWriter();
							txtWriter.writeFileSignUp(username.getText().toString(), email.getText().toString(), pass1.getText().toString());
							Intent dashboardIntent = new Intent(view.getContext(), DashboardActivity.class);
							startActivityForResult(dashboardIntent,0);
						}
					}); 
				}
				else
				{
					error.setText(R.string.passwordsnotequal);
					error.setTextColor(Color.RED);
				}
			}

			//		 	@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {				
			}

			//	  		@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
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