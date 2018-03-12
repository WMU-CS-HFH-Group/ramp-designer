package ramp.diagram;

import java.awt.BorderLayout;

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
				CustomItem item = listCustomItemsModel.getElementAt(arg0.getFirstIndex());
				switch (item.getType()) {
				case "Post":
					CustomPost post = (CustomPost) item;
					// TODO: Switch to post tab

					// Change the data in the post editor.
					textPostX.setText(post.getLocation().getX().toString());
					textPostY.setText(post.getLocation().getY().toString());
					textPostSize.setText(post.getSize().toString());
					currentItem = post;
					break;
				case "Box":
					break;
				case "Label":
					break;
				case "Text":
					break;
				}
			}
		});
		splitPane.setRightComponent(listCustomItems);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setLeftComponent(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Post", null, panel_1, null);

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
