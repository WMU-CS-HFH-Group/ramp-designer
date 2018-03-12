package ramp.diagram;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import ramp.geometry.Coordinate;
import ramp.geometry.Dimension;

public class CustomPost extends CustomItem {
	public CustomPost(Coordinate location, Dimension size) {
		super(location);
		this.size = size;
		this.type = "Post";
	}

	private Dimension size;

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		g.fillRect(Diagram.coord(this.getLocation().getX()), Diagram.coord(this.getLocation().getY()), Diagram.coord(size), Diagram.coord(size));
	}
	
	public String toString() {
		return String.format("Post at %s", getLocation());
	}
}