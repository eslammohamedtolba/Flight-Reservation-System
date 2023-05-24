import java.sql.*;
import java.util.ArrayList;

public class Ticket {
    private String Booking_id;
    private int Flight_id;
    private float price;
    private int seat_num;
    private String _class;
    private int Gate_Num;
    private Connection connection=DBconnection.getConnection();

    public Ticket(){}
    public Ticket(String booking_id, int flight_id, float price, int seat_num, String _class, int gate_Num) {
        Booking_id = booking_id;
        Flight_id = flight_id;
        this.price = price;
        this.seat_num = seat_num;
        this._class = _class;
        Gate_Num = gate_Num;
    }

    public void setBooking_id(String booking_id) {
        Booking_id = booking_id;
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
    public void setGate_Num(int gate_Num) {
        Gate_Num = gate_Num;
    }

    public void InsertTicket(){
        String query = "INSERT INTO Ticket (Booking_id, Flight_id, price, seat_num, class, Gate_Num) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Booking_id);
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
    public void DeleteTicketofFlight(int Ticket_id){
        String sql = "DELETE FROM Ticket WHERE id = " + Ticket_id;
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean ISExist(int Ticket_id){
        try {
            String sql = "SELECT COUNT(*) AS count FROM Ticket WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Ticket_id);
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
    public boolean TicketOfPass(int ssn,int Ticket_id){
        String sql = "SELECT COUNT(*) AS count " +
                "FROM Ticket t " +
                "JOIN Booking b ON t.Booking_id = b.id " +
                "JOIN Passenger p ON b.passenger_ssn = p.SSN " +
                "WHERE t.id = ? AND p.SSN = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Ticket_id);
            stmt.setInt(2, ssn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public int GetFlight_id(int Ticket_id){
        String sql = "SELECT F.id as Flight_id " +
                "FROM Flight F " +
                "Right JOIN Ticket t ON t.Flight_id = F.id " +
                "where t.id = ?";
        PreparedStatement stmt = null;
        int count=0;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Ticket_id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                count = rs.getInt("Flight_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public float GetPrice(int Ticket_id){
        float count=0f;
        try {
            String sql = "SELECT price FROM Ticket WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Ticket_id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                count = rs.getFloat("price");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public void UpdateClass(int Ticket_id,String Class,float newprice,float Oldprice,int Seat_number){
        String sql = "UPDATE Ticket SET price = ?, class = ?, seat_num = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setFloat(1, newprice);
            stmt.setString(2, Class);
            stmt.setInt(3, Seat_number);
            stmt.setInt(4, Ticket_id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String Booking_id=null;float Totalprice=0f;
        sql = "SELECT b.id AS Booking_id, b.Total_price as Tprice " +
                "FROM Ticket t " +
                "JOIN Booking b ON t.Booking_id = b.id " +
                "WHERE t.id = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Ticket_id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Booking_id = rs.getString("Booking_id");
                Totalprice = rs.getFloat("Tprice");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "UPDATE Booking SET Total_price = ? WHERE id = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setFloat(1, Totalprice-Oldprice+newprice);
            stmt.setString(2, Booking_id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void printTickets(){
        try {
            String query = "SELECT * FROM Ticket";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList Ar = new ArrayList();int k = 1;
            while (rs.next()) {
                Ar.add("Ticket id "+rs.getInt("id"));
                Ar.add("price "+rs.getFloat("price"));
                Ar.add("seat_num "+rs.getInt("seat_num"));
                Ar.add("class "+rs.getString("class"));
                Ar.add("Gate_Num "+ rs.getInt("Gate_Num"));
                System.out.println("(" + k + ") " + Ar.get(0));
                for (int i = 1; i < Ar.size(); i++) {
                    System.out.println("\t" + Ar.get(i));
                }
                k++;
                Ar.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
