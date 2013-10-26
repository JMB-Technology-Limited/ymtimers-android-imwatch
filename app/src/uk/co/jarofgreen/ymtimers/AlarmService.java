package uk.co.jarofgreen.ymtimers;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * 
 * @author James Baster <jamesbaster.co.uk>
 * @copyright JMB Technology 2013
 * @license 3-clause BSD
 *
 */
public class AlarmService extends IntentService {

	public AlarmService() {
		super("AlarmService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {


		boolean playsound = false;


		Log.i("AlarmService","go");
		
		
		OurApplication ourapp = (OurApplication)getApplication();
		for(Timer timer : ourapp.getTimers()) {
			if (timer.getTimeLeft().getTotalSeconds() < 0 && !timer.isAlarmSounded()) {
				playsound = true;
				timer.setAlarmHasSounded();
			}
		}
		
		if (playsound) {
			try {
				MediaPlayer mp = MediaPlayer.create(this, R.raw.beep1);
				mp.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	
	
	
}
