package ramp;

import java.util.ArrayList;

public class GUIData {
	private String length = ""; //Ramp length
	private double deckHeight = 0; //Height of deck
	private double[] deckDimention = {6, 6}; //Dimension of deck
	private int deckOffSet = 0; //Offset off of the center
	private double rampLenghtTotal = 0; //Amount of inches until ground
	private double usedIn = 0; //Total number of inches used
	private ArrayList<Double> rampLenght = new ArrayList<Double>(); //Length of each ramp piece
	private ArrayList<Integer> rampDirIndex = new ArrayList<Integer>(); //Direction off of deck/turn around, 0 west, 1 east, 2 south, 3 north
	private int[] turnArround = {48, 48}; //Size of turn around, SET SIZE
		
	
	public GUIData() {
		
	}


	public String getLength() {
		return length;
	}


	public void setLength(String length) {
		this.length = length;
	}


	public double getDeckHeight() {
		return deckHeight;
	}


	public void setDeckHeight(double deckHeight) {
		this.deckHeight = deckHeight;
	}


	public double[] getDeckDimention() {
		return deckDimention;
	}


	public void setDeckDimention(double[] deckDimention) {
		this.deckDimention = deckDimention;
	}


	public int getDeckOffSet() {
		return deckOffSet;
	}


	public void setDeckOffSet(int deckOffSet) {
		this.deckOffSet = deckOffSet;
	}


	public double getRampLenghtTotal() {
		return rampLenghtTotal;
	}


	public void setRampLenghtTotal(double rampLenghtTotal) {
		this.rampLenghtTotal = rampLenghtTotal;
	}


	public double getUsedIn() {
		return usedIn;
	}


	public void setUsedIn(double usedIn) {
		this.usedIn = usedIn;
	}


	public ArrayList<Double> getRampLenght() {
		return rampLenght;
	}


	public void setRampLenght(ArrayList<Double> rampLenght) {
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