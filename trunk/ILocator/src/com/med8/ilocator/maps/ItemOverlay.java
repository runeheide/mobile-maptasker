package com.med8.ilocator.maps;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.med8.support.TxtReader;
import com.med8.support.TxtWriter;

public class ItemOverlay extends ItemizedOverlay {

	Context mContext;
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	
	public ItemOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	@Override
	protected boolean onTap(int index) {
		
	  final OverlayItem item = mOverlays.get(index);
	  final AlertDialog builder = new AlertDialog.Builder(mContext).create();
	  builder.setTitle(item.getTitle());

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
				TxtReader txtReader = new TxtReader();
				txtWriter.writeFileAddObject(item.getTitle().toString(), txtReader.getObject("Category"), txtReader.getObject("ObjectType"), 
						"OK", txtReader.getObject("Latitude"), txtReader.getObject("Longitude"), txtReader.getObject("Altitude"));
				//ARData.removeMarkers();
				//updateDataOnClick();
			}
		});
		builder.setButton2("Broken down", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				TxtWriter txtWriter = new TxtWriter();
				TxtReader txtReader = new TxtReader();
				txtWriter.writeFileAddObject(item.getTitle().toString(), txtReader.getObject("Category"), txtReader.getObject("ObjectType"), 
						"Broken Down", txtReader.getObject("Latitude"), txtReader.getObject("Longitude"), txtReader.getObject("Altitude"));
				//ARData.removeMarkers();
				//updateDataOnClick();

			}
		});
		builder.setButton3("Needs attention", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				TxtWriter txtWriter = new TxtWriter();
				TxtReader txtReader = new TxtReader();
				txtWriter.writeFileAddObject(item.getTitle().toString(), txtReader.getObject("Category"), txtReader.getObject("ObjectType"), 
						"Needs Attention", txtReader.getObject("Latitude"), txtReader.getObject("Longitude"), txtReader.getObject("Altitude"));
				//ARData.removeMarkers();
				//updateDataOnClick();
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
	  
//	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
//	  dialog.setTitle(item.getTitle());
//	  dialog.setMessage(item.getSnippet());
//	  dialog.show();
	  return true;
	}
	
}
