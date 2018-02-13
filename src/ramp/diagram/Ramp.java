package ramp.diagram;

import ramp.geometry.Coordinate;
import ramp.geometry.Dimension;

public class Ramp extends DiagramComponent {
	/**
	 * The constant width of a ramp (perpendicular to its railing).
	 */
	@Deprecated
	public static final Dimension DEFAULT_WIDTH = new Dimension(40);

	/**
	 * Default minimum distance between posts of 2'.
	 */
	@Deprecated
	public static final Dimension DEFAULT_MIN_POST_DISTANCE = new Dimension(2, 0);

	/**
	 * Default maximum distance between posts of 4'.
	 */
	public static final Dimension DEFAULT_MAX_POST_DISTANCE = new Dimension(6, 0);

	/**
	 * The distance from the ramp start to the ramp end (not the length of the ramp
	 * itself).
	 */
	private Dimension length;

	/**
	 * The direction of the ramp from its landing.
	 */
	private Direction direction;

	public Ramp(Coordinate location, Dimension length, Direction direction) {
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

	public Coordinate getSize() {
		switch (this.direction) {
		case LEFT:
		case RIGHT:
			return new Coordinate(this.getLength(), Ramp.DEFAULT_WIDTH);

		case UP:
		case DOWN:
			return new Coordinate(Ramp.DEFAULT_WIDTH, this.getLength());

		default:
			return new Coordinate(new Dimension(0), new Dimension(0));
		}
	}
	
	public Coordinate getTopLeft() {
		Coordinate loc = this.getLocation().clone();
		
		switch (this.direction) {
		case LEFT:
			loc.getX().add(this.getLength().clone().negate());
			break;
		case UP:
			loc.getY().add(this.getLength().clone().negate());
			break;
		default: // Right or down
			// No transformations
		}
		
		return loc;
	}

	public Landing newLanding(Coordinate size, Dimension offset) {
		Coordinate location = this.getLocation().clone();

		switch (this.direction) {
		case LEFT:
			location.add(new Coordinate(new Dimension(0), size.getY().clone().scale(-0.5).add(Ramp.DEFAULT_WIDTH.clone().scale(0.5))));
			location.getY().add(offset);
			break;
		case RIGHT:
			location.add(new Coordinate(this.getLength(), size.getY().clone().scale(-0.5).add(Ramp.DEFAULT_WIDTH.clone().scale(0.5))));
			location.getY().add(offset);
			break;
		case UP:
			location.add(new Coordinate(size.getX().clone().scale(-0.5).add(Ramp.DEFAULT_WIDTH.clone().scale(0.5)), new Dimension(0)));
			location.getX().add(offset);
			break;
		default:
			location.add(new Coordinate(size.getX().clone().scale(-0.5).add(Ramp.DEFAULT_WIDTH.clone().scale(0.5)), this.getLength()));
			location.getX().add(offset);
			break;
		}

		return new Landing(location, size);
	}

	public Post[] generatePosts() {
		// Calculate the number of spaces c by the following formula:
		// c = (l - s) / I where l = length, s = post size, and I = ideal distance
//		double idealDist = Ramp.DEFAULT_MIN_POST_DISTANCE.getSum(Ramp.DEFAULT_MAX_POST_DISTANCE).getLength() / 2.0;
		double insideLength = this.length.clone().add(Post.DEFAULT_SIZE.clone().negate()).getLength();
		int spaceCount = (int) Math.ceil(insideLength / Ramp.DEFAULT_MAX_POST_DISTANCE.getLength());

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
			Coordinate postLocation = new Coordinate(new Dimension(pU), new Dimension(pV));
			
			switch (this.getDirection()) {
			case LEFT:
				postLocation.getY().negate();
				postLocation.getY().add(Post.DEFAULT_SIZE.clone().negate());
				postLocation.swapXY();
				break;
			case RIGHT:
				postLocation.swapXY();
				break;
			case UP:
				postLocation.getY().negate();
				postLocation.getY().add(Post.DEFAULT_SIZE.clone().negate());
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
