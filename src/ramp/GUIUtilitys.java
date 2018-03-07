package ramp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import ramp.diagram.Diagram;
import ramp.diagram.GUIData;
import ramp.geometry.Dimension;

public class GUIUtilitys{
	private GUIData guiData;
	private static JLabel lblFeetRemain;
	
	public GUIUtilitys(GUIData guiData) {
		this.guiData = guiData;
	}
	
	public double arraylistTotal(ArrayList<Double> list) {
		double sum = 0;
		for(int i = 0; i < list.size(); i++)
		    sum += list.get(i);
		return sum;
	}
	
	/****** Adds listener to comboBox ******/
	public void comboChangeTotal(JComboBox comboInch, JComboBox comboInPart){
		comboInch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFeetRemain(0, comboInch.getSelectedIndex(), comboInPart.getSelectedIndex());
			} 
		});
		
		comboInPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFeetRemain(0, comboInch.getSelectedIndex(), comboInPart.getSelectedIndex());
			} 
		});
	}

	/**
	 * Removes focus from object on Enter/Return key for JTextAreas
	 * @param lbl JTextArea to have return remove focus
	 */
	public void removeFocusEnter(JTextArea lbl) {
		InputMap inputMapHor = lbl.getInputMap(0);
		ActionMap actionMapHor = lbl.getActionMap();
		KeyStroke enterStrokeHor = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		inputMapHor.put(enterStrokeHor, enterStrokeHor.toString());
		actionMapHor.put(enterStrokeHor.toString(), new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lbl.transferFocus();
			}
		});
	}
	
	/**
	 * Clears windows on click enabler
	 * @param lbl label to enable
	 * @param data information held in GUIData
	 */
	public void clearSaveLenght(JTextArea lbl, int index) {
		lbl.addFocusListener(new FocusAdapter() {			
			@Override
			public void focusGained(FocusEvent arg0) {
				ArrayList<Double> update = guiData.getRampLength();
				Dimension convert = new Dimension(update.get(index));
				lbl.setText(convert.toString());
				lbl.setBackground(Color.white);
			}
			@Override
			public void focusLost(FocusEvent e) {
				ArrayList<Double> update = guiData.getRampLength();
				Dimension convert = new Dimension(lbl.getText());
				update.set(index, convert.getLength());
				guiData.setRampLength(update);
				lbl.setBackground(Color.GRAY);
				guiData.setUsedIn(arraylistTotal(update));  
				if((guiData.getRampLengthTotal()/12 - guiData.getUsedIn()/12) < 0){
					lblFeetRemain.setText("Ramp feet remaining: 0.0");
				} else{
					lblFeetRemain.setText("Ramp feet remaining: " + (guiData.getRampLengthTotal()/12 - guiData.getUsedIn()/12));
				}
			}
		});
	}
	
	
	public void setRamps(JPanel contentPane, SpringLayout sl_contentPane, JTextArea lblRampHor, JButton btnTurnAr, JLabel lblFeetRemain, int index) {
		ArrayList<Double> update = guiData.getRampLength();
		ArrayList<Integer> turnaround = guiData.getTurnAround();
		if (update.size() <= index) {
			update.add(index, 0.0);
			guiData.setRampLength(update);
			turnaround.add(index, 48);
		}
		
		lblRampHor.setText("#' #\"");
		lblRampHor.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRampHor.setLineWrap(true);
		lblRampHor.setWrapStyleWord(true);
		lblRampHor.setVisible(false);
		contentPane.add(lblRampHor);
				
		btnTurnAr.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/turnAround.png")));
		btnTurnAr.setVisible(false);
		contentPane.add(btnTurnAr);
		
		//** Set length of ramp piece **
		//**** Un-focus from enter length on enter ****
		removeFocusEnter(lblRampHor);
		//**** End Un-focus from enter length on enter ****
		//**** https://stackoverflow.com/questions/19569302/jtextarea-pressing-enter-adds-unnecessary-new-line ****
		
		//**** Clears/Saves input in bar ****
		clearSaveLenght(lblRampHor, index);
		//**** End Clears/Saves input in bar ****
		//** End Set length of ramp piece **//
		
		//** adds extra ramps on base click **//************************************************************************************************
		createRamp(btnTurnAr, contentPane, sl_contentPane, lblFeetRemain, index);
	}
	
	/**
	 * Basic turn around not including U-turn
	 * @param incase         Case 0 is left, Case 1 is right, any other Case is down
	 * @param sl_contentPane Pane that contains the ramp
	 * @param pivot          What the labels pivot around    
	 * @param lblRampHor     
	 * @param lblRampVert
	 * @param btnTurnAr
	 */
	public void setRampDirection(int incase, SpringLayout sl_contentPane, Component pivot, JTextArea lblRamp, JButton btnTurnAr, int index){
		lblRamp.setVisible(false);
		btnTurnAr.setVisible(false);
		ArrayList<Integer> update = guiData.getRampDir();
		if (update.size() <= index) {
			update.add(index, 0);	
		}
		update.set(index, incase);
		guiData.setRampDir(update);
		switch (incase) {
		case 0: //Up
			sl_contentPane.putConstraint(SpringLayout.NORTH, lblRamp, -100, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.WEST, lblRamp, pivot.getWidth()/2 - 15, SpringLayout.WEST, pivot);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, lblRamp, 0, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.EAST, lblRamp, pivot.getWidth()/2 + 15, SpringLayout.WEST, pivot);
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnTurnAr, -50, SpringLayout.NORTH, lblRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnTurnAr, -10, SpringLayout.WEST, lblRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnTurnAr, 0, SpringLayout.NORTH, lblRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnTurnAr, 10, SpringLayout.EAST, lblRamp);
			break;
		case 1: //Right
			sl_contentPane.putConstraint(SpringLayout.NORTH, lblRamp, pivot.getHeight()/2 - 15, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.WEST, lblRamp, 0, SpringLayout.EAST, pivot);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, lblRamp, pivot.getHeight()/2 + 15, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.EAST, lblRamp, 100, SpringLayout.EAST, pivot);
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnTurnAr, -10, SpringLayout.NORTH, lblRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnTurnAr, 0, SpringLayout.EAST, lblRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnTurnAr, 10, SpringLayout.SOUTH, lblRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnTurnAr, 50, SpringLayout.EAST, lblRamp);			
			break;
		case 2: //Down
			sl_contentPane.putConstraint(SpringLayout.NORTH, lblRamp, 0, SpringLayout.SOUTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.WEST, lblRamp, pivot.getWidth()/2 - 15, SpringLayout.WEST, pivot);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, lblRamp, 100, SpringLayout.SOUTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.EAST, lblRamp, pivot.getWidth()/2 + 15, SpringLayout.WEST, pivot);
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnTurnAr, 0, SpringLayout.SOUTH, lblRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnTurnAr, -10, SpringLayout.WEST, lblRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnTurnAr, 50, SpringLayout.SOUTH, lblRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnTurnAr, 10, SpringLayout.EAST, lblRamp);
			break;
		default: //Left
			sl_contentPane.putConstraint(SpringLayout.NORTH, lblRamp, pivot.getHeight()/2 - 15, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.WEST, lblRamp, -100, SpringLayout.WEST, pivot);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, lblRamp, pivot.getHeight()/2 + 15, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.EAST, lblRamp, 0, SpringLayout.WEST, pivot);
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnTurnAr, -10, SpringLayout.NORTH, lblRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnTurnAr, -50, SpringLayout.WEST, lblRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnTurnAr, 10, SpringLayout.SOUTH, lblRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnTurnAr, 0, SpringLayout.WEST, lblRamp);
			break;
		}
		lblRamp.setVisible(true);
		btnTurnAr.setVisible(true);
		
	}
	
	/**
	 * Basic ramp adder
	 * @param pivot
	 * @param contentPane
	 * @param sl_contentPane
	 */
	public void createRamp(JButton pivot, JPanel contentPane, SpringLayout sl_contentPane, JLabel lblFeetRemain, int index) {		
		pivot.addMouseListener(new MouseAdapter() {
			JTextArea lblRamp = new JTextArea();
			JButton btnTurnAr = new JButton();	
			int direction = -1;
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				switch (direction){
				case -1:
					setRamps(contentPane, sl_contentPane, lblRamp, btnTurnAr, lblFeetRemain, index+1);					
					setRampDirection(direction+1, sl_contentPane, pivot, lblRamp, btnTurnAr, index+1);
					direction+=2;
				case 0:
				case 1:
				case 2:
				case 3:
					setRampDirection(direction, sl_contentPane, pivot, lblRamp, btnTurnAr, index);
					direction++;
					if (direction>3) {
						direction = 0;
					}
					break;
				default:
					break;
				}
			}
		});
	}
	
	/**
	 * Actions for the submit button
	 * @param btnSubmit
	 */
	public void submitButtonActions(JButton btnSubmit) {
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					Diagram diagram = new Diagram(guiData);
					diagram.launch();
				} catch (Exception bad) {
					// TODO: pop up saying something went wrong
				}
			}
		});
	}
	
	/**
	 * Sets combo box range
	 * @param combo JComboBox to add range to
	 * @param begin start of range
	 * @param end end of range
	 */
	public void setComboBoxRangeNumber(JComboBox<Integer> combo, int begin, int end){
		for (int i = begin; i <= end; i++) {
			combo.addItem(i);
		}
	}
	
	//*******************changes feet, in, parts to Inches**********************//
	public double calcTotalIn(int comboFeet, int comboInch, int comboInPart){
		double calcIn = (float) (12.0 * (int) comboFeet);
		calcIn += (float) comboInch;
		calcIn += (float) (.125 * (int) comboInPart);
		return calcIn;
	}

	public static JLabel getLblFeetRemain() {
		return lblFeetRemain;
	}

	public static void setLblFeetRemain(JLabel lblFeetRemain) {
		GUIUtilitys.lblFeetRemain = lblFeetRemain;
	}

	private void updateFeetRemain(int feet, int inch, int inPart){
		double update = calcTotalIn(feet, inch, inPart);
		guiData.setDeckHeight(update);
		guiData.setRampLengthTotal(update*12);
		lblFeetRemain.setText("Ramp feet remaining: " + (update - guiData.getUsedIn()/12));
	}
}
