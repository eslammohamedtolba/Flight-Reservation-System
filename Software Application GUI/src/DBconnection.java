import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
    private static final String URL="jdbc:sqlserver://192.168.1.9:1433;database=Flight_Reservation_System;encrypt=true;trustservercertificate=true";
    private static final String Username="Flight_RS";      //name of login
    private static final String password="123456qwert@#$"; //password
    private static Connection connection=null;

    public static Connection getConnection(){
        String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL,Username,password);
            if(connection==null){
                System.out.println("the connection is null");
            }
            else{
                System.out.println("the connection is not null");
            }
        }
        catch (Exception e){
            System.out.println("com.models.DBconnection.getConnection()"+e.getMessage());
        }
        return connection;
    }
}
