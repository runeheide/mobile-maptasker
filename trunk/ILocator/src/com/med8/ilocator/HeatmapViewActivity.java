package com.med8.ilocator;

import com.med8.ilocator.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class HeatmapViewActivity extends Activity {
	
	HeatView heatView;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		setContentView(R.layout.categories);
		setContentView(R.layout.heatmapview);
		LinearLayout srfc = (LinearLayout)findViewById(R.id.surfaceView1);
		
		AlphaAnimation alpha = new AlphaAnimation(0.4F, 0.6F);
		alpha.setDuration(0); 
		alpha.setFillAfter(true); 
		srfc.startAnimation(alpha);
		
		heatView = new HeatView(this);
		//heatView.init();
		srfc.addView(heatView);
		
		
/*			
		Button backButton = (Button)findViewById(R.id.backButton);
		Button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		*/
	}	
}
