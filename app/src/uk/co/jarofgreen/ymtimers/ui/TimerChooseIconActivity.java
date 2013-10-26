package uk.co.jarofgreen.ymtimers.ui;

import uk.co.jarofgreen.ymtimers.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 
 * @author James Baster <jamesbaster.co.uk>
 * @copyright JMB Technology 2013
 * @license 3-clause BSD
 *
 */
public class TimerChooseIconActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer_choose_icon);
	}



	@Override
	protected void onPause() {
		super.onPause();
		getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	@Override
	protected void onResume() {
		super.onResume();
		getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	
	
	public void onClickIcon1(View v) {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("icon", R.drawable.icon1);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}

	public void onClickIcon2(View v) {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("icon", R.drawable.icon2);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
	
	public void onClickIcon3(View v) {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("icon", R.drawable.icon3);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
	
	public void onClickIcon4(View v) {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("icon", R.drawable.icon4);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
	
	public void onClickIcon5(View v) {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("icon", R.drawable.icon5);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
	
	public void onClickIcon6(View v) {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("icon", R.drawable.icon6);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
	
	public void onClickIcon7(View v) {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("icon", R.drawable.icon7);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
	
	public void onClickIcon8(View v) {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("icon", R.drawable.icon8);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
	
	public void onClickIcon9(View v) {
		Intent resultIntent = new Intent();
		resultIntent.putExtra("icon", R.drawable.icon9);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
	
	
}

