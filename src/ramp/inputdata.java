package ramp;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;

public class inputdata extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inputdata frame = new inputdata();
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
	public inputdata() {
		setTitle("RampDesign by Lana-Wulf Soft");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblHeightOfDeck = new JLabel("Height of Deck");
		lblHeightOfDeck.setBounds(10, 10, 92, 19);
		lblHeightOfDeck.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(lblHeightOfDeck);
		
		JComboBox<Integer> comboFeet = setFeet();
		comboFeet.setBounds(112, 10, 29, 22);
		
		JComboBox<Integer> comboInch = setInches();
		comboInch.setBounds(661, 10, 29, 22);
				
		JComboBox<String> comboInPart = setInParts();
		comboInPart.setBounds(203, 10, 29, 22);
		
		
		JLabel label_1 = new JLabel("''");
		label_1.setBounds(242, 11, 6, 17);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.setLayout(null);
		
		
		contentPane.add(comboFeet);
		
		JLabel labelFeet = new JLabel("'");
		labelFeet.setBounds(151, 11, 3, 17);
		labelFeet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(labelFeet);
		contentPane.add(comboInch);
		contentPane.add(comboInPart);
		contentPane.add(label_1);
		
		JLabel lblDimentionsOfDeck = new JLabel("Dimentions of Deck");
		lblDimentionsOfDeck.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDimentionsOfDeck.setBounds(336, 12, 131, 14);
		contentPane.add(lblDimentionsOfDeck);
		
		JComboBox<Integer> comboDimFt1 = setFeet();
		comboDimFt1.setBounds(477, 10, 29, 22);
		contentPane.add(comboDimFt1);
		
		JComboBox<Integer> comboDimIn1 = setInches();
		comboDimIn1.setBounds(529, 10, 29, 22);
		contentPane.add(comboDimIn1);
		
		JLabel label = new JLabel("'");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(516, 14, 3, 17);
		contentPane.add(label);
		
		JLabel lblX = new JLabel("''    X  ");
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblX.setBounds(568, 14, 43, 17);
		contentPane.add(lblX);
		
		JComboBox<Integer> comboDimFt2 = setFeet();
		comboDimFt2.setBounds(580, 10, 29, 22);
		contentPane.add(comboDimFt2);
		
		JComboBox<Integer> comboDimIn2 = setInches();
		comboDimIn2.setBounds(632, 10, 29, 22);
		contentPane.add(comboDimIn2);
		
//		lblLayout = new JLabel("Layout");
//		lblLayout.setBounds(10, 93, 43, 19);
//		lblLayout.setFont(new Font("Dialog", Font.PLAIN, 14));
//		contentPane.add(lblLayout);
//		
//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		contentPane.add(tabbedPane);
//		
//		panelNoTurns = new JPanel();
//		tabbedPane.addTab("No Turns", null, panelNoTurns, null);
//		
//		panel = new JPanel();
//		tabbedPane.addTab("One Turn", null, panel, null);
//		
//		panel_1 = new JPanel();
//		tabbedPane.addTab("Two Turns", null, panel_1, null);
	}
	
	//**********set combo boxes boxes*******************
	public JComboBox<Integer> setFeet(){
		JComboBox<Integer> comboFeet_1 = new JComboBox<Integer>();
		for (int i = 0; i < 20; i++) {
			comboFeet_1.addItem(i);
		}
		return comboFeet_1;
	}
	
	public JComboBox<Integer> setInches(){
		JComboBox<Integer> comboInches = new JComboBox<Integer>();
		for (int i = 0; i < 12; i++) {
			comboInches.addItem(i);
		}
		return comboInches;
	}
	
	public JComboBox<String> setInParts(){
		JComboBox<String> comboInParts = new JComboBox<String>();
		comboInParts.addItem("1/8");
		comboInParts.addItem("1/4");
		comboInParts.addItem("3/8");
		comboInParts.addItem("1/2");
		comboInParts.addItem("5/8");
		comboInParts.addItem("3/4");
		comboInParts.addItem("7/8");
		return comboInParts;
	}
}

