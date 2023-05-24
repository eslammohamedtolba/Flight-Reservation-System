import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PrintTicketsFlights extends JFrame {
    private JPanel PrintTicketsFlightsPenel;
    private JTable table;

    public PrintTicketsFlights(){
        // Create the column names
        String[] columnNames = {"Flight Id ","Number of Tickets "};
        // Create the table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        Flight f=new Flight();
        ArrayList<ArrayList>AAList=f.ticketsForFlights();
        for (ArrayList rowData:AAList){
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
