import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class UpdateFlight extends JFrame {
    private JPanel UpdateFlightPanel;
    private JPanel newFlightpanel;
    private JTextField BusinesspriceField;
    private JTextField VippriceField;
    private JTextField FlightIdField;
    private JTextField EconomypriceField;
    private JTextField GateNumField;
    private JTextField YearArrivalField;
    private JTextField MonthArrivalField;
    private JTextField MinutesArrivalField;
    private JTextField ArrivalAirportField;
    private JTextField DayArrivalField;
    private JTextField HourArrivalField;
    private JTextField MinutesDepFiled;
    private JTextField YearDepField;
    private JTextField MonthDepField;
    private JTextField DayDepField;
    private JTextField HourDepField;
    private JButton updateFlightButton;
    private JButton cancelFlightButton;
    private JLabel ErrorSubmitLabel;
    private JTextField AircraftIdFiled;
    int FLight_id,Aircraft_id,year,month,day,hour,minute,Economyprice,VIPprice,businessprice,Gate_n;
    LocalDateTime Dep_time,Arrival_time;
    String Arrival_airport;
    Flight F=new Flight();

    public UpdateFlight(){
        this.setTitle("Display Form");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(UpdateFlightPanel);
        updateFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FLight_id=Integer.parseInt(FlightIdField.getText());
                if(!F.ExistFlight(FLight_id)){
                    ErrorSubmitLabel.setText("the Flight id is not valid please enter valid id");
                }
                else{
                    Aircraft_id=Integer.parseInt(AircraftIdFiled.getText());
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

                    Arrival_airport=ArrivalAirportField.getText();

                    Economyprice=Integer.parseInt(EconomypriceField.getText());
                    VIPprice=Integer.parseInt(VippriceField.getText());
                    businessprice=Integer.parseInt(BusinesspriceField.getText());
                    Gate_n=Integer.parseInt(GateNumField.getText());
                    F.UpdateFlight(FLight_id,Aircraft_id,Dep_time,Arrival_time,Arrival_airport,Gate_n,Economyprice,VIPprice,businessprice);
                    new AdminFunctions();
                    setVisible(false);
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
    }
}
