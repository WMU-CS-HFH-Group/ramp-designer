package ramp.diagram;

import java.util.ArrayList;
import java.util.List;

import ramp.geometry.Coordinate;
import ramp.geometry.Dimension;

public class Ramp {
	public static final double SLOPE = 0.083333;

	private Coordinate location;
	private Dimension height;

	private List<Section> sections;

	public Ramp(Dimension height, Coordinate location) {
		this.height = height;
		this.sections = new ArrayList<Section>();
		this.location = location;
	}

	public Dimension getHeight() {
		return height;
	}

	public void setHeight(Dimension height) {
		this.height = height;
	}

	public Coordinate getLocation() {
		return location;
	}

	public void setLocation(Coordinate location) {
		this.location = location;
	}

	public void addSection(Section section) {
		this.sections.add(section);
	}

	public void addSection(Direction direction, Dimension landingOffset, Dimension rampOffset, Dimension rampWidth,
			Dimension rampLength, Dimension landingWidth, Dimension landingHeight) {
		Section s = new Section(direction, landingOffset, rampOffset, rampWidth, rampLength, landingWidth,
				landingHeight);
		this.addSection(s);
	}

	public Section getSection(int index) {
		return this.sections.get(index);
	}

	public int getLongestSection() {
		if (this.sections.size() > 0) {
			int index = 0;
			Section longest = this.getSection(index);
			for (int i = 0; i < this.sections.size(); i++) {
				Section s = this.getSection(i);
				if (s.getRampLength().getLength() > longest.getRampLength().getLength()) {
					longest = s;
					index = i;
				}
			}
			return index;
		} else {
			return -1;
		}
	}

	public int countSections() {
		return this.sections.size();
	}

	public Dimension calculateSectionHeight(int index) {
		double hSum = 0;

		// Sum the changes in height for each ramp before the one given.
		for (int i = 0; i < index; i++) {
			hSum += getSection(i).getRampLength().getLength() * Ramp.SLOPE;
		}

		return new Dimension(this.height.getLength() - hSum);
	}
}
