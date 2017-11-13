package oldExamples;
import java.sql.*;
import java.util.ArrayList;

public class Survey {
	public ArrayList<String> tuples = new ArrayList<>();
	private static Interface app;
	private String tableName;

	public Survey(String db, String table) throws SQLException {
		app = new Interface(db);
		String[][] survey = {
				{ "ageGroup", "major", "credits", "studentlevel", "numCaffBev",
						"prefCaff", "numWater", "startTime", "endTime",
						"hourSleep", "hourWork", "business" },
				{ "5", "40", "5", "8", "4", "15", "4", "8", "8", "6", "5", "10" } };
		tableName = table;
		app.databaseConnection(tableName, survey);
	}

	public Survey(String name, String[][] survey, String db) throws SQLException {
		app = new Interface(db);
		this.tableName = name;
		app.databaseConnection(tableName, survey);
	}

	public boolean submit(String[][] att) throws SQLException {
		String attribute = "", values = "";
		for (int i = 0; i < att[0].length; i++) {
			if (i < att[1].length - 1) {
				attribute += att[0][i] + ",";
				values += "'" + att[1][i] + "',";
			} else {
				attribute += att[0][i];
				values += "'" + att[1][i] + "'";
			}
		}
		if (app.run("insert into " + tableName + "(" + attribute + ") values("
				+ values + ");")) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<String> retrieveByNumber(int dataNum) {
		ArrayList<String> tuples = new ArrayList<>();
		try {
			ResultSet rs = app.select("select * from " + tableName
					+ " where dataNum=" + dataNum + ";");

			if (rs != null && rs.first()) {
				for (int i = 2; i <= rs.getMetaData().getColumnCount(); i++)
					tuples.add(rs.getString(i));
				rs.close();
			} else {
				tuples.clear();
			}
			return tuples;
		} catch (SQLException e) {
			return null;
		}
	}

	public ArrayList<ArrayList<String>> retrieveByAttribute(String attribute,
			String searchQuantity) {
		try {
			ArrayList<ArrayList<String>> tuple = new ArrayList<>();

			ResultSet rs = app.select("select * from healthsurvey where "
					+ attribute + "=" + searchQuantity + ";");

			tuple.clear();
			while (rs.next()) {
				ArrayList<String> temp = new ArrayList<>();
				for (int i = 2; i <= rs.getMetaData().getColumnCount(); i++)
					temp.add(rs.getString(i));
				tuple.add(temp);
			}

			return tuple;
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean delete(int dataNum) throws SQLException {
		if (app.run("delete ignore from " + tableName + " where dataNum="
				+ dataNum + ";")) {
			System.out.println("Tuble data has been deleted.");
			return true;
		} else {
			return false;
		}
	}

	public double retievePercent(String attribute, String answer) {

		try {
			double percent;
			ResultSet etable = app.select("select count(" + attribute
					+ ") from " + tableName + ";");

			ResultSet ftable = app.select("select count(" + attribute
					+ ") from " + tableName + " where " + attribute + "='"
					+ answer + "';");

			if (etable.first() && ftable.first())
				percent = (ftable.getDouble(1) / etable.getDouble(1)) * 100;
			else
				percent = 0;
			return percent;

		} catch (SQLException e) {
			return -1.0;
		}
	}
	
	public boolean submitCSV(){
		try{
			String temp = FileExplorer.openFile().replace('\\', '/');
			String str = "LOAD DATA LOCAL INFILE '"+temp+"' "
					+"INTO TABLE "+tableName+" "
					+"FIELDS TERMINATED BY ',' "
					+"ENCLOSED BY '\"' "
					+"LINES TERMINATED BY '\\n';";

			app.run(str);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
