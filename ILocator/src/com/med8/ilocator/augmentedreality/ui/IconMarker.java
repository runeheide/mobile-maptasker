package com.med8.ilocator.augmentedreality.ui;

import com.med8.ilocator.augmentedreality.common.Utilities;
import com.med8.ilocator.augmentedreality.ui.objects.PaintableIcon;
import com.med8.ilocator.augmentedreality.ui.objects.PaintablePosition;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * This class extends Marker and draws an icon instead of a circle for it's visual representation.
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class IconMarker extends Marker {
    private Bitmap bitmap = null;

    public IconMarker(String name, double latitude, double longitude, double altitude, int color, Bitmap bitmap) {
        super(name, latitude, longitude, altitude, color);
        this.bitmap = bitmap;
    }

	/**
	 * {@inheritDoc}
	 */
    @Override
    
    public void drawIcon(Canvas canvas) {
    	if (canvas==null || bitmap==null) throw new NullPointerException();

        if (gpsSymbol==null) gpsSymbol = new PaintableIcon(bitmap,96,96);

        textXyzRelativeToCameraView.get(textArray);
        symbolXyzRelativeToCameraView.get(symbolArray);

        float currentAngle = Utilities.getAngle(symbolArray[0], symbolArray[1], textArray[0], textArray[1]);
        float angle = currentAngle + 90;

        if (symbolContainer==null) symbolContainer = new PaintablePosition(gpsSymbol, symbolArray[0], symbolArray[1], angle, 1);
        else symbolContainer.set(gpsSymbol, symbolArray[0], symbolArray[1], angle, 1);

        symbolContainer.paint(canvas);
    }
}
