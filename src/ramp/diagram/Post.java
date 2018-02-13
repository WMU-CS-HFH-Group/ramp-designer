package ramp.diagram;

import ramp.geometry.Coordinate;
import ramp.geometry.Dimension;

public class Post extends DiagramComponent {
	public static final Dimension DEFAULT_SIZE = new Dimension(4);
	
	private Dimension size;
	
	public Post(Coordinate location, Dimension size) {
		super(location);
		this.setSize(size);
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}
}
