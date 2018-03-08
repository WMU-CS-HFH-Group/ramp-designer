package ramp.diagram;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;

public class DiagramFrame extends JFrame {

	private JPanel contentPane;
	private Diagram diagram;

	/**
	 * Launch the application.
	 */
	public static void dksjflkd(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiagramFrame frame = new DiagramFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
	}

	public DiagramFrame(Diagram diagram) {
		this();
		this.diagram = diagram;
		contentPane.add(diagram, BorderLayout.CENTER);
	}
}
