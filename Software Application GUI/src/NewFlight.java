import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class NewFlight extends JFrame {
    private JPanel newFlightpanel;
    private JTextField BusinesspriceField;
    private JTextField VippriceField;
    private JTextField AircraftIdField;
    private JTextField EconomypriceField;
    private JTextField GateNumField;
    private JTextField ArrivalcountryField;
    private JTextField YearArrivalField;
    private JTextField DepcountryField;
    private JTextField MonthArrivalField;
    private JTextField MinutesArrivalField;
    private JTextField depAirportField;
    private JTextField ArrivalAirportField;
    private JTextField DayArrivalField;
    private JTextField HourArrivalField;
    private JTextField MinutesDepFiled;
    private JTextField YearDepField;
    private JTextField MonthDepField;
    private JTextField DayDepField;
    private JTextField HourDepField;
    private JButton createFlightButton;
    private JButton cancelFlightButton;
    private JLabel ErrorSubmitLabel;
    int Aircraft_id,year,month,day,hour,minute,Economyprice,VIPprice,businessprice,Gate_n;
    LocalDateTime Dep_time,Arrival_time;
    String Dep_airport,Arrival_airport,Dep_country,Arrival_country;

    public NewFlight(){
        this.setTitle("New Flight Form");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(newFlightpanel);
        createFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Aircraft Af=new Aircraft();
                Aircraft_id=Integer.parseInt(AircraftIdField.getText());
                if(Af.ModelAircraftbyid(Aircraft_id).equals("null")){
                    ErrorSubmitLabel.setText("the Aircraft id is not valid please enter valid id");
                }
                else{
                    if(!Af.GetStatus(Aircraft_id)){
                        Af.ChangeStatus(Aircraft_id,1);
                        year=Integer.parseInt(YearArrivalField.getText());
                        month=Integer.parseInt(MonthArrivalField.getText());
                        day=Integer.parseInt(DayArrivalField.getText());
                        hour=Integer.parseInt(HourArrivalField.getText());
                        minute=Integer.parseInt(MinutesArrivalField.getText());
                        Arrival_time=LocalDateTime.of(year, month, day, hour, minute,0);

                        year=Integer.parseInt(YearDepField.getText());
                        month=Integer.parseInt(MonthDepField.getText());
                        day=Integer.parseInt(DayDepField.getText());
                        hour=Integer.parseInt(HourDepField.getText());
                        minute=Integer.parseInt(MinutesDepFiled.getText());
                        Dep_time = LocalDateTime.of(year, month, day, hour, minute,0);

                        Dep_airport=depAirportField.getText();
                        Arrival_airport=ArrivalAirportField.getText();
                        Dep_country=DepcountryField.getText();
                        Arrival_country=ArrivalcountryField.getText();

                        Economyprice=Integer.parseInt(EconomypriceField.getText());
                        VIPprice=Integer.parseInt(VippriceField.getText());
                        businessprice=Integer.parseInt(BusinesspriceField.getText());
                        Gate_n=Integer.parseInt(GateNumField.getText());

                        Flight F=new Flight(Economyprice,VIPprice,businessprice,Aircraft_id,Gate_n,Dep_time,Arrival_time,Dep_airport,Arrival_airport,Dep_country,Arrival_country);
                        F.InsertFlight();
                        new AdminFunctions();
                        setVisible(false);
                    }
                    else{
                        ErrorSubmitLabel.setText("Aircraft id you entered isn't available");
                    }
                }
            }
        });
        cancelFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminFunctions();
                setVisible(false);
            }
        });
        createFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
