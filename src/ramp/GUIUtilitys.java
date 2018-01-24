package ramp;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GUIUtilitys <Other>{
	public GUIUtilitys() {
		
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
	public void clearSaveLenght(JTextArea lbl, GUIData data) {
		lbl.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				lbl.setText(data.getLenghth());
				lbl.setBackground(Color.white);
			}
			@Override
			public void focusLost(FocusEvent e) {
				data.setLenghth(lbl.getText());
				lbl.setBackground(Color.GRAY);
			}
		});
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
	public void setRampDirection(int incase, SpringLayout sl_contentPane, Component pivot, JTextArea lblRampHor, JTextArea lblRampVert, JButton btnTurnAr){
		lblRampHor.setVisible(false);
		btnTurnAr.setVisible(false);
		lblRampVert.setVisible(false);
		switch (incase) {
		case 0:
			sl_contentPane.putConstraint(SpringLayout.NORTH, lblRampHor, 45, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.WEST, lblRampHor, -90, SpringLayout.WEST, pivot);
			sl_contentPane.putConstraint(SpringLayout.EAST, lblRampHor, 85, SpringLayout.WEST, pivot);
			sl_contentPane.putConstraint(SpringLayout.NORTH, btnTurnAr, -6, SpringLayout.NORTH, lblRampHor);
			sl_contentPane.putConstraint(SpringLayout.WEST, btnTurnAr, -50, SpringLayout.WEST, lblRampHor);
			sl_contentPane.putConstraint(SpringLayout.EAST, btnTurnAr, 0, SpringLayout.WEST, lblRampHor);
			lblRampHor.setVisible(true);
			btnTurnAr.setVisible(true);
			break;
		case 1:
			sl_contentPane.putConstraint(SpringLayout.NORTH, lblRampHor, 45, SpringLayout.NORTH, pivot);
			sl_contentPane.putConstraint(SpringLayout.WEST, lblRampHor, -55, SpringLayout.EAST, pivot);
			sl_contentPane.putConstraint(SpringLayout.EAST, lblRampHor, 120, SpringLayout.EAST, pivot);
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

}
