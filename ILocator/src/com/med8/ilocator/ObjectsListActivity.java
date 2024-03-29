package com.med8.ilocator;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.med8.support.TxtReader;
import com.med8.support.TxtWriter;

public class ObjectsListActivity extends ILocatorActivity {

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.objectslist);

		ListView objectsList = (ListView)findViewById(R.id.listViewObjects);

		TxtReader txtReader = new TxtReader();
		List<String> arrayList = txtReader.returnObjects();

		final ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.objectslist_item, arrayList);
		objectsList.setAdapter(adapt);

		objectsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View itemClicked,
					int position, long id) {

				TxtWriter txtWriter = new TxtWriter();
				txtWriter.writeButtonPressed(adapt.getItem(position));

				Intent editObjectIntent = new Intent(itemClicked.getContext(), EditObjectActivity.class);
				startActivityForResult(editObjectIntent, 0);
				System.out.println("2");
			}
		});

		ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
		ImageButton addObjectButton = (ImageButton)findViewById(R.id.addObjectButton);

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
	public void onResume()
	{
		onCreate(null);
	}
}