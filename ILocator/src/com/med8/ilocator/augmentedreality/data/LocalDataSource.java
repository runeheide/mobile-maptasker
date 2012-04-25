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
    public Context thisContext;
    
    public LocalDataSource(Resources res) {
        if (res==null) throw new NullPointerException();
        
        createIcon(res);
    }
    
    protected void createIcon(Resources res) {
        if (res==null) throw new NullPointerException();
        
        icon1=BitmapFactory.decodeResource(res, R.drawable.firehydrantred);
        icon2=BitmapFactory.decodeResource(res, R.drawable.firehydrantgreen);
    }
    
    public List<Marker> getMarkers() {
    	TxtReader txtReader = new TxtReader();
    	double latitude = Double.parseDouble(txtReader.getObject(thisContext, "Latitude"));
    	double longitude = Double.parseDouble(txtReader.getObject(thisContext, "Longitude"));
    	double altitude = Double.parseDouble(txtReader.getObject(thisContext, "Altitude"));
    	
    	Marker object1 = new IconMarker(txtReader.getObject(thisContext, "Name"), latitude, longitude, altitude, Color.DKGRAY, icon1);
    	cachedMarkers.add(object1);
    	
    	Marker object2 = new IconMarker("Martin", latitude, longitude*1.0001, altitude, Color.DKGRAY, icon2);
    	cachedMarkers.add(object2);
 
    	//To do: Load array of objects from text-file - dynamically create new markers...
    	
    	
    	
/*    	Marker home = new Marker("Mt Laurel", 39.95, -74.9, 0, Color.YELLOW);
        cachedMarkers.add(home);
*/
        
/*        Marker lon = new IconMarker("I am a really really long string which should wrap a number of times on the screen.", 
                                 39.95335, -74.9223445, 
                                 0, 
                                 Color.MAGENTA,
                                 icon);
        cachedMarkers.add(lon);
        Marker lon2 = new IconMarker("2: I am a really really long string which should wrap a number of times on the screen.", 
                39.95334, -74.9223446, 
                0, 
                Color.MAGENTA,
                icon);
        cachedMarkers.add(lon2);
        
*/
        
        float max = 10;
        for (float i=0; i<max; i++) {
            Marker marker = null;
            float decimal = i/max;
            if (i%2==0) marker = new Marker("Test-"+i, 39.99, -75.33+decimal, 0, Color.LTGRAY);
            marker = new IconMarker("Test-"+i, 39.99+decimal, -75.33, 0, Color.LTGRAY, icon1);
            cachedMarkers.add(marker);
    
        }
       

        return cachedMarkers;
    }
}
