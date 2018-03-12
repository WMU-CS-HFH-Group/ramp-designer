package ramp.diagram;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JList;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import ramp.diagram.Label.Alignment;
import ramp.geometry.Coordinate;
import ramp.geometry.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;

public class DiagramFrame extends JFrame {

	private JPanel contentPane;
	private Diagram diagram;

	private CustomItem currentItem;
	private JList<CustomItem> listCustomItems;
	private DefaultListModel<CustomItem> listCustomItemsModel;
	private JSplitPane splitPane;
	private JTextField textPostX;
	private JTextField textPostY;
	private JTextField textPostSize;
	private JTextField textBoxX;
	private JTextField textBoxY;
	private JTextField textBoxWidth;
	private JTextField textBoxHeight;
	private JTextField textBoxLabel;
	private JTabbedPane tabbedPane;
	private JTextField textLabelX;
	private JTextField textLabelY;
	private JTextField textLabelArrowX;
	private JTextField textLabelArrowY;
	private JTextField textLabelText;

	/**
	 * Create the frame.
	 */
	public DiagramFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);

		JButton btnNewprintButton = new JButton("Print");
		btnNewprintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(diagram);
				boolean ok = job.printDialog();
				if (ok) {
					try {
						job.print();
					} catch (PrinterException ex) {

					}
				}
			}
		});
		toolBar.add(btnNewprintButton);

		JButton btnSaveImage = new JButton("Save Image");
		btnSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int option = chooser.showSaveDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					BufferedImage image = diagram.generateImage();
					try {
						ImageIO.write(image, "png", chooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Could not save image.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		toolBar.add(btnSaveImage);

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.EAST);

		listCustomItemsModel = new DefaultListModel<CustomItem>();
		listCustomItems = new JList<CustomItem>(listCustomItemsModel);
		listCustomItems.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				CustomItem item = listCustomItemsModel.getElementAt(listCustomItems.getSelectedIndex());
				switch (item.getType()) {
				case "Post":
					CustomPost post = (CustomPost) item;
					// Switch to post tab
					tabbedPane.setSelectedIndex(0);

					// Change the data in the post editor.
					textPostX.setText(post.getLocation().getX().toString());
					textPostY.setText(post.getLocation().getY().toString());
					textPostSize.setText(post.getSize().toString());
					currentItem = post;
					break;
				case "Box":
					CustomBox box = (CustomBox) item;
					// Switch to box tab
					tabbedPane.setSelectedIndex(1);

					// Change the data in the box editor.
					textBoxX.setText(box.getLocation().getX().toString());
					textBoxY.setText(box.getLocation().getY().toString());
					textBoxWidth.setText(box.getWidth().toString());
					textBoxHeight.setText(box.getHeight().toString());
					textBoxLabel.setText(box.getLabel().getString());
					currentItem = box;
					break;
				case "Label":
					CustomLabel label = (CustomLabel) item;

					// Switch to label tab
					tabbedPane.setSelectedIndex(2);

					// Change data in the editor.
					textLabelX.setText(label.getLocation().getX().toString());
					textLabelY.setText(label.getLocation().getY().toString());
					textLabelArrowX.setText(label.getArrowLocation().getX().toString());
					textLabelArrowY.setText(label.getArrowLocation().getY().toString());
					textLabelText.setText(label.getLabel().getString());
					currentItem = label;
					break;
				case "Text":
					break;
				}
			}
		});
		splitPane.setRightComponent(listCustomItems);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setLeftComponent(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Post", null, panel_1, null);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JLabel label = new JLabel("x");
		panel_1.add(label);

		textPostX = new JTextField();
		panel_1.add(textPostX);
		textPostX.setColumns(10);

		JLabel label_1 = new JLabel("y");
		panel_1.add(label_1);

		textPostY = new JTextField();
		panel_1.add(textPostY);
		textPostY.setColumns(10);

		JLabel lblSize = new JLabel("Size");
		panel_1.add(lblSize);

		textPostSize = new JTextField();
		panel_1.add(textPostSize);
		textPostSize.setColumns(10);

		JButton btnNewPost = new JButton("New");
		panel_1.add(btnNewPost);

		JButton btnUpdatePost = new JButton("Update");
		btnUpdatePost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Update the selected post.
				if (currentItem.getType() == "Post") {
					CustomPost post = (CustomPost) currentItem;
					post.getLocation().setX(new Dimension(textPostX.getText()));
					post.getLocation().setY(new Dimension(textPostY.getText()));
					post.setSize(new Dimension(textPostSize.getText()));
					diagram.revalidate();
					diagram.repaint();
				}
			}
		});
		panel_1.add(btnUpdatePost);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Box", null, panel, null);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel lblX = new JLabel("x");
		panel.add(lblX);

		textBoxX = new JTextField();
		panel.add(textBoxX);
		textBoxX.setColumns(10);

		JLabel lblY = new JLabel("y");
		panel.add(lblY);

		textBoxY = new JTextField();
		panel.add(textBoxY);
		textBoxY.setColumns(10);

		JLabel lblWidth = new JLabel("width");
		panel.add(lblWidth);

		textBoxWidth = new JTextField();
		panel.add(textBoxWidth);
		textBoxWidth.setColumns(10);

		JLabel lblHeight = new JLabel("height");
		panel.add(lblHeight);

		textBoxHeight = new JTextField();
		panel.add(textBoxHeight);
		textBoxHeight.setColumns(10);

		JLabel lblLabel = new JLabel("Label");
		panel.add(lblLabel);

		textBoxLabel = new JTextField();
		panel.add(textBoxLabel);
		textBoxLabel.setColumns(10);

		JButton btnNewBox = new JButton("New");
		btnNewBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add a new custom box.
				CustomBox newBox = new CustomBox(new Coordinate(new Dimension(0), new Dimension(0)),
						new Dimension(3, 0), new Dimension(3, 0),
						new Label("", Alignment.CENTER, Alignment.CENTER, diagram.getLabelFont(), Color.BLACK));
				listCustomItemsModel.addElement(newBox);
				diagram.revalidate();
				diagram.repaint();
			}
		});
		panel.add(btnNewBox);

		JButton btnUpdateBox = new JButton("Update");
		btnUpdateBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Update the current box.
				if (currentItem.getType() == "Box") {
					CustomBox box = (CustomBox) currentItem;
					box.getLocation().setX(new Dimension(textBoxX.getText()));
					box.getLocation().setY(new Dimension(textBoxY.getText()));
					box.setWidth(new Dimension(textBoxWidth.getText()));
					box.setHeight(new Dimension(textBoxHeight.getText()));
					box.getLabel().setString(textBoxLabel.getText());
					diagram.revalidate();
					diagram.repaint();
				}
			}
		});
		panel.add(btnUpdateBox);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Label", null, panel_2, null);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		JLabel lblLabelX = new JLabel("Label x");
		panel_2.add(lblLabelX);

		textLabelX = new JTextField();
		panel_2.add(textLabelX);
		textLabelX.setColumns(10);

		JLabel lblLabelY = new JLabel("Label y");
		panel_2.add(lblLabelY);

		textLabelY = new JTextField();
		panel_2.add(textLabelY);
		textLabelY.setColumns(10);

		JLabel lblArrowX = new JLabel("Arrow x");
		panel_2.add(lblArrowX);

		textLabelArrowX = new JTextField();
		panel_2.add(textLabelArrowX);
		textLabelArrowX.setColumns(10);

		JLabel lblArrowY = new JLabel("Arrow y");
		panel_2.add(lblArrowY);

		textLabelArrowY = new JTextField();
		panel_2.add(textLabelArrowY);
		textLabelArrowY.setColumns(10);

		JLabel lblLabelText = new JLabel("Label Text");
		panel_2.add(lblLabelText);

		textLabelText = new JTextField();
		panel_2.add(textLabelText);
		textLabelText.setColumns(10);

		JButton btnNewLabel = new JButton("New");
		btnNewLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add a new custom label.
				CustomLabel newLabel = new CustomLabel(new Coordinate(new Dimension(0), new Dimension(0)),
						new Label("Label", Alignment.CENTER, Alignment.CENTER, diagram.getLabelFont(), Color.BLACK),
						new Coordinate(new Dimension(3, 0), new Dimension(3, 0)));

				listCustomItemsModel.addElement(newLabel);
				diagram.revalidate();
				diagram.repaint();
			}
		});
		panel_2.add(btnNewLabel);

		JButton btnUpdateLabel = new JButton("Update");
		btnUpdateLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentItem.getType() == "Label") {
					CustomLabel label = (CustomLabel) currentItem;
					label.getLocation().setX(new Dimension(textLabelX.getText()));
					label.getLocation().setY(new Dimension(textLabelY.getText()));
					label.getArrowLocation().setX(new Dimension(textLabelArrowX.getText()));
					label.getArrowLocation().setY(new Dimension(textLabelArrowY.getText()));
					label.getLabel().setString(textLabelText.getText());
					diagram.revalidate();
					diagram.repaint();
				}
			}
		});
		panel_2.add(btnUpdateLabel);
		btnNewPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Add a new custom post at 0, 0.
				CustomPost newPost = new CustomPost(new Coordinate(new Dimension(0), new Dimension(0)),
						new Dimension(4));
				listCustomItemsModel.addElement(newPost);
				diagram.revalidate();
				diagram.repaint();
			}
		});
	}

	public DiagramFrame(GUIData data, boolean side) {
		this();
		this.diagram = new Diagram(data, side, listCustomItemsModel);
		contentPane.add(diagram, BorderLayout.CENTER);

		if (side) {
			splitPane.setVisible(false);
		}
	}
}
