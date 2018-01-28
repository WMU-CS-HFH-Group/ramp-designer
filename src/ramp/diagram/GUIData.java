package ramp.diagram;

import java.util.ArrayList;
import java.util.List;

import ramp.geometry.DimensionOld;
import ramp.geometry.DimensionPair;
import ramp.geometry.DimensionVector;

/**
 * Represents a ramp with multiple sections. Provides convenience methods for
 * storing data for the ramp efficiently.
 */
public class GUIData {
	private String length = ""; // Ramp length
	private float deckHeight = 0; // Height of deck
	private float[] deckDimension = { 1, 2 }; // Dimension of deck
	private int deckOffSet = 0; // Offset off of the center
	private float rampLengthTotal = 0; // Amount of inches until ground
	private float usedIn = 0; // Total number of inches used
	private ArrayList<Float> rampLength = new ArrayList<Float>(); // Length of each ramp piece
	private ArrayList<Integer> rampDirIndex = new ArrayList<Integer>(); // Direction off of deck/turn around, 0 west, 1
																		// east, 2 south
	private int[] turnAround = { 48, 48 }; // Size of turn around

	//////////
	
	private List<DiagramComponent> components;

	public GUIData() {
		
	}
	
	public GUIData(double deckWidth, double deckLength, double deckHeight, double deckLocation, double firstRampLength, Direction firstRampDirection, double firstRampOffset) {
		this.components = new ArrayList<DiagramComponent>();
		
		this.components.add(new Landing(new DimensionVector(deckLocation, 0), new DimensionVector(deckWidth, deckLength, deckHeight)));
	}

	/**
	 * Adds a ramp section to the array.
	 * 
	 * @param landingWidth
	 *            Width in inches of the preceding landing or deck.
	 * @param landingLength
	 *            Height in inches of the preceding landing or deck.
	 * @param rampLength
	 *            The length of the ramp section in inches.
	 * @param offset
	 *            Offset from the center of the ramp section in inches.
	 * @param direction
	 *            The direction from the preceding landing or deck.
	 */
	public void addRampSection(float landingWidth, float landingLength, float rampLength, float offset,
			Direction direction) {
		// TODO: Incorporate diagram components into this data class.
	}

	//////////

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public float getDeckHeight() {
		return deckHeight;
	}

	public void setDeckHeight(float deckHeight) {
		this.deckHeight = deckHeight;
	}

	public float[] getDeckDimention() {
		return deckDimension;
	}

	public void setDeckDimention(float[] deckDimention) {
		this.deckDimension = deckDimention;
	}

	public int getDeckOffSet() {
		return deckOffSet;
	}

	public void setDeckOffSet(int deckOffSet) {
		this.deckOffSet = deckOffSet;
	}

	public float getRampLenghtTotal() {
		return rampLengthTotal;
	}

	public void setRampLenghtTotal(float rampLenghtTotal) {
		this.rampLengthTotal = rampLenghtTotal;
	}

	public float getUsedIn() {
		return usedIn;
	}

	public void setUsedIn(float usedIn) {
		this.usedIn = usedIn;
	}

	public ArrayList<Float> getRampLenght() {
		return rampLength;
	}

	public void setRampLenght(ArrayList<Float> rampLenght) {
		this.rampLength = rampLenght;
	}

	public ArrayList<Integer> getRampDirIndex() {
		return rampDirIndex;
	}

	public void setRampDirIndex(ArrayList<Integer> rampDirIndex) {
		this.rampDirIndex = rampDirIndex;
	}

	public int[] getTurnArround() {
		return turnAround;
	}

	public void setTurnArround(int[] turnArround) {
		this.turnAround = turnArround;
	}

	//////////
}