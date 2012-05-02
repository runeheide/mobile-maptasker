package com.med8.ilocator;

import com.med8.ilocator.R;
import com.med8.support.TxtReader;
import com.med8.support.TxtWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlertDialogViewActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.heatmapview);
		final Context thisContext = null;	
		Button backButton = (Button)findViewById(R.id.backButton);
		backButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		TxtWriter txtWriter = new TxtWriter();
		final TxtReader txtReader = new TxtReader();
		final AlertDialog builder = new AlertDialog.Builder(this).create();
		builder.setTitle(txtReader.getNameOfPressedButton());

		builder.setMessage("Hydrant: type 1");
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setButton("Done", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				//...
			}
		});
		builder.setButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				TxtWriter txtWriter = new TxtWriter();
				txtWriter.writeEditObject(txtReader.getNameOfPressedButton(), "EventStatus", "OK"); 
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
				//ARData.removeMarkers();
				
				
			}
		});
		builder.setButton2("Broken down", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				TxtWriter txtWriter = new TxtWriter();
				txtWriter.writeEditObject(txtReader.getNameOfPressedButton(), "EventStatus", "Broken Down");
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
				//ARData.removeMarkers();
				//ARData.addMarkers(localData.getMarkers());
				

			}
		});
		builder.setButton3("Needs attention", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				TxtWriter txtWriter = new TxtWriter();
				
				txtWriter.writeEditObject(txtReader.getNameOfPressedButton(), "EventStatus", "Needs Attention");
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
				//ARData.removeMarkers();
			}

		});
		
		/*		
		builder.setOnCancelListener(new OnCancelListener() {

			   public void onCancel(DialogInterface dialog) {
			    // TODO Auto-generated method stub
			    TextView txt=(TextView)findViewById(R.id.txt);
			    txt.setText(txt.getText()+" the cancel listner invoked");
			   }
			  });
		 */
		builder.show();
		
	}	
}
