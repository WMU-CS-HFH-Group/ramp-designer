package ramp;

import java.util.ArrayList;

import com.jgoodies.forms.layout.Size;

public class GUIData {
	private String length = ""; //Ramp length
	private float deckHeight = 0; //Height of deck
	private float[] deckDimention = {1, 2}; //Dimension of deck
	private int deckOffSet = 0; //Offset off of the center
	private float rampLenghtTotal = 0; //Amount of inches until ground
	private float usedIn = 0; //Total number of inches used
	private ArrayList<Float> rampLenght = new ArrayList<Float>(); //Length of each ramp piece
	private ArrayList<Integer> rampDirIndex = new ArrayList<Integer>(); //Direction off of deck/turn around, 0 west, 1 east, 2 south
	private int[] turnArround = {48, 48}; //Size of turn around
		
	
	public GUIData() {
		
	}


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
		return deckDimention;
	}


	public void setDeckDimention(float[] deckDimention) {
		this.deckDimention = deckDimention;
	}


	public int getDeckOffSet() {
		return deckOffSet;
	}


	public void setDeckOffSet(int deckOffSet) {
		this.deckOffSet = deckOffSet;
	}


	public float getRampLenghtTotal() {
		return rampLenghtTotal;
	}


	public void setRampLenghtTotal(float rampLenghtTotal) {
		this.rampLenghtTotal = rampLenghtTotal;
	}


	public float getUsedIn() {
		return usedIn;
	}


	public void setUsedIn(float usedIn) {
		this.usedIn = usedIn;
	}


	public ArrayList<Float> getRampLenght() {
		return rampLenght;
	}


	public void setRampLenght(ArrayList<Float> rampLenght) {
		this.rampLenght = rampLenght;
	}


	public ArrayList<Integer> getRampDirIndex() {
		return rampDirIndex;
	}


	public void setRampDirIndex(ArrayList<Integer> rampDirIndex) {
		this.rampDirIndex = rampDirIndex;
	}


	public int[] getTurnArround() {
		return turnArround;
	}


	public void setTurnArround(int[] turnArround) {
		this.turnArround = turnArround;
	}

	
	
}