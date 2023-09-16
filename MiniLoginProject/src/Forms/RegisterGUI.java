package Forms;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

import Constants.CommonConstants;
import db.jdbc;

public class RegisterGUI extends Form {

    public RegisterGUI() {
        super("Register");
        addGUIs();
    }

    private void addGUIs() {
        JLabel reg1Label = new JLabel("Register");
        reg1Label.setBounds(140, 25, 520, 100);
        reg1Label.setForeground(CommonConstants.TEXT_COLOR);
        reg1Label.setFont(new Font(CommonConstants.PIMARY_FONT, Font.BOLD, 40));
        reg1Label.setHorizontalAlignment(SwingUtilities.CENTER);

        add(reg1Label);
        JLabel reg2Label = new JLabel("Welcome to Registration !!!");
        reg2Label.setBounds(160, 80, 520, 80);
        reg2Label.setForeground(CommonConstants.TEXT_COLOR);
        reg2Label.setFont(new Font(CommonConstants.PIMARY_FONT, Font.BOLD, 30));
        reg2Label.setHorizontalAlignment(SwingUtilities.CENTER);
        add(reg2Label);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 230, 300, 18);
        usernameLabel.setForeground(CommonConstants.SECONDARY_COLOR);
        usernameLabel.setFont(new Font(CommonConstants.PIMARY_FONT, Font.PLAIN, 24));
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 255, 300, 55);
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font(CommonConstants.PIMARY_FONT, Font.PLAIN, 24));
        add(usernameField);

        // email
        JLabel emaiLabel = new JLabel("Email: ");
        emaiLabel.setBounds(410, 230, 300, 18);
        emaiLabel.setForeground(CommonConstants.SECONDARY_COLOR);
        emaiLabel.setFont(new Font(CommonConstants.PIMARY_FONT, Font.PLAIN, 24));
        add(emaiLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(410, 255, 300, 55);
        emailField.setBackground(CommonConstants.SECONDARY_COLOR);
        emailField.setForeground(CommonConstants.TEXT_COLOR);
        emailField.setFont(new Font(CommonConstants.PIMARY_FONT, Font.PLAIN, 24));
        add(emailField);

        // password
        JLabel passLabel = new JLabel("Password: ");
        passLabel.setBounds(30, 325, 300, 18);
        passLabel.setForeground(CommonConstants.SECONDARY_COLOR);
        passLabel.setFont(new Font(CommonConstants.PIMARY_FONT, Font.PLAIN, 24));
        add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 355, 300, 55);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font(CommonConstants.PIMARY_FONT, Font.PLAIN, 24));
        add(passwordField);

        // re-enter pass
        JLabel repassLabel = new JLabel("Re-enter the Password: ");
        repassLabel.setBounds(410, 330, 300, 18);
        repassLabel.setForeground(CommonConstants.SECONDARY_COLOR);
        repassLabel.setFont(new Font(CommonConstants.PIMARY_FONT, Font.PLAIN, 24));
        add(repassLabel);

        JPasswordField repasswordField = new JPasswordField();
        repasswordField.setBounds(410, 355, 300, 55);
        repasswordField.setBackground(CommonConstants.SECONDARY_COLOR);
        repasswordField.setForeground(CommonConstants.TEXT_COLOR);
        repasswordField.setFont(new Font(CommonConstants.PIMARY_FONT, Font.PLAIN, 24));
        add(repasswordField);

        // reg Button
        JButton regButton = new JButton("Register");
        regButton.setFont(new Font(CommonConstants.PIMARY_FONT, Font.BOLD, 18));
        regButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        regButton.setHorizontalAlignment(SwingUtilities.CENTER);
        regButton.setBounds(250, 550, 250, 50);
        regButton.setBackground(CommonConstants.SECONDARY_COLOR);
        regButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();

                String password = new String(passwordField.getPassword());
                String repass = new String(repasswordField.getPassword());

                String email = emailField.getText();

                // functionality to register button
                if (validateUserInput(username, email, password, repass)) {
                    if (jdbc.insertNewUser(username, email, password)) {
                        JOptionPane.showMessageDialog(RegisterGUI.this, "Registration Successfully");

                        // taking back to login GUI
                        RegisterGUI.this.dispose();
                        new LoginGUI().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(RegisterGUI.this,
                                "ERROR: Username/Email already used. Try different one");
                    }
                } else {
                    JOptionPane.showMessageDialog(RegisterGUI.this,
                            "ERROR: Username must be 6 character long\n" + "and/or password must match!");
                }
            }

        });
        add(regButton);

        JLabel loginLabel = new JLabel(
                "<html><p>Already have an account? Click here to login now! </p></html>");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.setBounds(250, 600, 300, 50);
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // disposing this GUI
                RegisterGUI.this.dispose();

                // Switch back login GUI
                new LoginGUI().setVisible(true);
            }
        });

        add(loginLabel);

    }
    public boolean validateUserInput(String username, String email, String pasword, String repass) {
        if (!(pasword.equals(repass))){
            return false;
        }
        if (username.equals(null) || email.equals(null) || pasword.equals(null))
            {return false;}
        if (username.length() <= 4)
            {return false;}

        // passes validation
        return true;
    
}
}