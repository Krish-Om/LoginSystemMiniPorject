package Constants;
import java.awt.*;
public class MyConstants {
    // Malachite green (#31EC56), 
    // razzmatazz (#EF036C), heliotrope (#EE72F8)
    public static final Color PRIMARY_COLOR = Color.decode("#F9B872");
    public static final Color  SECONDARY_COLOR = Color.decode("#FAE7A5");
    public static final Color TEXT_COLOR =  Color.decode("#000000");
    public static final Dimension MAX_SIZE = new Dimension(800,800);
    //mysql credentials
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/logindb";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "9866297437";
    public static  final String DB_TABLE_NAME_USERS = "users";
    public static  final String DB_TABLE_NAME_ADMIN = "admins";

}
