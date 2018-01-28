package ramp.diagram;

import ramp.geometry.DimensionVector;

public class DiagramComponent {
	private DimensionVector location;
	private String label;
	private boolean showLabel;
	
	public DiagramComponent(DimensionVector location) {
		this.location = location;
	}

	public DimensionVector getLocation() {
		return location;
	}

	public void setLocation(DimensionVector location) {
		this.location = location;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isShowLabel() {
		return showLabel;
	}

	public void setShowLabel(boolean showLabel) {
		this.showLabel = showLabel;
	}
}
