import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PassengerFunctions extends JFrame {
    private JPanel PassFunctionsPanel;
    private JButton backButton;
    private JButton showingAListOfButton;
    private JButton changingFlightClassButton;
    private JButton updatingAUserSButton;
    private JButton bookFlightButton;
    private JTextField ListFirsttextField;
    private JTextField ListSecondtextField;
    private JTextField ListThirdtextField;
    private JTextField BookFirsttextField;
    private JTextField BookSecondtextField;
    private JTextField BookThirdtextField;
    private JTextField UpdateFirstLabel;
    private JTextField UpdateSecondLabel;
    private JTextField UpdateThirdLabel;

    private JButton closeshowing;
    private JButton closeBooking;
    private JButton Closeupdating;
    private JButton buttonshowing;
    private JButton buttonBooking;
    private JButton buttonUpdating;
    private JLabel showingsourceLabel;
    private JLabel showingDistlabel;
    private JLabel showingseatslabel;
    private JLabel Bookingsourcealabel;
    private JLabel Bookingdistlabel;
    private JLabel BookingSeatslabel;
    private JLabel Updateingemailalabel;
    private JLabel Updatingphonelabel;
    private JLabel Updatingpassportlabel;
    private JLabel ErrorSubmitlabel;

    public PassengerFunctions(boolean isSignIn,int SSN){
        this.setTitle("Passenger Functions Form");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        buttonshowing.setVisible(false);
        closeshowing.setVisible(false);
        ListFirsttextField.setVisible(false);
        ListSecondtextField.setVisible(false);
        ListThirdtextField.setVisible(false);
        showingsourceLabel.setVisible(false);
        showingDistlabel.setVisible(false);
        showingseatslabel.setVisible(false);

        buttonBooking.setVisible(false);
        closeBooking.setVisible(false);
        BookFirsttextField.setVisible(false);
        BookSecondtextField.setVisible(false);
        BookThirdtextField.setVisible(false);
        Bookingsourcealabel.setVisible(false);
        Bookingdistlabel.setVisible(false);
        BookingSeatslabel.setVisible(false);

        buttonUpdating.setVisible(false);
        Closeupdating.setVisible(false);
        UpdateFirstLabel.setVisible(false);
        UpdateSecondLabel.setVisible(false);
        UpdateThirdLabel.setVisible(false);
        Updateingemailalabel.setVisible(false);
        Updatingphonelabel.setVisible(false);
        Updatingpassportlabel.setVisible(false);
        this.setContentPane(PassFunctionsPanel);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isSignIn){
                    new PassengerSignIn();
                }
                else{
                    new PassengerSignUp();
                }
                setVisible(false);
            }
        });
        showingAListOfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonshowing.setVisible(true);
                closeshowing.setVisible(true);
                ListFirsttextField.setVisible(true);
                ListSecondtextField.setVisible(true);
                ListThirdtextField.setVisible(true);
                showingsourceLabel.setVisible(true);
                showingDistlabel.setVisible(true);
                showingseatslabel.setVisible(true);
            }
        });
        updatingAUserSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonUpdating.setVisible(true);
                Closeupdating.setVisible(true);
                UpdateFirstLabel.setVisible(true);
                UpdateSecondLabel.setVisible(true);
                UpdateThirdLabel.setVisible(true);
                Updateingemailalabel.setVisible(true);
                Updatingphonelabel.setVisible(true);
                Updatingpassportlabel.setVisible(true);
            }
        });
        bookFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonBooking.setVisible(true);
                closeBooking.setVisible(true);
                BookFirsttextField.setVisible(true);
                BookSecondtextField.setVisible(true);
                BookThirdtextField.setVisible(true);
                Bookingsourcealabel.setVisible(true);
                Bookingdistlabel.setVisible(true);
                BookingSeatslabel.setVisible(true);
            }
        });
        closeshowing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonshowing.setVisible(false);
                closeshowing.setVisible(false);
                ListFirsttextField.setVisible(false);
                ListSecondtextField.setVisible(false);
                ListThirdtextField.setVisible(false);
                showingsourceLabel.setVisible(false);
                showingDistlabel.setVisible(false);
                showingseatslabel.setVisible(false);
            }
        });
        closeBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonBooking.setVisible(false);
                closeBooking.setVisible(false);
                BookFirsttextField.setVisible(false);
                BookSecondtextField.setVisible(false);
                BookThirdtextField.setVisible(false);
                Bookingsourcealabel.setVisible(false);
                Bookingdistlabel.setVisible(false);
                BookingSeatslabel.setVisible(false);
            }
        });
        Closeupdating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonUpdating.setVisible(false);
                Closeupdating.setVisible(false);
                UpdateFirstLabel.setVisible(false);
                UpdateSecondLabel.setVisible(false);
                UpdateThirdLabel.setVisible(false);
                Updateingemailalabel.setVisible(false);
                Updatingphonelabel.setVisible(false);
                Updatingpassportlabel.setVisible(false);
            }
        });
        buttonshowing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowListFlights(ListFirsttextField.getText(),ListSecondtextField.getText(),Integer.parseInt(ListThirdtextField.getText()),false,0,isSignIn);
                setVisible(false);
                buttonshowing.setVisible(false);
                closeshowing.setVisible(false);
                ListFirsttextField.setVisible(false);
                ListSecondtextField.setVisible(false);
                ListThirdtextField.setVisible(false);
                showingsourceLabel.setVisible(false);
                showingDistlabel.setVisible(false);
                showingseatslabel.setVisible(false);
            }
        });
        buttonBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowListFlights(BookFirsttextField.getText(),BookSecondtextField.getText(),Integer.parseInt(BookThirdtextField.getText()),true,SSN,isSignIn);
                setVisible(false);
                buttonBooking.setVisible(false);
                closeBooking.setVisible(false);
                BookFirsttextField.setVisible(false);
                BookSecondtextField.setVisible(false);
                BookThirdtextField.setVisible(false);
                Bookingsourcealabel.setVisible(false);
                Bookingdistlabel.setVisible(false);
                BookingSeatslabel.setVisible(false);
            }
        });
        buttonUpdating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Passenger p=new Passenger();
                p.setEmail(UpdateFirstLabel.getText());p.setPassport(UpdateThirdLabel.getText());p.setPhone(UpdateSecondLabel.getText());
                p.UpdatePassenger(SSN);
                buttonUpdating.setVisible(false);
                Closeupdating.setVisible(false);
                UpdateFirstLabel.setVisible(false);
                UpdateSecondLabel.setVisible(false);
                UpdateThirdLabel.setVisible(false);
                Updateingemailalabel.setVisible(false);
                Updatingphonelabel.setVisible(false);
                Updatingpassportlabel.setVisible(false);
                ErrorSubmitlabel.setText("The Updating succefully done");
                Timer timer = new Timer(10000, null);
                timer.addActionListener(ee -> ErrorSubmitlabel.setVisible(false));
                timer.start();
            }
        });
        changingFlightClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangeClass(isSignIn,SSN);
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        new PassengerFunctions(true,16);
    }
}
