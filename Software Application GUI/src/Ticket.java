import java.sql.*;

public class Ticket {
    private int Booking_id;
    private int Flight_id;
    private float price;
    private int seat_num;
    private String _class;
    private int Gate_Num;
    private Connection connection=DBconnection.getConnection();

    public Ticket(){}
    public Ticket(int booking_id, int flight_id, float price, int seat_num, String _class, int gate_Num) {
        Booking_id = booking_id;
        Flight_id = flight_id;
        this.price = price;
        this.seat_num = seat_num;
        this._class = _class;
        Gate_Num = gate_Num;
    }

    public int getBooking_id() {
        return Booking_id;
    }
    public void setBooking_id(int booking_id) {
        Booking_id = booking_id;
    }
    public int getFlight_id() {
        return Flight_id;
    }
    public void setFlight_id(int flight_id) {
        Flight_id = flight_id;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getSeat_num() {
        return seat_num;
    }
    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }
    public String get_class() {
        return _class;
    }
    public void set_class(String _class) {
        this._class = _class;
    }
    public int getGate_Num() {
        return Gate_Num;
    }
    public void setGate_Num(int gate_Num) {
        Gate_Num = gate_Num;
    }

    public void InsertTicket(){
        String query = "INSERT INTO Ticket (Booking_id, Flight_id, price, seat_num, _class, Gate_Num) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, Booking_id);
            stmt.setInt(2, Flight_id);
            stmt.setFloat(3, price);
            stmt.setInt(4, seat_num);
            stmt.setString(5, _class);
            stmt.setInt(6, Gate_Num);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void printTickets(){
        try {
            String query = "SELECT * FROM Ticket";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                price = rs.getFloat("price");
                seat_num = rs.getInt("seat_num");
                _class = rs.getString("_class");
                Gate_Num = rs.getInt("Gate_Num");

                System.out.println(" price "+price+" seat_num "+seat_num+" _class "+_class+ " Gate_Num "+Gate_Num);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
