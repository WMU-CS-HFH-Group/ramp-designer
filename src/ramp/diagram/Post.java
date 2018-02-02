package ramp.diagram;

import ramp.geometry.Dimension;
import ramp.geometry.DimensionVector;

public class Post extends DiagramComponent {
	public static final Dimension DEFAULT_SIZE = new Dimension(4);
	
	private Dimension size;
	
	public Post(DimensionVector location, Dimension size) {
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
