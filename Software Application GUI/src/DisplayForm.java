import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayForm extends JFrame {
    private JPanel DisplayPanel;
    private JButton signingUpAsAButton;
    private JButton logInAsAButton;
    private JButton signingInAsAButton;
    private JLabel DisplayLabel;

    public DisplayForm(){
        this.setTitle("Display Form");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(DisplayPanel);
        logInAsAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLogin().setVisible(true);
                setVisible(false);
            }
        });
        signingUpAsAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PassengerSignUp();
                setVisible(false);
            }
        });
        signingInAsAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PassengerSignIn();
                setVisible(false);
            }
        });
    }
    public static void main(String[] args) {
        new DisplayForm();
    }
}
