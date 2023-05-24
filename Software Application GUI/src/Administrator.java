import java.sql.*;

public class Administrator {
    private String Username;
    private String Password;
    private Connection connection=DBconnection.getConnection();

    Administrator(){}
    public Administrator(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }

    public void InsertAdmin(){
        String query = "INSERT INTO Administrator (username,password_ADmin) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,Username );
            stmt.setString(2, Password);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void printAdmins(){
        try {
            String query = "SELECT * FROM Administrator";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                 Username = rs.getString("username");
                System.out.println("admin username is "+Username);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean ExistAdmin(String username){
        try {
            String query = "SELECT * FROM Administrator";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Username = rs.getString("username");
                if(this.Username.equals(username)){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean Login(String username,String password){
        try {
            String query = "SELECT * FROM Administrator";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Username = rs.getString("username");
                Password = rs.getString("password_ADmin");
                if(this.Username.equals(username) && this.Password.equals(password)){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
