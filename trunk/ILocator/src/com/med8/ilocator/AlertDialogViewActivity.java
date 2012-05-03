package com.med8.ilocator;

import com.google.android.maps.OverlayItem;
import com.med8.ilocator.R;
import com.med8.support.TxtReader;
import com.med8.support.TxtWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlertDialogViewActivity extends ILocatorActivity {

	String objectName;
	
	public void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);		

		//	final OverlayItem item = mOverlays.get(index);
		final TxtWriter txtWriter = new TxtWriter();
		final AlertDialog builder = new AlertDialog.Builder(this).create();

		TxtReader txtReader = new TxtReader();
		objectName = txtReader.getNameOfPressedButton();

		//	  builder.setTitle(item.getTitle());

		builder.setMessage(objectName);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setButton("Done", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				//...
			}
		});
		builder.setButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				txtWriter.writeEditObject(objectName, "EventStatus", "OK");
				finish();
			}
		});
		builder.setButton2("Broken down", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				txtWriter.writeEditObject(objectName, "EventStatus", "Broken Down");
				//	Intent intent = new Intent();
				//	intent.setClass(builder.getContext(), GPSViewActivity.class);
				//	builder.getContext().startActivity(intent);

				//	Intent intent = new Intent();
				//	setResult(RESULT_OK, intent);
				finish();

			}
		});
		builder.setButton3("Needs attention", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				txtWriter.writeEditObject(objectName, "EventStatus", "Needs Attention");
				finish();
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


		Button backButton = (Button)findViewById(R.id.backButton);
		backButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}	
}
