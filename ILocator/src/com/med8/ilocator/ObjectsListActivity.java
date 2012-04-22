package com.med8.ilocator;

import com.med8.ilocator.R;
import com.med8.support.TxtReader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ObjectsListActivity extends ILocatorActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.objects);
		
		TextView object1 = (TextView)findViewById(R.id.Object1_TextView);		
		
		TxtReader txtReader = new TxtReader();
		String obj1 = txtReader.getObject(this, "Name");
		
		object1.setText(obj1);	
		
		//To do: - Get text from txtreader (test monday!) 
		//		 - Enable adjustments of category, types, event status, location (new class with txtreader-data as param.?)	
		//		 - Add more objects.
		
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
