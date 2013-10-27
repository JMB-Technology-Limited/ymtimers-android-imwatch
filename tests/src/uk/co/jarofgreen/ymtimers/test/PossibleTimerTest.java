package uk.co.jarofgreen.ymtimers.test;

import uk.co.jarofgreen.ymtimers.PossibleTimer;


public class PossibleTimerTest extends android.test.AndroidTestCase {

	public  PossibleTimerTest() {
		super();
	}
	
	
	
	public void test1() {
		PossibleTimer pt = new PossibleTimer();
		pt.setMinutes(90);
		assertEquals(1, pt.getHours());
		assertEquals(30, pt.getMinutes());
	}
	
}
