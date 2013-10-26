package uk.co.jarofgreen.ymtimers;

/**
 * 
 * @author James Baster <jamesbaster.co.uk>
 * @copyright JMB Technology 2013
 * @license 3-clause BSD
 *
 */
public class TimeLeft {
	
	private int totalSeconds;
	private boolean positive;
	private int hours;
	private int minutes;
	private int seconds;
	
	public TimeLeft(int seconds) {
		super();
		this.totalSeconds = seconds; 
		this.hours = Math.abs((int)seconds/(60*60));
		this.minutes = Math.abs(((int)seconds/60)%60);
		this.seconds = Math.abs(seconds%60);
		this.positive = seconds >= 0;
	}
	
	public String toString() {
		if (positive) {
			return hours > 0 ? 
				hours+":"+String.format("%02d", minutes) +":"+String.format("%02d", seconds) :
				String.format("%02d", minutes) +":"+String.format("%02d", seconds) ;
		} else {
			return hours > 0 ? 
				"-"+hours+":"+String.format("%02d", minutes) +":"+String.format("%02d", seconds)  : 
				"-"+String.format("%02d", minutes) +":"+String.format("%02d", seconds) ;
		}
	}

	public int getTotalSeconds() {
		return totalSeconds;
	}

	
	
}

