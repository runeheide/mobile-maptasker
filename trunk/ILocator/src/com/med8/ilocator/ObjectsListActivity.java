package com.med8.ilocator;

import com.med8.ilocator.R;
import com.med8.support.TxtReader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ObjectsListActivity extends ILocatorActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.objectslist);
		
		ListView objectsList = (ListView)findViewById(R.id.listViewObjects);
		
		TxtReader txtReader = new TxtReader();
		String[] items = { txtReader.getObject(this, "Name"), "test"};
		
		ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.objectslist_item, items);
		objectsList.setAdapter(adapt);
			
		// Martin To do: insert code from book p. 152-153..
		
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
