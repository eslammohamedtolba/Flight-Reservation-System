import java.sql.*;
import java.time.LocalDateTime;

public class Payment {
    private String method;
    private String Booking_id;
    private LocalDateTime Date_P;
    private Connection connection=DBconnection.getConnection();

    public Payment(){}
    public Payment(String method, String booking_id, LocalDateTime date_P) {
        this.method = method;
        Booking_id = booking_id;
        Date_P = date_P;
    }

    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getBooking_id() {
        return Booking_id;
    }
    public void setBooking_id(String booking_id) {
        Booking_id = booking_id;
    }
    public LocalDateTime getDate_P() {
        return Date_P;
    }
    public void setDate_P(LocalDateTime date_P) {
        Date_P = date_P;
    }

    public void InsertPayment(){
        String query = "INSERT INTO Payment (Booking_id, method, Date_P) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Booking_id);
            stmt.setString(2, method);
            stmt.setTimestamp(3, Timestamp.valueOf((Date_P)));
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
