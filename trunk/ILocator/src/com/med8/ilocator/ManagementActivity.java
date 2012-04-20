package com.med8.ilocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagementActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.management);
		
		Button assetsButton = (Button)findViewById(R.id.categoriesButton);
		Button tasksButton = (Button)findViewById(R.id.tasksButton);
		Button usersButton = (Button)findViewById(R.id.usersButton);
		Button backButton = (Button)findViewById(R.id.backButton);
		
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
