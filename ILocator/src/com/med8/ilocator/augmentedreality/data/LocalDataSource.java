package com.med8.ilocator.augmentedreality.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.med8.ilocator.R;
import com.med8.ilocator.augmentedreality.ui.IconMarker;
import com.med8.ilocator.augmentedreality.ui.Marker;
import com.med8.support.TxtReader;
//import com.jwetherell.augmented_reality.R;


/**
 * This class should be used as a example local data source. It is an example 
 * of how to add data programatically. You can add data either programatically, 
 * SQLite or through any other source.
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class LocalDataSource extends DataSource{
    private List<Marker> cachedMarkers = new ArrayList<Marker>();
    private static Bitmap icon1 = null;
    private static Bitmap icon2 = null;
    private static Bitmap icon3 = null;
    TxtReader txtReader = new TxtReader();
    public Context thisContext;
    
    public LocalDataSource(Resources res) {
        if (res==null) throw new NullPointerException();
        
        createIcon(res);
    }
    
    protected void createIcon(Resources res) {
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
    				
 //   		}
    		
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
    	
/*    	TxtReader txtReader = new TxtReader();
    	double latitude = Double.parseDouble(txtReader.getObject("Latitude"));
    	double longitude = Double.parseDouble(txtReader.getObject("Longitude"));
    	double altitude = Double.parseDouble(txtReader.getObject("Altitude"));
    	
    	String eventStatus = txtReader.getObject("EventStatus");
    	
    	if (eventStatus.equalsIgnoreCase("Broken Down"))
    	{
    		Marker object1 = new IconMarker(txtReader.getObject("Name"), latitude, longitude, altitude, Color.DKGRAY, icon1);
    		cachedMarkers.add(object1);
    	}
    	else if (eventStatus.equalsIgnoreCase("Needs Attention"))
    	{
    		Marker object1 = new IconMarker(txtReader.getObject("Name"), latitude, longitude, altitude, Color.DKGRAY, icon3);
    		cachedMarkers.add(object1);	
    	}
    	else
    	{
    		Marker object1 = new IconMarker(txtReader.getObject("Name"), latitude, longitude, altitude, Color.DKGRAY, icon2);
    		cachedMarkers.add(object1);	
    	}
    	
 /*   	Marker object2 = new IconMarker("Martin", latitude, longitude*1.0001, altitude, Color.DKGRAY, icon2);
    	cachedMarkers.add(object2);
    	
    	Marker object3 = new IconMarker("Rune", latitude, longitude*1.0002, altitude, Color.DKGRAY, icon3);
    	cachedMarkers.add(object3);
*/ 
    	//To do: Load array of objects from text-file - dynamically create new markers...
   /* 	        
        float max = 10;
        for (float i=0; i<max; i++) {
            Marker marker = null;
            float decimal = i/max;
            if (i%2==0) marker = new Marker("Test-"+i, 39.99, -75.33+decimal, 0, Color.LTGRAY);
            marker = new IconMarker("Test-"+i, 39.99+decimal, -75.33, 0, Color.LTGRAY, icon1);
            cachedMarkers.add(marker);
    */
       }
     
        return cachedMarkers;
    }
}
