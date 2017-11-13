package oldExamples;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;

import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class RetrieveData extends JFrame {
	private Survey health;
	private JPanel contentPane;
	private JTextField textDataRetrieve;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private final Action Back = new SwingAction();
	private final Action Forward = new SwingAction_1();
	private final Action Retrive = new SwingAction_2();
	private JLabel lblPersentThatAnswered;
	private JTextField textField;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;

	ArrayList<JTextField> textfld;
	private JTextField cMDLineOutput;

	/**
	 * Launch the application.
	 */
	public RetrieveData(Survey a) {
		setTitle("Retrieve Record");
		setResizable(false);
		health = a;
		textfld = new ArrayList<JTextField>();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 722, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textDataRetrieve = new JTextField();
		textDataRetrieve.setText("1");
                textDataRetrieve.setAction(new id());
		textDataRetrieve.setBounds(159, 8, 53, 20);
		contentPane.add(textDataRetrieve);
		textDataRetrieve.setColumns(10);

		JLabel lblEnterDataNumber = new JLabel("Enter data number to retrieve:");
		lblEnterDataNumber.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblEnterDataNumber.setBounds(10, 11, 178, 14);
		contentPane.add(lblEnterDataNumber);

		JTextPane txtpnThisIsThe = new JTextPane();
		txtpnThisIsThe.setFont(new Font("Courier New", Font.PLAIN, 20));
		txtpnThisIsThe.setOpaque(false);
		txtpnThisIsThe.setBackground(new Color(192, 192, 192));
		txtpnThisIsThe.setEditable(false);
		txtpnThisIsThe.setText("" + "Age Group-----------------: \n"
				+ "Major---------------------: \n"
				+ "Credits Taken-------------: \n"
				+ "Student Year--------------: \n"
				+ "Caffinated Drinks per Day-: \n"
				+ "Preferred Caffinated Drink: \n"
				+ "8 ozs of Water a Day------: \n"
				+ "Earliest Class------------: \n"
				+ "End of Work/Class Day-----: \n"
				+ "Hours of Sleep per Night--: \n"
				+ "Work Hours per Week-------: \n"
				+ "Considered Switching------: ");
		txtpnThisIsThe.setBounds(10, 48, 342, 291);
		contentPane.add(txtpnThisIsThe);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBackground(new Color(255, 255, 255));
		textField_1.setBounds(337, 53, 172, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBackground(new Color(255, 255, 255));
		textField_2.setBounds(337, 75, 172, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBackground(new Color(255, 255, 255));
		textField_3.setBounds(337, 99, 172, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBackground(new Color(255, 255, 255));
		textField_4.setBounds(337, 122, 172, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBackground(new Color(255, 255, 255));
		textField_5.setBounds(337, 145, 172, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBackground(new Color(255, 255, 255));
		textField_6.setBounds(337, 168, 172, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBackground(new Color(255, 255, 255));
		textField_7.setBounds(337, 191, 172, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setBackground(new Color(255, 255, 255));
		textField_8.setBounds(337, 214, 172, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);

		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setBackground(new Color(255, 255, 255));
		textField_9.setBounds(337, 237, 172, 20);
		contentPane.add(textField_9);
		textField_9.setColumns(10);

		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setBackground(new Color(255, 255, 255));
		textField_10.setBounds(337, 259, 172, 20);
		contentPane.add(textField_10);
		textField_10.setColumns(10);

		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setBackground(new Color(255, 255, 255));
		textField_11.setBounds(337, 283, 172, 20);
		contentPane.add(textField_11);
		textField_11.setColumns(10);

		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setBackground(new Color(255, 255, 255));
		textField_12.setBounds(337, 307, 172, 20);
		contentPane.add(textField_12);
		textField_12.setColumns(10);

		JButton btnRetrive = new JButton("Retrive");
		btnRetrive.setAction(Retrive);
		btnRetrive.setBounds(337, 7, 89, 23);
		contentPane.add(btnRetrive);

		JButton btnForward = new JButton("<-");
		btnForward.setAction(Back);
		btnForward.setBounds(222, 7, 45, 23);
		contentPane.add(btnForward);

		JButton btnBack = new JButton("->");
		btnBack.setAction(Forward);
		btnBack.setBounds(277, 7, 45, 23);
		contentPane.add(btnBack);

		lblPersentThatAnswered = new JLabel(
				"<html>Percent of people that answered this way.</html>");
		lblPersentThatAnswered.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		lblPersentThatAnswered.setVerticalAlignment(SwingConstants.TOP);
		lblPersentThatAnswered.setBounds(519, 8, 177, 331);
		contentPane.add(lblPersentThatAnswered);

		textField = new JTextField();
		textField.setBounds(530, 53, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_13 = new JTextField();
		textField_13.setBounds(530, 75, 86, 20);
		contentPane.add(textField_13);
		textField_13.setColumns(10);

		textField_14 = new JTextField();
		textField_14.setBounds(530, 99, 86, 20);
		contentPane.add(textField_14);
		textField_14.setColumns(10);

		textField_15 = new JTextField();
		textField_15.setBounds(530, 122, 86, 20);
		contentPane.add(textField_15);
		textField_15.setColumns(10);

		textField_16 = new JTextField();
		textField_16.setBounds(530, 145, 86, 20);
		contentPane.add(textField_16);
		textField_16.setColumns(10);

		textField_17 = new JTextField();
		textField_17.setBounds(530, 168, 86, 20);
		contentPane.add(textField_17);
		textField_17.setColumns(10);

		textField_18 = new JTextField();
		textField_18.setBounds(530, 191, 86, 20);
		contentPane.add(textField_18);
		textField_18.setColumns(10);

		textField_19 = new JTextField();
		textField_19.setBounds(530, 214, 86, 20);
		contentPane.add(textField_19);
		textField_19.setColumns(10);

		textField_20 = new JTextField();
		textField_20.setBounds(530, 237, 86, 20);
		contentPane.add(textField_20);
		textField_20.setColumns(10);

		textField_21 = new JTextField();
		textField_21.setBounds(530, 259, 86, 20);
		contentPane.add(textField_21);
		textField_21.setColumns(10);

		textField_22 = new JTextField();
		textField_22.setBounds(530, 283, 86, 20);
		contentPane.add(textField_22);
		textField_22.setColumns(10);

		textField_23 = new JTextField();
		textField_23.setBounds(530, 307, 86, 20);
		contentPane.add(textField_23);
		textField_23.setColumns(10);

		textfld.add(textField_1);
		textfld.add(textField_2);
		textfld.add(textField_3);
		textfld.add(textField_4);
		textfld.add(textField_5);
		textfld.add(textField_6);
		textfld.add(textField_7);
		textfld.add(textField_8);
		textfld.add(textField_9);
		textfld.add(textField_10);
		textfld.add(textField_11);
		textfld.add(textField_12);
		textfld.add(textField);
		textfld.add(textField_13);
		textfld.add(textField_14);
		textfld.add(textField_15);
		textfld.add(textField_16);
		textfld.add(textField_17);
		textfld.add(textField_18);
		textfld.add(textField_19);
		textfld.add(textField_20);
		textfld.add(textField_21);
		textfld.add(textField_22);
		textfld.add(textField_23);

		cMDLineOutput = new JTextField();
		cMDLineOutput.setFont(new Font("Courier New", Font.PLAIN, 22));
		cMDLineOutput.setBounds(10, 344, 686, 73);
                cMDLineOutput.setEditable(false);
		contentPane.add(cMDLineOutput);
		cMDLineOutput.setColumns(10);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "<-");
			putValue(SHORT_DESCRIPTION, "Brings up previous record.");
		}

		public void actionPerformed(ActionEvent e) {
			SystemOut.disableOutput();
                    boolean was1=false;
			if (Integer.parseInt(textDataRetrieve.getText()) < 2) {
				textDataRetrieve.setText("1");
                                was1=true;
				cMDLineOutput.setText("First Entry Reached.");
			} else {
				textDataRetrieve.setText(""
						+ (Integer.parseInt(textDataRetrieve.getText()) - 1));
			}
			if (!retriveInfo(Integer.parseInt(textDataRetrieve.getText()))) {

				ArrayList<String> queryReturn = health.retrieveByNumber(1);
				if (queryReturn.isEmpty()) {
					cMDLineOutput.setText("No Entries.");
				} else {
					cMDLineOutput.setText("Entry does not exist!");
				}
				clearFields();
			}else if (!was1){
				cMDLineOutput.setText("");
			}
			SystemOut.enableOutput();
		}
	}

	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "->");
			putValue(SHORT_DESCRIPTION, "Brings up next record.");
		}

		public void actionPerformed(ActionEvent e) {
			SystemOut.disableOutput();
			if (Integer.parseInt(textDataRetrieve.getText()) > 99999) {
				textDataRetrieve.setText("99999");
			} else {
				textDataRetrieve.setText(""
						+ (Integer.parseInt(textDataRetrieve.getText()) + 1));
			}
			if (!retriveInfo(Integer.parseInt(textDataRetrieve.getText()))) {

				ArrayList<String> queryReturn = health.retrieveByNumber(1);
				if (queryReturn.isEmpty()) {
					cMDLineOutput.setText("No Entries.");
					clearFields();
				} else {
					cMDLineOutput.setText("Last entry reached.");
					textDataRetrieve.setText(Integer.parseInt(textDataRetrieve
							.getText()) - 1 + "");
				}
			}else {
				cMDLineOutput.setText("");
			}
			SystemOut.enableOutput();
		}
	}

	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Retrieve");
			putValue(SHORT_DESCRIPTION, "Retrives the record of number in box.");
		}

		public void actionPerformed(ActionEvent e) {
                    retrieve();
		}
	}
        
	private class id extends AbstractAction {
		public id() {
			putValue(NAME, "ID");
			putValue(SHORT_DESCRIPTION, "Record ID");
		}

		public void actionPerformed(ActionEvent e) {
                    retrieve();
		}
	}
        
        public void retrieve(){
        	SystemOut.disableOutput();
			if (!retriveInfo(Integer.parseInt(textDataRetrieve.getText()))) {

				ArrayList<String> queryReturn = health.retrieveByNumber(1);
				if (queryReturn.isEmpty()) {
					cMDLineOutput.setText("No Entries.");
				} else {
					cMDLineOutput.setText("Entry does not exist!");
				}
				clearFields();
			}else {
				cMDLineOutput.setText("");
			}
			SystemOut.enableOutput();
        }

	/**
	 * 
	 * @param recNum
	 */
	private boolean retriveInfo(int recNum) {
		ArrayList<String> queryReturn = health.retrieveByNumber(recNum);
		if (queryReturn.isEmpty()) {
			return false;
		}
		for (int i = 0; i < 12; i++) {
			textfld.get(i).setText(queryReturn.get(i));
		}
		textField_2.setCaretPosition(0);
		String[] survey = { "ageGroup", "major", "credits", "studentlevel",
				"numCaffBev", "prefCaff", "numWater", "startTime", "endTime",
				"hourSleep", "hourWork", "business" };
		for (int i = 12; i < 24; i++) {
			textfld.get(i).setText(
					String.format(
							"%02.2f%%",
							health.retievePercent(survey[i - 12],
									queryReturn.get(i - 12))));
		}

		return true;
	}

	private void clearFields() {
		for (int i = 0; i < 24; i++) {
			textfld.get(i).setText("");
		}
	}
}
