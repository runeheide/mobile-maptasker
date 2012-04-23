package com.med8.ilocator;

import com.med8.ilocator.R;
import com.med8.ilocator.R.id;
import com.med8.ilocator.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ARViewActivity extends ILocatorActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arview);		
	
		Button backButton = (Button)findViewById(R.id.backButton);
		Button addObjectButton = (Button)findViewById(R.id.addObjectButton);
		
		backButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});

		addObjectButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent arIntent = new Intent(view.getContext(), ARApplicationActivity.class);
				startActivityForResult(arIntent,0);
			}
		});
	}	
}
