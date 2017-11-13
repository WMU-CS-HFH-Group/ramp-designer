package oldExamples;
/**
 * 
 * 
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class Main {

	private JFrame MainFrame;
	private final Action AddData = new SwingAction();
	private final Action RetrieveData = new SwingAction_1();
	private final ActionListener chooseDB = new SwingActionDB();
	private final JComboBox<String> comboDB = new JComboBox<String>();
	private final JComboBox<String> comboTable = new JComboBox<String>();
	
	//Interface connection;
//	ResultSet rs;
//	private String dBSelection = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SystemOut.disableOutput();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.MainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//outputStream.enableOutput();
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Main() throws SQLException {
		initialize();
		SystemOut.enableOutput();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		MainFrame = new JFrame();
		MainFrame.setTitle("SQL GUI");
		MainFrame.setResizable(false);
		MainFrame.setBounds(100, 100, 350, 255);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.getContentPane().setLayout(null);

		JButton btnAddData = new JButton("Add Data");
		btnAddData.setAction(AddData);
		btnAddData.setBounds(10, 11, 150, 70);
		MainFrame.getContentPane().add(btnAddData);

		JButton btnRetriveData = new JButton("Retrive Data");
		btnRetriveData.setAction(RetrieveData);
		btnRetriveData.setBounds(170, 11, 150, 70);
		MainFrame.getContentPane().add(btnRetriveData);

		comboDB.setBounds(100, 116, 140, 30);
		//Interface.PASSWORD = JOptionPane.showInputDialog(null, "Enter Password for Root");
		//connection = new Interface();
		//rs = connection.select("show databases;");
		comboDB.addItem("[Add New]");
		for (int i = 1; i < 12; i++) {
			comboDB.addItem("item" + i);
		}
		comboDB.addActionListener(chooseDB);
		MainFrame.getContentPane().add(comboDB);
		
		comboTable.setBounds(100, 181, 140, 20);
		
		MainFrame.getContentPane().add(comboTable);

		JLabel lblDatabaseChoice = new JLabel("Database Choice");
		lblDatabaseChoice.setBounds(100, 92, 140, 14);
		MainFrame.getContentPane().add(lblDatabaseChoice);
		
		JLabel lblTableName = new JLabel("Table Name");
		lblTableName.setBounds(100, 156, 80, 14);
		MainFrame.getContentPane().add(lblTableName);
		
	}
	
	public class SwingActionDB extends AbstractAction {
		public SwingActionDB() {
			putValue(NAME, "Read DAtabase");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
//			dBSelection = (String)comboDB.getSelectedItem();
			try {
//				connection.run("use "+dBSelection+";");
//				rs.close();
//				rs = connection.select("show tables;");
				comboTable.removeAllItems();
				comboTable.addItem("[New Table]");
				comboTable.addItem("[Load New]");
				for (int i = 1; i < 12; i++) {
					comboTable.addItem("combo" + i);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
    }

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Add Data");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			if(comboTable.getSelectedIndex() == 0){
				JOptionPane.showInputDialog(null, "Option not Implemented Yet");
			} else if(comboTable.getSelectedIndex() == 1){
				String table = JOptionPane.showInputDialog(null, "Enter new table name.");
				SystemOut.disableOutput();
				try {
					Survey survey = new Survey((String) comboDB.getSelectedItem(),table);
					AddData frame = new AddData(survey);
					survey.submitCSV();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				SystemOut.enableOutput();
			} else {
				SystemOut.disableOutput();
				try {
					AddData frame = new AddData(new Survey((String) comboDB.getSelectedItem(),
							(String) comboTable.getSelectedItem()));
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				SystemOut.enableOutput();
			}
		}
	}

	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Retrieve Data");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			if (comboTable.getSelectedIndex() == 0) {
				JOptionPane.showInputDialog(null, "Cannot retrive from what does not exist.");
			} else if(comboTable.getSelectedIndex() == 1){
				String table = JOptionPane.showInputDialog(null, "Enter new table name.");
				//SystemOut.disableOutput();
				try {
					Survey survey = new Survey((String) comboDB.getSelectedItem(),table);
					RetrieveData frame = new RetrieveData(survey);
					survey.submitCSV();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				SystemOut.enableOutput();
			}else {
				SystemOut.disableOutput();
				try {
					RetrieveData frame = new RetrieveData(new Survey((String) comboDB.getSelectedItem(),
							(String) comboTable.getSelectedItem()));
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				SystemOut.enableOutput();
			}
		}
	}
}
