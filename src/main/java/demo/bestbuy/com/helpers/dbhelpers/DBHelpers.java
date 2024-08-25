package demo.bestbuy.com.helpers.dbhelpers;

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

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import demo.bestbuy.com.helpers.apihelper.constants.BDDConstants;


/**
 * This class contains the methods related to DB Connection
 *
 * @author Lavendra Kumar Rajput
 * @Date 2 July 2021
 */
@Slf4j
public final class DBHelpers {

    /**
     * Read the script content from .sql file
     *
     * @param scriptPath : Path of script
     * @return String content of script
     * @author Lavendra rajput
     */
    public static String getDBScript(String scriptPath) {
        String LINE_SEPARATOR = System.lineSeparator();
        StringBuilder script = new StringBuilder();

        try (BufferedReader lineReader = new BufferedReader(new FileReader(scriptPath))) {
            String line;
            while ((line = lineReader.readLine()) != null) {
                script.append(line).append(LINE_SEPARATOR);
            }
        } catch (FileNotFoundException e) {
            log.error("File not found: {}", scriptPath, e);
        } catch (IOException e) {
            log.error("IO exception occurred while reading {}: {}", scriptPath, e.getMessage());
        }
        return script.toString();
    }


    /**
     * Execute query on sqllite DB.
     *
     * @param script : Script to Execute
     * @return HashMam with result
     * @author Lavendra rajput
     */
    public static List<HashMap<String, String>> executeScript(String script) {
        List<HashMap<String, String>> dataTable = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(BDDConstants.getDataBaseConnectionString()); Statement stmt = c.createStatement(); ResultSet rs = stmt.executeQuery(script)) {
            dataTable = convertResultSetToList(rs);
        } catch (SQLException e) {
            log.error("Error while executing the script: {}", e.getMessage(), e);
        }
        return dataTable;
    }


    /**
     * Execute script on sqllite db with parameters
     *
     * @param script : Script to execute
     * @param list   : List of parameters
     * @return Hashmap with stored results
     * @author Lavendra rajput
     */
    public static List<HashMap<String, String>> executeScriptWithStringParam(String script, List<String> list) {
        List<HashMap<String, String>> dataTable = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(BDDConstants.getDataBaseConnectionString()); PreparedStatement stmt = c.prepareStatement(script)) {
            for (int i = 1; i <= list.size(); i++) {
                stmt.setString(i, list.get(i - 1));
            }
            try (ResultSet rs = stmt.executeQuery()) {
                dataTable = convertResultSetToList(rs);
            }
        } catch (SQLException e) {
            log.error("SQL Exception : {}", e.getMessage(), e);
        }
        return dataTable;
    }

    /**
     * Exeute query on sqllite db with int parameters
     *
     * @param script : Script to execute
     * @param list   : List of Parameters
     * @return Hashmap with stored results
     * @author Lavendra rajput
     */
    public static List<HashMap<String, String>> executeScript(String script, List<Integer> list) {
        List<HashMap<String, String>> dataTable = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(BDDConstants.getDataBaseConnectionString()); PreparedStatement stmt = c.prepareStatement(script)) {
            for (int i = 1; i <= list.size(); i++) {
                stmt.setInt(i, list.get(i - 1));
            }
            try (ResultSet rs = stmt.executeQuery()) {
                dataTable = convertResultSetToList(rs);
            }
        } catch (SQLException e) {
            log.error("SQL Exception : {}", e.getMessage(), e);
        }
        return dataTable;
    }

    public static void executeNonQuery(String script) {
        try (Connection c = DriverManager.getConnection(BDDConstants.getDataBaseConnectionString()); Statement stmt = c.createStatement()) {
            stmt.executeUpdate(script);
        } catch (SQLException e) {
            log.error("SQL Exception : {}", e.getMessage(), e);
        }
    }

    /**
     * Convert ResultSet into JSON
     *
     * @param resultSet
     * @return JSON String
     * @throws SQLException
     */
    public static String convertResultSetToJson(ResultSet resultSet) throws SQLException {
        JSONArray jsonArray = new JSONArray();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        while (resultSet.next()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i); // Directly use column index
                jsonObject.put(columnName, columnValue);
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    private static List<HashMap<String, String>> convertResultSetToList(ResultSet resultSet) throws SQLException {
        // Retrieve metadata from the ResultSet
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount(); // Get the number of columns

        // List to hold the rows of the ResultSet as maps
        List<HashMap<String, String>> dataTable = new ArrayList<>();

        // Iterate over each row in the ResultSet
        while (resultSet.next()) {
            // Map to store column names and values for the current row
            HashMap<String, String> rowMap = new HashMap<>();

            // Iterate over each column
            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                // Retrieve the value for the current column and convert it to a string
                String columnValue = resultSet.getObject(i) != null ? resultSet.getObject(i).toString() : null;
                rowMap.put(columnName, columnValue);
            }

            // Add the current row's map to the list
            dataTable.add(rowMap);
        }

        return dataTable;
    }
}
