package ramp.diagram;

import java.awt.Graphics2D;

import ramp.geometry.Dimension;
import ramp.geometry.DimensionVector;
import ramp.geometry.DimensionVector.VectorMismatchException;

public class Ramp extends DiagramComponent {
	/**
	 * The constant width of a ramp (perpendicular to its railing).
	 */
	public static final Dimension width = new Dimension(40);

	/**
	 * The distance from the ramp start to the ramp end (not the length of the ramp
	 * itself).
	 */
	private Dimension length;

	/**
	 * The direction of the ramp from its landing.
	 */
	private Direction direction;

	public Ramp(DimensionVector location, Dimension length, Direction direction) {
		super(location);
		this.setLength(length);
		this.setDirection(direction);
		this.setLabel("Ramp");
	}

	public Dimension getLength() {
		return length;
	}

	public void setLength(Dimension length) {
		this.length = length;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public DimensionVector getSize() {
		switch (this.direction) {
		case LEFT:
		case RIGHT:
			return new DimensionVector(this.getLength(), Ramp.width);
			
		case UP:
		case DOWN:
			return new DimensionVector(Ramp.width, this.getLength()); 
					
		default:
			return new DimensionVector(0, 0);
		}
	}

	public Landing newLanding(DimensionVector size, Dimension offset) {
		DimensionVector location = this.getLocation().clone();

		try {
			switch (this.direction) {
			case LEFT:
				location.add(new Dimension(0), size.getY().getScaled(-0.5).add(Ramp.width.getScaled(0.5)));
				location.getY().add(offset);
				break;
			case RIGHT:
				location.add(this.getLength(), size.getY().getScaled(-0.5).add(Ramp.width.getScaled(0.5)));
				location.getY().add(offset);
				break;
			case UP:
				location.add(size.getX().getScaled(-0.5).add(Ramp.width.getScaled(0.5)), new Dimension(0));
				location.getX().add(offset);
				break;
			default:
				location.add(size.getX().getScaled(-0.5).add(Ramp.width.getScaled(0.5)), this.getLength());
				location.getX().add(offset);
				break;
			}
		} catch (VectorMismatchException e) {
			// Never the case
		}
		
		return new Landing(location, size);
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
}
