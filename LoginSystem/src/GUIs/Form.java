package GUIs;
// import javax.imageio.plugins.tiff.ExifTIFFTagSet;
// import java.awt.Color;

import javax.swing.*;

import Constants.MyConstants;

public class Form extends JFrame{
    public Form(String title){
        super(title);

        setSize(520, 680);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);

        setLocationRelativeTo(null);

        setResizable(false);

        getContentPane().setBackground(MyConstants.PRIMARY_COLOR);

    }
}
