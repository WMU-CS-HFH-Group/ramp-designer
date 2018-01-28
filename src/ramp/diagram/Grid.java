package ramp.diagram;
import java.awt.Color;

import ramp.geometry.DimensionOld;

public class Grid {
	private DimensionOld size;
	private int thickness;
	private Color color;
	private double disappearAtScale;
	
	// Labels on each measurement
	private boolean displayLabels;
	private int labelInterval;
	
	public Grid(DimensionOld size, int thickness, Color color, double disappearAtScale) {
		this.size = size;
		this.thickness = thickness;
		this.color = color;
		this.disappearAtScale = disappearAtScale;
		
		this.displayLabels = false;
		this.labelInterval = 1;
	}
	
	public DimensionOld getSize() {
		return size;
	}
	public void setSize(DimensionOld size) {
		this.size = size;
	}
	public int getThickness() {
		return thickness;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public double getDisappearAtScale() {
		return disappearAtScale;
	}

	public void setDisappearAtScale(double disappearAtScale) {
		this.disappearAtScale = disappearAtScale;
	}

	public boolean isDisplayLabels() {
		return displayLabels;
	}

	public void setDisplayLabels(boolean displayLabels) {
		this.displayLabels = displayLabels;
	}

	public int getLabelInterval() {
		return labelInterval;
	}

	public void setLabelInterval(int labelInterval) {
		this.labelInterval = labelInterval;
	}
	
	public static Grid createFeetGrid() {
		return new Grid(new DimensionOld(12.0f), 3, Color.LIGHT_GRAY, 0.1);
	}
	
	public static Grid createInchesGrid() {
		return new Grid(new DimensionOld(1.0f), 1, Color.LIGHT_GRAY, 1.0);
	}
}