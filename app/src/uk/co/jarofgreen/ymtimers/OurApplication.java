package uk.co.jarofgreen.ymtimers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

/**
 * 
 * @author James Baster <jamesbaster.co.uk>
 * @copyright JMB Technology 2013
 * @license 3-clause BSD
 *
 */
public class OurApplication extends Application  {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	//######################################### Alarm Timers
	
	protected List<Timer> timers = new ArrayList<Timer>();
	private Object timersLock = new Object();
	protected int nextTimerID = 1;
	
	public void addTimer(PossibleTimer possibleTimer) {
		synchronized(timersLock) {
			Log.i("OurApplication","adding timer");
			timers.add(new Timer(possibleTimer, nextTimerID));
			Collections.sort(timers);
			nextTimerID++;
		}
		startAlarmService();
	}
	

	public void onChangeTimerTime() {
		synchronized(timersLock) {
			Log.i("OurApplication","timer changed");
			Collections.sort(timers);
		}
	}
	
	
	
	
	public List<Timer> getTimers() {
		List<Timer> r = new ArrayList<Timer>();
		synchronized(timersLock) {
			Log.i("OurApplication","Getting Timers");
			for (Timer timer: timers) {
				r.add(timer);
			}
		}
		return r;
	}
	
	public Timer getTimerByID(int id) {
		synchronized(timersLock) {
			Log.i("OurApplication","Get specifir timer");
			for (Timer timer: timers) {
				if (timer.getId() == id) {
					return timer;
				}
			}
			return null;
		}	
	}
	
	public void deleteTimer(Timer timer) {
		synchronized(timersLock) {
			timers.remove(timer);
		}
		if (timers.size() == 0) {
			stopAlarmService();
		}
	}
	
	//######################################### Alarm Service
	// The Alarm Service runs every 15 seconds.
	// For now it just plays a noise if any timers have expired, but it should also update a notification.
	// When there are no timers left, it is stopped to save battery. 
	
	boolean alarmServiceStarted = false;
	
	protected Intent alarmServiceIntent;
	protected PendingIntent alarmServicePendingIntent;
	
	public void startAlarmService() {
		if (!alarmServiceStarted) {
			Log.i("OurApplication","Starting Alarm Service");
			// intent
			if (alarmServiceIntent == null || alarmServicePendingIntent == null) {
				alarmServiceIntent = new Intent(getApplicationContext(), AlarmService.class);
				alarmServicePendingIntent = PendingIntent.getService(getApplicationContext(), 2222, alarmServiceIntent, PendingIntent.FLAG_CANCEL_CURRENT);
			}
			// time
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.SECOND, 15);
			//registering our pending intent with alarmmanager
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			am.setInexactRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),15000, alarmServicePendingIntent);
			// started
			alarmServiceStarted = true;
		}
	}
	
	public void stopAlarmService() {
		if (alarmServiceStarted) {
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			am.cancel(alarmServicePendingIntent);
			alarmServiceStarted = false;			
		}
	}
	
	
}
