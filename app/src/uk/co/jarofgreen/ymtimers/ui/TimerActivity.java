package uk.co.jarofgreen.ymtimers.ui;

import uk.co.jarofgreen.ymtimers.OurApplication;
import uk.co.jarofgreen.ymtimers.R;
import uk.co.jarofgreen.ymtimers.TimeLeft;
import uk.co.jarofgreen.ymtimers.Timer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author James Baster <jamesbaster.co.uk>
 * @copyright JMB Technology 2013
 * @license 3-clause BSD
 *
 */
public class TimerActivity extends Activity {

	Timer timer;
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handler=new Handler();

		setContentView(R.layout.activity_timer);

		Bundle extras = getIntent().getExtras();
		
		int timerID = extras.getInt("timerID");
		
		Log.i("TimerActivity","ID "+timerID);
		
		OurApplication ourapp = (OurApplication)getApplication();
		timer = ourapp.getTimerByID(timerID);
		
		update();
		updateIcon();
		
	}


	@Override
	protected void onPause() {
		super.onPause();
		handler.removeCallbacks(runnable);
		getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	@Override
	protected void onResume() {
		super.onResume();
		update();
		handler.postDelayed(runnable, MainActivity.REFRESH_DELAY);
		getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	
	final Runnable runnable = new Runnable() {
		public void run() {
			TimerActivity.this.update();
			TimerActivity.this.handler.postDelayed(runnable, MainActivity.REFRESH_DELAY);
		}
	};
	

	protected void update() {
		TimeLeft timeLeft = timer.getTimeLeft();

		TextView tv =  (TextView)findViewById(R.id.time_left);
		tv.setText(timeLeft.toString());
		if (timeLeft.getTotalSeconds() > 0) {
			tv.setTextColor(Color.WHITE);
		} else {
			tv.setTextColor(Color.RED);
		}
	}

	protected void updateIcon() {
		ImageView bt =  (ImageView)findViewById(R.id.button_icon);
		bt.setImageResource(timer.getIcon());
	}
	
	public void onClickPlusOneMinute(View v) {
		timer.addOneMinute();
		update();
		new SortTimersAsyncTask().execute(true); 
	}
	
	public void onClickBack(View v) {
		finish();
	}
	
	public class SortTimersAsyncTask extends AsyncTask<Boolean, Boolean, Boolean> {

		@Override
		protected Boolean doInBackground(Boolean... params) {
			OurApplication ourapp = (OurApplication)getApplication();
			ourapp.onChangeTimerTime();
			return null;
		}
		
	}
	
	public void onClickDelete(View v) {
		OurApplication ourapp = (OurApplication)getApplication();
		ourapp.deleteTimer(timer);
		finish();
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
				timer.setIcon(data.getIntExtra("icon", R.drawable.icon1));
				updateIcon();
			} 
			break; 
		} 
		} 
	}

	
}
