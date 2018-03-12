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
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
	private JTextField textTextX;
	private JTextField textTextY;
	private JTextPane textTextText;

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
				if (currentItem != null) {
					if (currentItem.getType() == "Post") {
						CustomPost post = (CustomPost) currentItem;
						post.getLocation().setX(new Dimension(textPostX.getText()));
						post.getLocation().setY(new Dimension(textPostY.getText()));
						post.setSize(new Dimension(textPostSize.getText()));
						diagram.revalidate();
						diagram.repaint();
					}
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
				// Set defaults
				Coordinate location = new Coordinate(new Dimension(0), new Dimension(0));
				Dimension width = new Dimension(3, 0);
				Dimension height = new Dimension(3, 0);
				Label label = new Label(textBoxLabel.getText(), Alignment.CENTER, Alignment.CENTER,
						diagram.getLabelFont(), Color.BLACK);

				// Take input
				if (textBoxX.getText().length() > 0) {
					location.setX(new Dimension(textBoxX.getText()));
				}

				if (textBoxY.getText().length() > 0) {
					location.setY(new Dimension(textBoxY.getText()));
				}

				if (textBoxWidth.getText().length() > 0) {
					width = new Dimension(textBoxWidth.getText());
				}

				if (textBoxHeight.getText().length() > 0) {
					height = new Dimension(textBoxHeight.getText());
				}

				// Add a new custom box.
				CustomBox newBox = new CustomBox(location, width, height, label);
				listCustomItemsModel.addElement(newBox);
				listCustomItems.setSelectedValue(newBox, true);
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
				// Set defaults
				Coordinate location = new Coordinate(new Dimension(0), new Dimension(0));
				Label label = new Label(textLabelText.getText(), Alignment.CENTER, Alignment.CENTER,
						diagram.getLabelFont(), Color.BLACK);
				Coordinate arrowLocation = new Coordinate(new Dimension(3, 0), new Dimension(3, 0));

				// Take input
				if (textLabelX.getText().length() > 0) {
					location.setX(new Dimension(textLabelX.getText()));
				}

				if (textLabelY.getText().length() > 0) {
					location.setY(new Dimension(textLabelY.getText()));
				}

				if (textLabelArrowX.getText().length() > 0) {
					arrowLocation.setX(new Dimension(textLabelArrowX.getText()));
				}

				if (textLabelArrowY.getText().length() > 0) {
					arrowLocation.setY(new Dimension(textLabelArrowY.getText()));
				}

				// Add a new custom label.
				CustomLabel newLabel = new CustomLabel(location, label, arrowLocation);

				listCustomItemsModel.addElement(newLabel);
				listCustomItems.setSelectedValue(newLabel, true);
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

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Text", null, panel_3, null);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		JLabel lblOriginX = new JLabel("Origin x");
		panel_3.add(lblOriginX);

		textTextX = new JTextField();
		panel_3.add(textTextX);
		textTextX.setColumns(10);

		JLabel lblOriginY = new JLabel("Origin y");
		panel_3.add(lblOriginY);

		textTextY = new JTextField();
		panel_3.add(textTextY);
		textTextY.setColumns(10);

		JLabel lblText = new JLabel("Text");
		panel_3.add(lblText);

		textTextText = new JTextPane();
		panel_3.add(textTextText);

		JLabel lblHorizontalAlignment = new JLabel("Horizontal Alignment");
		panel_3.add(lblHorizontalAlignment);

		JComboBox<Alignment> comboTextHorizontal = new JComboBox<Alignment>();
		comboTextHorizontal.setModel(new DefaultComboBoxModel<Alignment>(Alignment.values()));
		panel_3.add(comboTextHorizontal);

		JLabel lblVerticalAlignment = new JLabel("Vertical Alignment");
		panel_3.add(lblVerticalAlignment);

		JComboBox<Alignment> comboTextVertical = new JComboBox<Alignment>();
		comboTextVertical.setModel(new DefaultComboBoxModel<Alignment>(Alignment.values()));
		panel_3.add(comboTextVertical);

		JButton btnNewText = new JButton("New");
		btnNewText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alignment hAlignment = (Alignment) comboTextHorizontal.getSelectedItem();
				Alignment vAlignment = (Alignment) comboTextVertical.getSelectedItem();

				// Set defaults
				Coordinate location = new Coordinate(new Dimension(0), new Dimension(0));
				Label label = new Label(textTextText.getText(), hAlignment, vAlignment, diagram.getLabelFont(),
						Color.BLACK);

				// Take input
				if (textTextX.getText().length() > 0) {
					location.setX(new Dimension(textTextX.getText()));
				}

				if (textTextY.getText().length() > 0) {
					location.setY(new Dimension(textTextY.getText()));
				}

				CustomText newText = new CustomText(location, label);
				listCustomItemsModel.addElement(newText);
				listCustomItems.setSelectedValue(newText, true);
			}
		});
		panel_3.add(btnNewText);

		JButton btnUpdateText = new JButton("Update");
		btnUpdateText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentItem.getType() == "Text") {
					Alignment hAlignment = (Alignment) comboTextHorizontal.getSelectedItem();
					Alignment vAlignment = (Alignment) comboTextVertical.getSelectedItem();

					CustomText text = (CustomText) currentItem;
					text.getLocation().setX(new Dimension(textTextX.getText()));
					text.getLocation().setY(new Dimension(textTextY.getText()));
					text.getLabel().setString(textTextText.getText());
					text.getLabel().setAlignmentX(hAlignment);
					text.getLabel().setAlignmentY(vAlignment);
					diagram.revalidate();
					diagram.repaint();
				}
			}
		});
		panel_3.add(btnUpdateText);

		JPanel panel_4 = new JPanel();
		splitPane.setRightComponent(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		listCustomItems = new JList<CustomItem>(listCustomItemsModel);
		panel_4.add(listCustomItems);

		JToolBar toolBar_1 = new JToolBar();
		panel_4.add(toolBar_1, BorderLayout.NORTH);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listCustomItems.getSelectedIndex() >= 0) {
					listCustomItemsModel.removeElementAt(listCustomItems.getSelectedIndex());
					diagram.revalidate();
					diagram.repaint();
				}
			}
		});
		
		JButton btnDuplicate = new JButton("Duplicate");
		btnDuplicate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomItem item = listCustomItems.getSelectedValue();
				CustomItem item2 = item.clone();
				listCustomItemsModel.addElement(item2);
				listCustomItems.setSelectedValue(item2, true);
			}
		});
		toolBar_1.add(btnDuplicate);
		toolBar_1.add(btnDelete);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listCustomItemsModel.clear();
				diagram.revalidate();
				diagram.repaint();
			}
		});
		toolBar_1.add(btnClear);
		listCustomItems.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (listCustomItems.getSelectedIndex() >= 0) {
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
						CustomText text = (CustomText) item;

						// Switch to text tab.
						tabbedPane.setSelectedIndex(3);

						// Change data in the editor.
						textTextX.setText(text.getLocation().getX().toString());
						textTextY.setText(text.getLocation().getY().toString());
						textTextText.setText(text.getLabel().getString());

						currentItem = text;
						break;
					}
				}
			}
		});
		btnNewPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Set defaults
				Coordinate location = new Coordinate(new Dimension(0), new Dimension(0));
				Dimension size = new Dimension(4);

				if (textPostX.getText().length() > 0) {
					location.setX(new Dimension(textPostX.getText()));
				}

				if (textPostY.getText().length() > 0) {
					location.setY(new Dimension(textPostY.getText()));
				}

				if (textPostSize.getText().length() > 0) {
					size = new Dimension(textPostSize.getText());
				}

				// Create the new post
				CustomPost newPost = new CustomPost(location, size);
				listCustomItemsModel.addElement(newPost);
				listCustomItems.setSelectedValue(newPost, true);
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
