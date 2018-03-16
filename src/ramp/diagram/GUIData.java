package ramp.diagram;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

/**
 * Represents a ramp with multiple sections. Provides convenience methods for
 * storing data for the ramp efficiently.
 */
public class GUIData {
	private static double deckHeight = 0; // Height of deck
	private static double[] deckDimension = { 0, 0 }; // Dimension of deck
	private static int deckOffSet = 0; // Offset off of the center
	private static double rampLengthTotal = 0; // Amount of in until ground
	private static double usedIn = 0; // Total number of inches used
	private static ArrayList<Double> rampLength = new ArrayList<Double>(); // Length of each ramp piece
	private static ArrayList<Integer> rampDir = new ArrayList<Integer>(); // Direction off of deck/turn around: 0 up, 1
																			// right
																			// 2 down, 3 left
	private static ArrayList<Integer> turnAround = new ArrayList<Integer>(); // Type of turn around: 0 normal,
																				// 1 uturn up right, 2 uturn up left,
																				// 3 uturn down right, 4 uturn down
																				// left,
																				// 5 uturn left up, 6 uturn left down,
																				// 7 uturn right up, 8 uturn right down
	private static int[] coords = { 0, 0, 0, 0 }; // The min and max coordinates the components are kept:
													// 0 min x, 1 min y, 2 max x, 3 max y
	
	private static double[] deckLocation = {36, 36};
	private static double[] sideViewOrigin = {36, 36};
	
	// Custom items added after generating the ramp.
	private DefaultListModel<CustomItem> items;

	public double getDeckHeight() {
		return deckHeight;
	}

	public void setDeckHeight(double deckHeight) {
		GUIData.deckHeight = deckHeight;
	}

	public double[] getDeckDimension() {
		return deckDimension;
	}

	public void setDeckDimension(double[] deckDimension) {
		GUIData.deckDimension = deckDimension;
	}

	public int getDeckOffSet() {
		return deckOffSet;
	}

	public void setDeckOffSet(int deckOffSet) {
		GUIData.deckOffSet = deckOffSet;
	}

	public double getRampLengthTotal() {
		return rampLengthTotal;
	}

	public void setRampLengthTotal(double rampLengthTotal) {
		GUIData.rampLengthTotal = rampLengthTotal;
	}

	public double getUsedIn() {
		return usedIn;
	}

	public void setUsedIn(double usedIn) {
		GUIData.usedIn = usedIn;
	}

	public ArrayList<Double> getRampLength() {
		return rampLength;
	}

	public void setRampLength(ArrayList<Double> rampLength) {
		GUIData.rampLength = rampLength;
	}

	public ArrayList<Integer> getRampDir() {
		return rampDir;
	}

	public void setRampDir(ArrayList<Integer> rampDirIndex) {
		GUIData.rampDir = rampDirIndex;
	}

	public ArrayList<Integer> getTurnAround() {
		return turnAround;
	}

	public void setTurnAround(ArrayList<Integer> turnAround) {
		GUIData.turnAround = turnAround;
	}

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int[] coords) {
		GUIData.coords = coords;
	}

	public DefaultListModel<CustomItem> getItems() {
		return items;
	}

	public void setItems(DefaultListModel<CustomItem> items) {
		this.items = items;
	}

	public double[] getDeckLocation() {
		return deckLocation;
	}

	public void setDeckLocation(double[] deckLocation) {
		GUIData.deckLocation = deckLocation;
	}

	public double[] getSideViewOrigin() {
		return sideViewOrigin;
	}

	public void setSideViewOrigin(double[] sideViewOrigin) {
		GUIData.sideViewOrigin = sideViewOrigin;
	}
}