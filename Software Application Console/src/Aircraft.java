import java.sql.*;
import java.util.ArrayList;

public class Aircraft {
    private int Airline_id;
    private String model;
    private int total_Num_seats;
    private boolean status_Aircraft;
    private Connection connection=DBconnection.getConnection();

    public Aircraft(){}
    public Aircraft(int airline_id, String model, int total_Num_seats) {
        Airline_id = airline_id;
        this.model = model;
        this.total_Num_seats = total_Num_seats;
    }

    public void InsertAircraft(){
        String query = "INSERT INTO Aircraft (Airline_id,model,total_Num_seats,status_Aircraft) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,Airline_id );
            stmt.setString(2, model);
            stmt.setInt(3,total_Num_seats );
            stmt.setBoolean(4, status_Aircraft);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void printAircrafts(){
        try {
            String sql = "SELECT Af.*, Al.Name_Airline AS AirlineName " +
                    "FROM Aircraft Af " +
                    "LEFT OUTER JOIN Airline Al ON Af.Airline_id = Al.id";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList Ar = new ArrayList();int k = 1;
            while (rs.next()) {
                Ar.add("Aircraft id " + rs.getInt("id"));
                Ar.add("Aircraft model " + rs.getString("model"));
                Ar.add("Number seats " + rs.getInt("total_Num_seats"));
                Ar.add("Status " +rs.getBoolean("status_Aircraft"));
                Ar.add("Airline name " + rs.getString("AirlineName"));
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
    public boolean ExistAircraft(String model){
        try {
            String sql = "SELECT COUNT(*) AS count FROM Aircraft WHERE model = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, model);
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
    public String ModelAircraftbyid(int searchId){
        try {
            String query = "SELECT * FROM Aircraft";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                model = rs.getString("model");
                if(id==searchId){
                    return model;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "null";
    }
    public String Namelinebycraftid(int searchId){
        try {
            String query = "SELECT * FROM Aircraft";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Airline Ar=new Airline();
            while (rs.next()) {
                int id = rs.getInt("id");
                Airline_id = rs.getInt("Airline_id");
                if(id==searchId){
                    return Ar.NameAirlineid(Airline_id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "null";
    }
    public void ChangeStatus(int Aircraft_id,int status){
        String query = "UPDATE Aircraft SET status_Aircraft = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, status);
            stmt.setInt(2, Aircraft_id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean GetStatus(int Aircraft_id){
        try {
            String sql = "SELECT COUNT(*) AS count FROM Aircraft WHERE id = ? AND status_Aircraft = 1";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Aircraft_id);
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
}