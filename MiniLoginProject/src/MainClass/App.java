package MainClass;

// import Forms.Form;
import Forms.LoginGUI;
// import db.jdbc;
// import Forms.RegisterGUI;
import db.jdbc;

public class App {
    public static void main(String[] args) throws Exception {
        new LoginGUI().setVisible(true);
        // System.out.println(jdbc.checkUser("user"));
        // new RegisterGUI().setVisible(true);
        // System.out.println(jdbc.checkUserExist("user", "123@gmail.com"));
    }
}
