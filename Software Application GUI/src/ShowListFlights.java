import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowListFlights extends JFrame {
    private JPanel panel1;
    private JTable table;

    ShowListFlights(String Source,String Dist,int NSeats,boolean frombook,int SSN,boolean issignin){
        this.setTitle("Showing Flights Form");
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new Label("All Flights Searched by source and Destination and number of seats"));
        getContentPane().add(inputPanel, BorderLayout.NORTH);

        // Create the column names
        String[] columnNames = {"Flight id", "Aircraft id", "Dep_time","Arrival_Time","Dep_Airport","Arrival_Airport","Dep_country","Arrival_country","Reserved_Seats","Aircraft_model","Airline_Name","Gate_num","Economyprice","VIPprice","Businessprice"};
        // Create the table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        Flight f=new Flight();
        ArrayList<ArrayList<Object>>AA=f.printFlightsccriteria(Source,Dist,NSeats);
        for (ArrayList<Object> rowData : AA) {
            model.addRow(rowData.toArray());
        }
        // Create the JTable using the table model
        table = new JTable(model);
        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        // Add the JScrollPane to the JFrame
        getContentPane().add(scrollPane);

        JButton okButton = new JButton("Continue");
        getContentPane().add(okButton, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(frombook==true){
                    new BookFlight(NSeats,SSN,issignin);
                }
                else{
                    new PassengerFunctions(issignin,SSN);
                }
                setVisible(false);
            }
        });
    }
}
