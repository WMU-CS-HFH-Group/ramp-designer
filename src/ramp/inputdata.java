package ramp;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ramp.diagram.GUIData;
import ramp.diagram.RampFile;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class inputdata extends JFrame {
	/**
	 * Just to make java happy
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private GUIData guiData = new GUIData();
	private GUIUtilitys guiUtility = new GUIUtilitys(guiData);
	private final int MAX_FEET = 24;
	private final int MAX_INCH = 48;

	/**
	 * Create the frame and basic GUI.
	 */
	public inputdata() {
		setTitle("RampDesign");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		//****************** Setting up each component ******************//
		JLabel lblHeightOfDeck = new JLabel("Height of Deck");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblHeightOfDeck, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblHeightOfDeck, 10, SpringLayout.WEST, contentPane);
		lblHeightOfDeck.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(lblHeightOfDeck);
		
		JComboBox<Integer> comboInch = new JComboBox<Integer>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboInch, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboInch, 10, SpringLayout.EAST, lblHeightOfDeck);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboInch, 60, SpringLayout.EAST, lblHeightOfDeck);
		guiUtility.setComboBoxRangeNumber(comboInch, 0, MAX_INCH);
		contentPane.add(comboInch);
				
		JComboBox<String> comboInPart = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.WEST, comboInPart, 10, SpringLayout.EAST, comboInch);
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboInPart, 0, SpringLayout.NORTH, lblHeightOfDeck);
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
		sl_contentPane.putConstraint(SpringLayout.WEST, comboDimFtW, 10, SpringLayout.EAST, lblDimentionsOfDeck);
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
		
		JComboBox<String> comboRampOffset = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboRampOffset, 0, SpringLayout.NORTH, lblStyle);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboRampOffset, 6, SpringLayout.EAST, lblStyle);
		sl_contentPane.putConstraint(SpringLayout.EAST, comboRampOffset, 80, SpringLayout.EAST, lblStyle);
		guiUtility.setOffsetRangeNumber(comboRampOffset, -MAX_FEET, MAX_FEET);
		comboRampOffset.setSelectedIndex(MAX_FEET);
		contentPane.add(comboRampOffset);
		
		JLabel lblRampIn = new JLabel("\" off center."); //off of the ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblRampIn, 0, SpringLayout.NORTH, comboRampOffset);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblRampIn, 3, SpringLayout.EAST, comboRampOffset);
		lblRampIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblRampIn);
		
		JLabel lblHouse = new JLabel("");
		lblHouse.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/House.png")));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblHouse, 10, SpringLayout.SOUTH, lblRampIn);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblHouse, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblHouse, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblHouse, 0, SpringLayout.EAST, contentPane);
		contentPane.add(lblHouse);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				int option = chooser.showSaveDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					try {
						RampFile.saveToFile(guiData, chooser.getSelectedFile().toString() + ".json");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSave, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSave, 10, SpringLayout.WEST, contentPane);
		contentPane.add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int option = chooser.showOpenDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					try {
						GUIData loadData = RampFile.loadFromFile(chooser.getSelectedFile().toString());
						guiData.setCoords(loadData.getCoords());
						guiData.setDeckDimension(loadData.getDeckDimension());
						guiData.setDeckHeight(loadData.getDeckHeight());
						guiData.setDeckLocation(loadData.getDeckLocation());
						guiData.setDeckOffSet(loadData.getDeckOffSet());
						guiData.setItems(loadData.getItems());
						guiData.setRampDir(loadData.getRampDir());
						guiData.setRampLength(loadData.getRampLength());
						guiData.setRampLengthTotal(loadData.getRampLengthTotal());
						guiData.setSideViewOrigin(loadData.getSideViewOrigin());
						guiData.setTurnAround(loadData.getTurnAround());
						guiData.setUsedIn(loadData.getUsedIn());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnLoad, 0, SpringLayout.NORTH, btnSave);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnLoad, 5, SpringLayout.EAST, btnSave);
		contentPane.add(btnLoad);
		
		JButton btnSubmit = new JButton("Submit");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSubmit, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnSubmit, -25, SpringLayout.EAST, contentPane);
		contentPane.add(btnSubmit);
		
		JLabel lblFeetRemain = new JLabel("Ramp feet remaining: ");
		sl_contentPane.putConstraint(SpringLayout.EAST, lblFeetRemain, -25, SpringLayout.WEST, btnSubmit);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblFeetRemain, 0, SpringLayout.NORTH, btnSubmit);
		lblFeetRemain.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblFeetRemain.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblFeetRemain);
		guiUtility.setLblFeetRemain(lblFeetRemain);
		
		JScrollPane scrollRamps = new JScrollPane();
		scrollRamps.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollRamps.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollRamps, 0, SpringLayout.SOUTH, lblHouse);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollRamps, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollRamps, -5, SpringLayout.NORTH, btnSubmit);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollRamps, -10, SpringLayout.EAST, contentPane);
		contentPane.add(scrollRamps);
		
		JPanel scrollPanel = new JPanel();
		scrollRamps.setViewportView(scrollPanel);
		SpringLayout sl_scrollPanel = new SpringLayout();
		scrollPanel.setLayout(sl_scrollPanel);
		
		
		JLabel lblDeck = new JLabel("");
		sl_scrollPanel.putConstraint(SpringLayout.NORTH, lblDeck, 170, SpringLayout.NORTH, scrollPanel);
		sl_scrollPanel.putConstraint(SpringLayout.WEST, lblDeck, 320, SpringLayout.WEST, scrollPanel);
		lblDeck.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/Deck.png")));
		scrollPanel.add(lblDeck);
		int[] coords = {lblDeck.getLocation().x,
				lblDeck.getLocation().y,lblDeck.getLocation().x + lblDeck.getWidth(),
				lblDeck.getLocation().y + lblDeck.getHeight()};
		guiData.setCoords(coords);
		
		guiUtility.setRamps(scrollPanel, sl_scrollPanel, lblDeck, null, 0);
		guiUtility.comboChangeTotal(comboInch, comboInPart);
		
		//** Updates dimensions in GUI data **//
		comboDimFtW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double[] update = guiData.getDeckDimension();
				update[0] = guiUtility.calcTotalIn(comboDimFtW.getSelectedIndex(), comboDimInW.getSelectedIndex(), 0);
				guiData.setDeckDimension(update);
				if (guiData.getRampDir().size() != 0 && guiData.getRampDir().get(0)%2 == 0){
					guiUtility.setOffsetRangeNumber(comboRampOffset, -(int)(update[0]/2 - 20), (int)update[0]/2 - 20);
					comboRampOffset.setSelectedItem((int)update[0]/2 - 20);
				}
			} 
		});
		
		comboDimInW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double[] update = guiData.getDeckDimension();
				update[0] = guiUtility.calcTotalIn(comboDimFtW.getSelectedIndex(), comboDimInW.getSelectedIndex(), 0);
				guiData.setDeckDimension(update);
				if (guiData.getRampDir().size() != 0 && guiData.getRampDir().get(0)%2 == 0){
					guiUtility.setOffsetRangeNumber(comboRampOffset, -(int)(update[0]/2 - 20), (int)update[0]/2 - 20);
					comboRampOffset.setSelectedItem((int)update[0]/2 - 20);
				}
			} 
		});
		
		comboDimFtL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double[] update = guiData.getDeckDimension();
				update[1] = guiUtility.calcTotalIn(comboDimFtL.getSelectedIndex(), comboDimInL.getSelectedIndex(), 0);
				guiData.setDeckDimension(update);
				if (guiData.getRampDir().size() != 0 && guiData.getRampDir().get(0)%2 == 1){
					guiUtility.setOffsetRangeNumber(comboRampOffset, -(int)(update[1]/2 - 20), (int)update[1]/2 - 20);
					comboRampOffset.setSelectedItem((int)update[1]/2 - 20);
				}
			} 
		});
		
		comboDimInL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double[] update = guiData.getDeckDimension();
				update[1] = guiUtility.calcTotalIn(comboDimFtL.getSelectedIndex(), comboDimInL.getSelectedIndex(), 0);
				guiData.setDeckDimension(update);
				if (guiData.getRampDir().size() != 0 && guiData.getRampDir().get(0)%2 == 1){
					guiUtility.setOffsetRangeNumber(comboRampOffset, -(int)(update[1]/2 - 20), (int)update[1]/2 - 20);
					comboRampOffset.setSelectedItem((int)update[1]/2 - 20);
				}
			} 
		});
		
		//** Updates on offset changes **//
		comboRampOffset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double[] range = guiData.getDeckDimension();
				if (guiData.getRampDir().size() == 0){
					guiData.setDeckOffSet(comboRampOffset.getSelectedIndex() - (MAX_FEET - 20));
				} else if (guiData.getRampDir().get(0)%2 == 0) {
					guiData.setDeckOffSet(comboRampOffset.getSelectedIndex() - (int)(range[0]/2 - 20));
				} else {
					guiData.setDeckOffSet(comboRampOffset.getSelectedIndex() - (int)(range[1]/2 - 20));
				}
			} 
		});
				
		//** click to change direction of first ramp **//
		lblDeck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				double[] update = guiData.getDeckDimension();
				if (guiData.getRampDir().get(0)%2 == 0 && update[0] != 0){
					guiUtility.setOffsetRangeNumber(comboRampOffset, -(int)(update[0]/2 - 20), (int)update[0]/2 - 20);
					comboRampOffset.setSelectedItem((int)update[0]/2 - 20);
				} else if (guiData.getRampDir().get(0)%2 == 1 && update[0] != 0){
					guiUtility.setOffsetRangeNumber(comboRampOffset, -(int)(update[1]/2 - 20), (int)update[1]/2 - 20);
					comboRampOffset.setSelectedItem((int)update[1]/2 - 20);
				}
			}
		});
		
		
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//************************************************TODO: Add save function ***************************************//
			}
		});
		
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//************************************************TODO: Add save function ***************************************//
			}
		});
		
		
		//** Set Submit button **//
		guiUtility.submitButtonActions(btnSubmit);
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
}