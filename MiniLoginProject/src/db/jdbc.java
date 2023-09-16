package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Constants.CommonConstants;

public class jdbc {

    public static boolean validateUserLogin(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME,
                    CommonConstants.DB_PASSWORD);

            PreparedStatement validateLogin = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_TABLE_NAME + " WHERE username = ? AND password = ?");
            validateLogin.setString(1, username);
            validateLogin.setString(2, password);

            ResultSet resultSet = validateLogin.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return false;
            }

            connection.close();
            validateLogin.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean checkUserExist(String userName, String email) {
        try {
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME,
                    CommonConstants.DB_PASSWORD);

            PreparedStatement checkUserExist = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_TABLE_NAME + " WHERE username = ? AND email = ?");
            checkUserExist.setString(1, userName);
            checkUserExist.setString(2, email);

            ResultSet resultSet = checkUserExist.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return false;
            }
            connection.close();
            checkUserExist.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean insertNewUser(String username, String email, String password) {
        if (!checkUserExist(username, email)) {
            try {
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL, CommonConstants.DB_USERNAME,
                        CommonConstants.DB_PASSWORD);
              
                PreparedStatement addUser = connection.prepareStatement(
                        " INSERT INTO " + CommonConstants.DB_TABLE_NAME + " (username, email, password) " + " VALUES (?, ?, ?)");
                addUser.setString(1, username);
                addUser.setString(2, email);
                addUser.setString(3, password);

                addUser.executeUpdate();


                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
