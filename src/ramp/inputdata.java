package ramp;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.print.attribute.standard.PrinterMessageFromOperator;
import javax.swing.ImageIcon;

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
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblHeightOfDeck = new JLabel("Height of Deck");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblHeightOfDeck, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblHeightOfDeck, 10, SpringLayout.WEST, contentPane);
		lblHeightOfDeck.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(lblHeightOfDeck);
		
		JComboBox<Integer> comboFeet = new JComboBox<Integer>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboFeet, 0, SpringLayout.NORTH, lblHeightOfDeck);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboFeet, 6, SpringLayout.EAST, lblHeightOfDeck);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboFeet, 49, SpringLayout.EAST, lblHeightOfDeck);
		setFeet(comboFeet);
		contentPane.add(comboFeet);
		
		JLabel labelFeet = new JLabel("'");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelFeet, 0, SpringLayout.NORTH, comboFeet);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelFeet, 3, SpringLayout.EAST, comboFeet);
		labelFeet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(labelFeet);
		
		JComboBox<Integer> comboInch = new JComboBox<Integer>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboInch, 0, SpringLayout.NORTH, labelFeet);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboInch, 6, SpringLayout.EAST, labelFeet);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboInch, 49, SpringLayout.EAST, labelFeet);
		setInches(comboInch);
		contentPane.add(comboInch);
				
		JComboBox<String> comboInPart = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboInPart, 0, SpringLayout.NORTH, lblHeightOfDeck);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboInPart, 8, SpringLayout.EAST, comboInch);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboInPart, 59, SpringLayout.EAST, comboInch);
		setInParts(comboInPart);
		contentPane.add(comboInPart);		
		
		JLabel labelIn = new JLabel("''");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelIn, 0, SpringLayout.NORTH, comboInPart);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelIn, 3, SpringLayout.EAST, comboInPart);
		labelIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(labelIn);
		
		JLabel lblDimentionsOfDeck = new JLabel("Dimentions of Deck  W:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDimentionsOfDeck, 12, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDimentionsOfDeck, 320, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblDimentionsOfDeck, 26, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblDimentionsOfDeck, 467, SpringLayout.WEST, contentPane);
		lblDimentionsOfDeck.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(lblDimentionsOfDeck);
		
		JComboBox<Integer> comboDimFtW = new JComboBox<Integer>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboDimFtW, 12, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboDimFtW, 194, SpringLayout.EAST, labelIn);
		setFeet(comboDimFtW);
		contentPane.add(comboDimFtW);
		
		JLabel lblDimFt1 = new JLabel("'");
		sl_contentPane.putConstraint(SpringLayout.EAST, comboDimFtW, -3, SpringLayout.WEST, lblDimFt1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDimFt1, 519, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDimFt1, 0, SpringLayout.NORTH, comboDimFtW);
		lblDimFt1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblDimFt1);
		
		JComboBox<Integer> comboDimInW = new JComboBox<Integer>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboDimInW, 0, SpringLayout.NORTH, lblDimFt1);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboDimInW, 6, SpringLayout.EAST, lblDimFt1);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboDimInW, 49, SpringLayout.EAST, lblDimFt1);
		setInches(comboDimInW);
		contentPane.add(comboDimInW);
		
		JLabel lblDimIn1 = new JLabel("''    X    L:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDimIn1, 0, SpringLayout.NORTH, comboDimInW);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDimIn1, 3, SpringLayout.EAST, comboDimInW);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblDimIn1, 64, SpringLayout.EAST, comboDimInW);
		lblDimIn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblDimIn1);
		
		JComboBox<Integer> comboDimFtL = new JComboBox<Integer>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboDimFtL, 0, SpringLayout.NORTH, lblDimIn1);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboDimFtL, 0, SpringLayout.EAST, lblDimIn1);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboDimFtL, 49, SpringLayout.EAST, lblDimIn1);
		setFeet(comboDimFtL);
		contentPane.add(comboDimFtL);
		
		JLabel lblDimFt2 = new JLabel("'");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDimFt2, 0, SpringLayout.NORTH, comboDimFtL);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDimFt2, 3, SpringLayout.EAST, comboDimFtL);
		lblDimFt2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblDimFt2);
		
		JComboBox<Integer> comboDimInL = new JComboBox<Integer>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboDimInL, 0, SpringLayout.NORTH, lblDimFt2);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboDimInL, 6, SpringLayout.EAST, lblDimFt2);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboDimInL, 49, SpringLayout.EAST, lblDimFt2);
		setInches(comboDimInL);
		contentPane.add(comboDimInL);
		
		JLabel lblDimIn2 = new JLabel("''");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDimIn2, 0, SpringLayout.NORTH, comboDimInL);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDimIn2, 3, SpringLayout.EAST, comboDimInL);
		lblDimIn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblDimIn2);
		
		JLabel lblStyle = new JLabel("Ramp Style: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblStyle, 10, SpringLayout.SOUTH, lblHeightOfDeck);
		lblStyle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sl_contentPane.putConstraint(SpringLayout.WEST, lblStyle, 0, SpringLayout.WEST, lblHeightOfDeck);
		contentPane.add(lblStyle);
		
		JComboBox<Integer> comboRampIn = new JComboBox<Integer>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboRampIn, 0, SpringLayout.NORTH, lblStyle);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboRampIn, 6, SpringLayout.EAST, lblStyle);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboRampIn, 59, SpringLayout.EAST, lblStyle);
		setInchesOffCenter(comboRampIn);
		contentPane.add(comboRampIn);
		
		JLabel lblRampIn = new JLabel("'' off of the ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblRampIn, 0, SpringLayout.NORTH, comboRampIn);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblRampIn, 3, SpringLayout.EAST, comboRampIn);
		lblRampIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblRampIn);
		
		JComboBox<String> comboFromDeck = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboFromDeck, 0, SpringLayout.NORTH, lblRampIn);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboFromDeck, 3, SpringLayout.EAST, lblRampIn);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboFromDeck, 66, SpringLayout.EAST, lblRampIn);
		comboFromDeck.addItem("Left");
		comboFromDeck.addItem("Right");
		comboFromDeck.addItem("Center");
		contentPane.add(comboFromDeck);
		
		JLabel lblWithARamp = new JLabel("with a ramp style of");
		lblWithARamp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWithARamp, 0, SpringLayout.NORTH, comboFromDeck);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblWithARamp, 6, SpringLayout.EAST, comboFromDeck);
		contentPane.add(lblWithARamp);
		
		JComboBox<String> comboRampStyle = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboRampStyle, 0, SpringLayout.NORTH, lblWithARamp);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboRampStyle, 6, SpringLayout.EAST, lblWithARamp);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboRampStyle, 79, SpringLayout.EAST, lblWithARamp);
		comboRampStyle.addItem("Straight");
		comboRampStyle.addItem("L Turn");
		comboRampStyle.addItem("U Turn");
		contentPane.add(comboRampStyle);
		
		JButton btnSubmit = new JButton("Submit");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSubmit, 226, SpringLayout.SOUTH, comboDimInL);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSubmit, 0, SpringLayout.WEST, lblDimFt2);
		contentPane.add(btnSubmit);
		
		JLabel lblNewLabel = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 31, SpringLayout.SOUTH, comboRampIn);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, -27, SpringLayout.WEST, btnSubmit);
		lblNewLabel.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/Deck.png")));
		contentPane.add(lblNewLabel);
		
		System.out.println();
		
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
	public void setFeet(JComboBox<Integer> combo){
		for (int i = 0; i < 20; i++) {
			combo.addItem(i);
		}
	}
	
	public void setInches(JComboBox<Integer> combo){
		for (int i = 0; i < 48; i++) {
			combo.addItem(i);
		}
	}
	
	public void setInchesOffCenter(JComboBox<Integer> combo){
		for (int i = -24; i < 25; i++) {
			combo.addItem(i);
		}
	}
	
	public void setInParts(JComboBox<String> combo){
		combo.addItem("1/8");
		combo.addItem("1/4");
		combo.addItem("3/8");
		combo.addItem("1/2");
		combo.addItem("5/8");
		combo.addItem("3/4");
		combo.addItem("7/8");
	}
}

