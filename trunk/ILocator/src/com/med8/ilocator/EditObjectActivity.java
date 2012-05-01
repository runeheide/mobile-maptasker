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

		final Spinner category = (Spinner)findViewById(R.id.Spinner_Category);
		final Spinner objectType = (Spinner)findViewById(R.id.Spinner_ObjectType);
		final Spinner eventStatus = (Spinner)findViewById(R.id.Spinner_EventStatus);
		
		TxtReader txtReader = new TxtReader();
		String pressedObjectName = txtReader.getNameOfPressedButton();

		final String nameOfClicked = txtReader.getObject(pressedObjectName, "Name");
		name.setText(nameOfClicked);

		Button backButton = (Button)findViewById(R.id.backButton);
		Button saveButton = (Button)findViewById(R.id.saveButton);

		String _category = txtReader.getObject("Category");			
		String _objectType = txtReader.getObject("ObjectType");		
		String _eventStatus = txtReader.getObject("EventStatus");		

		if (_category.equalsIgnoreCase("Wells"))
		{
			category.setSelection(0, true);
		}
		else if (_category.equalsIgnoreCase("Hydrants"))
		{
			category.setSelection(1, true);
		}
		else if (_category.equalsIgnoreCase("Parks"))
		{
			category.setSelection(2, true);
		}

		if (_objectType.equalsIgnoreCase("Type 1"))
		{
			objectType.setSelection(0);
		}
		else if (_objectType.equalsIgnoreCase("Type 2"))
		{
			objectType.setSelection(1);
		}
		
		if (_eventStatus.equalsIgnoreCase("OK"))
		{
			eventStatus.setSelection(0);
		}
		else if (_eventStatus.equalsIgnoreCase("Needs Attention"))
		{
			eventStatus.setSelection(1);
		}
		else if (_eventStatus.equalsIgnoreCase("Broken Down"))
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

				TxtWriter txtWriter = new TxtWriter();
				System.out.println(category.getSelectedItem().toString());
				txtWriter.writeEditObject(nameOfClicked, "Category", category.getSelectedItem().toString());
				txtWriter.writeEditObject(nameOfClicked, "ObjectType", objectType.getSelectedItem().toString());
				txtWriter.writeEditObject(nameOfClicked, "EventStatus", eventStatus.getSelectedItem().toString());

				//To do: Save new information about object / item
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}	
}
