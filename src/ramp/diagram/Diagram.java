package ramp.diagram;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JFrame;

import ramp.diagram.Label.Alignment;
import ramp.diagram.Label.LabelSize;
import ramp.geometry.Dimension;
import ramp.geometry.DimensionOld;
import ramp.geometry.DimensionUtil;
import ramp.geometry.DimensionVector;
import ramp.geometry.DimensionVector.VectorMismatchException;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Diagram extends Component {
	// Constants
	@Deprecated
	private static final double LINE_SPACING = 0.25; // Ratio to the text height to leave between lines

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

		// Draw the diagram according to data.
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));

		this.drawSample(g);
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
				int xPos = x * Diagram.toPixels(grid.getSize());
				g.setColor(grid.getColor());
				g.drawLine(xPos, 0, xPos, Diagram.toPixels(this.maxHeight));

				// Draw labels on the top if required.
				if (grid.isDisplayLabels() && x % grid.getLabelInterval() == 0) {
					g.setColor(Color.DARK_GRAY);
					g.drawString(grid.getSize().getScaled((float) x).toString(), xPos, 0);
				}
			}

			for (int y = 0; y < horizontals & y < 1000; y++) {
				int yPos = y * Diagram.toPixels(grid.getSize());
				g.setColor(grid.getColor());
				g.drawLine(0, yPos, Diagram.toPixels(this.maxWidth), yPos);

				// Draw labels on the side if required.
				if (grid.isDisplayLabels() && y % grid.getLabelInterval() == 0) {
					g.setColor(Color.DARK_GRAY);
					g.drawString(grid.getSize().getScaled((float) y).toString(), 0, yPos);
				}
			}
		}
	}

	public void drawArrow(Graphics2D g, Arrow a) {
		
	}

	public void drawLabel(Graphics2D g, Label l) {
		LabelSize size = l.calculateSize();
		String[] lines = l.toLines();
		int x = Diagram.toPixels(l.getOrigin().getX());
		int y = Diagram.toPixels(l.getOrigin().getY());

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

	@Deprecated
	public void drawStrings_(Graphics2D g, String s, Font font, DimensionVector location, boolean center) {
		// Break the string into lines, if necessary.
		String[] lines = s.split("\n");

		// Measure the strings.
		int[] size = Diagram.getStringSize(font, lines);

		int x = Diagram.toPixels(location.getX());
		int y = Diagram.toPixels(location.getY());

		// Iterate through the strings and draw them.
		g.setFont(font);

		for (int l = 0; l < lines.length; l++) {
			int lineX = x;
			int lineY = y + size[2] * l;
			if (center) {
				int lineWidth = Diagram.getStringSize(font, lines[l])[1];
				lineX += size[1] / 2 - lineWidth / 2;
			}
			g.drawString(lines[l], lineX, lineY);
		}
	}

	@Deprecated
	public void centerStrings_(Graphics2D g, String s, Font font, DimensionVector center, DimensionVector offset) {
		int[] size = Diagram.getStringSize(font, s);
		DimensionVector halfSize = new DimensionVector((double) size[2] / 2.0, (double) size[1] / 2.0);
		try {
			this.drawStrings_(g, s, font, DimensionUtil.getVectorSum(center, halfSize, offset), true);
		} catch (VectorMismatchException e) {
			e.printStackTrace();
		}
	}

	public void drawLanding(Graphics2D g, Landing l) {
		// Set state
		g.setStroke(new BasicStroke(2));

		// Draw rectangle
		g.drawRect(toPixels(l.getLocation().getX()), toPixels(l.getLocation().getY()), toPixels(l.getSize().getX()),
				toPixels(l.getSize().getY()));
	}

	public void drawRamp(Graphics2D g, Ramp r) {
		// Set state
		g.setStroke(new BasicStroke(2));

		// Find location and size
		DimensionVector loc = r.getTopLeft();
		DimensionVector size = r.getSize();

		// Draw rectangle
		g.drawRect(toPixels(loc.getX()), toPixels(loc.getY()), toPixels(size.getX()), toPixels(size.getY()));

		// Draw posts
		for (Post p : r.generatePosts()) {
			this.drawPost(g, p);
		}
	}

	public void drawPost(Graphics2D g, Post p) {
		// Draw filled rectangle
		g.fillRect(toPixels(p.getLocation().getX()), toPixels(p.getLocation().getY()), toPixels(p.getSize()),
				toPixels(p.getSize()));
	}

	@Deprecated
	private void drawCenteredString(Graphics2D g, String s, DimensionVector location, Dimension offsetX,
			Dimension offsetY) {
		try {
			this.drawStrings_(g, s, this.labelFont, location.getSum(new DimensionVector(offsetX, offsetY)), true);
		} catch (VectorMismatchException e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	private void drawCenteredString(Graphics2D g, String s, DimensionVector location) {
		this.drawCenteredString(g, s, location, new Dimension(0), new Dimension(0));
	}

	private void drawSample(Graphics2D g) {
		// Test dimension parsing.
		try {
			System.out.println(Dimension.inchesFromString("1 in"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Test ramp post generation
		Ramp r = new Ramp(new DimensionVector(36, 72), new Dimension(18, 0), Direction.DOWN);
		this.drawRamp(g, r);

		// -- Sample diagram --

		// Title
		Font titleFont = new Font("Arial", Font.BOLD, 100);
		Label title = new Label("Hickory Hills Mobile Home Park #63", new DimensionVector(12, 12), titleFont);
		Label subtitle = new Label("36\" Rise Sidewalk to Landing\nAll Treated Lumber\n12:1 Slope Ratio (or more)",
				new DimensionVector(12, 24), labelFont);
		this.drawLabel(g, title);
		this.drawLabel(g, subtitle);

		// Old Title
		// g.setFont(new Font("Arial", Font.BOLD, 100));
		// g.drawString("Hickory Hills Mobile Home Park #63", new Dimension(1,
		// 0).toFractionalParts(8),
		// new Dimension(1, 0).toFractionalParts(8));
		// g.setFont(new Font("Arial", Font.PLAIN, 100));
		// g.drawString("36\" Rise Sidewalk to Landing", new Dimension(1,
		// 0).toFractionalParts(8),
		// new Dimension(2, 0).toFractionalParts(8));
		// g.drawString("All Treated Lumber", new Dimension(1, 0).toFractionalParts(8),
		// new Dimension(3, 0).toFractionalParts(8));
		// g.drawString("12:1 Slope Ratio (or more)", new Dimension(1,
		// 0).toFractionalParts(8),
		// new Dimension(4, 0).toFractionalParts(8));

		g.setFont(new Font("Arial", Font.PLAIN, 100));

		// House
		g.drawRect(Diagram.toPixels(new Dimension(24, 0)), Diagram.toPixels(new Dimension(0)),
				Diagram.toPixels(new Dimension(24, 0)), Diagram.toPixels(new Dimension(32, 0)));
		Label houseLabel = new Label("House", new DimensionVector(new Dimension(27, 0), new Dimension(15, 0)),
				Alignment.CENTER, Alignment.CENTER, labelFont, Color.BLACK);
		this.drawLabel(g, houseLabel);

		// Landing
		g.drawRect(Diagram.toPixels(new Dimension(18, 0)), Diagram.toPixels(new Dimension(8, 0)),
				Diagram.toPixels(new Dimension(6, 0)), Diagram.toPixels(new Dimension(6, 0)));
		Label landingLabel = new Label("6'x6' Landing",
				new DimensionVector(new Dimension(36).add(new Dimension(18 * 12)),
						new Dimension(36).add(new Dimension(8 * 12))),
				Alignment.CENTER, Alignment.CENTER, labelFont, Color.BLACK);
		this.drawLabel(g, landingLabel);

		// First ramp section
		g.drawRect(Diagram.toPixels(new Dimension(19, 6)), Diagram.toPixels(new Dimension(14, 0)),
				Diagram.toPixels(new Dimension(40)), Diagram.toPixels(new Dimension(20, 0)));
		DimensionVector ramp1center = new DimensionVector(new Dimension(19, 6).add(new Dimension(20)),
				new Dimension(14, 0).add(new Dimension(10, 0)));
		// TODO: label rotation and arrows
		this.drawCenteredString(g, "← 40\" →", ramp1center);
		this.drawCenteredString(g, "20'", ramp1center, new Dimension(0), new Dimension(-3, 0));
		this.drawCenteredString(g, "↑", ramp1center, new Dimension(0), new Dimension(-4, 0));
		this.drawCenteredString(g, "↓", ramp1center, new Dimension(0), new Dimension(-2, 0));

		// Turnaround
		g.drawRect(Diagram.toPixels(new Dimension(19, 6)), Diagram.toPixels(new Dimension(34, 0)),
				Diagram.toPixels(new Dimension(4, 0)), Diagram.toPixels(new Dimension(4, 0)));
		this.drawCenteredString(g, "4' x 4'",
				new DimensionVector(new Dimension(19, 6).add(new Dimension(2, 0)), new Dimension(36, 0)));

		// Second ramp section
		g.drawRect(Diagram.toPixels(new Dimension(19, 6).add(new Dimension(4, 0))),
				Diagram.toPixels(new Dimension(35, 0).add(new Dimension(4).getNegation())),
				Diagram.toPixels(new Dimension(12, 0)), Diagram.toPixels(new Dimension(40)));

		DimensionVector ramp2center = new DimensionVector(new Dimension(23, 6).add(new Dimension(6, 0)),
				new Dimension(34, 8).add(new Dimension(20)));
		this.drawCenteredString(g, "← 12' →", ramp2center);
		this.drawCenteredString(g, "40\"", ramp2center, new Dimension(-3, 0), new Dimension(0));
		this.drawCenteredString(g, "↑", ramp2center, new Dimension(-3, 0), new Dimension(-1, 0));
		this.drawCenteredString(g, "↓", ramp2center, new Dimension(-3, 0), new Dimension(1, 0));

		// Driveway
		g.drawRect(Diagram.toPixels(new Dimension(31, 6).add(new Dimension(4, 0))),
				Diagram.toPixels(new Dimension(32, 0)), Diagram.toPixels(new Dimension(40, 0)),
				Diagram.toPixels(new Dimension(10, 0)));
		Label drivewayLabel = new Label("Driveway", new DimensionVector(new Dimension(37, 0), new Dimension(37, 0)),
				Alignment.CENTER, Alignment.CENTER, labelFont, Color.BLACK);
		this.drawLabel(g, drivewayLabel);
	}

	public static int toPixels(Dimension d) {
		return d.toFractionalParts(8);
	}

	@Deprecated
	public static int[] getStringSize(Font font, String... ss) {
		FontRenderContext context = new FontRenderContext(null, true, true);
		double width = 0.0;
		double height = 0.0;
		double lineHeight = 0.0;

		for (int l = 0; l < ss.length; l++) {
			Rectangle2D lineR = font.getStringBounds(ss[l], context);

			// Compare the width
			int iWidth = (int) Math.round(lineR.getWidth());
			if (iWidth > width) {
				width = iWidth;
			}

			// Increase the height
			lineHeight = lineR.getHeight() * (1 + Diagram.LINE_SPACING);
			height += lineHeight;
		}

		return new int[] { (int) Math.round(width), (int) Math.round(height), (int) Math.round(lineHeight) };
	}
}
