package ramp;

/* TODO:
 * - Length of each ramp
 * - How each ramp connects to another
 * - Sizes of each turnaround
 * - Offset from the center on turnaround and deck
 * - Size and height of deck
 */

public class GUIData {
	private String lenghth = "";
	private float totalIn = 0;
	private float usedIn = 0;
	
	public GUIData() {
		
	}

	public String getLenghth() {
		return lenghth;
	}

	public void setLenghth(String lenghth) {
		this.lenghth = lenghth;
	}

	public float getTotalIn() {
		return totalIn;
	}

	public void setTotalIn(float totalIn) {
		this.totalIn = totalIn;
	}

	public float getUsedIn() {
		return usedIn;
	}

	public void setUsedIn(float usedIn) {
		this.usedIn = usedIn;
	}
	
	

}
