import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Passenger {
    private int SSN;
    private String Fname;
    private String Sname;
    private String Email;
    private LocalDate Birthday;
    private String phone;
    private String passport;
    private Connection connection=DBconnection.getConnection();

    public Passenger(){}
    public Passenger(int SSN, String fname, String sname, String email, LocalDate birthday, String phone, String passport) {
        this.SSN = SSN;
        Fname = fname;
        Sname = sname;
        Email = email;
        Birthday = birthday;
        this.phone = phone;
        this.passport = passport;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }
    public void setFname(String fname) {
        Fname = fname;
    }
    public void setSname(String sname) {
        Sname = sname;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public void setBirthday(LocalDate birthday) {
        Birthday = birthday;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void InsertPassenger(){
        String query = "INSERT INTO Passenger (ssn, fname, sname, email, birthday, phone, passport) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, SSN);
            stmt.setString(2, Fname);
            stmt.setString(3, Sname);
            stmt.setString(4, Email);
            stmt.setDate(5, Date.valueOf(Birthday)); // convert LocalTime to java.sql.Time
            stmt.setString(6, phone);
            stmt.setString(7, passport);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void UpdatePassenger(int ssn){
        String sql = "UPDATE Passenger SET Email = ?,phone = ?, passport = ? WHERE SSN = ?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, Email);
            stmt.setString(2, phone);
            stmt.setString(3, passport);
            stmt.setInt(4, ssn);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void DeletePassenger(int ssn){
        Statement stmt = null;
        try {
            Booking b=new Booking();
            b.DeleteBookings(ssn);

            stmt = connection.createStatement();
            String sql = "DELETE FROM Passenger WHERE SSN = " + ssn;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void printPassengers(){
        try {
            String query = "SELECT * FROM Passenger";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList Ar = new ArrayList();int k = 1;
            while (rs.next()) {
                Ar.add("SSN "+rs.getInt("ssn"));
                Ar.add("Fname "+rs.getString("fname"));
                Ar.add("Sname "+rs.getString("sname"));
                Ar.add("Email "+rs.getString("email"));
                Ar.add("birthday "+rs.getDate("birthday"));
                Ar.add("phone "+rs.getString("phone"));
                Ar.add("passport "+rs.getString("passport"));
                System.out.println("(" + k + ") " + Ar.get(0));
                for (int i = 1; i < Ar.size(); i++) {
                    System.out.println("\t" + Ar.get(i));
                }
                k++;
                Ar.clear();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String PassNamebySSN(int ssn){
        try {
            String query = "SELECT * FROM Passenger";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                SSN = rs.getInt("SSN");
                Fname = rs.getString("Fname");
                Sname = rs.getString("Sname");
                if(this.SSN==ssn){
                    return (Fname+" "+Sname);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "null";
    }
}
