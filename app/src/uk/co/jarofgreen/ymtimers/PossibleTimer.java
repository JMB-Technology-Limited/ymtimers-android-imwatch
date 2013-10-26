package uk.co.jarofgreen.ymtimers;

/**
 * 
 * @author James Baster <jamesbaster.co.uk>
 * @copyright JMB Technology 2013
 * @license 3-clause BSD
 *
 */
public class PossibleTimer {

	private int hours = 0;
	private int minutes = 2;
	private int seconds = 0;
	private int icon = R.drawable.icon1;
	
	
	public int getHours() {
		return hours;
	}
	public int getMinutes() {
		return minutes;
	}
	public int getSeconds() {
		return seconds;
	}
	
	
	public void addSecond() {
		seconds += 1;
		if (seconds == 60) {
			seconds = 0;
			addMinute();
		}
	}

	public void addMinute() {
		minutes++;
		if(minutes == 60) {
			minutes = 0;
			addHour();
		}
	}

	public void addHour() {
		hours++;
	}	

	public void minusSecond() {
		seconds--;
		if (seconds == -1) {
			if (minutes == 0 && hours == 0) {
				seconds = 0;
			} else {
				seconds = 59;
				minusMinute();
			}
		}
	}

	public void minusMinute() {
		minutes--;
		if (minutes == -1){
			minutes = 59;
			minusHour();
		}
	}

	public void minusHour() {
		if (hours > 0) hours--;
	}
	
	
	
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	
	
	public void setHours(int hours) {
		if (this.hours < 0) {
			this.hours = 0;
		} else {
			this.hours = hours;
		}
	}
	
	public void setMinutes(int minutes) {
		if (minutes < 0) {
			this.minutes = 0;
		} else if (minutes > 60) {
			this.hours += (int)(minutes / 60);
			this.minutes = (minutes%60);
		} else {
			this.minutes = (minutes%60);
		}
	}
	
	public void setSeconds(int seconds) {
		if (seconds < 0) {
			this.seconds = 0;
		} else if (seconds > 60*60) {
			this.hours += ((int)(seconds / (60*60)))%60;
			this.minutes += ((int)(seconds / 60))%60;
			this.seconds = (seconds%60);
		} else if (seconds > 60) {
			this.minutes += (int)(seconds / 60);
			this.seconds = (seconds%60);
		} else {
			this.seconds = seconds;
		}
	}	
	
}
