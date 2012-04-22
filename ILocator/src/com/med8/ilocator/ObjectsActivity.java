package com.med8.ilocator;

import com.med8.ilocator.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ObjectsActivity extends ILocatorActivity {
	
	SharedPreferences objectSettings;
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.objects);
	
		objectSettings = getSharedPreferences(OBJECT_1, Context.MODE_PRIVATE);
		
		TextView object1 = (TextView)findViewById(R.id.Object1_TextView);		
		object1.setText(objectSettings.getString(OBJECT_1_NAME, ""));
		
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
				Intent addObjectIntent = new Intent(view.getContext(), AddObjectActivity.class);
				startActivityForResult(addObjectIntent,0);
			}
		});
	}	
}
