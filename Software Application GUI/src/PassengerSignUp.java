import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class PassengerSignUp extends JFrame {
    private JPanel PassSignUpPanel;
    private JButton backButton;
    private JButton signUpButton;
    private JTextField textFieldSSN;
    private JTextField textFieldFName;
    private JTextField textFieldSName;
    private JTextField textFieldEmail;
    private JTextField textFieldYear;
    private JTextField textFieldMonth;
    private JTextField textFieldDay;
    private JTextField textFieldPhone;
    private JTextField textFieldPassport;
    private JButton SuggestionSignInButton;
    private JLabel SuggestionSignInLabel;
    private JLabel LabelSubmitError;

    public PassengerSignUp(){
        this.setTitle("Sign Up Form");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(PassSignUpPanel);
        SuggestionSignInButton.setVisible(false);
        SuggestionSignInLabel.setVisible(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayForm();
                setVisible(false);
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ssn=Integer.parseInt(textFieldSSN.getText()),
                        year=Integer.parseInt(textFieldYear.getText()),
                        month=Integer.parseInt(textFieldMonth.getText()),
                        day=Integer.parseInt(textFieldDay.getText());
                String Fname=textFieldFName.getText(),
                        Sname=textFieldSName.getText(),
                        Email=textFieldEmail.getText(),
                        phone=textFieldPhone.getText(),
                        passport=textFieldPassport.getText();
                LocalDate Birthday=LocalDate.of(year, month, day);
                Passenger p=new Passenger();
                if(p.PassNamebySSN(ssn).equals("null")){
                    p.setSSN(ssn);p.setFname(Fname);
                    p.setSname(Sname);p.setEmail(Email);
                    p.setBirthday(Birthday);p.setPhone(phone);
                    p.setPassport(passport);
                    p.InsertPassenger();
                    new PassengerFunctions(false,ssn);
                    setVisible(false);
                }
                else{
                    SuggestionSignInButton.setVisible(true);
                    SuggestionSignInLabel.setVisible(true);
                    LabelSubmitError.setText("SSN is already exist. Please try again.");
                }
            }
        });
        SuggestionSignInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PassengerSignIn();
                setVisible(false);
            }
        });
    }
}
