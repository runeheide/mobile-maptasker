package com.med8.ilocator;

import com.med8.ilocator.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ManagementActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.management);
		
		ImageButton assetsButton = (ImageButton)findViewById(R.id.categoriesButton);
		ImageButton tasksButton = (ImageButton)findViewById(R.id.tasksButton);
		ImageButton usersButton = (ImageButton)findViewById(R.id.usersButton);
		ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
		
		assetsButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent categoriesIntent = new Intent(view.getContext(), CategoriesActivity.class);
				startActivityForResult(categoriesIntent,0);
			}
		});
		
		tasksButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent tasksIntent = new Intent(view.getContext(), TasksActivity.class);
				startActivityForResult(tasksIntent,0);
			}
		});
		
		usersButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent usersIntent = new Intent(view.getContext(), UsersActivity.class);
				startActivityForResult(usersIntent,0);
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
