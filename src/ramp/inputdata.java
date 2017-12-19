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
import javax.swing.SwingConstants;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inputdata extends JFrame {

	private JPanel contentPane;
	private float totalIn = 0;

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
		
		JLabel lblHouseDeck = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblHouseDeck, 6, SpringLayout.SOUTH, comboFromDeck);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblHouseDeck, 10, SpringLayout.WEST, comboFromDeck);
		lblHouseDeck.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/Deck.png")));
		contentPane.add(lblHouseDeck);
		
		JLabel lblRampHor = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblRampHor, 17, SpringLayout.SOUTH, comboRampIn);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblRampHor, 0, SpringLayout.EAST, comboInch);
		lblRampHor.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/rampHorizontal.png")));
		contentPane.add(lblRampHor);
		
		JLabel lblRampVert = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblRampVert, 6, SpringLayout.SOUTH, lblRampHor);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblRampVert, 0, SpringLayout.WEST, lblHeightOfDeck);
		lblRampVert.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/rampVertical.png")));
		contentPane.add(lblRampVert);
		
		JLabel lblTurnAr = new JLabel("");
		lblTurnAr.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/turnAround.png")));
		contentPane.add(lblTurnAr);
		
		JLabel lblRampHor2 = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblTurnAr, 6, SpringLayout.SOUTH, lblRampHor2);
		lblRampHor2.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/rampHorizontal.png")));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblRampHor2, 6, SpringLayout.SOUTH, lblRampHor);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblRampHor2, 10, SpringLayout.WEST, lblRampHor);
		contentPane.add(lblRampHor2);
		
		JLabel lblRampVert2 = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblTurnAr, 6, SpringLayout.EAST, lblRampVert2);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblRampVert2, 6, SpringLayout.SOUTH, lblRampHor2);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblRampVert2, 10, SpringLayout.EAST, lblRampVert);
		lblRampVert2.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/rampVertical.png")));
		contentPane.add(lblRampVert2);
		
		JLabel lblTurnUVert = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblTurnUVert, 0, SpringLayout.WEST, lblTurnAr);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblTurnUVert, 0, SpringLayout.SOUTH, btnSubmit);
		lblTurnUVert.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/turnUVertical.png")));
		contentPane.add(lblTurnUVert);
		
		JLabel lblTurnUHor = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblTurnUHor, 6, SpringLayout.EAST, lblTurnAr);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblTurnUHor, 0, SpringLayout.SOUTH, lblTurnAr);
		lblTurnUHor.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/turnUHorizontal.png")));
		contentPane.add(lblTurnUHor);
		
		JLabel lblInchesRemaining = new JLabel("Ramp inches remaining: ");
		lblInchesRemaining.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblInchesRemaining.setHorizontalAlignment(SwingConstants.RIGHT);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblInchesRemaining, 0, SpringLayout.NORTH, btnSubmit);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblInchesRemaining, 0, SpringLayout.EAST, comboDimFtL);
		contentPane.add(lblInchesRemaining);
		
		comboFeet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float calcIn = (float) (12.0 * (int) comboFeet.getSelectedIndex());
				calcIn += (float) comboInch.getSelectedIndex();
				calcIn += (float) (.125 * (int) comboInPart.getSelectedIndex());
				setTotalIn(calcIn * 12);
				lblInchesRemaining.setText("Ramp inches remaining: " + (calcIn * 12));
			} 
		});
		
		comboInch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float calcIn = (float) (12.0 * (int) comboFeet.getSelectedIndex());
				calcIn += (float) comboInch.getSelectedIndex();
				calcIn += (float) (.125 * (int) comboInPart.getSelectedIndex());
				setTotalIn(calcIn * 12);
				lblInchesRemaining.setText("Ramp inches remaining: " + (calcIn * 12));
			} 
		});
		
		comboInPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float calcIn = (float) (12.0 * (int) comboFeet.getSelectedIndex());
				calcIn += (float) comboInch.getSelectedIndex();
				calcIn += (float) (.125 * (int) comboInPart.getSelectedIndex());
				setTotalIn(calcIn * 12);
				lblInchesRemaining.setText("Ramp inches remaining: " + (calcIn * 12));
			} 
		});
		
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

	public float getTotalIn() {
		return totalIn;
	}

	public void setTotalIn(float totalIn) {
		this.totalIn = totalIn;
	}
	
	
}

