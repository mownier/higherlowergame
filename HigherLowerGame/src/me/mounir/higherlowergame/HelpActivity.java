package me.mounir.higherlowergame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class HelpActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.help);
    }
    
    public void onClickBackToMain(View view) {
	finish();
    }
}
