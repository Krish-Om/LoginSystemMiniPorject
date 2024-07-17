import javax.swing.SwingUtilities;

import GUIs.LoginFormGUI;
public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFormGUI().setVisible(true);

                //test cases:-
                // new AdminInterface("lakjds").setVisible(true);
                // new AdminInterface("krishom").setVisible(true);
                // new RegisterFormGUI().setVisible(true);
                // new AdminInterface()

                // testing the database
                // MYJDBC.getUsersList();
                // System.out.println(MYJDBC.checkUser(""));

                // check register test
                // System.out.println(MYJDBC.register("kri","345"));

                // check validate test
                // System.out.println(MYJDBC.validateLogin("kr3", "345"));
            }
        });
    }
}
