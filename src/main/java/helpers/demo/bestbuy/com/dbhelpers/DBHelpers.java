package demo.bestbuy.com.dbhelpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import demo.bestbuy.com.constants.BDDConstants;

/**
 * This class contains the methods related to DB Connection
 * 
 * @author Lavendra Kumar Rajput
 *
 * @Date 2 July 2021
 */
public class DBHelpers {

	/**
	 * Read the script content from .sql file
	 * 
	 * @param scriptPath : Path of script
	 * @return String content of script
	 * @author Lavendra rajput
	 */
	public static String getDBScript(String scriptPath) {
		String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
		Reader reader = null;
		try {
			reader = new BufferedReader(new FileReader(scriptPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder script = new StringBuilder();
		BufferedReader lineReader = new BufferedReader(reader);
		String line;
		try {
			while ((line = lineReader.readLine()) != null) {
				script.append(line);
				script.append(LINE_SEPARATOR);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String command = script.toString();
		return command;
	}

	/**
	 * Execute query on sqllite DB.
	 * 
	 * @param script : Script to Execute
	 * @return HashMam with result
	 * @author Lavendra rajput
	 */
	public static String executeScript(String script) {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		String json = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(BDDConstants.getDataBaseConnectionString());
			c.setAutoCommit(false);
			stmt = c.createStatement();
			rs = stmt.executeQuery(script);
			json = convertResultSetToJson(rs);
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return json;
	}

	/**
	 * Execute script on sqllite db with parameters
	 * 
	 * @param script : Script to execute
	 * @param list   : List of parameters
	 * @return Hashmap with stored results
	 * @author Lavendra rajput
	 */
	public static String executeScriptWithStringParam(String script, List<String> list) {
		Connection c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String json = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(BDDConstants.getDataBaseConnectionString());
			c.setAutoCommit(false);
			stmt = c.prepareStatement(script);
			for (int i = 1; i <= list.size(); i++) {
				stmt.setString(i, list.get(i - 1));
			}
			rs = stmt.executeQuery();
			json = convertResultSetToJson(rs);
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return json;
	}

	/**
	 * Exeute query on sqllite db with int parameters
	 * 
	 * @param script : Script to execute
	 * @param list   : List of Parameters
	 * @return Hashmap with stored results
	 * @author Lavendra rajput
	 */
	public static String executeScript(String script, List<Integer> list) {
		Connection c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String json = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(BDDConstants.getDataBaseConnectionString());
			c.setAutoCommit(false);
			stmt = c.prepareStatement(script);
			for (int i = 1; i <= list.size(); i++) {
				stmt.setInt(i, list.get(i - 1));
			}
			rs = stmt.executeQuery();
			json = convertResultSetToJson(rs);
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return json;
	}

	/**
	 * Convert ResultSet into JSON
	 * @param resultSet
	 * @return JSON String
	 * @throws SQLException
	 */
	private static String convertResultSetToJson(ResultSet resultSet) throws SQLException {
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		int columns = resultSetMetaData.getColumnCount();
		JSONArray jsonArray = new JSONArray();
		while (resultSet.next()) {
			JSONObject jsonObject = new JSONObject();
			for (int i = 1; i <= columns; i++) {
				String columnName = resultSetMetaData.getColumnName(i);
				jsonObject.put(columnName, resultSet.getObject(columnName));
			}
			jsonArray.put(jsonObject);
		}
		return jsonArray.toString();
	}
}
