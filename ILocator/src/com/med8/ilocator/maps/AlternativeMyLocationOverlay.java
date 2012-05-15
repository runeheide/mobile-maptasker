package com.med8.ilocator.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.med8.ilocator.R;

public class AlternativeMyLocationOverlay extends MyLocationOverlay {

	// TODO: use dynamic calculation?

	private MapController    mc;
	private Bitmap           marker;
	private Point            currentPoint            = new Point();

	private int              height;
	private int              width;

	public AlternativeMyLocationOverlay(Context context, MapView mapView) {
		super(context, mapView);
		this.mc = mapView.getController();
		this.marker = BitmapFactory.decodeResource(context.getResources(), R.drawable.duerher);
	}

	@Override
	protected void drawMyLocation(Canvas canvas, MapView mapView, Location lastFix, GeoPoint myLocation, long when) {
		// TODO: find a better way to get height/width once the mapView is layed out correctly
		if (this.height == 0) {
			this.height = mapView.getHeight();
			this.width = mapView.getWidth();
		}
		mapView.getProjection().toPixels(myLocation, currentPoint);
		canvas.drawBitmap(marker, currentPoint.x-(marker.getWidth()/2), currentPoint.y-(marker.getHeight()), null);
	}
}