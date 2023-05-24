import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class AdminLogin extends JFrame{
    private JPanel AdminPanel;
    private JTextField textFieldUserName;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JButton backButton;
    private JLabel LabelSubmitError;

    public AdminLogin(){
        this.setTitle("Admin Form");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(AdminPanel);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Username=textFieldUserName.getText(),
                       Password=String.valueOf(passwordField.getPassword());
                if(new Administrator().Login(Username,Password)){
                    new AdminFunctions();
                    setVisible(false);
                }
                else{
                    LabelSubmitError.setText("Incorrect username or password. Please try again.");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayForm();
                setVisible(false);
            }
        });
    }
}

