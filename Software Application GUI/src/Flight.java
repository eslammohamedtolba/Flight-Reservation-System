import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Flight {
    private int Aircraft_id,Economyprice,VIPprice,Businessprice,Gate_num;
    private LocalDateTime Dep_time,Arrival_time;
    private String Dep_airport,Arrival_country,Arrival_airport,Dep_country;
    private int ReservedNum=0;
    private Connection connection=DBconnection.getConnection();

    public Flight(){}
    public Flight(int economyprice,int VIPprice,int businessprice,int craft_id,int Gate_n,LocalDateTime dep_time, LocalDateTime arrival_time, String dep_airport,String arrival_airport,String dep_country,String arrival_country) {
        Aircraft_id=craft_id;
        Economyprice = economyprice;
        this.VIPprice = VIPprice;
        Businessprice = businessprice;
        Dep_time = dep_time;
        Arrival_time = arrival_time;
        Dep_airport = dep_airport;
        Arrival_country = arrival_country;
        Arrival_airport = arrival_airport;
        Dep_country = dep_country;
        Gate_num=Gate_n;
    }

    public void InsertFlight(){
        String query = "INSERT INTO Flight (Aircraft_id,Dep_time,Arrival_time,Dep_airport,Arrival_airport,Dep_country,Arrival_country,ReservedNum,Gate_num,Economyprice,VIPprice,Businessprice) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, Aircraft_id);
            stmt.setTimestamp(2,Timestamp.valueOf(Dep_time));
            stmt.setTimestamp(3, Timestamp.valueOf((Arrival_time)));
            stmt.setString(4,Dep_airport);
            stmt.setString(5,Arrival_airport);
            stmt.setString(6,Dep_country);
            stmt.setString(7,Arrival_country);
            stmt.setInt(8,ReservedNum);
            stmt.setInt(9,Gate_num);
            stmt.setInt(10,Economyprice);
            stmt.setInt(11,VIPprice);
            stmt.setInt(12,Businessprice);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<ArrayList> printFlights(){
        ArrayList<ArrayList>AAList=new ArrayList<>();
        try {
            String sql = "SELECT F.*, Af.model, Al.Name_Airline AS AirlineName " +
                    "FROM Flight F " +
                    "LEFT JOIN Aircraft Af ON F.Aircraft_id = Af.id " +
                    "LEFT JOIN Airline Al ON Af.Airline_id = Al.id";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ArrayList Ar=new ArrayList();
                Ar.add(rs.getInt("id"));
                Ar.add(rs.getInt("Aircraft_id"));
                Ar.add(rs.getTimestamp("Dep_time"));
                Ar.add(rs.getTimestamp("Arrival_time"));
                Ar.add(rs.getString("Dep_airport"));
                Ar.add(rs.getString("Arrival_airport"));
                Ar.add(rs.getString("Dep_country"));
                Ar.add(rs.getString("Arrival_country"));
                Ar.add(rs.getInt("ReservedNum"));
                Ar.add(rs.getString("model"));
                Ar.add(rs.getString("AirlineName"));
                Ar.add(rs.getInt("Gate_num"));
                Ar.add(rs.getInt("Economyprice"));
                Ar.add(rs.getString("VIPprice"));
                Ar.add(rs.getString("Businessprice"));
                AAList.add(Ar);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return AAList;
    }
    public void UpdateFlight(int Flight_id,int Aircraft_id,LocalDateTime Dep_time,LocalDateTime Arrival_time,String Arrival_airport,int Gate_num,int Economyprice,int VIPprice,int Businessprice){
        String sql = "UPDATE Flight SET Aircraft_id = ?,Dep_time = ?, Arrival_time = ?, Arrival_airport = ?, Gate_num = ?, Economyprice = ?, VIPprice = ?, Businessprice = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Aircraft_id);
            stmt.setTimestamp(2, Timestamp.valueOf(Dep_time));
            stmt.setTimestamp(3, Timestamp.valueOf(Arrival_time));
            stmt.setString(4, Arrival_airport);
            stmt.setInt(5, Gate_num);
            stmt.setInt(6, Economyprice);
            stmt.setInt(7, VIPprice);
            stmt.setInt(8, Businessprice);
            stmt.setInt(9, Flight_id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int GetAircraftIdByFlight(int Flight_id){
        String query = "SELECT * FROM Flight WHERE id = ?";
        PreparedStatement stmt=null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, Flight_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Aircraft_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
    public ArrayList<ArrayList<Object>> printFlightsccriteria(String source,String destination,int num_Seats){
        ArrayList<ArrayList<Object>> AAList = new ArrayList<>();
        try {
            String sql = "SELECT F.*, Af.model AS AircraftModel, Af.id AS AircraftId, Al.Name_Airline AS AirlineName " +
                    "FROM Flight F " +
                    "LEFT JOIN Aircraft Af ON F.Aircraft_id = Af.id " +
                    "LEFT JOIN Airline Al ON Af.Airline_id = Al.id " +
                    "WHERE F.Arrival_country = ? AND F.Dep_country = ? AND Af.total_Num_seats - F.ReservedNum >= ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, destination);
            stmt.setString(2, source);
            stmt.setInt(3, num_Seats);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ArrayList<Object> row = new ArrayList<>();
                row.add(rs.getInt("id"));
                row.add(rs.getInt("AircraftId"));
                row.add(rs.getTimestamp("Dep_time"));
                row.add(rs.getTimestamp("Arrival_time"));
                row.add(rs.getString("Dep_airport"));
                row.add(rs.getString("Arrival_airport"));
                row.add(rs.getString("Dep_country"));
                row.add(rs.getString("Arrival_country"));
                row.add(rs.getInt("ReservedNum"));
                row.add(rs.getString("AircraftModel"));
                row.add(rs.getString("AirlineName"));
                row.add(rs.getInt("Gate_num"));
                row.add(rs.getInt("Economyprice"));
                row.add(rs.getString("VIPprice"));
                row.add(rs.getString("Businessprice"));
                AAList.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return AAList;
    }
    public void DeleteFlight(int Flight_id){
        String sql = "SELECT Af.id as Aircraft_id, t.id as Ticket_id " +
                "FROM Flight F " +
                "LEFT JOIN Aircraft Af ON F.Aircraft_id = Af.id " +
                "RIGHT JOIN Ticket t ON t.Flight_id = F.id " +
                "WHERE F.id = ?";

        PreparedStatement stmt = null;
        try {
            Ticket t=new Ticket();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Flight_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int count = rs.getInt("Ticket_id");
                Aircraft_id=rs.getInt("Aircraft_id");
                t.DeleteTicketofFlight(count);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Aircraft Af=new Aircraft();
        Af.ChangeStatus(Aircraft_id,0);

        Statement stmt2 = null;
        try {
            stmt2 = connection.createStatement();
            sql = "DELETE FROM Flight WHERE id = " + Flight_id;
            stmt2.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public int GetGAte_Num(int Flight_id){
        String query = "SELECT * FROM Flight where id=" + Flight_id;
        Statement stmt = null;
        ArrayList Ar = new ArrayList();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                return rs.getInt("Gate_num");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
    public ArrayList<Float> GetPrices(int Flight_id){
        String query = "SELECT * FROM Flight where id = " + Flight_id;
        Statement stmt = null;
        ArrayList Ar = new ArrayList();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                Ar.add(rs.getFloat("Economyprice"));
                Ar.add(rs.getFloat("VIPprice"));
                Ar.add(rs.getFloat("Businessprice"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Ar;
    }
    public ArrayList<Integer> GetSeatnum(int Flight_id){
        String query = "SELECT * FROM Flight where id = " + Flight_id;
        Statement stmt = null;
        ArrayList<Integer> Ar = new ArrayList();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                Ar.add(rs.getInt("EconomyST"));
                Ar.add(rs.getInt("VIPST"));
                Ar.add(rs.getInt("BusinessST"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Ar;
    }
    public ArrayList<ArrayList> ticketsForFlights(){
        ArrayList<ArrayList>AAList=new ArrayList<>();
        try {
            String sql = "SELECT COUNT(*) AS count , F.id as FId " +
                    "FROM Flight F " +
                    "join Ticket t on t.Flight_id=F.id " +
                    "group by F.id " +
                    "order by count desc";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ArrayList Ar=new ArrayList();
                Ar.add(rs.getInt("FId"));
                Ar.add(rs.getInt("count"));
                AAList.add(Ar);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return AAList;
    }
    public void UpdateSeats(int Flight_id,int EcoSeat,int VIPSeats,int BusSeats){
        String sql = "UPDATE Flight SET EconomyST = ?,  VIPST = ?, BusinessST = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, EcoSeat);
            stmt.setInt(2, VIPSeats);
            stmt.setInt(3, BusSeats);
            stmt.setInt(4, Flight_id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean ExistFlight(int F_id){
        try {
            String sql = "SELECT COUNT(*) AS count FROM Flight WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, F_id);
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
    public void INcreReservedSeats(int Flight_id,int Reserved_St){
        String sql = "SELECT F.ReservedNum as RS FROM Flight F where id = "+Flight_id;
        PreparedStatement stmt = null;int count=Reserved_St;
        try {
            Ticket t=new Ticket();
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                count += rs.getInt("RS");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sql = "UPDATE Flight SET ReservedNum = ? WHERE id = " + Flight_id;
        PreparedStatement stmt2 = null;
        try {
            stmt2 = connection.prepareStatement(sql);
            stmt2.setInt(1, count);
            stmt2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
