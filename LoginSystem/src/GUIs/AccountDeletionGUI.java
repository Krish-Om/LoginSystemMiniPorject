package GUIs;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Constants.MyConstants;
import db.MYJDBC;

public class AccountDeletionGUI extends Form {

    public AccountDeletionGUI() {
        super("Account Deletion");
        addGUIComponent();
    }

    private void addGUIComponent() {

        JLabel detaiLabel = new JLabel("Confirm Your Details:");
        detaiLabel.setBounds(0, 25, 520, 100);

        // change font color
        detaiLabel.setForeground(MyConstants.TEXT_COLOR);

        // change the font size
        detaiLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        // center text
        detaiLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // add Component to GUI
        add(detaiLabel);

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
        JLabel passJLabel = new JLabel("Confirm your Password");
        passJLabel.setBounds(30, 265, 400, 25);
        passJLabel.setForeground(MyConstants.TEXT_COLOR);
        passJLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // Password Textfield
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 295, 450, 55);
        passwordField.setBackground(MyConstants.SECONDARY_COLOR);
        passwordField.setForeground(MyConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialong", Font.PLAIN, 24));

        add(passJLabel);
        add(passwordField);

        // create password label
        JLabel confirmationLabel = new JLabel("Confirm-Password");
        confirmationLabel.setBounds(30, 365, 400, 25);
        confirmationLabel.setForeground(MyConstants.TEXT_COLOR);
        confirmationLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // Password Textfield
        JPasswordField confirmationField = new JPasswordField();
        confirmationField.setBounds(30, 395, 450, 55);
        confirmationField.setBackground(MyConstants.SECONDARY_COLOR);
        confirmationField.setForeground(MyConstants.TEXT_COLOR);
        confirmationField.setFont(new Font("Dialong", Font.PLAIN, 24));

        add(confirmationLabel);
        add(confirmationField);

        JButton deleteButton = new JButton("Delete Account");
        deleteButton.setBounds(205, 460, 120, 50);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // adding functionality to delete button
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                if (MYJDBC.checkUser(username)) {
                    if (MYJDBC.deleteUser(username, password)) {
                        JOptionPane.showMessageDialog(AccountDeletionGUI.this,
                                "Your account has been deleted successfully");

                        AccountDeletionGUI.this.dispose();
                        new LoginFormGUI().setVisible(true);
                    }
                }else{
                    JOptionPane.showMessageDialog(AccountDeletionGUI.this, "It seems your username or password doesn't match");
                }
            }
        });
        add(deleteButton);

    }

}
