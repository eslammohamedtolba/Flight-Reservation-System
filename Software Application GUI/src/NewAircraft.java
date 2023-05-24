import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAircraft extends JFrame {
    private JTextField AirlineIdField;
    private JTextField NumSeatsField;
    private JTextField ModelField;
    private JButton ADDAircraftButton;
    private JLabel SumbitErrorLabel;
    private JPanel AircraftPanel;
    private JButton cancelAircraftButton;

    public NewAircraft(){
        this.setTitle("Display Form");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(AircraftPanel);
        ADDAircraftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Airline Al=new Airline();
                int Airline_id=Integer.parseInt(AirlineIdField.getText());
                if(Al.NameAirlineid(Airline_id).equals("null")){
                    SumbitErrorLabel.setText("the Airline id is not valid please enter valid id");
                }
                else{
                    Aircraft f= new Aircraft(Airline_id,ModelField.getText(),Integer.parseInt(NumSeatsField.getText()));
                    f.InsertAircraft();
                    setVisible(false);
                    new AdminFunctions();
                }
            }
        });
        cancelAircraftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AdminFunctions();
            }
        });
    }

    public static void main(String[] args) {
        new NewAircraft();
    }
}
