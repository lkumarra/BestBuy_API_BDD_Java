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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.bestbuy.com.constants.BDDConstants;

public class DBHelpers {

	/**
	 * Read the script content from .sql file
	 * 
	 * @param scriptPath : Path of script
	 * @return String content of script
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
	 */
	public static HashMap<String, List<Object>> executeScript(String script) {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		Map<String, List<Object>> dataTable = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(BDDConstants.dataBaseConnString);
			c.setAutoCommit(false);
			stmt = c.createStatement();
			rs = stmt.executeQuery(script);
			dataTable = resultSetToArrayList(rs);
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return (HashMap<String, List<Object>>) dataTable;
	}

	/**
	 * Execute script on sqllite db with parameters
	 * 
	 * @param script : Script to execute
	 * @param list   : List of parameters
	 * @return Hashmap with stored results
	 */
	public static HashMap<String, List<Object>> executeScriptWithStringParam(String script, List<String> list) {
		Connection c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Map<String, List<Object>> dataTable = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(BDDConstants.dataBaseConnString);
			c.setAutoCommit(false);
			stmt = c.prepareStatement(script);
			for (int i = 1; i <= list.size(); i++) {
				stmt.setString(i, list.get(i - 1));
			}
			rs = stmt.executeQuery();
			dataTable = resultSetToArrayList(rs);
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return (HashMap<String, List<Object>>) dataTable;
	}

	/**
	 * Exeute query on sqllite db with int parameters
	 * 
	 * @param script : Script to execute
	 * @param list   : List of Parameters
	 * @return Hashmap with stored results
	 */
	public static HashMap<String, List<Object>> executeScript(String script, List<Integer> list) {
		Connection c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Map<String, List<Object>> dataTable = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(BDDConstants.dataBaseConnString);
			c.setAutoCommit(false);
			stmt = c.prepareStatement(script);
			for (int i = 1; i <= list.size(); i++) {
				stmt.setInt(i, list.get(i - 1));
			}
			rs = stmt.executeQuery();
			dataTable = resultSetToArrayList(rs);
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return (HashMap<String, List<Object>>) dataTable;
	}

	/**
	 * Strore DB Results in hashMap
	 * 
	 * @param rs ResultSet
	 * @return Map<String, List<Object>> with results
	 * @throws SQLException
	 */
	private static Map<String, List<Object>> resultSetToArrayList(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		Map<String, List<Object>> map = new HashMap<>(columns);
		for (int i = 1; i <= columns; ++i) {
			map.put(md.getColumnName(i), new ArrayList<>());
		}
		while (rs.next()) {
			for (int i = 1; i <= columns; ++i) {
				map.get(md.getColumnName(i)).add(rs.getObject(i));
			}
		}
		return map;
	}
}
