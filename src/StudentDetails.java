package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class StudentDetails extends JFrame implements ActionListener {

    private Choice crollno;
    private JTable table;
    private JButton search, print, update, add, cancel;

    public StudentDetails() {

        setTitle("Student Details");

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        setupHeader();
        setupChoice();
        setupTable();
        setupButtons();

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    private void setupHeader() {
        JLabel heading = new JLabel("Search by Roll Number");
        heading.setBounds(20, 20, 150, 20);
        add(heading);
    }

    private void setupChoice() {
        crollno = new Choice();
        crollno.setBounds(180, 20, 150, 20);
        add(crollno);

        populateRollNumbers();
    }

    private void populateRollNumbers() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while (rs.next()) {
                crollno.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            showError("Error fetching roll numbers.");
        }
    }

    private void setupTable() {
        table = new JTable();

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            showError("Error loading student data.");
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);
    }

    private void setupButtons() {
        search = createButton("Search", 20, 70);
        print = createButton("Print", 120, 70);
        add = createButton("Add", 220, 70);
        update = createButton("Update", 320, 70);
        cancel = createButton("Cancel", 420, 70);
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 80, 20);
        button.addActionListener(this);
        add(button);
        return button;
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if (source == search) {
            searchStudent();
        } else if (source == print) {
            printTable();
        } else if (source == add) {
            openAddStudent();
        } else if (source == update) {
            openUpdateStudent();
        } else if (source == cancel) {
            dispose();
        }
    }

    private void searchStudent() {
        String query = "select * from student where rollno = '" + crollno.getSelectedItem() + "'";
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            showError("Error searching student.");
        }
    }

    private void printTable() {
        try {
            table.print();
        } catch (Exception e) {
            showError("Error printing table.");
        }
    }

    private void openAddStudent() {
        setVisible(false);
        new AddStudent();
    }

    private void openUpdateStudent() {
        setVisible(false);
        new UpdateStudent();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new StudentDetails();
    }
}