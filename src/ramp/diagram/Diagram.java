package ramp.diagram;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JFrame;

import ramp.diagram.Label.Alignment;
import ramp.diagram.Label.LabelSize;
import ramp.geometry.Coordinate;
import ramp.geometry.Dimension;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Diagram extends Component {
	// Constants
	@Deprecated
	private static final double LINE_SPACING = 0.25; // Ratio to the text height to leave between lines
	private static final Dimension MAX_POST_SPACE = new Dimension(6, 0);

	// Transformation variables
	private double scale;
	private int translationX, translationY;
	private Point panOrigin;
	private Point lastTranslation;

	// Transformation limits
	private double minScale = 0.1;
	private double maxScale = 3.0;

	// Grid sizes
	private List<Grid> grids;

	// Page Specifications
	private Dimension maxWidth, maxHeight;
	private Font labelFont;

	// Input
	private GUIData guiData;

	public Diagram(GUIData guiData) {
		// Store Input
		this.guiData = guiData;

		// Transformation
		this.scale = 0.25;
		this.translationX = 0;
		this.translationY = 0;
		this.panOrigin = new Point(0, 0);
		this.lastTranslation = new Point(0, 0);

		// Grids
		this.grids = new ArrayList<Grid>();

		// Default grid
		Grid ftGrid = Grid.createFeetGrid();
		ftGrid.setDisplayLabels(true);
		ftGrid.setLabelInterval(3);

		this.addGrid(ftGrid);

		// Page Specifications
		this.maxWidth = new Dimension(50, 0);
		this.maxHeight = new Dimension(50, 0);
		this.labelFont = new Font("Arial", Font.PLAIN, 100);

		// Event for zooming
		this.addMouseWheelListener(new MouseAdapter() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				double delta = 0.05 * e.getPreciseWheelRotation();
				if (scale + delta <= maxScale && scale + delta >= minScale) {
					scale += delta;
					revalidate();
					repaint();
				}
			}
		});

		// Events for panning
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panOrigin.setLocation(e.getX(), e.getY());
				lastTranslation.setLocation(translationX, translationY);
			}
		});

		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				translationX = (int) lastTranslation.getX() + e.getX() - (int) panOrigin.getX();
				translationY = (int) lastTranslation.getY() + e.getY() - (int) panOrigin.getY();
				revalidate();
				repaint();
			}
		});
	}

	public void launch() {
		JFrame frame;

		frame = new JFrame("Ramp Diagram");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(800, 800);
		frame.setBackground(Color.white);
		frame.setLayout(new BorderLayout());
		frame.add("Center", this);

		frame.setVisible(true);
	}

	public void resetTranslation() {
		this.translationX = 0;
		this.translationY = 0;
		revalidate();
		repaint();
	}

	public void resetScale() {
		this.scale = 1.0;
		revalidate();
		repaint();
	}

	public void addGrid(Grid grid) {
		this.grids.add(grid);
	}

	public void removeGrid(Grid grid) {
		this.grids.remove(grid);
	}

	public void paint(Graphics graphics) {
		// Set up graphics
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Make transformations
		g.getTransform().setToIdentity();
		g.translate(this.translationX, this.translationY);
		g.scale(this.scale, this.scale);

		// Draw grids
		for (Grid grid : this.grids) {
			this.drawGrid(g, grid);
		}

		this.drawSample(g);
	}

	public void drawRampTop(Graphics2D g, Ramp r) {
		Dimension x = r.getLocation().getX();
		Dimension y = r.getLocation().getY();

		for (int i = 0; i < r.countSections(); i++) {
			Section s = r.getSection(i);

			// Set graphics settings.
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(2));

			// Draw landing before ramp.
			g.drawRect(coord(x), coord(y), coord(s.getLandingWidth()), coord(s.getLandingLength()));

			// Draw ramp.
			switch (s.getDirection()) {
			case UP:
				break;
			case DOWN:
				// Adjust x and y for ramp.
				x.add(s.getLandingWidth().clone().scale(0.5));
				x.subtract(s.getRampWidth().clone().scale(0.5));
				x.add(s.getLandingOffset());
				
				y.add(s.getLandingLength());

				g.drawRect(coord(x), coord(y), coord(s.getRampWidth()), coord(s.getRampLength()));

				// Adjust x and y for next section.
				if (i < r.countSections() - 1) {
					Section s2 = r.getSection(i + 1);

					x.add(s.getRampWidth().clone().scale(0.5));
					x.subtract(s2.getLandingWidth().clone().scale(0.5));
					x.add(s2.getLandingOffset());

					y.add(s.getRampLength());
				}
				break;
			case LEFT:
			case RIGHT:
				// Adjust x and y for ramp.
				x.add(s.getLandingWidth());
				
				y.add(s.getLandingLength().clone().scale(0.5));
				y.subtract(s.getRampWidth().clone().scale(0.5));
				y.add(s.getRampOffset());

				g.drawRect(coord(x), coord(y), coord(s.getRampLength()), coord(s.getRampWidth()));

				// Adjust x and y for next section.
				if (i < r.countSections() - 1) {
					Section s2 = r.getSection(i + 1);

					x.add(s.getRampLength());

					y.add(s.getRampWidth().clone().scale(0.5));
					y.subtract(s2.getRampWidth().clone().scale(0.5));
					y.add(s2.getLandingOffset());
				}
				break;
			default:
			}

		}
	}

	private Coordinate[] generatePosts(Coordinate rampLocation, Coordinate rampSize, Direction rampDirection,
			Dimension postSize) {
		Dimension rampWidth = rampSize.getX();
		Dimension rampLength = rampSize.getY();

		if (rampDirection == Direction.LEFT || rampDirection == Direction.RIGHT) {
			rampWidth = rampSize.getY();
			rampLength = rampSize.getX();
		}

		double insideLength = rampLength.clone().add(postSize.clone().negate()).getLength();
		int spaceCount = (int) Math.ceil(insideLength / MAX_POST_SPACE.getLength());

		// Calculate the distance d between these posts by the following formula:
		// d = (l - s) / c
		double dist = insideLength / spaceCount;

		// Allocate an array of posts
		Coordinate[] posts = new Coordinate[(spaceCount + 1) * 2];

		// When the ramp is vertical, u = x and v = y. When the ramp is horizontal, u =
		// y and v = x.
		double pU = -postSize.getLength();
		double pV = 0;

		// Generate left or top posts
		for (int i = 0; i < posts.length; i++) {
			// If we have finished the first side, switch to the next side.
			if (i == posts.length / 2) {
				// Adjust the u and v coordinates accordingly.
				pU = rampWidth.getLength();
				pV = 0;
			}

			// Perform coordinate calculations for the post based on the u, v coordinates
			Coordinate postLocation = new Coordinate(new Dimension(pU), new Dimension(pV));

			switch (rampDirection) {
			case LEFT:
				postLocation.getY().negate();
				postLocation.getY().add(postSize.clone().negate());
				postLocation.swapXY();
				break;
			case RIGHT:
				postLocation.swapXY();
				break;
			case UP:
				postLocation.getY().negate();
				postLocation.getY().add(postSize.clone().negate());
				break;
			default: // Down
				// No transformations necessary
			}

			// Add the post location vector to the ramp location's
			postLocation.add(rampLocation);

			// Create the post
			posts[i] = postLocation.clone();

			// Increase the lengthwise position
			pV += dist;
		}

		return posts;
	}

	public void drawGrid(Graphics2D g, Grid grid) {
		// Determine whether the grid should be drawn from the scale.
		if (this.scale > grid.getDisappearAtScale()) {
			// Calculate the number of gridlines.
			int verticals = (int) Math.floor(this.maxWidth.getLength() / grid.getSize().getLength());
			int horizontals = (int) Math.floor(this.maxHeight.getLength() / grid.getSize().getLength());

			// Set the state of the graphics object.
			g.setStroke(new BasicStroke(grid.getThickness()));
			g.setFont(new Font("Arial", Font.PLAIN, 75));

			// Draw all the gridlines.
			for (int x = 0; x < verticals && x < 1000; x++) {
				int xPos = x * Diagram.coord(grid.getSize());
				g.setColor(grid.getColor());
				g.drawLine(xPos, 0, xPos, Diagram.coord(this.maxHeight));

				// Draw labels on the top if required.
				if (grid.isDisplayLabels() && x % grid.getLabelInterval() == 0) {
					g.setColor(Color.DARK_GRAY);
					g.drawString(grid.getSize().clone().scale((float) x).toString(), xPos, 0);
				}
			}

			for (int y = 0; y < horizontals & y < 1000; y++) {
				int yPos = y * Diagram.coord(grid.getSize());
				g.setColor(grid.getColor());
				g.drawLine(0, yPos, Diagram.coord(this.maxWidth), yPos);

				// Draw labels on the side if required.
				if (grid.isDisplayLabels() && y % grid.getLabelInterval() == 0) {
					g.setColor(Color.DARK_GRAY);
					g.drawString(grid.getSize().clone().scale((float) y).toString(), 0, yPos);
				}
			}
		}
	}

	public void drawArrow(Graphics2D g, Arrow a) {
		// Draw the line for the arrow.
		Coordinate destination = a.calculateDestination();
		g.setColor(a.getColor());
		g.setStroke(new BasicStroke(a.getThickness()));
		g.drawLine(coord(a.getLocation().getX()), coord(a.getLocation().getY()), coord(destination.getX()),
				coord(destination.getY()));

		// If the label must be shown, draw a white rectangle behind it.
		if (a.isLabelShown()) {
			// Generate a label with the arrow's length.
			Label l;
			l = new Label(a.getLength().toString(), a.getLocation().getMidpoint(destination), Alignment.CENTER,
					Alignment.CENTER, labelFont, a.getColor());

			// Draw the label.
			this.drawLabel(g, l);
		}

		// Draw the head on the destination.
		int[] xPoints = new int[] { coord(destination.getX()), 0, 0 };
		int[] yPoints = new int[] { coord(destination.getY()), 0, 0 };

		int arrowSize = 48;

		switch (a.getDirection()) {
		case UP:
			xPoints[1] = coord(destination.getX()) - arrowSize / 2;
			yPoints[1] = coord(destination.getY()) + arrowSize;

			xPoints[2] = coord(destination.getX()) + arrowSize / 2;
			yPoints[2] = coord(destination.getY()) + arrowSize;
			break;
		case DOWN:
			xPoints[1] = coord(destination.getX()) - arrowSize / 2;
			yPoints[1] = coord(destination.getY()) - arrowSize;

			xPoints[2] = coord(destination.getX()) + arrowSize / 2;
			yPoints[2] = coord(destination.getY()) - arrowSize;
			break;
		case LEFT:
			xPoints[1] = coord(destination.getX()) + arrowSize;
			yPoints[1] = coord(destination.getY()) - arrowSize / 2;

			xPoints[2] = coord(destination.getX()) + arrowSize;
			yPoints[2] = coord(destination.getY()) + arrowSize / 2;
			break;
		case RIGHT:
		default:
			xPoints[1] = coord(destination.getX()) - arrowSize;
			yPoints[1] = coord(destination.getY()) - arrowSize / 2;

			xPoints[2] = coord(destination.getX()) - arrowSize;
			yPoints[2] = coord(destination.getY()) + arrowSize / 2;
			break;
		}

		// Draw the arrow based on the calculated triangle.
		g.setColor(a.getColor());
		g.fillPolygon(xPoints, yPoints, 3);

		// If the arrow is two-headed, draw one on the location point.
		if (a.isTwoHeaded()) {
			xPoints[0] = coord(a.getLocation().getX());
			yPoints[0] = coord(a.getLocation().getY());

			// Flip the arrow's head and translate it to the other end.
			switch (a.getDirection()) {
			case DOWN:
				xPoints[1] = coord(a.getLocation().getX()) - arrowSize / 2;
				yPoints[1] = coord(a.getLocation().getY()) + arrowSize;

				xPoints[2] = coord(a.getLocation().getX()) + arrowSize / 2;
				yPoints[2] = coord(a.getLocation().getY()) + arrowSize;
				break;
			case UP:
				xPoints[1] = coord(a.getLocation().getX()) - arrowSize / 2;
				yPoints[1] = coord(a.getLocation().getY()) - arrowSize;

				xPoints[2] = coord(a.getLocation().getX()) + arrowSize / 2;
				yPoints[2] = coord(a.getLocation().getY()) - arrowSize;
				break;
			case RIGHT:
				xPoints[1] = coord(a.getLocation().getX()) + arrowSize;
				yPoints[1] = coord(a.getLocation().getY()) - arrowSize / 2;

				xPoints[2] = coord(a.getLocation().getX()) + arrowSize;
				yPoints[2] = coord(a.getLocation().getY()) + arrowSize / 2;
				break;
			case LEFT:
			default:
				xPoints[1] = coord(a.getLocation().getX()) - arrowSize;
				yPoints[1] = coord(a.getLocation().getY()) - arrowSize / 2;

				xPoints[2] = coord(a.getLocation().getX()) - arrowSize;
				yPoints[2] = coord(a.getLocation().getY()) + arrowSize / 2;
				break;
			}

			// Draw the other head of the arrow.
			g.fillPolygon(xPoints, yPoints, 3);
		}
	}

	public void drawLabel(Graphics2D g, Label l) {
		LabelSize size = l.calculateSize();
		String[] lines = l.toLines();
		int x = Diagram.coord(l.getOrigin().getX());
		int y = Diagram.coord(l.getOrigin().getY());

		// Calculate the location of the bottom-left corner based on the vertical
		// alignment.
		if (l.getAlignmentY() == Alignment.CENTER) {
			y -= size.getHeight() / 2;
		} else if (l.getAlignmentY() == Alignment.RIGHT_OR_BOTTOM) {
			y -= size.getHeight();
		}

		// Set the font and color
		g.setFont(l.getFont());
		g.setColor(l.getColor());

		// Draw the lines of the string.
		for (int i = 0; i < lines.length; i++) {
			int lx = x;
			int ly = y + (size.getLineHeight() + size.getLineSpacing()) * i;

			// Calculate the x position based on the horizontal alignment.
			if (l.getAlignmentX() == Alignment.CENTER) {
				lx -= size.getWidthOfLine(i) / 2;
			} else if (l.getAlignmentX() == Alignment.RIGHT_OR_BOTTOM) {
				lx -= size.getWidthOfLine(i);
			}

			// Draw the string
			g.drawString(lines[i], lx, ly + size.getLineHeight());
		}
	}

	private void drawSample(Graphics2D g) {
		// Draw ramp.
		Ramp r = new Ramp(new Dimension(36), new Coordinate(new Dimension(18, 0), new Dimension(8, 0)));
		r.addSection(Direction.DOWN, new Dimension(0), new Dimension(0), new Dimension(40), new Dimension(24, 0),
				new Dimension(6, 0), new Dimension(6, 0));
		r.addSection(Direction.RIGHT, new Dimension(4), new Dimension(-4), new Dimension(40), new Dimension(12, 0),
				new Dimension(4, 0), new Dimension(4, 0));

		this.drawRampTop(g, r);

		/*
		 * // Test dimension parsing. try {
		 * System.out.println(Dimension.inchesFromString("1 in")); } catch (Exception e)
		 * { e.printStackTrace(); }
		 * 
		 * // Test arrows. Arrow a = new Arrow(new Coordinate(new Dimension(0), new
		 * Dimension(0)), Direction.RIGHT, new Dimension(12, 0), 2, Color.black, true,
		 * true); this.drawArrow(g, a);
		 * 
		 * // -- Sample diagram --
		 * 
		 * // Title Font titleFont = new Font("Arial", Font.BOLD, 100); Label title =
		 * new Label("Hickory Hills Mobile Home Park #63", new Coordinate(new
		 * Dimension(12), new Dimension(12)), titleFont); Label subtitle = new
		 * Label("36\" Rise Sidewalk to Landing\nAll Treated Lumber\n12:1 Slope Ratio (or more)"
		 * , new Coordinate(new Dimension(12), new Dimension(24)), labelFont);
		 * this.drawLabel(g, title); this.drawLabel(g, subtitle);
		 * 
		 * // Old Title // g.setFont(new Font("Arial", Font.BOLD, 100)); //
		 * g.drawString("Hickory Hills Mobile Home Park #63", new Dimension(1, //
		 * 0).toFractionalParts(8), // new Dimension(1, 0).toFractionalParts(8)); //
		 * g.setFont(new Font("Arial", Font.PLAIN, 100)); //
		 * g.drawString("36\" Rise Sidewalk to Landing", new Dimension(1, //
		 * 0).toFractionalParts(8), // new Dimension(2, 0).toFractionalParts(8)); //
		 * g.drawString("All Treated Lumber", new Dimension(1, 0).toFractionalParts(8),
		 * // new Dimension(3, 0).toFractionalParts(8)); //
		 * g.drawString("12:1 Slope Ratio (or more)", new Dimension(1, //
		 * 0).toFractionalParts(8), // new Dimension(4, 0).toFractionalParts(8));
		 * 
		 * g.setFont(new Font("Arial", Font.PLAIN, 100));
		 * 
		 * // House g.drawRect(Diagram.coord(new Dimension(24, 0)), Diagram.coord(new
		 * Dimension(0)), Diagram.coord(new Dimension(24, 0)), Diagram.coord(new
		 * Dimension(32, 0))); Label houseLabel = new Label("House", new Coordinate(new
		 * Dimension(27, 0), new Dimension(15, 0)), Alignment.CENTER, Alignment.CENTER,
		 * labelFont, Color.BLACK); this.drawLabel(g, houseLabel);
		 * 
		 * // Landing g.drawRect(Diagram.coord(new Dimension(18, 0)), Diagram.coord(new
		 * Dimension(8, 0)), Diagram.coord(new Dimension(6, 0)), Diagram.coord(new
		 * Dimension(6, 0))); Label landingLabel = new Label("6'x6' Landing", new
		 * Coordinate(new Dimension(36).add(new Dimension(18 * 12)), new
		 * Dimension(36).add(new Dimension(8 * 12))), Alignment.CENTER,
		 * Alignment.CENTER, labelFont, Color.BLACK); this.drawLabel(g, landingLabel);
		 * 
		 * // First ramp section g.drawRect(Diagram.coord(new Dimension(19, 6)),
		 * Diagram.coord(new Dimension(14, 0)), Diagram.coord(new Dimension(40)),
		 * Diagram.coord(new Dimension(20, 0)));
		 * 
		 * // Turnaround g.drawRect(Diagram.coord(new Dimension(19, 6)),
		 * Diagram.coord(new Dimension(34, 0)), Diagram.coord(new Dimension(4, 0)),
		 * Diagram.coord(new Dimension(4, 0)));
		 * 
		 * // Second ramp section g.drawRect(Diagram.coord(new Dimension(19, 6).add(new
		 * Dimension(4, 0))), Diagram.coord(new Dimension(35, 0).add(new
		 * Dimension(4).clone().negate())), Diagram.coord(new Dimension(12, 0)),
		 * Diagram.coord(new Dimension(40)));
		 * 
		 * // Driveway g.drawRect(Diagram.coord(new Dimension(31, 6).add(new
		 * Dimension(4, 0))), Diagram.coord(new Dimension(32, 0)), Diagram.coord(new
		 * Dimension(40, 0)), Diagram.coord(new Dimension(10, 0))); Label drivewayLabel
		 * = new Label("Driveway", new Coordinate(new Dimension(37, 0), new
		 * Dimension(37, 0)), Alignment.CENTER, Alignment.CENTER, labelFont,
		 * Color.BLACK); this.drawLabel(g, drivewayLabel);
		 */
	}

	public static int coord(Dimension d) {
		return d.toFractionalParts(8);
	}

}
