package oldExamples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
//import com.mysql.jdbc.Driver;

/**
 * 
 * @author Devlin
 */
public class Interface {

	private static Connection dbConnection;
//	private static Driver driver;
	private static final String USER = "root";
	public static String PASSWORD = "2222";
	private String dbName;

	public Interface(String db) {
		dbName = db;
		try {
//			driver = new Driver();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		try {

			dbConnection = DriverManager
					.getConnection("jdbc:mysql://localhost/" + db + "?"
							+ "user=" + USER + "&password=" + PASSWORD
							+ "use ssl=false");
			dbConnection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Interface() {
		dbName = "";
		try {
//			driver = new Driver();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		try {
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost"
					+ "?" + "user=" + USER + "&password=" + PASSWORD);
			dbConnection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void databaseConnection(String name, String[][] attributes)
			throws SQLException {
		try{
		String str[] = new String[3];
		str[0] = "create database if not exists " + dbName + ";";
		str[1] = "use " + dbName + ";";

		str[2] = "create table if not exists " + name
				+ " (dataNum integer auto_increment primary key,";
		int temp = attributes[1].length - 1;
		for (int i = 0; i < temp; i++)
			str[2] += attributes[0][i] + "  varchar(" + attributes[1][i] + "),";
		str[2] = str[2] + attributes[0][temp] + "  varchar("
				+ attributes[1][temp] + "));";
		for (int i = 0; i < str.length; i++) {
			this.run(str[i]);
		}
		dbConnection.commit();
		}catch (SQLException e) {
			dbConnection.rollback();
			e.printStackTrace();
		}
	}

	public ResultSet select(String a) {
		Statement s;
		ResultSet r;
		try {
			s = dbConnection.createStatement();
			r = s.executeQuery(a);
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean run(String data) throws SQLException {
		Statement s = null;
		try {
			s = dbConnection.createStatement();
			s.execute(data);
			dbConnection.commit();
		} catch (SQLException e) {
			dbConnection.rollback();
			e.printStackTrace();
			return false;
		}
		try {
			if (s != null) {
				s.close();
			}
		} catch (Exception e) {
		}
		return true;
	}
	
	public void finishUp (){
		try {
			dbConnection.setAutoCommit(true);
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
