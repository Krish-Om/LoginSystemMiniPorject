package Forms;

import javax.swing.JFrame;
// import java.awt.*;
import Constants.CommonConstants;

public class Form extends JFrame {
   

    public Form(String title){

        if(title.equals("Login"))
        {
            setTitle(title);
            setResizable(false);
            setSize(595, 700);
            setLayout(null);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);
        }
        else if(title.equals("Register")){
            setTitle(title);
            setResizable(false);
            setSize(800, 780);
            setLayout(null);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);
        }

    }

}
