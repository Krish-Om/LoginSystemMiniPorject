package GUIs;
// import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.event.MouseAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
// import javax.swing.SwingConstants;

import Constants.MyConstants;
import db.MYJDBC;

public class LoginFormGUI extends Form implements ActionListener {
    private static  int userType;
    private static String user_name;
    public static JRadioButton admin;
    public static JRadioButton user;

    public LoginFormGUI() {
        super("Login");
        addGUIComponents();
    }

    public void loadUsers(String user) {
        if (user == "admin") {
        } else if (user == "user") {

        }
    }

    private void addGUIComponents() {
        // label
        JLabel loginLabel = new JLabel("Login");

        // configure the component's x,y postion relative to the GUI
        loginLabel.setBounds(0, 25, 520, 100);

        // change font color
        loginLabel.setForeground(MyConstants.TEXT_COLOR);

        // change the font size
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));

        // center text
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // add Component to GUI
        add(loginLabel);

        // radio button
        admin = new JRadioButton("Admin");
        user = new JRadioButton("User");

        // ButtonGroup
        ButtonGroup group = new ButtonGroup();
        group.add(admin);
        group.add(user);
        admin.setBounds(50, 110, 70, 40);
        user.setBounds(120, 110, 70, 40);
        admin.addActionListener(this);
        user.addActionListener(this);
        add(admin);
        add(user);

        // create Username label
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(MyConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // Textfield
        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(30, 185, 450, 55);
        usernameTextField.setBackground(MyConstants.SECONDARY_COLOR);
        usernameTextField.setForeground(MyConstants.TEXT_COLOR);
        usernameTextField.setFont(new Font("Dialong", Font.PLAIN, 24));

        add(usernameLabel);
        add(usernameTextField);

        // create password label
        JLabel passJLabel = new JLabel("Password");
        passJLabel.setBounds(30, 335, 400, 25);
        passJLabel.setForeground(MyConstants.TEXT_COLOR);
        passJLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // Password Textfield

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 365, 450, 55);
        passwordField.setBackground(MyConstants.SECONDARY_COLOR);
        passwordField.setForeground(MyConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialong", Font.PLAIN, 24));

        add(passJLabel);
        add(passwordField);

        // Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));
        // change cursor to hand cursor when it hovers in login button

        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(MyConstants.SECONDARY_COLOR);
        loginButton.setBounds(125, 520, 250, 50);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // retrieve the credentials from users
                String username = usernameTextField.getText();
                user_name = username;
                String password = new String(passwordField.getPassword());
                // checking if theere is empty login
                if (validateUserLogin(username, password, userType)) {
                    // checking in with the database
                    if (MYJDBC.validateLogin(username, password)) {
                        // login success
                        JOptionPane.showMessageDialog(LoginFormGUI.this, "Login Successful!");
                        LoginFormGUI.this.dispose();
                        if (userType == 2) {
                            new UserInsterface().setVisible(true);
                        }else if(userType == 1){
                            new AdminInterface(user_name).setVisible(true);
                        }

                    } else {
                        // no data found in database
                        JOptionPane.showMessageDialog(LoginFormGUI.this, "ERROR: No user found !\n" +
                                "Seems Like you are new here, Hurry up and Register yourself ! 😁😁 ");
                    }
                }
            }
        });
        add(loginButton);

        // create a register label
        JLabel regLabel = new JLabel("New here? Click here to register.");
        regLabel.setHorizontalAlignment(SwingConstants.CENTER);
        regLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        regLabel.setForeground(MyConstants.TEXT_COLOR);

        // add fucntionality to Jlabel so that when clicked it will switch to register
        // form gui
        regLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // dispose of this gui form
                LoginFormGUI.this.dispose();
                // launch the register GUI
                new RegisterFormGUI().setVisible(true);
            }
        });

        regLabel.setBounds(125, 600, 250, 30);
        add(regLabel);
    }

    private boolean validateUserLogin(String name, String password, int userType) {
        if (userType != 0) {
            if (name != null && password != null) {
                return true;
            } else {
                JOptionPane.showMessageDialog(LoginFormGUI.this, "Either your username or password seems to be empty");
            }
        } else {
            JOptionPane.showMessageDialog(LoginFormGUI.this, "Please select Admin if you are admin and User for user.");
        }

        return false;
    }

    public static String getUsername() {
        return user_name;
    }

    public static int getUserType(){
        return userType;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == admin) {
            userType =1;
            // user2.isUserPressed();
        } else if (e.getSource() == user) {
            userType =2;
            // isAdminPressed();
            // user1.isAdminPressed();
        }
    }
}
