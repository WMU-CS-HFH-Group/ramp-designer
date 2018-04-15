package ramp.diagram;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

/**
 * Represents a ramp with multiple sections. Provides convenience methods for
 * storing data for the ramp efficiently.
 */
public class GUIData {
	private double deckHeight = 0; // Height of deck
	private double[] deckDimension = { 0, 0 }; // Dimension of deck
	private int deckOffSet = 0; // Offset off of the center
	private double rampLengthTotal = 0; // Amount of in until ground
	private double usedIn = 0; // Total number of inches used
	private ArrayList<Double> rampLength = new ArrayList<Double>(); // Length of each ramp piece
	private ArrayList<Integer> rampDir = new ArrayList<Integer>(); // Direction off of deck/turn around: 0 up, 1
																			// right
																			// 2 down, 3 left
	private ArrayList<Integer> turnAround = new ArrayList<Integer>(); // Type of turn around: 0 normal,
																				// 1 uturn up right, 2 uturn up left,
																				// 3 uturn down right, 4 uturn down
																				// left,
																				// 5 uturn left up, 6 uturn left down,
																				// 7 uturn right up, 8 uturn right down
	private int[] coords = { 0, 0, 0, 0 }; // The min and max coordinates the components are kept:
													// 0 min x, 1 min y, 2 max x, 3 max y
	
	private double[] deckLocation = {36, 36};
	private double[] sideViewOrigin = {36, 36};
	
	// Custom items added after generating the ramp.
	private DefaultListModel<CustomItem> items = new DefaultListModel<CustomItem>();
	
	public double getDeckHeight() {
		return deckHeight;
	}

	public void setDeckHeight(double deckHeight) {
		this.deckHeight = deckHeight;
	}

	public double[] getDeckDimension() {
		return deckDimension;
	}

	public void setDeckDimension(double[] deckDimension) {
		this.deckDimension = deckDimension;
	}

	public int getDeckOffSet() {
		return deckOffSet;
	}

	public void setDeckOffSet(int deckOffSet) {
		this.deckOffSet = deckOffSet;
	}

	public double getRampLengthTotal() {
		return rampLengthTotal;
	}

	public void setRampLengthTotal(double rampLengthTotal) {
		this.rampLengthTotal = rampLengthTotal;
	}

	public double getUsedIn() {
		return usedIn;
	}

	public void setUsedIn(double usedIn) {
		this.usedIn = usedIn;
	}

	public ArrayList<Double> getRampLength() {
		return rampLength;
	}

	public void setRampLength(ArrayList<Double> rampLength) {
		this.rampLength = rampLength;
	}

	public ArrayList<Integer> getRampDir() {
		return rampDir;
	}

	public void setRampDir(ArrayList<Integer> rampDirIndex) {
		this.rampDir = rampDirIndex;
	}

	public ArrayList<Integer> getTurnAround() {
		return turnAround;
	}

	public void setTurnAround(ArrayList<Integer> turnAround) {
		this.turnAround = turnAround;
	}

	public int[] getCoords() {
		return coords;
	}

	public void setCoords(int[] coords) {
		this.coords = coords;
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
		this.deckLocation = deckLocation;
	}

	public double[] getSideViewOrigin() {
		return sideViewOrigin;
	}

	public void setSideViewOrigin(double[] sideViewOrigin) {
		this.sideViewOrigin = sideViewOrigin;
	}
}