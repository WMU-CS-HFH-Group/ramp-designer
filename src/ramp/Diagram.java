package ramp;

import java.awt.*;
import java.awt.event.*;
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
		
		frame = new JFrame("Graphics Spike");
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
	
	public void drawPost(Graphics2D g, Dimension x, Dimension y) {
		g.fillRect(x.toEighths(), y.toEighths(), this.postSize.toEighths(), this.postSize.toEighths());
	}
	
	// Vertical = 0
	// Horizontal = 1
	public void drawRampSection(Graphics2D g, Dimension x, Dimension y, Dimension length, int orientation) {
		// Flip'em if the orientation is horizontal.
		if (orientation == 0) {
			//g.drawRect(x, y, length, this.rampWidth);
			g.drawRect(x.toEighths(), y.toEighths(), this.rampWidth.toEighths(), length.toEighths());
		} else {
			//g.drawRect(x, y, this.rampWidth, length);
			g.drawRect(x.toEighths(), y.toEighths(), length.toEighths(), this.rampWidth.toEighths());
		}
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
		
		g.setColor(Color.BLACK);
		this.drawPost(g, new Dimension(20), new Dimension(20));
		this.drawRampSection(g, new Dimension(24), new Dimension(20), new Dimension(120), 0); // 10ft
	}
}
