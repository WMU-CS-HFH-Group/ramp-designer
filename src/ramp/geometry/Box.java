package ramp.geometry;

public class Box {
	private Coordinate location;
	private Dimension width, height;

	public Box(Coordinate location, Dimension width, Dimension height) {
		this.location = location;
		this.width = width;
		this.height = height;
	}

	public Coordinate getLocation() {
		return location;
	}

	public void setLocation(Coordinate location) {
		this.location = location;
	}

	public Dimension getWidth() {
		return width;
	}

	public void setWidth(Dimension width) {
		this.width = width;
	}

	public Dimension getHeight() {
		return height;
	}

	public void setHeight(Dimension height) {
		this.height = height;
	}
	
	public Coordinate getCenter() {
		return this.location.clone().add(new Coordinate(width.clone().scale(0.5), height.clone().scale(0.5)));
	}
	
	public void swapWidthHeight() {
		Dimension newHeight = this.width;
		this.width = this.height;
		this.height = newHeight;
	}
}
