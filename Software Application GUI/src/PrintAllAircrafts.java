import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PrintAllAircrafts extends JFrame{
    private JPanel PrintAllAircraftspanel;
    private JTable table;
    private Aircraft f;

    PrintAllAircrafts(){
        // Create the column names
        String[] columnNames = {"Aircraft id ","Aircraft model ","Number seats ","Status ","Airline name "};
        // Create the table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        Aircraft f=new Aircraft();
        ArrayList<ArrayList>AA=f.printAircrafts();
        for(ArrayList<Object> rowData:AA){
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
