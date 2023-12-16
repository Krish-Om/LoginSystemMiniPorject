package db;

// import java.awt.List;
import java.sql.*;
// import java.util.Arrays;

import javax.swing.JTable;
// import javax.swing.table.DefaultTableModel;

// import javax.swing.table.DefaultTableModel;

import Constants.MyConstants;
import GUIs.LoginFormGUI;

//JDBC - Java DataBase Connectivity
//this class will be the gateway in accessing our MySQL database
public class MYJDBC {
    private static JTable data;
    private static String tableName = MyConstants.DB_TABLE_NAME_ADMIN;
    private static void isTableChaged(){
        if(LoginFormGUI.getUserType() == 2){
            tableName = MyConstants.DB_TABLE_NAME_USERS;
        }
    }
    // register new user to the database
    // true - register success
    // false - register failed or user already exists
    public static boolean register(String username, String password) {
        isTableChaged();
        // first check if user exists or not
        if (!checkUser(username)) {
            try {
                Connection connection = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USERNAME,
                        MyConstants.DB_PASSWORD);

                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + tableName + " (username, password) "
                                + " VALUES (?, ?)");
                // inserting parameters in insert query
                insertUser.setString(1, username);
                insertUser.setString(2, password);

                // update db with new user
                insertUser.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean checkUser(String username) {
        try {
            isTableChaged(); 
            Connection connection = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USERNAME,
                    MyConstants.DB_PASSWORD);

            PreparedStatement checkUserExist = connection.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE USERNAME = ?");
            checkUserExist.setString(1, username);
            ResultSet resultSet = checkUserExist.executeQuery();

            // check to see if the result set is empty
            // if it is empty it means that there was no data row that contains the username
            // i.e user doesn't exist
            if (!resultSet.isBeforeFirst()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // validate the user through checking the username/password pair exist in
    // database

    public static boolean validateLogin(String username, String password) {
        isTableChaged();
        try {
            Connection connection = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USERNAME,
                    MyConstants.DB_PASSWORD);
            PreparedStatement validateUser = connection.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE username = ? AND password = ?");

            validateUser.setString(1, username);
            validateUser.setString(2, password);

            ResultSet resultSet = validateUser.executeQuery();
            // isBeforeFirst() is used to see if our query returned any rows that matched
            // our query
            // if true it means there exist the pair username and password that matched the
            // user input
            if (!resultSet.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean deleteUser(String username, String password) {
        isTableChaged();
        try {
            Connection conn = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USERNAME,
                    MyConstants.DB_PASSWORD);
            PreparedStatement deleteUser = conn.prepareStatement(
                    "DELETE FROM " + tableName + " WHERE username = ? AND password = ?");
            deleteUser.setString(1, username);
            deleteUser.setString(2, password);

            deleteUser.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
