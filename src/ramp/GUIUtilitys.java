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
import ramp.diagram.DiagramFrame;
import ramp.diagram.GUIData;
import ramp.geometry.Dimension;

public class GUIUtilitys{
	private GUIData guiData;
	private static JLabel lblFeetRemain;
	
	/**
	 * Basic constructor for the GUIUtility class
	 * @param guiData data container for program
	 */
	public GUIUtilitys(GUIData guiData) {
		this.guiData = guiData;
	}
	
	/**
	 * Finds the total of the array list containing the ramp lengths
	 * @param list    Array list containing the ramp lengths
	 * @return double Sum total of all ramp lengths
	 */
	public double arraylistTotal(ArrayList<Double> list) {
		double sum = 0;
		for(int i = 0; i < list.size(); i++)
		    sum += list.get(i);
		return sum;
	}
	
	/**
	 * Adds listener to comboBox
	 * @param comboInch   Inches of the deck in height
	 * @param comboInPart Eight of an inch of the deck height
	 */
	public void comboChangeTotal(JComboBox<Integer> comboInch, JComboBox<String> comboInPart){
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
	 * https://stackoverflow.com/questions/19569302/jtextarea-pressing-enter-adds-unnecessary-new-line
	 * @param lbl JTextArea to have return remove focus
	 */
	public void removeFocusEnter(JTextArea lbl) {
		InputMap inputMapHor = lbl.getInputMap(0);
		ActionMap actionMapHor = lbl.getActionMap();
		KeyStroke enterStrokeHor = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		inputMapHor.put(enterStrokeHor, enterStrokeHor.toString());
		actionMapHor.put(enterStrokeHor.toString(), new AbstractAction() {
			/**
			 * used for the abstract action
			 */
			private static final long serialVersionUID = 1L;

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
	public void updateRAmpText(JTextArea lbl, int index) {
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
	
	/**
	 * Changes ramp configuration for non-U-turns
	 * @param incase         Direction for ramp to point
	 * @param sl_contentPane Used to determine position of ramps
	 * @param pivot          What the content is pivoting around
	 * @param nextRamp        Ramp with length data
	 * @param btnAdd      Button to create new ramps
	 */
	private void setTurnAround(int incase, SpringLayout sl_contentPane, Component pivot, JTextArea preRamp, 
			JTextArea nextRamp, JButton btnAdd, JButton btnRemove) {
		if (preRamp != null)
		if (preRamp.getWidth() > 50){ //comes in right/left
			if(preRamp.getBounds().x > pivot.getBounds().x){ //comes from right
				sl_contentPane.putConstraint(SpringLayout.NORTH, pivot, -10, SpringLayout.NORTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.WEST, pivot, -50, SpringLayout.WEST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, pivot, 10, SpringLayout.SOUTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 0, SpringLayout.WEST, preRamp);
			} else { //comes from left
				sl_contentPane.putConstraint(SpringLayout.NORTH, pivot, -10, SpringLayout.NORTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.WEST, pivot, 0, SpringLayout.EAST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, pivot, 10, SpringLayout.SOUTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 50, SpringLayout.EAST, preRamp);
			}
		}else { //comes in up/down
			if (preRamp.getBounds().y > pivot.getBounds().y){//comes from up
				sl_contentPane.putConstraint(SpringLayout.NORTH, pivot, -50, SpringLayout.NORTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.WEST, pivot, -10, SpringLayout.WEST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, pivot, 0, SpringLayout.NORTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 10, SpringLayout.EAST, preRamp);				
			}else{//comes from down
				sl_contentPane.putConstraint(SpringLayout.NORTH, pivot, 0, SpringLayout.SOUTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.WEST, pivot, -10, SpringLayout.WEST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, pivot, 50, SpringLayout.SOUTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 10, SpringLayout.EAST, preRamp);
			}
		}
		
		switch (incase) {
		case 0: //Up
			sl_contentPane.putConstraint(SpringLayout.NORTH, nextRamp, -100, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.WEST, nextRamp, pivot.getWidth()/2 - 15, SpringLayout.WEST, pivot);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, nextRamp, 0, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.EAST, nextRamp, pivot.getWidth()/2 + 15, SpringLayout.WEST, pivot);
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdd, -32, SpringLayout.NORTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, -16, SpringLayout.WEST, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAdd, 0, SpringLayout.NORTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnAdd, -16, SpringLayout.EAST, nextRamp);
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnRemove, -32, SpringLayout.NORTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnRemove, 16, SpringLayout.WEST, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnRemove, 0, SpringLayout.NORTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnRemove, 16, SpringLayout.EAST, nextRamp);
			break;
		case 1: //Right
			sl_contentPane.putConstraint(SpringLayout.NORTH, nextRamp, pivot.getHeight()/2 - 15, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.WEST, nextRamp, 0, SpringLayout.EAST, pivot);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, nextRamp, pivot.getHeight()/2 + 15, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.EAST, nextRamp, 100, SpringLayout.EAST, pivot);
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdd, -16, SpringLayout.NORTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, 0, SpringLayout.EAST, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAdd, -16, SpringLayout.SOUTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnAdd, 30, SpringLayout.EAST, nextRamp);
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnRemove, 16, SpringLayout.NORTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnRemove, 0, SpringLayout.EAST, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnRemove, 16, SpringLayout.SOUTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnRemove, 30, SpringLayout.EAST, nextRamp);
			break;
		case 2: //Down
			sl_contentPane.putConstraint(SpringLayout.NORTH, nextRamp, 0, SpringLayout.SOUTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.WEST, nextRamp, pivot.getWidth()/2 - 15, SpringLayout.WEST, pivot);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, nextRamp, 100, SpringLayout.SOUTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.EAST, nextRamp, pivot.getWidth()/2 + 15, SpringLayout.WEST, pivot);
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdd, 0, SpringLayout.SOUTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, -16, SpringLayout.WEST, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAdd, 30, SpringLayout.SOUTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnAdd, -16, SpringLayout.EAST, nextRamp);
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnRemove, 0, SpringLayout.SOUTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnRemove, 16, SpringLayout.WEST, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnRemove, 30, SpringLayout.SOUTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnRemove,16, SpringLayout.EAST, nextRamp);
			break;
		default: //Left
			sl_contentPane.putConstraint(SpringLayout.NORTH, nextRamp, pivot.getHeight()/2 - 15, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.WEST, nextRamp, -100, SpringLayout.WEST, pivot);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, nextRamp, pivot.getHeight()/2 + 15, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.EAST, nextRamp, 0, SpringLayout.WEST, pivot);
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdd, -16, SpringLayout.NORTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, -30, SpringLayout.WEST, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAdd, -16, SpringLayout.SOUTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnAdd, 0, SpringLayout.WEST, nextRamp);
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnRemove, 16, SpringLayout.NORTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnRemove, -30, SpringLayout.WEST, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnRemove, 16, SpringLayout.SOUTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnRemove, 0, SpringLayout.WEST, nextRamp);
			break;
		}
	}
	
	/**
	 * Used for updating ramps with U-turns
	 * @param incase         Direction of ramp
	 * @param sl_contentPane Used to position objects
	 * @param pivot          Pivot point of ramp
	 * @param preRamp        Previous ramp for fixing pivot
	 * @param nextRamp        Next ramp that holds length data
	 * @param btnAdd      Button to create new ramp
	 */
	private int setUturn(int incase, SpringLayout sl_contentPane, Component pivot, JTextArea preRamp, JToggleButton toggle, 
			JTextArea nextRamp, JButton btnAdd, JButton btnRemove) {
		int out = 1;
		switch (incase) {
		case 0: //Up
			if (toggle.isSelected()){ //right
				sl_contentPane.putConstraint(SpringLayout.NORTH, pivot, 0, SpringLayout.SOUTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.WEST, pivot, -10, SpringLayout.WEST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, pivot, 50, SpringLayout.SOUTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 80, SpringLayout.EAST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.NORTH, nextRamp, -100, SpringLayout.NORTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.WEST, nextRamp, -40, SpringLayout.EAST, pivot);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, nextRamp, 0, SpringLayout.NORTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.EAST, nextRamp, -10, SpringLayout.EAST, pivot);
				out = 1;
			} else { //left
				sl_contentPane.putConstraint(SpringLayout.NORTH, pivot, 0, SpringLayout.SOUTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.WEST, pivot, -80, SpringLayout.WEST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, pivot, 50, SpringLayout.SOUTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 10, SpringLayout.EAST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.NORTH, nextRamp, -100, SpringLayout.NORTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.WEST, nextRamp, 10, SpringLayout.WEST, pivot);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, nextRamp, 0, SpringLayout.NORTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.EAST, nextRamp, 40, SpringLayout.WEST, pivot);
				out = 2;
			}
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdd, -30, SpringLayout.NORTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, -2, SpringLayout.WEST, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAdd, 0, SpringLayout.NORTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnAdd, 2, SpringLayout.EAST, nextRamp);
			break;
		case 1: //Right
			if (toggle.isSelected()){ //down
				sl_contentPane.putConstraint(SpringLayout.NORTH, pivot, -10, SpringLayout.NORTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.WEST, pivot, -50, SpringLayout.WEST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, pivot, 80, SpringLayout.SOUTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 0, SpringLayout.WEST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.NORTH, nextRamp, -40, SpringLayout.SOUTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.WEST, nextRamp, 0, SpringLayout.EAST, pivot);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, nextRamp, -10, SpringLayout.SOUTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.EAST, nextRamp, 100, SpringLayout.EAST, pivot);
				out = 8;
			} else { //up
				sl_contentPane.putConstraint(SpringLayout.NORTH, pivot, -80, SpringLayout.NORTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.WEST, pivot, -50, SpringLayout.WEST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, pivot, 10, SpringLayout.SOUTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 0, SpringLayout.WEST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.NORTH, nextRamp, 10, SpringLayout.NORTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.WEST, nextRamp, 0, SpringLayout.EAST, pivot);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, nextRamp, 40, SpringLayout.NORTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.EAST, nextRamp, 100, SpringLayout.EAST, pivot);
				out = 7;
			}
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdd, -2, SpringLayout.NORTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, 0, SpringLayout.EAST, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAdd, 2, SpringLayout.SOUTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnAdd, 30, SpringLayout.EAST, nextRamp);			
			break;
		case 2: //Down
			if (toggle.isSelected()){ //right
				sl_contentPane.putConstraint(SpringLayout.NORTH, pivot, -50, SpringLayout.NORTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.WEST, pivot, -10, SpringLayout.WEST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, pivot, 00, SpringLayout.NORTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 80, SpringLayout.EAST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.NORTH, nextRamp, 00, SpringLayout.SOUTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.WEST, nextRamp, -40, SpringLayout.EAST, pivot);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, nextRamp, 100, SpringLayout.SOUTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.EAST, nextRamp, -10, SpringLayout.EAST, pivot);
				out = 3;
			} else { //left
				sl_contentPane.putConstraint(SpringLayout.NORTH, pivot, -50, SpringLayout.NORTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.WEST, pivot, -80, SpringLayout.WEST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, pivot, 00, SpringLayout.NORTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 10, SpringLayout.EAST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.NORTH, nextRamp, 00, SpringLayout.SOUTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.WEST, nextRamp, 10, SpringLayout.WEST, pivot);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, nextRamp, 100, SpringLayout.SOUTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.EAST, nextRamp, 40, SpringLayout.WEST, pivot);
				out = 4;
			}
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdd, 0, SpringLayout.SOUTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, -2, SpringLayout.WEST, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAdd, 30, SpringLayout.SOUTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnAdd, 2, SpringLayout.EAST, nextRamp);
			break;
		default: //Left
			if (toggle.isSelected()){ //down
				sl_contentPane.putConstraint(SpringLayout.NORTH, pivot, -10, SpringLayout.NORTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 0, SpringLayout.EAST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, pivot, 80, SpringLayout.SOUTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 50, SpringLayout.EAST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.NORTH, nextRamp, -40, SpringLayout.SOUTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.WEST, nextRamp, -100, SpringLayout.WEST, pivot);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, nextRamp, -10, SpringLayout.SOUTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.EAST, nextRamp, 00, SpringLayout.WEST, pivot);
				out = 2;
			} else { //up
				sl_contentPane.putConstraint(SpringLayout.NORTH, pivot, -80, SpringLayout.NORTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 0, SpringLayout.EAST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, pivot, 10, SpringLayout.SOUTH, preRamp);
				sl_contentPane.putConstraint(SpringLayout.EAST, pivot, 50, SpringLayout.EAST, preRamp);
				sl_contentPane.putConstraint(SpringLayout.NORTH, nextRamp, 10, SpringLayout.NORTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.WEST, nextRamp, -100, SpringLayout.WEST, pivot);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, nextRamp, 40, SpringLayout.NORTH, pivot);
				sl_contentPane.putConstraint(SpringLayout.EAST, nextRamp, 00, SpringLayout.WEST, pivot);
				out = 1;
			}
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnAdd, -2, SpringLayout.NORTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, -30, SpringLayout.WEST, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAdd, 2, SpringLayout.SOUTH, nextRamp);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnAdd, 0, SpringLayout.WEST, nextRamp);
			break;
		}
		return out;
	}	
		
	/**
	 * Basic turn around not including U-turn
	 * @param incase         Case 0 is left, Case 1 is right, any other Case is down
	 * @param sl_contentPane Pane that contains the ramp
	 * @param pivot          What the labels pivot around    
	 * @param lblRamp        Label for taking in ramp length
	 * @param btnAdd      Button for creating a new turn around
	 */
	public void setRampDirection(int incase, SpringLayout sl_contentPane, Component pivot, JTextArea preRamp, 
			JToggleButton toggle, JTextArea lblRamp, JButton btnAdd, JButton btnRemove, int index){
		lblRamp.setVisible(false);
		btnAdd.setVisible(false);
		btnRemove.setVisible(false);
		ArrayList<Integer> rampDir = guiData.getRampDir();
		ArrayList<Integer> turn = guiData.getTurnAround();
		rampDir.set(index, incase);
		
		if (index > 0){
			if ((rampDir.get(index)-rampDir.get(index-1))%2 == 0 && rampDir.get(index) != rampDir.get(index-1)) {
				if (incase == 0 || incase == 2){
					((JButton) pivot).setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/turnUHorizontal.png")));
					if (incase == 0){
						sl_contentPane.putConstraint(SpringLayout.NORTH, toggle, 3, SpringLayout.SOUTH, pivot);
						sl_contentPane.putConstraint(SpringLayout.WEST, toggle, pivot.getWidth()/2 - 25, SpringLayout.WEST, pivot);
						sl_contentPane.putConstraint(SpringLayout.SOUTH, toggle, 23, SpringLayout.SOUTH, pivot);
						sl_contentPane.putConstraint(SpringLayout.EAST, toggle, -pivot.getWidth()/2 + 25, SpringLayout.EAST, pivot);
					} else {
						sl_contentPane.putConstraint(SpringLayout.NORTH, toggle, -23, SpringLayout.NORTH, pivot);
						sl_contentPane.putConstraint(SpringLayout.WEST, toggle, pivot.getWidth()/2 - 25, SpringLayout.WEST, pivot);
						sl_contentPane.putConstraint(SpringLayout.SOUTH, toggle, -3, SpringLayout.NORTH, pivot);
						sl_contentPane.putConstraint(SpringLayout.EAST, toggle, -pivot.getWidth()/2 + 25, SpringLayout.EAST, pivot);
					}
				}else{
					((JButton) pivot).setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/turnUVertical.png")));
					if (incase == 1){
						sl_contentPane.putConstraint(SpringLayout.NORTH, toggle, pivot.getHeight()/2 - 11, SpringLayout.NORTH, pivot);
						sl_contentPane.putConstraint(SpringLayout.WEST, toggle, -53, SpringLayout.WEST, pivot);
						sl_contentPane.putConstraint(SpringLayout.SOUTH, toggle, -pivot.getHeight()/2 + 11, SpringLayout.SOUTH, pivot);
						sl_contentPane.putConstraint(SpringLayout.EAST, toggle, -3, SpringLayout.WEST, pivot);
					} else {
						sl_contentPane.putConstraint(SpringLayout.NORTH, toggle, pivot.getHeight()/2 - 11, SpringLayout.NORTH, pivot);
						sl_contentPane.putConstraint(SpringLayout.WEST, toggle, 3, SpringLayout.EAST, pivot);
						sl_contentPane.putConstraint(SpringLayout.SOUTH, toggle, -pivot.getHeight()/2 + 11, SpringLayout.SOUTH, pivot);
						sl_contentPane.putConstraint(SpringLayout.EAST, toggle, 53, SpringLayout.EAST, pivot);
					}
				}
				toggle.setVisible(true);
				turn.set(index, setUturn(incase, sl_contentPane, pivot, preRamp, toggle, lblRamp, btnAdd, btnRemove));
				toggle.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try{
							lblRamp.setVisible(false);
							btnAdd.setVisible(false);
							turn.set(index, setUturn(incase, sl_contentPane, pivot, preRamp, toggle, lblRamp, btnAdd, btnRemove));
							guiData.setTurnAround(turn);
							lblRamp.setVisible(true);
							btnAdd.setVisible(true);
						} catch (Exception bad) {
							// TODO: pop up saying something went wrong
						}
					}
				});
			} else {
				turn.set(index, 0);
				((JButton) pivot).setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/turnAround.png")));
				toggle.setVisible(false);
				setTurnAround(incase, sl_contentPane, pivot, preRamp, lblRamp, btnAdd, btnRemove);
			}
		} else {
			setTurnAround(incase, sl_contentPane, pivot, preRamp, lblRamp, btnAdd, btnRemove);
		}
		
		guiData.setRampDir(rampDir);
		guiData.setTurnAround(turn);
		lblRamp.setVisible(true);
		btnAdd.setVisible(true);
		btnRemove.setVisible(true);
	}
	
	/**
	 * Basic ramp adder
	 * @param pivot
	 * @param contentPane
	 * @param sl_contentPane
	 */
	public void createRamp(JButton pivot, JTextArea preRamp, JToggleButton toggle, JPanel contentPane, 
			SpringLayout sl_contentPane, JLabel lblFeetRemain, int index) {		
		pivot.addMouseListener(new MouseAdapter() {
			JTextArea lblRamp = new JTextArea();
			JButton btnAdd = new JButton();	
			JButton btnRemove = new JButton();
			int direction = -1;
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				switch (direction){
				case -1:
					setRamps(contentPane, sl_contentPane, lblRamp, btnAdd, btnRemove, lblFeetRemain, index+1);			
					direction++;
				case 0:
				case 1:
				case 2:
				case 3:
					setRampDirection(direction, sl_contentPane, pivot, preRamp, toggle, lblRamp, btnAdd, btnRemove, index+1);
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
	 * Sets initial ramp information and listeners
	 * @param contentPane Pane that the ramps and turn around
	 * @param sl_contentPane used for placing content in specific locations
	 * @param lblRamp Ramp label used for taking in length information
	 * @param btnAdd button used to create new ramps
	 * @param lblFeetRemain How many feet left until ground
	 * @param index Which section of ramp is being used
	 */
	public void setRamps(JPanel contentPane, SpringLayout sl_contentPane, JTextArea lblRamp, JButton btnAdd, JButton btnRemove,
			JLabel lblFeetRemain, int index) {
		ArrayList<Double> rampLength = guiData.getRampLength();
		ArrayList<Integer> turnAround = guiData.getTurnAround();
		ArrayList<Integer> rampDir = guiData.getRampDir();
		if (rampLength.size() <= index) {
			rampLength.add(index, 0.0);
			guiData.setRampLength(rampLength);
			turnAround.add(index, 0);
			guiData.setTurnAround(turnAround);
			rampDir.add(index, 0);
			guiData.setRampDir(rampDir);
		}
		
		lblRamp.setText("#' #\"");
		lblRamp.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRamp.setLineWrap(true);
		lblRamp.setWrapStyleWord(true);
		lblRamp.setVisible(false);
		contentPane.add(lblRamp);
				
		btnAdd.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/Add.png")));
		btnAdd.setVisible(false);
		contentPane.add(btnAdd);
		
		btnRemove.setIcon(new ImageIcon(inputdata.class.getResource("/ramp/Images/Remove.png")));
		btnRemove.setVisible(false);
		contentPane.add(btnRemove);
		
		JToggleButton tglbtnToggle = new JToggleButton("Side");
		tglbtnToggle.setVisible(false);
		tglbtnToggle.setFont(new Font("Dialog", Font.PLAIN, 8));
		contentPane.add(tglbtnToggle);
		
		removeFocusEnter(lblRamp);
		updateRAmpText(lblRamp, index);
		createRamp(btnAdd, lblRamp, tglbtnToggle, contentPane, sl_contentPane, lblFeetRemain, index);
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
					DiagramFrame sideView = new DiagramFrame(guiData, true);
					sideView.setTitle("Ramp Diagram - Side View");
					sideView.setVisible(true);
					
					DiagramFrame frame = new DiagramFrame(guiData, false);
					frame.setTitle("Ramp Diagram");
					frame.setVisible(true);
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
	
	/**
	 * Calculates total inches based on feet, inches, and parts of inches
	 * @param feet    number of feet to convert
	 * @param inch    number of inches to add to
	 * @param inPart  number of eight of an inch to convert
	 * @return double number of inches
	 */
	public double calcTotalIn(int comboFeet, int comboInch, int comboInPart){
		double calcIn = (float) (12.0 * (int) comboFeet);
		calcIn += (float) comboInch;
		calcIn += (float) (.125 * (int) comboInPart);
		return calcIn;
	}

	/**
	 * Method for updating GUIData with information
	 * @param feet   Amount of feet
	 * @param inch   Amount of inches
	 * @param inPart Amount of eight of inches
	 */
	private void updateFeetRemain(int feet, int inch, int inPart){
		double update = calcTotalIn(feet, inch, inPart);
		guiData.setDeckHeight(update);
		guiData.setRampLengthTotal(update*12);
		lblFeetRemain.setText("Ramp feet remaining: " + (update - guiData.getUsedIn()/12));
	}
	
	/**
	 * Sets the label for feet remaining so it can be updated in this method
	 * @param lblFeetRemain label to output feet remaining
	 */
	public void setLblFeetRemain(JLabel lblFeetRemain) {
		GUIUtilitys.lblFeetRemain = lblFeetRemain;
	}
}
