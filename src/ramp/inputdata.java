package ramp;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.FlowLayout;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblHeightOfDeck = new JLabel("Height of Deck");
		lblHeightOfDeck.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblHeightOfDeck);
		
		JComboBox<Integer> comboFeet = setFeet();
		contentPane.add(comboFeet);
		
		JLabel labelFeet = new JLabel("'");
		labelFeet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(labelFeet);
		
		JComboBox<Integer> comboInch = setInches();
		contentPane.add(comboInch);
				
		JComboBox<String> comboInPart = setInParts();
		contentPane.add(comboInPart);
		
		JLabel label_1 = new JLabel("''");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(label_1);
	}
	
	//**********set boxes*******************
	public JComboBox<Integer> setFeet(){
		JComboBox<Integer> comboFeet = new JComboBox<Integer>();
		for (int i = 0; i < 20; i++) {
			comboFeet.addItem(i);
		}
		return comboFeet;
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

