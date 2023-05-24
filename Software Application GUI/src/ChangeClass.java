import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChangeClass extends JFrame {
    private JTextField TicketIdField;
    private JButton TicketIdButton;
    private JLabel ErrorSubmitLabel;
    private JComboBox comboBox;
    private JButton changeClassButton;
    private JButton cancelChangingButton;
    private JPanel ChangeclassPanel;
    private int Ticket_id;
    private int Flight_id;
    Ticket t=new Ticket();
    Flight F=new Flight();
    ArrayList<Float> PricesSeats;
    ArrayList<Integer>NumSeats;
    String str1,str2,str3;

    public ChangeClass(boolean isSignIn,int SSN){
        this.setTitle("Change Class Form");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(ChangeclassPanel);
        comboBox.setVisible(false);
        changeClassButton.setVisible(false);
        TicketIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ticket_id=Integer.parseInt(TicketIdField.getText());
                if(t.ISExist(Ticket_id)){
                    if(t.TicketOfPass(SSN,Ticket_id)){
                        Flight_id=t.GetFlight_id(Ticket_id);
                        PricesSeats=F.GetPrices(Flight_id);
                        NumSeats=F.GetSeatnum(Flight_id);
                        TicketIdField.disable();
                        TicketIdButton.disable();
                        str1="Economy price "+(PricesSeats.get(0)).toString();
                        str2="VIP price "+(PricesSeats.get(1)).toString();
                        str3="business price "+(PricesSeats.get(2)).toString();
                        Object[] options = {"Enter Option", str1, str2,str3};
                        comboBox.setModel(new DefaultComboBoxModel<>(options));
                        comboBox.setVisible(true);
                        changeClassButton.setVisible(true);
                    }
                    else{
                        ErrorSubmitLabel.setText("Entered Ticket is not related to your SSN");
                    }
                }
                else{
                    ErrorSubmitLabel.setText("Entered id not valid");
                }
            }
        });
        changeClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                if(selectedOption.equals("Enter Option")){
                    ErrorSubmitLabel.setText("You should choose one option");
                }
                else {
                    if(selectedOption.equals(str1)){
                        t.setPrice(PricesSeats.get(0));
                        t.setSeat_num(NumSeats.get(0));
                        NumSeats.set(0,NumSeats.get(0)+1);
                        t.set_class("Economy");
                    }
                    else if(selectedOption.equals(str2)){
                        t.setPrice(PricesSeats.get(1));
                        t.setSeat_num(NumSeats.get(1));
                        NumSeats.set(1,NumSeats.get(1)+1);
                        t.set_class("VIP");
                    }
                    else{
                        t.setPrice(PricesSeats.get(2));
                        t.setSeat_num(NumSeats.get(2));
                        NumSeats.set(2,NumSeats.get(2)+1);
                        t.set_class("Business");
                    }
                    F.UpdateSeats(Flight_id,NumSeats.get(0),NumSeats.get(1),NumSeats.get(2));
                    F.INcreReservedSeats(Flight_id,1);
                    t.UpdateClass(Ticket_id,t.get_class(),t.getPrice(),t.GetPrice(Ticket_id),t.getSeat_num());
                    new PassengerFunctions(isSignIn,SSN);
                    setVisible(false);
                }
            }
        });
        cancelChangingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PassengerFunctions(isSignIn,SSN);
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        new ChangeClass(true,16);
    }
}
