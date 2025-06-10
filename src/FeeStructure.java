package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class FeeStructure extends JFrame {

    JTable table;

    FeeStructure() {

        // Frame setup
        setTitle("Fee Structure");
        setSize(1000, 700);
        setLocation(250, 50);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Heading
        JLabel heading = new JLabel("Fee Structure");
        heading.setBounds(50, 10, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);

        // Table initialization
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Scroll Pane
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 60, 1000, 700);
        add(jsp);

        // Load fee data
        loadFeeData();

        setVisible(true);
    }

    // Load fee structure data from database
    private void loadFeeData() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM fee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            c.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading fee data.");
            e.printStackTrace();
        }
    }

    // Main method to run the frame
    public static void main(String[] args) {
        new FeeStructure();
    }
}