package Lesson_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Connection connection;
    private static Statement stmt;
    //private static PreparedStatement pstmt;
    private static final String TABLE = "students";


    public static void main(String[] args) throws SQLException {

        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //создадим таблицу
        String[] columns = {"'id' INTEGER PRIMARY KEY AUTOINCREMENT", "'name' TEXT", "'score' TEXT"};
        createTable(TABLE, columns);

        //Кого-то принимаем заранее, по блату.
        insert(TABLE, new String[]{"name", "score"}, new String[][]{{"Bob1", "20"}, {"Bob2", "10"}});

        Scanner scnFileUpdate = null;
        try {
            scnFileUpdate = new Scanner(new File("DZ_update.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Идентификацию по name выбрал потому, что студента могли исключить, потом принять, id у него изменился.
        //Хотя по одному только name, конечно, маловато идентифицировать, еще хотя бы фамилию.
        connection.setAutoCommit(false);
        while (scnFileUpdate.hasNextLine()) {
            String[] values = scnFileUpdate.nextLine().split("  ");
            ResultSet rs = select(TABLE, "name", values[1]);
            //здесь проверяю только наличие хотя бы одной пришедшей строки,
            // если есть, значит и запись есть, идем в апдейт,
            // если строк ноль, то тогда в инсёрт.
            if (rs.next()) {
                update(TABLE, "score", values[2], "name='" + values[1] + "'");
            } else {
                insert(TABLE, new String[]{"name", "score"}, new String[][]{{values[1], values[2]}});
            }
        }
        connection.setAutoCommit(true);

        //Боб1 и Боб2 нахулиганили, поэтому отчисляем, хоть и принимали по блату.
        delete(TABLE, "name='Bob2' OR name='Bob1'");

        disconnect();

    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(String nameTable, String[] columns) throws SQLException {
        String strSQLQuery = "CREATE TABLE IF NOT EXISTS '" + nameTable + "' (";
        for (int i = 0; i < columns.length; i++) {
            strSQLQuery += columns[i] + ((i + 1 != columns.length) ? ", " : "");
        }
        strSQLQuery += ");";
        stmt.executeUpdate(strSQLQuery);
    }

    // Я, скорее всего, что-то не додумал и можно было сделать по другому.
    // Здесь может поступать большое количество значений, которые все уйдут за один запрос.

    private static void insert(String table, String[] columns, String[][] values) throws SQLException {
        String strSQLQuery = "INSERT INTO " + table + " (";
        for (int i = 0; i < columns.length; i++) {
            strSQLQuery += "'" + columns[i] + "'" + ((i + 1 != columns.length) ? ", " : "");
        }
        strSQLQuery += ") VALUES ";
        for (int i = 0; i < values.length; i++) {
            strSQLQuery += "(";
            for (int j = 0; j < values[i].length; j++) {
                strSQLQuery += "'" + values[i][j] + "'" + ((j + 1 != values[i].length) ? ", " : ")");
            }
            strSQLQuery += ((i + 1 != values.length) ? ", " : ";");
        }
        stmt.executeUpdate(strSQLQuery);
    }

    private static ResultSet select(String table, String column, String value) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT " + column
                + " FROM " + table
                + " WHERE " + column + "='" + value + "';");
        return rs;
    }

    private static void update(String table, String column, String value, String condition) throws SQLException {
        String strSQLQuery = "UPDATE " + table + " SET " + column + "='" + value + "' WHERE " + condition + ";";
        stmt.executeUpdate(strSQLQuery);
    }

    private static void delete(String table, String condition) throws SQLException {
        stmt.executeUpdate("DELETE FROM " + table + " WHERE " + condition);
    }
}
