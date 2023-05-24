import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookFlight extends JFrame {
    private JPanel BookFlightPanel;
    private JTextField FlightIdField;
    private JButton clickButton;
    private JLabel ErrorSubmitLabel;
    private JComboBox comboBox;
    private JButton ADDTicketButton;
    private JButton cancelBookingButton;
    private JLabel ErrorSubmitComboLabel;
    ArrayList<Float> PricesSeats;
    ArrayList<Integer>NumSeats;
    String str1,str2,str3,myBooking_id;
    int totalprice=0,EnteredTickets=0,FlightId;

    BookFlight(int TicketNum,int SSN,boolean isSignIn){
        this.setTitle("BookFlight Form");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(BookFlightPanel);
        ArrayList<Ticket>Tickets=new ArrayList<Ticket>(TicketNum);
        comboBox.setVisible(false);
        ADDTicketButton.setVisible(false);
        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Flight F=new Flight();
                if(F.ExistFlight(Integer.parseInt(FlightIdField.getText()))){
                    FlightId=Integer.parseInt(FlightIdField.getText());
                    PricesSeats=F.GetPrices(FlightId);
                    NumSeats=F.GetSeatnum(FlightId);
                    str1="Economy price "+(PricesSeats.get(0)).toString();
                    str2="VIP price "+(PricesSeats.get(1)).toString();
                    str3="business price "+(PricesSeats.get(2)).toString();
                    Object[] options = {"Enter Option", str1, str2,str3};
                    comboBox.setModel(new DefaultComboBoxModel<>(options));
                    comboBox.setVisible(true);
                    ADDTicketButton.setVisible(true);
                    FlightIdField.disable();
                    clickButton.disable();
                }
                else{
                    ErrorSubmitLabel.setText("The Flight id not Exist");
                }
            }
        });
        ADDTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ticket t=new Ticket();
                Flight F=new Flight();
                String selectedOption = (String) comboBox.getSelectedItem();
                if(selectedOption.equals("Enter Option")){
                    ErrorSubmitComboLabel.setText("You should choose one option");
                }
                else if(selectedOption.equals(str1)){
                    ErrorSubmitComboLabel.setText("");
                    EnteredTickets++;
                    totalprice+=PricesSeats.get(0);
                    t.setPrice(PricesSeats.get(0));
                    t.setSeat_num(NumSeats.get(0));
                    NumSeats.set(0,NumSeats.get(0)+1);
                    t.setGate_Num(F.GetGAte_Num(FlightId));
                    t.set_class("Economy");
                    Tickets.add(t);
                }
                else if(selectedOption.equals(str2)){
                    ErrorSubmitComboLabel.setText("");
                    EnteredTickets++;
                    totalprice+=PricesSeats.get(1);
                    t.setPrice(PricesSeats.get(1));
                    t.setSeat_num(NumSeats.get(1));
                    NumSeats.set(1,NumSeats.get(1)+1);
                    t.setGate_Num(F.GetGAte_Num(FlightId));
                    t.set_class("VIP");
                    Tickets.add(t);
                }
                else{
                    ErrorSubmitComboLabel.setText("");
                    EnteredTickets++;
                    totalprice+=PricesSeats.get(2);
                    t.setPrice(PricesSeats.get(2));
                    t.setSeat_num(NumSeats.get(2));
                    NumSeats.set(2,NumSeats.get(2)+1);
                    t.set_class("Business");
                    t.setGate_Num(F.GetGAte_Num(FlightId));
                    Tickets.add(t);
                }
                if(EnteredTickets==TicketNum){
                    F.UpdateSeats(Integer.parseInt(FlightIdField.getText()),NumSeats.get(0),NumSeats.get(1),NumSeats.get(2));
                    Booking b=new Booking(SSN,TicketNum,totalprice);
                    myBooking_id=b.getBooking_id();
                    b.InsertBooking();
                    for(int i=0;i<TicketNum;i++){
                        Tickets.get(i).setBooking_id(myBooking_id);
                        Tickets.get(i).setFlight_id(FlightId);
                        Tickets.get(i).setGate_Num(F.GetGAte_Num(FlightId));
                        Tickets.get(i).InsertTicket();
                    }
                    F.INcreReservedSeats(FlightId,TicketNum);
                    new PassengerFunctions(isSignIn,SSN);
                    setVisible(false);
                }
            }
        });
        cancelBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PassengerFunctions(isSignIn,SSN);
                setVisible(false);
            }
        });
    }
}
