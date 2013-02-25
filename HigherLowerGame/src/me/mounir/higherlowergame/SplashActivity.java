package me.mounir.higherlowergame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.splash);

	startAnimating();
    }
    
    public void startAnimating() {
	ImageView image1 = (ImageView) findViewById(R.id.splashImageView);
	Animation fade1 = AnimationUtils.loadAnimation(getApplicationContext(),
		R.anim.custom_anim);
	
	fade1.setAnimationListener(new AnimationListener() {
	
	    public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		Intent i = new Intent(SplashActivity.this, MenuActivity.class);
		startActivity(i);
		SplashActivity.this.finish();
	    }

	    public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
	    }

	    public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
	    }
	});
	image1.startAnimation(fade1);
    }
}