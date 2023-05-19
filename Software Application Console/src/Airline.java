import java.sql.*;

public class Airline {
    private String Name_Airline;
    private String Headquarters;
    private Connection connection=DBconnection.getConnection();

    public Airline(){}
    public Airline(String name_Airline, String headquarters) {
        Name_Airline = name_Airline;
        Headquarters = headquarters;
    }

    public String getName_Airline() {
        return Name_Airline;
    }
    public void setName_Airline(String name_Airline) {
        Name_Airline = name_Airline;
    }
    public String getHeadquarters() {
        return Headquarters;
    }
    public void setHeadquarters(String headquarters) {
        Headquarters = headquarters;
    }

    public void InsertAirline(){
        String query = "INSERT INTO Airline (Name_Airline,Headquarters) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,Name_Airline );
            stmt.setString(2, Headquarters);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void printAirlines(){
        try {
            String query = "SELECT * FROM Airline";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Name_Airline = rs.getString("Name_Airline");
                Headquarters = rs.getString("Headquarters");
                System.out.println("Airline name "+Name_Airline+" Headquarters "+Headquarters);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean ExistAirline(String Name){
        try {
            String query = "SELECT * FROM Airline";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Name_Airline = rs.getString("Name_Airline");
                if(this.Name_Airline.equals(Name)){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public String NameAirlineid(int searchId){
        try {
            String query = "SELECT * FROM Airline";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                Name_Airline = rs.getString("Name_Airline");
                if(id==searchId){
                    return Name_Airline;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
