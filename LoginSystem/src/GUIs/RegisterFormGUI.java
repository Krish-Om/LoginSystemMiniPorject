package GUIs;

import javax.swing.*;
import Constants.MyConstants;
import db.MYJDBC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterFormGUI extends Form {

    public RegisterFormGUI() {
        super("Register");
        addComponent();
    }

    private void addComponent() {
        // label
        JLabel regLabel = new JLabel("Register");

        // configure the component's x,y postion relative to the GUI
        regLabel.setBounds(0, 25, 520, 100);

        // change font color
        regLabel.setForeground(MyConstants.TEXT_COLOR);

        // change the font size
        regLabel.setFont(new Font("Dialog", Font.BOLD, 40));

        // center text
        regLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // add Component to GUI
        add(regLabel);



        
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
        passJLabel.setBounds(30, 255, 400, 25);
        passJLabel.setForeground(MyConstants.TEXT_COLOR);
        passJLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // Password Textfield

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 285, 450, 55);
        passwordField.setBackground(MyConstants.SECONDARY_COLOR);
        passwordField.setForeground(MyConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialong", Font.PLAIN, 24));

        add(passJLabel);
        add(passwordField);

        // create re-enter password label

        JLabel reenterPassLabel = new JLabel("Re-enter Password");
        reenterPassLabel.setBounds(30, 365, 400, 25);
        reenterPassLabel.setForeground(MyConstants.TEXT_COLOR);
        reenterPassLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // Password Textfield

        JPasswordField reenterpass = new JPasswordField();
        reenterpass.setBounds(30, 395, 450, 55);
        reenterpass.setBackground(MyConstants.SECONDARY_COLOR);
        reenterpass.setForeground(MyConstants.TEXT_COLOR);
        reenterpass.setFont(new Font("Dialong", Font.PLAIN, 24));

        add(reenterPassLabel);
        add(reenterpass);

        // Button
        JButton regButton = new JButton("Register");
        regButton.setFont(new Font("Dialog", Font.BOLD, 18));

        // change cursor to hand cursor when it hovers in register button

        regButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        regButton.setBackground(MyConstants.SECONDARY_COLOR);
        regButton.setBounds(125, 520, 250, 50);
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get username
                String username = usernameTextField.getText();

                // get password
                String password = new String(passwordField.getPassword());

                // get repass
                String rePass = new String(reenterpass.getPassword());

                // validate userInputs
                if (validateUserInput(username, password, rePass)) {
                    // register user to the database
                    if (MYJDBC.register(username, password)) {
                        // dispose of register GUI
                        RegisterFormGUI.this.dispose();

                        // take back to login GUI
                        LoginFormGUI loginFormGUI = new LoginFormGUI();
                        loginFormGUI.setVisible(true);

                        // creating a result dialog
                        JOptionPane.showMessageDialog(loginFormGUI, "Registered Account Successfully! U_U");
                    } else {
                        // registration failed (due to existence of username in database)
                        JOptionPane.showMessageDialog(RegisterFormGUI.this,
                                "ERRO : Username is taken, try a different one");
                    }
                } else {
                    // invalid user Input
                    JOptionPane.showMessageDialog(RegisterFormGUI.this, "ERROR : Username must be 6 character long\n" +
                            "and/ or Password must be matched");
                }
            }
        });
        add(regButton);

        JLabel loginLabel = new JLabel("Have an account already? Login here! 😉");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(MyConstants.TEXT_COLOR);

        // add functinality to login label
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // dispose
                RegisterFormGUI.this.dispose();
                // launch login GUI
                new LoginFormGUI().setVisible(true);
            }
        });
        loginLabel.setBounds(125, 600, 290, 30);

        add(loginLabel);

    }

    private boolean validateUserInput(String username, String Password, String reenterPassword) {
        // all the textfield must not be empty
        if (username == null && Password == null && reenterPassword == null)
            return false;

        // username has to be atleast 6 characters long
        if (username.length() < 6)
            return false;

        // reentered password must be equal to password
        if (!Password.equals(reenterPassword))
            return false;

        // passes validation
        return true;
    }

}
