import java.sql.*;

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

    public int getAirline_id() {
        return Airline_id;
    }
    public void setAirline_id(int airline_id) {
        Airline_id = airline_id;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getTotal_Num_seats() {
        return total_Num_seats;
    }
    public void setTotal_Num_seats(int total_Num_seats) {
        this.total_Num_seats = total_Num_seats;
    }
    public boolean isStatus_Aircraft() {
        return status_Aircraft;
    }
    public void setStatus_Aircraft(boolean status_Aircraft) {
        this.status_Aircraft = status_Aircraft;
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
            String query = "SELECT * FROM Aircraft";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Airline Ar=new Airline();
            while (rs.next()) {
                Airline_id = rs.getInt("Airline_id");
                model = rs.getString("model");
                total_Num_seats = rs.getInt("total_Num_seats");
                status_Aircraft = rs.getBoolean("status_Aircraft");
                System.out.println("Aircraft model "+model+" Airline name "+Ar.NameAirlineid(Airline_id)+" Number seats "+total_Num_seats+" Status "+status_Aircraft);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean ExistAircraft(String model){
        try {
            String query = "SELECT * FROM Aircraft";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                this.model = rs.getString("model");
                if(this.model.equals(model)){
                    return true;
                }
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
        return null;
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
}










