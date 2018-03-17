package ramp.diagram;

import java.awt.Graphics2D;

import ramp.geometry.Coordinate;

public abstract class CustomItem {
	private Coordinate location;
	protected String type;

	public CustomItem(Coordinate location) {
		this.location = location;
		this.type = "Generic";
	}

	public Coordinate getLocation() {
		return location;
	}

	public void setLocation(Coordinate location) {
		this.location = location;
	}
	
	public String getType() {
		return type;
	}

	public abstract void draw(Graphics2D g);
	
	public abstract CustomItem clone();
}