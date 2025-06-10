package university.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ExaminationDetails extends JFrame implements ActionListener {

    JTextField search;
    JButton submit, cancel;
    JTable table;

    ExaminationDetails() {

        // Frame Setup
        setTitle("Examination Details");
        setSize(1000, 475);
        setLocation(300, 100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Heading
        JLabel heading = new JLabel("Check Result");
        heading.setBounds(80, 15, 400, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        // Search Field
        search = new JTextField();
        search.setBounds(80, 90, 200, 30);
        search.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(search);

        // Submit Button
        submit = new JButton("Result");
        submit.setBounds(300, 90, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        // Cancel Button
        cancel = new JButton("Back");
        cancel.setBounds(440, 90, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        // Table
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 130, 1000, 310);
        add(jsp);

        // Load student data into table on frame load
        loadStudentData();

        // Mouse click on table populates search field
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    search.setText(table.getModel().getValueAt(row, 2).toString());
                }
            }
        });

        setVisible(true);
    }

    // Load all students in table
    private void loadStudentData() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            c.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading student data.");
            e.printStackTrace();
        }
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            if (validateSearchInput()) {
                // Open Marks window with searched roll number
                setVisible(false);
                new Marks(search.getText());
            }
        } else {
            setVisible(false);
        }
    }

    // Validate that search field is not empty
    private boolean validateSearchInput() {
        if (search.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter or select a Roll Number.");
            return false;
        }
        return true;
    }

    // Main method to run the frame
    public static void main(String[] args) {
        new ExaminationDetails();
    }
}