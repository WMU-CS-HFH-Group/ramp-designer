package ramp;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTabbedPane;

public class inputdata extends JFrame {

	private JPanel contentPane;
	private JComboBox<Integer> comboFeet_1;
	private JComboBox<Integer> comboInches;
	private JComboBox<String> comboInParts;
	private JPanel panelNoTurns;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblLayout;

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
		
		JLabel lblHeightOfDeck = new JLabel("Height of Deck");
		lblHeightOfDeck.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JComboBox<Integer> comboFeet = setFeet();
		
		JLabel labelFeet = new JLabel("'");
		labelFeet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox<Integer> comboInch = setInches();
				
		JComboBox<String> comboInPart = setInParts();
		
		JLabel label_1 = new JLabel("''");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("220px:grow"),
				ColumnSpec.decode("32px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("3px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("32px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("32px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("10px"),},
			new RowSpec[] {
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("24px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		contentPane.add(lblHeightOfDeck, "2, 2, left, center");
		contentPane.add(comboFeet_1, "3, 2, left, top");
		contentPane.add(labelFeet, "5, 2, left, center");
		contentPane.add(comboInches, "7, 2, left, top");
		contentPane.add(comboInParts, "9, 2, left, top");
		contentPane.add(label_1, "11, 2, left, center");
		
		lblLayout = new JLabel("Layout");
		lblLayout.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(lblLayout, "2, 4");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "2, 6, 9, 1, fill, fill");
		
		panelNoTurns = new JPanel();
		tabbedPane.addTab("No Turns", null, panelNoTurns, null);
		
		panel = new JPanel();
		tabbedPane.addTab("One Turn", null, panel, null);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Two Turns", null, panel_1, null);
	}
	
	//**********set boxes*******************
	public JComboBox<Integer> setFeet(){
		comboFeet_1 = new JComboBox<Integer>();
		for (int i = 0; i < 20; i++) {
			comboFeet_1.addItem(i);
		}
		return comboFeet_1;
	}
	
	public JComboBox<Integer> setInches(){
		comboInches = new JComboBox<Integer>();
		for (int i = 0; i < 12; i++) {
			comboInches.addItem(i);
		}
		return comboInches;
	}
	
	public JComboBox<String> setInParts(){
		comboInParts = new JComboBox<String>();
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

