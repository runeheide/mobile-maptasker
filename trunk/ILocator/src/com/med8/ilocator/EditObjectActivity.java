package com.med8.ilocator;

import com.med8.ilocator.R;
import com.med8.support.TxtReader;
import com.med8.support.TxtWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class EditObjectActivity extends ILocatorActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editobject);		
		
		TextView name = (TextView)findViewById(R.id.NameTextView);
		Spinner category = (Spinner)findViewById(R.id.Spinner_Category);
		Spinner objectType = (Spinner)findViewById(R.id.Spinner_ObjectType);
		Spinner eventStatus = (Spinner)findViewById(R.id.Spinner_EventStatus);
		TxtReader txtReader = new TxtReader();
		TxtWriter txtWriter = new TxtWriter();
		
		TxtReader txtReader2 = new TxtReader();
		String pressedObjectName = txtReader2.getSimpleName();
		
		name.setText(txtReader.getObject(pressedObjectName, "Name"));
		
		Button backButton = (Button)findViewById(R.id.backButton);
		Button saveButton = (Button)findViewById(R.id.saveButton);
			
		String _category = txtReader.getObject(this, "Category");			
		String _objectType = txtReader.getObject(this, "ObjectType");		
		String _eventStatus = txtReader.getObject(this, "EventStatus");		
		
		if (_category.equals("Wells"))
		{
			category.setSelection(0, true);
		}
		else if (_category.equals("Hydrants"))
		{
			category.setSelection(1, true);
		}
		else if (_category.equals("Parks"))
		{
			category.setSelection(2, true);
		}
		
		if (_objectType.equals("Type 1"))
		{
			objectType.setSelection(0);
		}
		else if (_objectType.equals("Type 2"))
		{
			objectType.setSelection(1);
		}
		
		if (_eventStatus.equals("OK"))
		{
			eventStatus.setSelection(0);
		}
		else if (_eventStatus.equals("Needs Attention"))
		{
			eventStatus.setSelection(1);
		}
		else if (_eventStatus.equals("Broken Down"))
		{
			eventStatus.setSelection(2);
		}
		
		
		backButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				
				//To do: Save new information about object / item
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}	
}
