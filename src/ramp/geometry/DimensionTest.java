/**
 * 
 */
package ramp.geometry;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Brandon
 *
 */
class DimensionTest {

	@Test
	void testToString() {
		Dimension wholeFoot = new Dimension(60);
		assertEquals(wholeFoot.toString(), "5'");
		
		Dimension wholeInch = new Dimension(77);
		assertEquals(wholeInch.toString(), "6' 5\"");
		
		Dimension eighth = new Dimension(55.875);
		assertEquals(eighth.toString(), "4' 7 7/8\"");
		
		Dimension fourth = new Dimension(4.25);
		assertEquals(fourth.toString(), "4 1/4\"");
		
		Dimension half = new Dimension(36.5);
		assertEquals(half.toString(), "3' 1/2\"");
	}
	
	@Test
	void testDoubleFromString() {	
		try {
			assertEquals(Dimension.doubleFromString("50.5"), 50.5);
			assertEquals(Dimension.doubleFromString("50"), 50.0);
			assertEquals(Dimension.doubleFromString(".5"), 0.5);
			assertEquals(Dimension.doubleFromString("50 1/2"), 50.5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testInchesFromString() {
		String[] sixFeet = new String[]{"6'0\"","6'","6 feet 0 inches","6 feet","6 ft",
				"6ft","6feet", "6'0.0\"", "6 feet 0.0 inches"};
		try {
			for(int i = 0; i < sixFeet.length; i++) {
				assertEquals(Dimension.inchesFromString(sixFeet[i]), 72);
			}
			
			assertEquals(Dimension.inchesFromString("0'"), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
