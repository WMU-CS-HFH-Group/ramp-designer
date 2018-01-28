package ramp;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ramp.diagram.GUIData;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class inputdata extends JFrame {

	private JPanel contentPane;
	private static GUIData guiData = new GUIData();
	private GUIUtilitys guiUtility = new GUIUtilitys(guiData);
	private final int MAX_FEET = 24;
	private final int MAX_INCH = 48;
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					inputdata frame = new inputdata();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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
		guiUtility.setComboBoxRangeNumber(comboFeet, 0, MAX_FEET);
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
		guiUtility.setComboBoxRangeNumber(comboInch, 0, MAX_INCH);
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
		guiUtility.setComboBoxRangeNumber(comboDimFtW, 0, MAX_FEET);
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
		guiUtility.setComboBoxRangeNumber(comboDimInW, 0, MAX_INCH);
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
		guiUtility.setComboBoxRangeNumber(comboDimFtL, 0, MAX_FEET);
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
		guiUtility.setComboBoxRangeNumber(comboDimInL, 0, MAX_INCH);
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
		guiUtility.setComboBoxRangeNumber(comboRampIn, -MAX_FEET, MAX_FEET);
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
		
		JLabel lblNewLabel = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, 10, SpringLayout.SOUTH, comboFromDeck);
		lblNewLabel.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/House.png")));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, 0, SpringLayout.EAST, contentPane);
		contentPane.add(lblNewLabel);
		
		JButton btnSubmit = new JButton("Submit");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSubmit, 0, SpringLayout.WEST, lblDimFt2);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSubmit, -10, SpringLayout.SOUTH, contentPane);
		contentPane.add(btnSubmit);
		
		
		JTextArea lblRampHor = new JTextArea();
		JTextArea lblRampVert = new JTextArea();
		JButton btnTurnAr = new JButton();
		
		guiUtility.setRamps(contentPane, sl_contentPane, lblRampHor, lblRampVert, btnTurnAr);
		
		JLabel lblHouseDeck = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblHouseDeck, 6, SpringLayout.SOUTH, comboFromDeck);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblHouseDeck, 10, SpringLayout.EAST, comboFromDeck);
		lblHouseDeck.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/Deck.png")));
		contentPane.add(lblHouseDeck);
		
		JLabel lblInchesRemaining = new JLabel("Ramp inches remaining: ");
		lblInchesRemaining.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblInchesRemaining.setHorizontalAlignment(SwingConstants.RIGHT);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblInchesRemaining, 0, SpringLayout.NORTH, btnSubmit);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblInchesRemaining, 0, SpringLayout.EAST, comboDimFtL);
		contentPane.add(lblInchesRemaining);
		
		
		//listeners go last
		//** changes inches remaining whenever **// 
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
		
		//** Add information on deck dimensions **//
		comboDimFtW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changetotal(comboFeet, comboInch, comboInPart, lblInchesRemaining);
			} 
		});
		
		//** changes direction of first ramp **//
		comboFromDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiUtility.setRampDirection(comboFromDeck.getSelectedIndex(), sl_contentPane, lblHouseDeck, lblRampHor, lblRampVert, btnTurnAr);
			}
		});
		
		//** click to change direction of first ramp **//
		lblHouseDeck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if ((comboFromDeck.getSelectedIndex() + 1) >= comboFromDeck.getItemCount()){
					comboFromDeck.setSelectedIndex(0);
				} else {
					comboFromDeck.setSelectedIndex(comboFromDeck.getSelectedIndex() + 1);
				}
				guiUtility.setRampDirection(comboFromDeck.getSelectedIndex(), sl_contentPane, lblHouseDeck, lblRampHor, lblRampVert, btnTurnAr);
			}
		});
		
		
		
		//** Set Submit button **//
		guiUtility.submitButtonActions(btnSubmit);
		
		System.out.println();
	}
	
	//**********set combo boxes boxes*******************
	
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
	
	//*******************changes total remaining value**********************//
	private void changetotal(JComboBox comboFeet, JComboBox comboInch, JComboBox comboInPart, JLabel lblInchesRemaining){
		float calcIn = (float) (12.0 * (int) comboFeet.getSelectedIndex());
		calcIn += (float) comboInch.getSelectedIndex();
		calcIn += (float) (.125 * (int) comboInPart.getSelectedIndex());
		guiData.setDeckHeight(calcIn);
		guiData.setRampLenghtTotal(calcIn*12);
		lblInchesRemaining.setText("Ramp inches remaining: " + (calcIn * 12));
	}
}
