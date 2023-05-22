import java.sql.*;
public class jdbc_practice {
    public static void main(String[] args) {
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName); // here is the ClassNotFoundException
            System.out.println("Connecting to the Database");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","HAaz_9086");
            System.out.println("Connection Established");
        }
        catch (Exception e){
            System.out.println(e);
        }


    }

}