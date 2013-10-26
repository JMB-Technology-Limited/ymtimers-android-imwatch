package uk.co.jarofgreen.ymtimers.ui;

import java.util.List;

import uk.co.jarofgreen.ymtimers.OurApplication;
import uk.co.jarofgreen.ymtimers.R;
import uk.co.jarofgreen.ymtimers.TimeLeft;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import uk.co.jarofgreen.ymtimers.Timer;

/**
 * 
 * @author James Baster <jamesbaster.co.uk>
 * @copyright JMB Technology 2013
 * @license 3-clause BSD
 *
 */
public class MainActivity extends Activity {

	GridView gridView;
	GridViewAdaptor gridViewAdaptor;
	Handler handler;

	
	protected static final int REFRESH_DELAY = 1000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gridView = (GridView)findViewById(R.id.gridview);
		gridViewAdaptor = new GridViewAdaptor();
		gridView.setAdapter(gridViewAdaptor);
		handler=new Handler();
	}

	public void onClickNewTimer(View v) {
		Intent i = new Intent(this, NewTimerActivity.class);
		startActivity(i);
	}
	
	public void onClickTimer(View v) {
		Integer timerID = (Integer)v.getTag();

		Intent i = new Intent(this, TimerActivity.class);
		i.putExtra("timerID", timerID);
		startActivity(i);
		
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
		reloadData();
		handler.postDelayed(runnable, MainActivity.REFRESH_DELAY);
		getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	final Runnable runnable = new Runnable() {
		public void run() {
			Log.i("MainActivity","run()");
			MainActivity.this.reloadData();
			MainActivity.this.handler.postDelayed(runnable, MainActivity.REFRESH_DELAY);
		}
	};
	
	protected void reloadData() {
		gridViewAdaptor.loadData();
		gridView.invalidateViews();
	}
	
	
	protected class GridViewAdaptor extends BaseAdapter {

		protected LayoutInflater layoutInflater; 
		protected List<Timer> timers;
		
		public GridViewAdaptor() {
			super();
			this.layoutInflater = (LayoutInflater)getSystemService( Context.LAYOUT_INFLATER_SERVICE );
			loadData();
		}

		public void loadData() {
			OurApplication ourapp = (OurApplication)getApplication();
			timers = ourapp.getTimers();
		}
		
		
		@Override
		public int getCount() {
			return timers.size();
		}

		@Override
		public Object getItem(int arg0) {
			if (arg0 < timers.size()) {
				return timers.get(arg0);
			} else {
				return null;
			}
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			if (convertView == null) {
				view = layoutInflater.inflate( R.layout.activity_main_timer, parent, false );
			} else {
				view = convertView;
			}
			
			Timer timer = (Timer)getItem(position);
			TimeLeft timeLeft = timer.getTimeLeft();
			
			TextView tv = (TextView)view.findViewById(R.id.timeleft);
			tv.setText(timeLeft.toString());
			if (timeLeft.getTotalSeconds() > 0) {
				tv.setTextColor(Color.WHITE);
			} else {
				tv.setTextColor(Color.RED);
			}
			
			ImageView iv = (ImageView)view.findViewById(R.id.icon);
			iv.setImageResource(timer.getIcon());
			
			view.setTag(timer.getId());
			
			return view;
		}
		
	}
	
}
