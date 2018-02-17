package ramp.diagram;

import ramp.geometry.Dimension;

public class Section {
	private Direction direction;
	private Dimension landingOffset, rampOffset;
	private Dimension rampWidth, rampLength;

	private Dimension landingWidth, landingLength;

	public Section(Direction direction, Dimension landingOffset, Dimension rampOffset, Dimension rampWidth, Dimension rampLength, Dimension landingWidth,
			Dimension landingLength) {
		this.direction = direction;
		
		this.landingOffset = landingOffset;
		this.rampOffset = rampOffset;
		
		this.rampWidth = rampWidth;
		this.rampLength = rampLength;

		this.landingWidth = landingWidth;
		this.landingLength = landingLength;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Dimension getLandingOffset() {
		return landingOffset;
	}

	public void setLandingOffset(Dimension landingOffset) {
		this.landingOffset = landingOffset;
	}

	public Dimension getRampOffset() {
		return rampOffset;
	}

	public void setRampOffset(Dimension offset) {
		this.rampOffset = offset;
	}

	public Dimension getRampWidth() {
		return rampWidth;
	}

	public void setRampWidth(Dimension rampWidth) {
		this.rampWidth = rampWidth;
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

	public Dimension getLandingLength() {
		return landingLength;
	}

	public void setLandingSize(Dimension landingWidth, Dimension landingLength) {
		this.landingWidth = landingWidth;
		this.landingLength = landingLength;
	}

}
