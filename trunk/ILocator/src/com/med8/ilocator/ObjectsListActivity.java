package com.med8.ilocator;

import java.util.List;

import com.med8.ilocator.R;
import com.med8.support.TxtReader;
import com.med8.support.TxtWriter;
import com.med8.ilocator.EditObjectActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    	System.out.println(txtReader.returnObjects());
    	List<String> arrayList = txtReader.returnObjects();
    	
    	int i = 0;
    	
    	System.out.println("pee");
    	
    	while (i<arrayList.size())
    	{	
    		System.out.println(arrayList.get(i));
    		i++;
    	}
		
		final ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.objectslist_item, arrayList);
		objectsList.setAdapter(adapt);
		
		objectsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View itemClicked,
					int position, long id) {

					TxtWriter txtWriter = new TxtWriter();
					txtWriter.writeButtonPressed(adapt.getItem(position));
									
					Intent editObjectIntent = new Intent(itemClicked.getContext(), EditObjectActivity.class);
					startActivityForResult(editObjectIntent, 0);
			}
		});
		
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