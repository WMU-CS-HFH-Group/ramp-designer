package ramp.diagram;

import ramp.geometry.Dimension;

public class Section {
	private Direction direction;
	private Dimension offset;
	private Dimension rampLength;
	
	private Dimension landingWidth, landingHeight;
	
	public Section(Direction direction, Dimension offset, Dimension rampLength, Dimension landingWidth, Dimension landingHeight) {
		this.direction = direction;
		this.offset = offset;
		this.rampLength = rampLength;
		
		this.landingWidth = landingWidth;
		this.landingHeight = landingHeight;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Dimension getOffset() {
		return offset;
	}

	public void setOffset(Dimension offset) {
		this.offset = offset;
	}

	public Dimension getRampLength() {
		return rampLength;
	}

	public void setRampLength(Dimension rampLength) {
		this.rampLength = rampLength;
	}

	public Dimension getLandingWidth() {
		return landingWidth;
	}
	
	public Dimension getLandingHeight() {
		return landingHeight;
	}
	
	public void setLandingSize(Dimension landingWidth, Dimension landingHeight) {
		this.landingWidth = landingWidth;
		this.landingHeight = landingHeight;
	}
}
