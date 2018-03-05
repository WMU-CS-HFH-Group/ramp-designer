package ramp.diagram;

import java.util.ArrayList;

/**
 * Represents a ramp with multiple sections. Provides convenience methods for
 * storing data for the ramp efficiently.
 */
public class GUIData {
	private static double deckHeight = 0; // Height of deck
	private static double[] deckDimension = { 1, 2 }; // Dimension of deck
	private static int deckOffSet = 0; // Offset off of the center
	private static double rampLengthTotal = 0; // Amount of in until ground
	private static double usedIn = 0; // Total number of inches used
	private static ArrayList<Double> rampLength = new ArrayList<Double>(); // Length of each ramp piece
	private static ArrayList<Integer> rampDir = new ArrayList<Integer>(); // Direction off of deck/turn around, 0 west, 1
																		// east, 2 south, eventually 3 north
	private int[] turnAround = { 48, 48 }; // Size of turn around

	public GUIData() {
		
	}
	
	public GUIData(double deckWidth, double deckLength, double deckHeight, double deckLocation, double firstRampLength, Direction firstRampDirection, double firstRampOffset) {
		
	}

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

	public int[] getTurnAround() {
		return turnAround;
	}

	public void setTurnAround(int[] turnAround) {
		this.turnAround = turnAround;
	}
}