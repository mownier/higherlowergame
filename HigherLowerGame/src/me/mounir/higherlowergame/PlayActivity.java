package me.mounir.higherlowergame;

import java.util.Random;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity {

	private int hiddenNumber;
	private int guessNumber;
	private long lFinishTime;
	private String sFinishTime;
	private String sElapsedTime;
	private AlertDialog dialog;
	private CountDownTimer cdt;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);

		createDialog();
		randomize();
		initCounter();
		cdt.start();
	}// onCreate

	@Override
	public void onStop() {
		super.onStop();
		cdt.cancel();
		finish();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		cdt.cancel();
		finish();
	}

	public void initCounter() {
		cdt = new CountDownTimer(120000, 950) {

			TextView countDown = (TextView) findViewById(R.id.countDownTextView);

			public void onTick(long millisUntilFinished) {
				long min = millisUntilFinished / 60000;
				long sec = ((millisUntilFinished - (60000 * min)) / 1000);
				if (sec >= 0 && sec <= 9)
					countDown.setText("" + min + ":0" + sec);
				else
					countDown.setText("" + min + ":" + sec);
				lFinishTime = millisUntilFinished;
				sFinishTime = "" + min + ":" + sec;
				sElapsedTime = "" + (1 - min) + ":" + (60 - sec);
			}

			public void onFinish() {
				countDown.setText("0:00");
				dialog.setMessage("Better luck next time! Want to play again?");
				dialog.show();
			}
		};
	}

	private void createDialog() {
		dialog = new AlertDialog.Builder(this).create();
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						randomize();
						cdt.start();
					}
				});
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						cdt.cancel();
						finish();
					}
				});
	}

	private void randomize() {
		Random r = new Random();
		hiddenNumber = r.nextInt(100 - 1) + 1;

	}// end randomize

	public void saveHighScore() {
		if (lFinishTime > GamePrefsApplication.I_HIGH_SCORE) {
			GamePrefsApplication.I_HIGH_SCORE = lFinishTime;
			GamePrefsApplication.S_HIGH_SCORE = sFinishTime;
			GamePrefsApplication.ELAPSED_TIME = sElapsedTime;
			
			AppCacheManager.sharedInstance.getScores().put("high score", sElapsedTime.toString());
		}
	}

	public void onClickGuess(View view) {
		EditText input = (EditText) findViewById(R.id.guessEditText);
		try {
			guessNumber = Integer.parseInt("" + input.getText());
			// Toast.makeText(this, "" + hiddenNumber,
			// Toast.LENGTH_SHORT).show();
			if (guessNumber == hiddenNumber) {
				cdt.cancel();
				dialog.setMessage("Congrats! You uncover the hidden number. Want to play again?");
				dialog.show();
				// Toast.makeText(this, "Elapsed: " + sElapsedTime,
				// Toast.LENGTH_SHORT).show();
				// Toast.makeText(this, "UntilFinish: " + sFinishTime,
				// Toast.LENGTH_SHORT).show();
				saveHighScore();
			} else {
				if (guessNumber < hiddenNumber) {
					Toast.makeText(this, "Higher!", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(this, "Lower!", Toast.LENGTH_SHORT).show();
				}
			}

		} catch (NumberFormatException e) {
			Toast.makeText(this, "You do know numbers, don't you?",
					Toast.LENGTH_SHORT).show();
		}
	}// end onClickGuess
}
