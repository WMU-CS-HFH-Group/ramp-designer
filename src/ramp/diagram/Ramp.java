package ramp.diagram;

import ramp.geometry.Dimension;
import ramp.geometry.DimensionUtil;
import ramp.geometry.DimensionVector;
import ramp.geometry.DimensionVector.VectorMismatchException;

public class Ramp extends DiagramComponent {
	/**
	 * The constant width of a ramp (perpendicular to its railing).
	 */
	public static final Dimension DEFAULT_WIDTH = new Dimension(40);

	/**
	 * Default minimum distance between posts of 2'.
	 */
	public static final Dimension DEFAULT_MIN_POST_DISTANCE = new Dimension(2, 0);

	/**
	 * Default maximum distance between posts of 4'.
	 */
	public static final Dimension DEFAULT_MAX_POST_DISTANCE = new Dimension(4, 0);

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
			return new DimensionVector(this.getLength(), Ramp.DEFAULT_WIDTH);

		case UP:
		case DOWN:
			return new DimensionVector(Ramp.DEFAULT_WIDTH, this.getLength());

		default:
			return new DimensionVector(0, 0);
		}
	}
	
	public DimensionVector getTopLeft() {
		DimensionVector loc = this.getLocation().clone();
		
		switch (this.direction) {
		case LEFT:
			loc.getComponent(0).add(this.getLength().getNegation());
			break;
		case UP:
			loc.getComponent(1).add(this.getLength().getNegation());
			break;
		default: // Right or down
			// No transformations
		}
		
		return loc;
	}

	public Landing newLanding(DimensionVector size, Dimension offset) {
		DimensionVector location = this.getLocation().clone();

		switch (this.direction) {
		case LEFT:
			location.add(new Dimension(0), size.getY().getScaled(-0.5).add(Ramp.DEFAULT_WIDTH.getScaled(0.5)));
			location.getY().add(offset);
			break;
		case RIGHT:
			location.add(this.getLength(), size.getY().getScaled(-0.5).add(Ramp.DEFAULT_WIDTH.getScaled(0.5)));
			location.getY().add(offset);
			break;
		case UP:
			location.add(size.getX().getScaled(-0.5).add(Ramp.DEFAULT_WIDTH.getScaled(0.5)), new Dimension(0));
			location.getX().add(offset);
			break;
		default:
			location.add(size.getX().getScaled(-0.5).add(Ramp.DEFAULT_WIDTH.getScaled(0.5)), this.getLength());
			location.getX().add(offset);
			break;
		}

		return new Landing(location, size);
	}

	public Post[] generatePosts() {
		// Calculate the number of spaces c by the following formula:
		// c = (l - s) / I where l = length, s = post size, and I = ideal distance
		double idealDist = Ramp.DEFAULT_MIN_POST_DISTANCE.getSum(Ramp.DEFAULT_MAX_POST_DISTANCE).getLength() / 2.0;
		double insideLength = this.length.getSum(Post.DEFAULT_SIZE.getNegation()).getLength();
		int spaceCount = (int) Math.floor(insideLength / idealDist);

		// Calculate the distance d between these posts by the following formula:
		// d = (l - s) / c
		double dist = insideLength / spaceCount;

		// Allocate an array of posts
		Post[] posts = new Post[(spaceCount + 1) * 2];

		// When the ramp is vertical, u = x and v = y. When the ramp is horizontal, u =
		// y and v = x.
		double pU = -Post.DEFAULT_SIZE.getLength();
		double pV = 0;

		// Generate left or top posts
		for (int i = 0; i < posts.length; i++) {
			// If we have finished the first side, switch to the next side.
			if (i == posts.length / 2) {
				// Adjust the u and v coordinates accordingly.
				pU = Ramp.DEFAULT_WIDTH.getLength();
				pV = 0;
			}

			// Perform coordinate calculations for the post based on the u, v coordinates
			DimensionVector postLocation = new DimensionVector(pU, pV);
			switch (this.getDirection()) {
			case LEFT:
				postLocation.getComponent(1).negate();
				postLocation.getComponent(1).add(Post.DEFAULT_SIZE.getNegation());
				postLocation.swapComponents(0, 1);
				break;
			case RIGHT:
				postLocation.swapComponents(0, 1);
				break;
			case UP:
				postLocation.getComponent(1).negate();
				postLocation.getComponent(1).add(Post.DEFAULT_SIZE.getNegation());
				break;
			default: // Down
				// No transformations necessary
			}

			// Add the post location vector to the ramp location's
			postLocation.add(this.getLocation());

			// Create the post
			posts[i] = new Post(postLocation, Post.DEFAULT_SIZE);

			// Increase the lengthwise position
			pV += dist;
		}

		return posts;
	}
}
