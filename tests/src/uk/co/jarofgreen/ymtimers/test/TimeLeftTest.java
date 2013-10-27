package uk.co.jarofgreen.ymtimers.test;

import uk.co.jarofgreen.ymtimers.TimeLeft;


public class TimeLeftTest extends android.test.AndroidTestCase {

	public  TimeLeftTest() {
		super();
	}
	
	
	
	public void test1() {
		TimeLeft tl = new TimeLeft(61);
		assertEquals(1, tl.getSeconds());
		assertEquals(1, tl.getMinutes());
	}
	
}
