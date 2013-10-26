package uk.co.jarofgreen.ymtimers;


/**
 * 
 * @author James Baster <jamesbaster.co.uk>
 * @copyright JMB Technology 2013
 * @license 3-clause BSD
 *
 */
public class Timer implements Comparable {

	private int initialHours;
	private int initialMinutes;
	private int extraMinutes = 0;
	private int initialSeconds;
	private long startedMillis;
	private int id;
	private int icon;
	private boolean alarmSounded = false;

	public Timer(PossibleTimer possibleTimer, int id) {
		super();
		this.initialHours = possibleTimer.getHours();
		this.initialMinutes = possibleTimer.getMinutes();
		this.initialSeconds = possibleTimer.getSeconds();
		startedMillis = System.currentTimeMillis();
		this.id = id;
		this.icon = possibleTimer.getIcon();
	}
	
	
	public TimeLeft getTimeLeft() {
		long now  = System.currentTimeMillis();
		long elapsedMillis = now - startedMillis;
		int elapsedSeconds = (int)(elapsedMillis / 1000);
		int secondsToShow = (60*60*initialHours)+(60*(initialMinutes+extraMinutes))+initialSeconds;
		
		int secondsOnClock = secondsToShow - elapsedSeconds;
		
		return new TimeLeft(secondsOnClock);
	}
	

	public int getId() {
		return id;
	}
	
	public void addOneMinute() {
		extraMinutes++;
		// TODO this should be done better
		alarmSounded = false;
	}


	@Override
	public int compareTo(Object another) {
		if (another instanceof Timer) {
			TimeLeft tl = getTimeLeft();
			if (((Timer) another).getTimeLeft().getTotalSeconds() > tl.getTotalSeconds()) {
				return -1;
			}
			if (((Timer) another).getTimeLeft().getTotalSeconds() < tl.getTotalSeconds()) {
				return 1;
			}
		}
		
		return 0;
	}


	public int getIcon() {
		return icon;
	}


	public void setIcon(int icon) {
		this.icon = icon;
	}


	public boolean isAlarmSounded() {
		return alarmSounded;
	}


	public void setAlarmHasSounded() {
		this.alarmSounded = true;
	}
	
	
	
	
}
