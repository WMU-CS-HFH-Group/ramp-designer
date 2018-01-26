package ramp;

/**
 * Axis-aligned bounding box composed of Dimension objects.
 */
public class Box {
	private Dimension x, y, width, height;
	
	public Box(Dimension x, Dimension y, Dimension width, Dimension height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Dimension getX() {
		return x;
	}

	public Dimension getY() {
		return y;
	}

	public Dimension getWidth() {
		return width;
	}

	public Dimension getHeight() {
		return height;
	}
}
