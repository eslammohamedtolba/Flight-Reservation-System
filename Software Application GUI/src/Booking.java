import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

public class Booking {
    private String Booking_id;
    private int passenger_ssn;
    private LocalDateTime Booking_Date;
    private float Total_price;
    private int Tickets_Num;
    private Connection connection=DBconnection.getConnection();

    public Booking(){}
    public Booking(int passenger_ssn,int tickets_Num,float T_price) {
        this.passenger_ssn = passenger_ssn;
        Tickets_Num = tickets_Num;
        Booking_Date= LocalDateTime.now();
        Total_price=T_price;
        UUID uuid = UUID.randomUUID();
        Booking_id = uuid.toString();
    }
    public String getBooking_id() {
        return Booking_id;
    }
    public LocalDateTime getBooking_Date() {
        return Booking_Date;
    }

    public void InsertBooking(){
        String query = "INSERT INTO Booking (id,passenger_ssn,Booking_Date,Total_price,Tickets_Num) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Booking_id);
            stmt.setInt(2, passenger_ssn);
            stmt.setTimestamp(3, Timestamp.valueOf(Booking_Date));
            stmt.setFloat(4, Total_price);
            stmt.setInt(5, Tickets_Num);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void DeleteBookings(int ssn){
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "DELETE FROM Booking WHERE passenger_ssn = " + ssn;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean ExistBooking(String id){
        try {
            String sql = "SELECT COUNT(*) AS count FROM Booking WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                int count = rs.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public void PassBookings(int pass_ssn){
        try {
            String query = "SELECT * FROM Booking where passenger_ssn =" + pass_ssn;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                passenger_ssn = rs.getInt("passenger_ssn");
                Booking_Date = rs.getTimestamp("Booking_Date").toLocalDateTime();
                Total_price = rs.getFloat("Total_price");
                Tickets_Num = rs.getInt("Tickets_Num");
                if(passenger_ssn==pass_ssn){
                    System.out.println("passenger_ssn " + passenger_ssn + " booking date " + Booking_Date + " total price " + Total_price + " tickets number " + Tickets_Num);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList MinMaxPriceForBookings(){
        ArrayList A=new ArrayList();
        try {
            String sql = "SELECT min(Total_price) AS MinPrice , b.id as BId \n" +
                    "FROM Booking b " +
                    "group by b.id " +
                    "order by MinPrice asc";

            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                A.add(rs.getString("BId"));
                A.add(rs.getInt("MinPrice"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String sql = "SELECT max(Total_price) AS MaxPrice , b.id as BId \n" +
                    "FROM Booking b " +
                    "group by b.id " +
                    "order by MaxPrice desc";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                A.add(rs.getString("BId"));
                A.add(rs.getInt("MaxPrice"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return A;
    }
}
