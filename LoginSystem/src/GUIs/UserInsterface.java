package GUIs;

import java.awt.Cursor;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import javax.swing.SwingConstants;

import Constants.MyConstants;

public class UserInsterface extends JFrame {

    public UserInsterface() {
        super("Interface");

        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // setResizable(true);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(MyConstants.PRIMARY_COLOR);

        addGUIComponents();
        // setVisible(true);
    }

    private void addGUIComponents() {
        JLabel welcomeLabel = new JLabel("Welcome! " + LoginFormGUI.getUsername() + ".");
        welcomeLabel.setBounds(250, 45, 520, 100);
        welcomeLabel.setForeground(MyConstants.TEXT_COLOR);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Dialog", Font.BOLD, 46));
        add(welcomeLabel);

        JLabel thanksLabel = new JLabel(
                "<html><p>We are glad to have you here!, Thank-you for joining us !!!</p></html>");
        thanksLabel.setBounds(310, 120, 450, 65);
        thanksLabel.setForeground(MyConstants.TEXT_COLOR);
        // thanksLabel.setVerticalAlignment(SwingConstants.LEFT);
        thanksLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        add(thanksLabel);

        JLabel feedback = new JLabel("Any suggestions?:");
        feedback.setBounds(320, 300, 450, 45);
        feedback.setForeground(MyConstants.TEXT_COLOR);
        // feedback.setVerticalAlignment(SwingConstants.LEFT);
        feedback.setFont(new Font("Dialog", Font.ITALIC, 24));
        add(feedback);

        JTextArea feedbackField= new JTextArea();
        feedbackField.setBounds(320, 350, 350, 100);
        feedbackField.setForeground(MyConstants.TEXT_COLOR);
        feedbackField.setFont(new Font("Dialog", Font.PLAIN, 14));
        add(feedbackField);

    
        
        JButton feedBackButton = new JButton("Send");
        feedBackButton.setBounds(690, 350, 100, 40);
        feedBackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(feedBackButton);

        JLabel deleteLabel = new JLabel("Account Deletion:");
        deleteLabel.setBounds(800, 400, 200, 45);
        deleteLabel.setForeground(MyConstants.TEXT_COLOR);
        deleteLabel.setFont(new Font("Dialog", Font.ROMAN_BASELINE, 18));
        deleteLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                UserInsterface.this.dispose();

                new AccountDeletionGUI().setVisible(true);
            }
        });
        add(deleteLabel);


    }

}
