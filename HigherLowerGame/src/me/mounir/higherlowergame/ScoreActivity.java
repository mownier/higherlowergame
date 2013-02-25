package me.mounir.higherlowergame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstaceState) {
		super.onCreate(savedInstaceState);
		setContentView(R.layout.score);

		TextView hs = (TextView) findViewById(R.id.highScore);
		hs.setText("Finish in: " + GamePrefsApplication.ELAPSED_TIME);
	}

	public void onClickBackToMain(View view) {
		Toast.makeText(this, "<high score> " + AppCacheManager.sharedInstance.getScores().get("high score"), Toast.LENGTH_SHORT).show();
//		finish();
	}

}
