package ramp.diagram;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import ramp.geometry.DimensionVector;

/**
 * A data storage class for a label's attributes. This information will be used
 * to draw the label on a diagram. It may be aligned in any direction, any
 * color, and any font. It may have multiple lines delimited by a newline
 * character.
 * 
 */
public class Label {
	public static final String LINE_DELIM = "\n";
	private static final double LINE_SPACING = 0.15; // Ratio to the text height to leave between lines

	private String string;
	private DimensionVector origin;
	private Alignment alignmentX, alignmentY;
	private Font font;
	private Color color;

	public Label(String string, DimensionVector origin, Alignment alignmentX, Alignment alignmentY, Font font,
			Color color) {
		this.string = string;
		this.origin = origin;
		this.alignmentX = alignmentX;
		this.alignmentY = alignmentY;
		this.font = font;
		this.color = color;
	}

	/**
	 * Constructor with sensible defaults for alignment and color. The default
	 * alignment is top-left, and the default color is black.
	 * 
	 * @param string
	 *            The string to display. This may be multi-line.
	 * @param topLeft
	 *            The location of the top-left corner of the string.
	 * @param font
	 *            The font to render the string with.
	 */
	public Label(String string, DimensionVector topLeft, Font font) {
		this(string, topLeft, Alignment.LEFT_OR_TOP, Alignment.LEFT_OR_TOP, font, Color.BLACK);
	}

	/**
	 * Constructor for a non-existent/null label. This will not be rendered unless
	 * it is given other parameters.
	 */
	public Label() {
		this("", new DimensionVector(0, 0), new Font("Arial", Font.PLAIN, 12));
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public DimensionVector getOrigin() {
		return origin;
	}

	public void setOrigin(DimensionVector origin) {
		this.origin = origin;
	}

	public Alignment getAlignmentX() {
		return alignmentX;
	}

	public void setAlignmentX(Alignment alignmentX) {
		this.alignmentX = alignmentX;
	}

	public Alignment getAlignmentY() {
		return alignmentY;
	}

	public void setAlignmentY(Alignment alignmentY) {
		this.alignmentY = alignmentY;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String[] toLines() {
		return this.string.split(Label.LINE_DELIM);
	}

	public LabelSize calculateSize() {
		FontRenderContext context = new FontRenderContext(null, true, true);

		double width = 0.0;
		double height = 0.0;
		double lineHeight = 0.0;
		double lineSpacing = 0.0;

		String[] lines = this.toLines();
		int[] lineWidths = new int[lines.length];

		if (lines.length > 0) {
			lineHeight = font.getStringBounds(lines[0], context).getHeight();
			lineSpacing = lineHeight * Label.LINE_SPACING;
		}

		for (int l = 0; l < lines.length; l++) {
			Rectangle2D lineR = font.getStringBounds(lines[l], context);

			// Compare the width
			if (lineR.getWidth() > width) {
				width = lineR.getWidth();
			}
			System.out.println("width l: " + lineR.getWidth());
			System.out.println("width: " + width);

			// Increase the height.
			height += lineHeight;

			// If this is not the last, add extra spacing.
			if (l < lines.length - 1) {
				height += lineSpacing;
			}

			// Store the width of the line
			lineWidths[l] = (int) Math.round(lineR.getWidth());
		}

		return new LabelSize((int) Math.round(width), (int) Math.round(height), (int) Math.round(lineHeight),
				(int) Math.round(lineSpacing), lineWidths);
	}

	public enum Alignment {
		LEFT_OR_TOP, CENTER, RIGHT_OR_BOTTOM,
	}

	public class LabelSize {
		private int width;
		private int height;
		private int lineHeight;
		private int lineSpacing;
		private int[] lineWidths;

		public LabelSize(int width, int height, int lineHeight, int lineSpacing, int[] lineWidths) {
			this.width = width;
			this.height = height;
			this.lineHeight = lineHeight;
			this.lineSpacing = lineSpacing;
			this.lineWidths = lineWidths;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public int getLineHeight() {
			return lineHeight;
		}

		public int getLineSpacing() {
			return lineSpacing;
		}

		public int[] getLineWidths() {
			return lineWidths;
		}

		public int getWidthOfLine(int i) {
			return this.lineWidths[i];
		}

		public int getLines() {
			return lineWidths.length;
		}
	}
}
