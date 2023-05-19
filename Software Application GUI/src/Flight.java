import java.sql.*;
import java.time.LocalTime;

public class Flight {
    private int Aircraft_id;
    private LocalTime Dep_time;
    private LocalTime Arrival_time;
    private String Dep_airport;
    private String Arrival_airport;
    private String Dep_country;
    private String Arrival_country;
    private int ReservedNum=0;
    private Connection connection=DBconnection.getConnection();

    public Flight(){}
    public Flight(int aircraft_id, LocalTime dep_time, LocalTime arrival_time, String dep_airport, String arrival_airport, String dep_country, String arrival_country) {
        Aircraft_id = aircraft_id;
        Dep_time = dep_time;
        Arrival_time = arrival_time;
        Dep_airport = dep_airport;
        Arrival_airport = arrival_airport;
        Dep_country = dep_country;
        Arrival_country = arrival_country;
    }

    public int getAircraft_id() {
        return Aircraft_id;
    }
    public void setAircraft_id(int aircraft_id) {
        Aircraft_id = aircraft_id;
    }
    public LocalTime getDep_time() {
        return Dep_time;
    }
    public void setDep_time(LocalTime dep_time) {
        Dep_time = dep_time;
    }
    public LocalTime getArrival_time() {
        return Arrival_time;
    }
    public void setArrival_time(LocalTime arrival_time) {
        Arrival_time = arrival_time;
    }
    public String getDep_airport() {
        return Dep_airport;
    }
    public void setDep_airport(String dep_airport) {
        Dep_airport = dep_airport;
    }
    public String getArrival_airport() {
        return Arrival_airport;
    }
    public void setArrival_airport(String arrival_airport) {
        Arrival_airport = arrival_airport;
    }
    public String getDep_country() {
        return Dep_country;
    }
    public void setDep_country(String dep_country) {
        Dep_country = dep_country;
    }
    public String getArrival_country() {
        return Arrival_country;
    }
    public void setArrival_country(String arrival_country) {
        Arrival_country = arrival_country;
    }
    public int getReservedNum() {
        return ReservedNum;
    }
    public void setReservedNum(int reservedNum) {
        ReservedNum = reservedNum;
    }

    public void InsertFlight(){
        String query = "INSERT INTO Flight (Aircraft_id,Dep_time,Arrival_time,Dep_airport,Arrival_airport,Dep_country,Arrival_country,ReservedNum) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, Aircraft_id);
            stmt.setTime(2,Time.valueOf(Dep_time));
            stmt.setTime(3, Time.valueOf(Arrival_time));
            stmt.setString(4,Dep_airport);
            stmt.setString(5,Arrival_airport);
            stmt.setString(6,Dep_country);
            stmt.setString(7,Arrival_country);
            stmt.setInt(8,ReservedNum);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void printFlights(){
        try {
            String query = "SELECT * FROM Flight";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Aircraft Af=new Aircraft();
            while (rs.next()) {
                Aircraft_id = rs.getInt("Aircraft_id");
                Dep_time = rs.getTime("Dep_time").toLocalTime();
                Arrival_time = rs.getTime("Arrival_time").toLocalTime();
                Dep_airport = rs.getString("Dep_airport");
                Arrival_airport = rs.getString("Arrival_airport");
                Dep_country = rs.getString("Dep_country");
                Arrival_country = rs.getString("Arrival_country");
                ReservedNum = rs.getInt("ReservedNum");
                System.out.println("Aircraft model "+Af.ModelAircraftbyid(Aircraft_id)+" Airline Name "+Af.Namelinebycraftid(Aircraft_id)+" Dep_time "+Dep_time+" Arrival_Time "+Arrival_time+" Dep_Airport "+Dep_airport+" Arrival_Airport "+Arrival_airport+" Dep_country "+Dep_country+" Arrival_country "+Arrival_country+" Reserved_Seats "+ReservedNum);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean ExistFlight(int Searchid){
        try {
            String query = "SELECT * FROM Flight";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int id;
            while (rs.next()) {
                id=rs.getInt("id");
                if(id==Searchid){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
