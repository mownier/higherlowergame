package me.mounir.higherlowergame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
	}

	public void onClickPlay(View view) {
		Intent playIntent = new Intent(this, PlayActivity.class);
		startActivity(playIntent);
	}

	public void onClickScore(View view) {
		Intent scoreIntent = new Intent(this, ScoreActivity.class);
		startActivity(scoreIntent);
	}

	public void onClickHelp(View view) {
		Intent helpIntent = new Intent(this, HelpActivity.class);
		startActivity(helpIntent);
	}
	
}
