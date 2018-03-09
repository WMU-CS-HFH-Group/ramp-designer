package ramp.diagram;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class DiagramFrame extends JFrame {

	private JPanel contentPane;
	private Diagram diagram;

	/**
	 * Create the frame.
	 */
	public DiagramFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 800);
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
						JOptionPane.showMessageDialog(null, "Could not save image.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		toolBar.add(btnSaveImage);
	}

	public DiagramFrame(Diagram diagram) {
		this();
		this.diagram = diagram;
		contentPane.add(diagram, BorderLayout.CENTER);
	}
}
