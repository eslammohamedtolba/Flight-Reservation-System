import java.sql.*;
import java.time.LocalTime;

public class Booking {
    private int passenger_ssn;
    private LocalTime Booking_Date;
    private float Total_price;
    private int Tickets_Num;
    private Connection connection=DBconnection.getConnection();

    public Booking(){}
    public Booking(int passenger_ssn,int tickets_Num) {
        this.passenger_ssn = passenger_ssn;
        Tickets_Num = tickets_Num;
        Booking_Date= LocalTime.now();
    }

    public void setTickets_Num(int tickets_Num) {
        Tickets_Num = tickets_Num;
    }
    public LocalTime getBooking_Date() {
        return Booking_Date;
    }
    public float getTotal_price() {
        return Total_price;
    }
    public int getTickets_Num() {
        return Tickets_Num;
    }

    public void InsertBooking(){
        String query = "INSERT INTO Booking (passenger_ssn,Booking_Date,Total_price,Tickets_Num) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, passenger_ssn);
            stmt.setTime(2, Time.valueOf(Booking_Date)); // convert LocalTime to java.sql.Time
            stmt.setFloat(3, Total_price);
            stmt.setInt(4, Tickets_Num);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void printBookings(){
        try {
            String query = "SELECT * FROM Booking";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                passenger_ssn = rs.getInt("passenger_ssn");
                Booking_Date = rs.getTime("Booking_Date").toLocalTime();
                Total_price = rs.getFloat("Total_price");
                Tickets_Num = rs.getInt("Tickets_Num");

                System.out.println("passenger_ssn "+passenger_ssn+" booking date "+Booking_Date+" total price "+Total_price+" tickets number "+Tickets_Num);
            }
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
    public void PassBookings(int pass_ssn){
        try {
            String query = "SELECT * FROM Booking";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                passenger_ssn = rs.getInt("passenger_ssn");
                Booking_Date = rs.getTime("Booking_Date").toLocalTime();
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
}
