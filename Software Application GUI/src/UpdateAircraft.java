import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateAircraft extends JFrame {

    private JPanel UpdateAircraftpanel;
    private JTextField textFieldAircraftId;
    private JLabel ErrorSubmitLabel;
    private JTextField textFieldAircraftStatus;
    private JButton updateButton;
    private JButton cancelUpadatingButton;

    UpdateAircraft(){
        this.setTitle("Display Form");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(UpdateAircraftpanel);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Aircraft_id=Integer.parseInt(textFieldAircraftId.getText());
                Aircraft Af=new Aircraft();
                if(Af.ModelAircraftbyid(Aircraft_id).equals("null")){
                    ErrorSubmitLabel.setText("the Aircraft id is not valid please enter valid id");
                }
                else{
                    Af.ChangeStatus(Aircraft_id,Integer.parseInt(textFieldAircraftStatus.getText()));
                    new AdminFunctions();
                    setVisible(false);
                }
            }
        });
        cancelUpadatingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminFunctions();
                setVisible(false);
            }
        });
    }
}
