import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFunctions extends JFrame {
    private JPanel AdminFuncPanel;
    private JButton backButton;
    private JButton addingAnAircraftButton;
    private JButton printingAllAircraftButton;
    private JButton updatingAnAircraftDetailsButton;
    private JButton printingAllFlightButton;
    private JButton addingAFlightButton;
    private JButton updatingAFlightDetailsButton;
    private JButton cancelFlightButton;
    private JButton printBookingsNumberForButton;
    private JButton printTicketsNumberForButton;
    private JButton selectMaxAndMinButton;
    private JTextField FlightIdFeild;
    private JLabel ErrorSumbitLabel;
    private JButton Cancel;
    private JLabel FlightIdLabel;
    private JButton closeCancel;

    public AdminFunctions(){
        this.setTitle("AdminFucntions Form");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        Cancel.setVisible(false);
        FlightIdFeild.setVisible(false);
        FlightIdLabel.setVisible(false);
        closeCancel.setVisible(false);
        this.setContentPane(AdminFuncPanel);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLogin();
                setVisible(false);
            }
        });
        addingAnAircraftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewAircraft();
                setVisible(false);
            }
        });
        updatingAnAircraftDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateAircraft();
                setVisible(false);
            }
        });
        addingAFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewFlight();
                setVisible(false);
            }
        });
        updatingAFlightDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateFlight();
                setVisible(false);
            }
        });
        cancelFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cancel.setVisible(true);
                FlightIdFeild.setVisible(true);
                FlightIdLabel.setVisible(true);
                closeCancel.setVisible(true);
            }
        });
        printingAllFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PrintAllFlights();
                setVisible(false);
            }
        });
        printingAllAircraftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PrintAllAircrafts();
                setVisible(false);
            }
        });
        printBookingsNumberForButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookingsNPassengers();
                setVisible(false);
            }
        });
        printTicketsNumberForButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PrintTicketsFlights();
                setVisible(false);
            }
        });
        selectMaxAndMinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaxMinBooksprices f= new MaxMinBooksprices();
                setVisible(false);
            }
        });
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Flight F=new Flight();
                int FLight_id;
                FLight_id=Integer.parseInt(FlightIdFeild.getText());
                if(!F.ExistFlight(FLight_id)){
                    ErrorSumbitLabel.setForeground(Color.RED);
                    ErrorSumbitLabel.setText("the Flight id is not valid please enter valid id");
                }
                else{
                    F.DeleteFlight(FLight_id);
                    ErrorSumbitLabel.setForeground(Color.GREEN);
                    ErrorSumbitLabel.setText("the Flight has been deleted");
                    Cancel.setVisible(false);
                    FlightIdFeild.setVisible(false);
                    FlightIdLabel.setVisible(false);
                    Timer timer = new Timer(10000, null);
                    timer.addActionListener(ee -> ErrorSumbitLabel.setVisible(false));
                    timer.start();
                }
            }
        });
        closeCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cancel.setVisible(false);
                FlightIdFeild.setVisible(false);
                FlightIdLabel.setVisible(false);
                closeCancel.setVisible(false);
            }
        });
    }
}
