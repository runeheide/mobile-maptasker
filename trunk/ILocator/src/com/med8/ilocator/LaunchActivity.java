package com.med8.ilocator;

import com.med8.ilocator.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LaunchActivity extends ILocatorActivity {
	
	
	
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);
        ImageView launchIcon = (ImageView)findViewById(R.id.ilocator_launch);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
    	launchIcon.startAnimation(fadeIn);
    
    	Handler x = new Handler();
    	x.postDelayed(new LaunchHandler(), 4000);
    	
    	
    }
    
    class LaunchHandler implements Runnable {
    	public void run() {
    		startActivity(new Intent(getApplication(), StartScreenActivity.class));
    		LaunchActivity.this.finish();
    	}
    }
}