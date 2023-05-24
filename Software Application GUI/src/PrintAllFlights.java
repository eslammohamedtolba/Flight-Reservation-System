import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PrintAllFlights extends JFrame{
    private JPanel PrintAllFlightPanel;
    private JTable table;
    Flight f=new Flight();

    public PrintAllFlights(){
        // Create the column names
        String[] columnNames = {"Flight id", "Aircraft id", "Dep_time","Arrival_Time","Dep_Airport","Arrival_Airport","Dep_country","Arrival_country","Reserved_Seats","Aircraft_model","Airline_Name","Gate_num","Economyprice","VIPprice","Businessprice"};
        // Create the table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        ArrayList<ArrayList>AA=f.printFlights();
        for (ArrayList<Object> rowData : AA) {
            model.addRow(rowData.toArray());
        }
        // Create the JTable using the table model
        table = new JTable(model);
        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        // Add the JScrollPane to the JFrame
        getContentPane().add(scrollPane);

        JButton okButton = new JButton("OK");
        getContentPane().add(okButton, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminFunctions();
                setVisible(false);
            }
        });
    }
}
