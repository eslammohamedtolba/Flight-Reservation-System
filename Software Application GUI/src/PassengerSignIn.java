import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassengerSignIn extends JFrame{
    private JButton backButton;
    private JPanel PassSignInPanel;
    private JButton signInButton;
    private JTextField textFieldSSN;
    private JLabel LabelSubmitError;
    private JButton SuggestionSignUpButton;
    private JLabel SuggestionSignUpLabel;

    public PassengerSignIn() {
        this.setTitle("Sign In Form");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(PassSignInPanel);
        SuggestionSignUpButton.setVisible(false);
        SuggestionSignUpLabel.setVisible(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayForm();
                setVisible(false);
            }
        });
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ssn=Integer.parseInt(textFieldSSN.getText());
                Passenger p=new Passenger();
                if(!p.PassNamebySSN(ssn).equals("null")){
                    new PassengerFunctions(true,ssn);
                    setVisible(false);
                }
                else{
                    LabelSubmitError.setText("Incorrect SSN. Please try again.");
                    SuggestionSignUpButton.setVisible(true);
                    SuggestionSignUpLabel.setVisible(true);
                }
            }
        });
        SuggestionSignUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PassengerSignUp();
                setVisible(false);
            }
        });
    }
}
