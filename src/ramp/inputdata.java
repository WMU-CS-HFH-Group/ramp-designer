package ramp;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;


public class inputdata extends JFrame {

	private JPanel contentPane;
	private float totalIn = 0;
	private float usedIn = 0;
	private GUIData data = new GUIData();
	private GUIUtilitys guiUtility = new GUIUtilitys();

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
		
		JLabel lblStyle = new JLabel("The ramp is: ");
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
		
		JButton btnSubmit = new JButton("Submit");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSubmit, 0, SpringLayout.WEST, lblDimFt2);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSubmit, -10, SpringLayout.SOUTH, contentPane);
		contentPane.add(btnSubmit);
		
		JLabel lblHouseDeck = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblHouseDeck, 6, SpringLayout.SOUTH, comboFromDeck);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblHouseDeck, 10, SpringLayout.WEST, comboFromDeck);
		lblHouseDeck.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/Deck.png")));
		contentPane.add(lblHouseDeck);
		
		JTextArea lblRampHor = new JTextArea();
		lblRampHor.setText("#' #\"");
		lblRampHor.setFont(new Font("Tahoma", Font.BOLD, 20));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblRampHor, 45, SpringLayout.NORTH, lblHouseDeck);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblRampHor, -90, SpringLayout.WEST, lblHouseDeck);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblRampHor, 85, SpringLayout.WEST, lblHouseDeck);
		contentPane.add(lblRampHor);
		
		JTextArea lblRampVert = new JTextArea();
		sl_contentPane.putConstraint(SpringLayout.WEST, lblRampVert, 110, SpringLayout.WEST, lblHouseDeck);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblRampVert, 180, SpringLayout.SOUTH, lblHouseDeck);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblRampVert, -75, SpringLayout.EAST, lblHouseDeck);
		lblRampVert.setText("#' #\"");
		lblRampVert.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRampVert.setLineWrap(true);
		lblRampVert.setWrapStyleWord(true);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblRampVert, -10, SpringLayout.SOUTH, lblHouseDeck);
		lblRampVert.setVisible(false);
		contentPane.add(lblRampVert);
		
		JButton btnTurnAr = new JButton();
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnTurnAr, -6, SpringLayout.NORTH, lblRampHor);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnTurnAr, -50, SpringLayout.WEST, lblRampHor);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnTurnAr, 0, SpringLayout.WEST, lblRampHor);
		btnTurnAr.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/turnAround.png")));
		contentPane.add(btnTurnAr);
		
		JLabel lblTurnUVert = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblTurnUVert, 272, SpringLayout.SOUTH, lblStyle);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblTurnUVert, 23, SpringLayout.WEST, contentPane);
		lblTurnUVert.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/turnUVertical.png")));
		contentPane.add(lblTurnUVert);
		
		JLabel lblTurnUHor = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblTurnUHor, 30, SpringLayout.EAST, lblTurnUVert);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblTurnUHor, 0, SpringLayout.SOUTH, lblTurnUVert);
		lblTurnUHor.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/turnUHorizontal.png")));
		contentPane.add(lblTurnUHor);
		
		JLabel lblInchesRemaining = new JLabel("Ramp inches remaining: ");
		lblInchesRemaining.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblInchesRemaining.setHorizontalAlignment(SwingConstants.RIGHT);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblInchesRemaining, 0, SpringLayout.NORTH, btnSubmit);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblInchesRemaining, 0, SpringLayout.EAST, comboDimFtL);
		contentPane.add(lblInchesRemaining);
		
		
		//listeners go last
		//changes inches remaining whenever 
		comboFeet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changetotal(comboFeet, comboInch, comboInPart, lblInchesRemaining);
			} 
		});
		
		comboInch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changetotal(comboFeet, comboInch, comboInPart, lblInchesRemaining);
			} 
		});
		
		comboInPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changetotal(comboFeet, comboInch, comboInPart, lblInchesRemaining);
			} 
		});
		
		//changes direction of first ramp
		comboFromDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblRampHor.setVisible(false);
				btnTurnAr.setVisible(false);
				lblRampVert.setVisible(false);
				switch (comboFromDeck.getSelectedIndex()) {
				case 0:
					sl_contentPane.putConstraint(SpringLayout.NORTH, lblRampHor, 45, SpringLayout.NORTH, lblHouseDeck);
					sl_contentPane.putConstraint(SpringLayout.WEST, lblRampHor, -90, SpringLayout.WEST, lblHouseDeck);
					sl_contentPane.putConstraint(SpringLayout.EAST, lblRampHor, 85, SpringLayout.WEST, lblHouseDeck);
					sl_contentPane.putConstraint(SpringLayout.NORTH, btnTurnAr, -6, SpringLayout.NORTH, lblRampHor);
					sl_contentPane.putConstraint(SpringLayout.WEST, btnTurnAr, -50, SpringLayout.WEST, lblRampHor);
					sl_contentPane.putConstraint(SpringLayout.EAST, btnTurnAr, 0, SpringLayout.WEST, lblRampHor);
					lblRampHor.setVisible(true);
					btnTurnAr.setVisible(true);
					break;
				case 1:
					sl_contentPane.putConstraint(SpringLayout.NORTH, lblRampHor, 45, SpringLayout.NORTH, lblHouseDeck);
					sl_contentPane.putConstraint(SpringLayout.WEST, lblRampHor, -55, SpringLayout.EAST, lblHouseDeck);
					sl_contentPane.putConstraint(SpringLayout.EAST, lblRampHor, 120, SpringLayout.EAST, lblHouseDeck);
					sl_contentPane.putConstraint(SpringLayout.NORTH, btnTurnAr, -6, SpringLayout.NORTH, lblRampHor);
					sl_contentPane.putConstraint(SpringLayout.WEST, btnTurnAr, 0, SpringLayout.EAST, lblRampHor);
					sl_contentPane.putConstraint(SpringLayout.EAST, btnTurnAr, 50, SpringLayout.EAST, lblRampHor);
					lblRampHor.setVisible(true);
					btnTurnAr.setVisible(true);
					break;

				default:
					sl_contentPane.putConstraint(SpringLayout.NORTH, btnTurnAr, 0, SpringLayout.SOUTH, lblRampVert);
					sl_contentPane.putConstraint(SpringLayout.WEST, btnTurnAr, -5, SpringLayout.WEST, lblRampVert);
					sl_contentPane.putConstraint(SpringLayout.EAST, btnTurnAr, 5, SpringLayout.EAST, lblRampVert);
					lblRampVert.setVisible(true);
					btnTurnAr.setVisible(true);
					break;
				}
			}
		});
		
		//** Set length of ramp piece ****************************************************************************************
		//**** Un-focus from enter length on enter ****
		guiUtility.removeFocus(lblRampHor);
		guiUtility.removeFocus(lblRampVert);
		
		//**** End Un-focus from enter length on enter ****
		//**** https://stackoverflow.com/questions/19569302/jtextarea-pressing-enter-adds-unnecessary-new-line****************
		
		//**** Clears/Saves input in bar ****
		guiUtility.clearSaveLenght(lblRampHor, data);
		guiUtility.clearSaveLenght(lblRampVert, data);
		//**** End Clears/Saves input in bar ****
		//** End Set length of ramp piece **//
		
		System.out.println();
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
		combo.addItem("0");
		combo.addItem("1/8");
		combo.addItem("1/4");
		combo.addItem("3/8");
		combo.addItem("1/2");
		combo.addItem("5/8");
		combo.addItem("3/4");
		combo.addItem("7/8");
	}
	
	/*******************changes total remaining value**********************/
	private void changetotal(JComboBox comboFeet, JComboBox comboInch, JComboBox comboInPart, JLabel lblInchesRemaining){
		float calcIn = (float) (12.0 * (int) comboFeet.getSelectedIndex());
		calcIn += (float) comboInch.getSelectedIndex();
		calcIn += (float) (.125 * (int) comboInPart.getSelectedIndex());
		setTotalIn(calcIn * 12);
		lblInchesRemaining.setText("Ramp inches remaining: " + (calcIn * 12));
	}

	public float getTotalIn() {
		return totalIn;
	}

	public void setTotalIn(float totalIn) {
		this.totalIn = totalIn;
	}

	public float getUsedIn() {
		return usedIn;
	}

	public void setUsedIn(float usedIn) {
		this.usedIn = usedIn;
	}

}

