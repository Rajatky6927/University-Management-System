package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class EnterMarks extends JFrame implements ActionListener {

    Choice crollno;
    JComboBox<String> cbsemester;
    JTextField tfsub1, tfsub2, tfsub3, tfsub4, tfsub5;
    JTextField tfmarks1, tfmarks2, tfmarks3, tfmarks4, tfmarks5;
    JButton cancel, submit;

    EnterMarks() {

        // Frame Setup
        setTitle("Enter Marks");
        setSize(1000, 500);
        setLocation(300, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Image Section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/exam.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 40, 400, 300);
        add(image);

        // Heading
        JLabel heading = new JLabel("Enter Marks of Student");
        heading.setBounds(50, 0, 500, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        // Roll Number Dropdown
        JLabel lblrollnumber = new JLabel("Select Roll Number");
        lblrollnumber.setBounds(50, 70, 150, 20);
        add(lblrollnumber);

        crollno = new Choice();
        crollno.setBounds(200, 70, 150, 20);
        add(crollno);

        populateRollNumbers();

        // Semester Dropdown
        JLabel lblsemester = new JLabel("Select Semester");
        lblsemester.setBounds(50, 110, 150, 20);
        add(lblsemester);

        String semester[] = {
            "1st Semester", "2nd Semester", "3rd Semester", "4th Semester",
            "5th Semester", "6th Semester", "7th Semester", "8th Semester"
        };
        cbsemester = new JComboBox<>(semester);
        cbsemester.setBounds(200, 110, 150, 20);
        cbsemester.setBackground(Color.WHITE);
        add(cbsemester);

        // Subjects & Marks Heading
        JLabel lblentersubject = new JLabel("Enter Subject");
        lblentersubject.setBounds(100, 150, 200, 40);
        add(lblentersubject);

        JLabel lblentermarks = new JLabel("Enter Marks");
        lblentermarks.setBounds(320, 150, 200, 40);
        add(lblentermarks);

        // Subject Fields
        tfsub1 = new JTextField(); tfsub1.setBounds(50, 200, 200, 20); add(tfsub1);
        tfsub2 = new JTextField(); tfsub2.setBounds(50, 230, 200, 20); add(tfsub2);
        tfsub3 = new JTextField(); tfsub3.setBounds(50, 260, 200, 20); add(tfsub3);
        tfsub4 = new JTextField(); tfsub4.setBounds(50, 290, 200, 20); add(tfsub4);
        tfsub5 = new JTextField(); tfsub5.setBounds(50, 320, 200, 20); add(tfsub5);

        // Marks Fields
        tfmarks1 = new JTextField(); tfmarks1.setBounds(250, 200, 200, 20); add(tfmarks1);
        tfmarks2 = new JTextField(); tfmarks2.setBounds(250, 230, 200, 20); add(tfmarks2);
        tfmarks3 = new JTextField(); tfmarks3.setBounds(250, 260, 200, 20); add(tfmarks3);
        tfmarks4 = new JTextField(); tfmarks4.setBounds(250, 290, 200, 20); add(tfmarks4);
        tfmarks5 = new JTextField(); tfmarks5.setBounds(250, 320, 200, 20); add(tfmarks5);

        // Buttons
        submit = new JButton("Submit");
        submit.setBounds(70, 360, 150, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Back");
        cancel.setBounds(280, 360, 150, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    // Populates the roll number choice dropdown
    private void populateRollNumbers() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT rollno FROM student");
            while (rs.next()) {
                crollno.add(rs.getString("rollno"));
            }
            c.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading Roll Numbers.");
            e.printStackTrace();
        }
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            if (validateInput()) {
                insertMarks();
            }
        } else {
            setVisible(false);
        }
    }

    // Validate that required fields are filled
    private boolean validateInput() {
        if (tfsub1.getText().isEmpty() || tfmarks1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter at least one subject and marks.");
            return false;
        }
        return true;
    }

    // Insert the entered marks into database
    private void insertMarks() {
        try {
            Conn c = new Conn();

            String rollno = crollno.getSelectedItem();
            String semester = (String) cbsemester.getSelectedItem();

            String query1 = "INSERT INTO subject VALUES('" + rollno + "', '" + semester + "', '" +
                    tfsub1.getText() + "', '" + tfsub2.getText() + "', '" + tfsub3.getText() + "', '" +
                    tfsub4.getText() + "', '" + tfsub5.getText() + "')";

            String query2 = "INSERT INTO marks VALUES('" + rollno + "', '" + semester + "', '" +
                    tfmarks1.getText() + "', '" + tfmarks2.getText() + "', '" + tfmarks3.getText() + "', '" +
                    tfmarks4.getText() + "', '" + tfmarks5.getText() + "')";

            c.s.executeUpdate(query1);
            c.s.executeUpdate(query2);

            c.close();

            JOptionPane.showMessageDialog(null, "Marks Inserted Successfully");
            setVisible(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inserting marks.");
            e.printStackTrace();
        }
    }

    // Main method to run the frame
    public static void main(String[] args) {
        new EnterMarks();
    }
}