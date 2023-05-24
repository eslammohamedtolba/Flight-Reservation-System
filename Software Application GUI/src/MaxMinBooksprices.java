import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MaxMinBooksprices extends JFrame{
    private JTable table;
    private JPanel panel;

    public MaxMinBooksprices(){
        String[] columnNames = {"Booking Id of min price","Min price","Booking Id of Max price","Max price"};
        Booking b=new Booking();
        ArrayList rowData=b.MinMaxPriceForBookings();
        // Create the table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        model.addRow(rowData.toArray());
        // Create the JTable using the table model
        table = new JTable(model);
        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        // Add the JScrollPane to the JFrame
        getContentPane().add(scrollPane);

        JButton okButton = new JButton("OK");
        getContentPane().add(okButton, BorderLayout.PAGE_END);

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
