package ramp;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JFrame;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Diagram extends Component {
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

	// Common dimensions
	private Dimension rampWidth = new Dimension(40.0f);
	private Dimension postSize = new Dimension(4.0f);

	// Input
	private GUIData guiData;

	public Diagram(GUIData guiData) {
		// Store Input
		this.guiData = guiData;

		// Transformation
		this.scale = 1.0;
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
		Grid inGrid = Grid.createInchesGrid();

		this.addGrid(ftGrid);
		this.addGrid(inGrid);

		// Page Specifications
		this.maxWidth = new Dimension(50, 0, 0);
		this.maxHeight = new Dimension(50, 0, 0);

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

	public Box getStringBox(String str, Font font, DimensionPair center) {
		// Calculate the size of the string.
		FontRenderContext context = new FontRenderContext(null, true, true);
		Rectangle2D r = font.getStringBounds(str, context);

		// Convert to a dimension pair representing size.
		Box b = new Box(new Dimension(0), new Dimension(0), Dimension.newFromEighths((int) Math.round(r.getWidth())),
				Dimension.newFromEighths((int) Math.round(r.getHeight())));

		b.setCenter(center);

		return b;
	}

	public void drawCenteredString(Graphics2D g, String s, Box b, Dimension xOffset, Dimension yOffset) {
		// Calculate the size of the string.
		Font font = new Font("Arial", Font.PLAIN, 100);
		FontRenderContext context = new FontRenderContext(null, true, true);
		Rectangle2D r = font.getStringBounds(s, context);

		// Calculate the text location.
		DimensionPair center = b.getCenter();
		double strX = center.getX().toEighths() - Math.round(r.getWidth() / 2) - r.getX() + xOffset.toEighths();
		double strY = center.getY().toEighths() - Math.round(r.getHeight() / 2) - r.getY() + yOffset.toEighths();

		// Draw the text.
		g.setFont(font);
		g.drawString(s, (int) Math.round(strX), (int) Math.round(strY));
	}

	public void drawCenteredString(Graphics2D g, String s, Box b) {
		this.drawCenteredString(g, s, b, new Dimension(0), new Dimension(0));
	}

	/**
	 * Draws an arrow, but only if the line is vertical or horizontal. This is
	 * useful for drawing dimensions.
	 */
	public void drawArrow(Graphics2D g, DimensionPair location, DimensionPair size) {
		// Set parameters
		g.setStroke(new BasicStroke(1));
		g.setColor(Color.BLACK);

		// Draw line, but only if the orientation is acceptable.
		if (size.getOrientation().orthogonal()) {
			g.drawLine(location.getX().toEighths(), location.getY().toEighths(), location.getX().add(size.getX()).toEighths(),
					location.getY().add(size.getY()).toEighths());
		}

		// Draw the arrow cap.
		if (size.getOrientation() == Orientation.HORIZONTAL) {
			// TODO
		} else if (size.getOrientation() == Orientation.VERTICAL) {

		}
	}

	/**
	 * Draws arrows around a label, which will be centered within the given box.
	 * 
	 * @param g
	 *            2D Graphics object.
	 * @param label
	 *            The dimension label.
	 * @param b
	 *            The box to measure.
	 * @param o
	 *            Orientation to measure. Arrows will be drawn along this axis.
	 */
	public void drawDimension(Graphics2D g, Box b, Orientation o, DimensionPair offset) {
		String label = b.getSize(o).toString();

		// Draw the dimension string.
		this.drawCenteredString(g, label, b, offset.getX(), offset.getY());

		// Draw the arrows.
		Box labelBox = this.getStringBox(label, new Font("Arial", Font.PLAIN, 100), b.getCenter());

		if (o == Orientation.HORIZONTAL) {
			// Left arrow
			this.drawArrow(g, labelBox.getLeftCenter(), b.getLeftCenter().subtract(labelBox.getLeftCenter()));

			// Right arrow
			this.drawArrow(g, labelBox.getRightCenter(), b.getRightCenter().subtract(labelBox.getRightCenter()));
		} else if (o == Orientation.VERTICAL) {
			// Top arrow

			// Bottom arrow
		}
	}

	public void drawDimension(Graphics2D g, Box b, Orientation o) {
		this.drawDimension(g, b, o, new DimensionPair(new Dimension(0), new Dimension(0)));
	}

	public void drawBox(Graphics2D g, Box b, Color color, int thickness) {
		g.setStroke(new BasicStroke(thickness));
		g.setColor(color);
		g.drawRect(b.getX().toEighths(), b.getY().toEighths(), b.getWidth().toEighths(), b.getHeight().toEighths());
	}

	public void drawPost(Graphics2D g, Dimension x, Dimension y) {
		g.fillRect(x.toEighths(), y.toEighths(), this.postSize.toEighths(), this.postSize.toEighths());
	}

	// Vertical = 0
	// Horizontal = 1
	public void drawRampSection(Graphics2D g, Dimension x, Dimension y, Dimension length, int orientation) {
		Box b = new Box(new DimensionPair(x, y), this.rampWidth, length);
		DimensionPair widthOffset = new DimensionPair(new Dimension(0), new Dimension(-3, 0, 0));

		// Flip'em if the orientation is horizontal.
		if (orientation == 1) {
			b.transpose();
			widthOffset.transpose();
		}

		// Draw the ramp section and its labels.
		this.drawBox(g, b, Color.BLACK, 2);
		this.drawDimension(g, b, b.getLongestSide());
		this.drawDimension(g, b, b.getShortestSide(), widthOffset);
	}

	public void drawLanding(Graphics2D g, Box b) {
		this.drawBox(g, b, Color.BLACK, 2);
		this.drawCenteredString(g, String.format("%s x %s Landing", b.getWidth().toString(), b.getHeight().toString()),
				b);
	}

	public void drawTurnaround(Graphics2D g, Box b) {
		this.drawBox(g, b, Color.BLACK, 2);
		this.drawCenteredString(g, String.format("%s x %s", b.getWidth().toString(), b.getHeight().toString()), b);
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
			// Determine whether the grid should be drawn from the scale.
			if (this.scale > grid.getDisappearAtScale()) {
				// Calculate the number of gridlines.
				int verticals = (int) Math.floor(this.maxWidth.divideBy(grid.getSize()));
				int horizontals = (int) Math.floor(this.maxHeight.divideBy(grid.getSize()));

				// Set the state of the graphics object.
				g.setStroke(new BasicStroke(grid.getThickness()));
				g.setFont(new Font("Arial", Font.PLAIN, 20));

				// Draw all the gridlines.
				for (int x = 0; x < verticals; x++) {
					int xPos = x * grid.getSize().toEighths();
					g.setColor(grid.getColor());
					g.drawLine(xPos, 0, xPos, this.maxHeight.toEighths());

					// Draw labels on the top if required.
					if (grid.isDisplayLabels() && x % grid.getLabelInterval() == 0) {
						g.setColor(Color.DARK_GRAY);
						g.drawString(grid.getSize().scale((float) x).toString(), xPos, 0);
					}
				}

				for (int y = 0; y < horizontals; y++) {
					int yPos = y * grid.getSize().toEighths();
					g.setColor(grid.getColor());
					g.drawLine(0, yPos, this.maxWidth.toEighths(), yPos);

					// Draw labels on the side if required.
					if (grid.isDisplayLabels() && y % grid.getLabelInterval() == 0) {
						g.setColor(Color.DARK_GRAY);
						g.drawString(grid.getSize().scale((float) y).toString(), 0, yPos);
					}
				}
			}
		}

		// Draw the diagram according to data.
		g.setColor(Color.BLACK);

		// -- Sample diagram --

		// House
		g.drawRect(new Dimension(24, 0, 0).toEighths(), new Dimension(0).toEighths(),
				new Dimension(24, 0, 0).toEighths(), new Dimension(32, 0, 0).toEighths());
		// Landing
		this.drawLanding(g, new Box(new Dimension(18, 0, 0), new Dimension(8, 0, 0), new Dimension(6, 0, 0),
				new Dimension(6, 0, 0)));
		// First ramp section
		this.drawRampSection(g, new Dimension(19, 6, 0), new Dimension(14, 0, 0), new Dimension(20, 0, 0), 0);
		// Turnaround
		this.drawTurnaround(g, new Box(new Dimension(19, 6, 0), new Dimension(34, 0, 0), new Dimension(4, 0, 0),
				new Dimension(4, 0, 0)));
		// Second ramp section
		this.drawRampSection(g, new Dimension(19, 6, 0).add(new Dimension(4, 0, 0)),
				new Dimension(35, 0, 0).subtract(new Dimension(4)), new Dimension(12, 0, 0), 1);
		// Driveway
		g.drawRect(new Dimension(31, 6, 0).add(new Dimension(4, 0, 0)).toEighths(), new Dimension(32, 0, 0).toEighths(),
				new Dimension(3, 0, 0).toEighths(), new Dimension(10, 0, 0).toEighths());
	}
}
