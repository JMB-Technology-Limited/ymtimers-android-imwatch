package uk.co.jarofgreen.ymtimers.ui;

import uk.co.jarofgreen.ymtimers.OurApplication;
import uk.co.jarofgreen.ymtimers.PossibleTimer;
import uk.co.jarofgreen.ymtimers.R;
import uk.co.jarofgreen.ymtimers.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

/**
 * 
 * @author James Baster <jamesbaster.co.uk>
 * @copyright JMB Technology 2013
 * @license 3-clause BSD
 *
 */
public class NewTimerActivity extends Activity {

	PossibleTimer possibleTimer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_timer);
		possibleTimer = new PossibleTimer();
		update();
		updateIcon();
	}


	@Override
	protected void onPause() {
		super.onPause();
		getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	@Override
	protected void onResume() {
		super.onResume();
		update();
		getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	protected void update() {
		TextView tv;
		
		tv = (TextView)findViewById(R.id.hours);
		tv.setText(Integer.toString(possibleTimer.getHours()));
		
		tv = (TextView)findViewById(R.id.minutes);
		tv.setText(Integer.toString(possibleTimer.getMinutes()));
		
		tv = (TextView)findViewById(R.id.seconds);
		tv.setText(Integer.toString(possibleTimer.getSeconds()));
		
	}


	protected void updateIcon() {
		ImageView bt =  (ImageView)findViewById(R.id.button_icon);
		bt.setImageResource(possibleTimer.getIcon());
	}
	
	public void onClickAddTimer(View v) {
		OurApplication ourapp = (OurApplication)getApplication();
		ourapp.addTimer(possibleTimer);
		finish();	
	}

	public void onClickPlusHour(View v) {
		possibleTimer.addHour();
		update();
	}

	public void onClickMinusHour(View v) {
		possibleTimer.minusHour();
		update();
	}

	public void onClickPlusMinute(View v) {
		possibleTimer.addMinute();
		update();
	}

	public void onClickMinusMinute(View v) {
		possibleTimer.minusMinute();
		update();
	}

	public void onClickPlusSecond(View v) {
		possibleTimer.addSecond();
		update();
	}

	public void onClickMinusSecond(View v) {
		possibleTimer.minusSecond();
		update();
	}
	
	public final int chooseHoursRequestCode = 2;

	public void onClickHours(View v) {
		Intent i = new Intent(this, EnterNumberActivity.class);
		startActivityForResult(i, chooseHoursRequestCode);
	}
	
	public final int chooseMinutesRequestCode = 3;

	public void onClickMinutes(View v) {
		Intent i = new Intent(this, EnterNumberActivity.class);
		startActivityForResult(i, chooseMinutesRequestCode);
	}
	
	public final int chooseSecondsRequestCode = 4;

	public void onClickSeconds(View v) {
		Intent i = new Intent(this, EnterNumberActivity.class);
		startActivityForResult(i, chooseSecondsRequestCode);
	}
	
	public final int chooseIconRequestCode = 1;
	
	public void onClickIcon(View v) {
		Intent i = new Intent(this, TimerChooseIconActivity.class);
		startActivityForResult(i, chooseIconRequestCode);
	}

	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) {     
		super.onActivityResult(requestCode, resultCode, data); 
		switch(requestCode) { 
			case (chooseIconRequestCode) : { 
				if (resultCode == Activity.RESULT_OK) { 
					possibleTimer.setIcon(data.getIntExtra("icon", R.drawable.icon1));
					updateIcon();
				} 
				break; 
			}
			case (chooseHoursRequestCode) : { 
				if (resultCode == Activity.RESULT_OK) { 
					possibleTimer.setHours(data.getIntExtra("number", 0));
					updateIcon();
				} 
				break; 
			}
			case (chooseMinutesRequestCode) : { 
				if (resultCode == Activity.RESULT_OK) { 
					possibleTimer.setMinutes(data.getIntExtra("number", 0));
					updateIcon();
				} 
				break; 
			}
			case (chooseSecondsRequestCode) : { 
				if (resultCode == Activity.RESULT_OK) { 
					possibleTimer.setSeconds(data.getIntExtra("number", 0));
					updateIcon();
				} 
				break; 
			}
		} 
	}




}
