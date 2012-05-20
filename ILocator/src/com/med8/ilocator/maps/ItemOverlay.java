package com.med8.ilocator.maps;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.med8.ilocator.AlertDialogViewActivity2;
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
		OverlayItem item = mOverlays.get(index);
		String nameOfObject = item.getTitle();
		
		TxtWriter txtWriter = new TxtWriter();
		txtWriter.writeButtonPressed(nameOfObject);
				
		Intent intent = new Intent();
		intent.setClass(mContext, AlertDialogViewActivity2.class);
		mContext.startActivity(intent);
		
	
	  return true;
	}
	
}
