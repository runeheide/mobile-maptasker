package com.med8.ilocator.augmentedreality.data;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.med8.ilocator.R;
import com.med8.ilocator.augmentedreality.ui.IconMarker;
import com.med8.ilocator.augmentedreality.ui.Marker;
import com.med8.support.TxtReader;

public class LocalDataSource extends DataSource{
    private List<Marker> cachedMarkers = new ArrayList<Marker>();
    private static Bitmap icon1 = null;
    private static Bitmap icon2 = null;
    private static Bitmap icon3 = null;
    
    TxtReader txtReader = new TxtReader();
    
    public LocalDataSource(Resources res) {
        if (res==null) throw new NullPointerException();
        
        createIcon(res);
    }
    
    public void createIcon(Resources res) {
        if (res==null) throw new NullPointerException();
        
        icon1=BitmapFactory.decodeResource(res, R.drawable.firehydrantgreen);
        icon2=BitmapFactory.decodeResource(res, R.drawable.firehydrantyellow);
        icon3=BitmapFactory.decodeResource(res,  R.drawable.firehydrantred);
    }
    
    public List<Marker> getMarkers() {
    	
    	List<String> arrayList = txtReader.returnObjects();
    	
 //   	if (arrayList.size() >= 1)
 //   	{
    	
    		for (int i = 0; i < arrayList.size(); i++)
    		{
    	
    		double latitude = Double.parseDouble(txtReader.getObject(arrayList.get(i), "Latitude"))/1000000;
    		double longitude = Double.parseDouble(txtReader.getObject(arrayList.get(i), "Longitude"))/1000000;
    					
    			if (txtReader.getObject(arrayList.get(i), "EventStatus").equalsIgnoreCase("OK"))
    			{
    			
    				Marker object1 = new IconMarker(txtReader.getObject(arrayList.get(i), "Name"), latitude, longitude,
    						0.0, Color.DKGRAY, icon1);
    				cachedMarkers.add(object1);
    			}
    			else if (txtReader.getObject(arrayList.get(i), "EventStatus").equalsIgnoreCase("Needs Attention"))
    			{
    				Marker object1 = new IconMarker(txtReader.getObject(arrayList.get(i), "Name"), latitude, longitude,
    						0.0, Color.DKGRAY, icon2);
    				cachedMarkers.add(object1);
    			}
    			else
    			{
    				Marker object1 = new IconMarker(txtReader.getObject(arrayList.get(i), "Name"), latitude, longitude,
    						0.0, Color.DKGRAY, icon3);
    				cachedMarkers.add(object1);
    			}
    		}
    		
 //   	}
 //   	else
 //   	{
 //   		Marker object1 = new IconMarker("HEY",  52.0000, 9.899999,0.0, Color.DKGRAY, icon1);
		//System.out.println("after marker initialize");
 //   		cachedMarkers.add(object1);
 //   	}
    	//for (int i = 0; i < cachedMarkers.size()-1; i++)
    	//{
    	//	System.out.println("CACHED OBJECT " + i + ": " + cachedMarkers.get(i).getName());
    	//}
    	  
        return cachedMarkers;
    }
}
