package ramp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GUIUtilitys {
	public GUIUtilitys() {
		
	}

	public void removeFocus(JTextArea lbl) {
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

}
