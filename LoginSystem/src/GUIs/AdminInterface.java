package GUIs;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
// import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Constants.MyConstants;
// import db.MYJDBC;

// import Constants.MyConstants;

// import Constants.MyConstants;

public class AdminInterface extends JFrame {

    public AdminInterface(String adminName) {
        setTitle("LoginSystem");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setBackground(new Color(100000));
        loadGuis();
    }

    private void loadGuis() {
        // int[] ids= MYJDBC.getIds();
        JTable table = new JTable();
        // String[] usernames = MYJDBC.getUsernames();
        JLabel label = new JLabel(
                "<html>Welcome! " + LoginFormGUI.getUsername() + "." + " Check the status of your users.</html>");
        label.setBounds(0, 25, 400, 100);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Dialog", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        add(label);

        
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(45, 130, 400, 400);
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USERNAME,
                    MyConstants.DB_PASSWORD);
            PreparedStatement getUser = conn.prepareStatement(
                    "SELECT * FROM " + MyConstants.DB_TABLE_NAME_USERS);
            ResultSet resultSet = getUser.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            // DefaultTableModel model = data.getModel();

            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);
            String id, username;
            while (resultSet.next()) {
                id = resultSet.getString(1);
                username = resultSet.getString(2);
                String[] row = { id, username };
                model.addRow(row);
            }
            add(scrollPane);
            // ResultSetMetaData rsmd = resultSet.getMetaData();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    // add(table);

    // add(new JScrollPane(table));
    // scrollPane.add(table);
    // add(scrollPane);

}
