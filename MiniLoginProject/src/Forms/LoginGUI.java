package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Constants.CommonConstants;
import db.jdbc;

public class LoginGUI extends Form {
    public LoginGUI() {
        super("Login");
        addGUIs();
    }

    private void addGUIs() {
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(0, 25, 520, 100);
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.setFont(new Font(CommonConstants.PIMARY_FONT, Font.BOLD, 40));
        loginLabel.setHorizontalAlignment(SwingUtilities.CENTER);

        add(loginLabel);


        //Username
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(30, 230, 400, 18);
        usernameLabel.setForeground(CommonConstants.SECONDARY_COLOR);
        usernameLabel.setFont(new Font(CommonConstants.PIMARY_FONT, Font.PLAIN, 24));
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 255, 450, 55);
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font(CommonConstants.PIMARY_FONT, Font.PLAIN, 24));
        add(usernameField);

        //password
        JLabel passLabel = new JLabel("Password: ");
        passLabel.setBounds(30, 325, 400, 18);
        passLabel.setForeground(CommonConstants.SECONDARY_COLOR);
        passLabel.setFont(new Font(CommonConstants.PIMARY_FONT, Font.PLAIN, 24));
        add(passLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30 , 355, 450, 55);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font(CommonConstants.PIMARY_FONT, Font.PLAIN, 24));
        add(passwordField);

        //Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font(CommonConstants.PIMARY_FONT, Font.BOLD, 18));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBounds(125, 445, 250, 50);
        loginButton.setBackground(CommonConstants.SECONDARY_COLOR);
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if(jdbc.validateUserLogin(username, password)){
                    JOptionPane.showMessageDialog(LoginGUI.this, "Login SuccessFully");
                }
                else{
                    JOptionPane.showMessageDialog(LoginGUI.this,"Login Failed!, user not found.");
                }
            }
            
        });
        add(loginButton);


        JLabel regLabel = new JLabel(
            "<html><p>Haven't registered yet? Click here to create a new account. Right now!!</p></html>"
            
            );
        regLabel.setHorizontalAlignment(SwingConstants.CENTER);
        regLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        regLabel.setForeground(CommonConstants.TEXT_COLOR);
        regLabel.setBounds(125, 500, 300, 50);
        regLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                //dispose Login GUi
                LoginGUI.this.dispose();
                
                //launch Reg GUI
                new RegisterGUI().setVisible(true);
                
            }
        });
        add(regLabel);
        
    }

}
