package university.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Marks extends JFrame implements ActionListener {

    String rollno;
    JButton cancel;

    JLabel sub1, sub2, sub3, sub4, sub5;
    JLabel lblsemester;

    Marks(String rollno) {
        this.rollno = rollno;

        // Frame settings
        setTitle("Student Marks - Roll No: " + rollno);
        setSize(500, 600);
        setLocation(500, 100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Heading
        JLabel heading = new JLabel("Delhi Technical University");
        heading.setBounds(100, 10, 500, 25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        // Subheading
        JLabel subheading = new JLabel("Result of Examination 2022");
        subheading.setBounds(100, 50, 500, 20);
        subheading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(subheading);

        // Roll number
        JLabel lblrollno = new JLabel("Roll Number: " + rollno);
        lblrollno.setBounds(60, 100, 500, 20);
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblrollno);

        // Semester
        lblsemester = new JLabel();
        lblsemester.setBounds(60, 130, 500, 20);
        lblsemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblsemester);

        // Subject & marks labels
        sub1 = createSubjectLabel(200);
        sub2 = createSubjectLabel(230);
        sub3 = createSubjectLabel(260);
        sub4 = createSubjectLabel(290);
        sub5 = createSubjectLabel(320);

        // Load subject and marks data
        loadSubjectAndMarks();

        // Cancel button
        cancel = new JButton("Back");
        cancel.setBounds(250, 500, 120, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    private JLabel createSubjectLabel(int y) {
        JLabel label = new JLabel();
        label.setBounds(100, y, 500, 20);
        label.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(label);
        return label;
    }

    private void loadSubjectAndMarks() {
        String subjectQuery = "SELECT * FROM subject WHERE rollno = ?";
        String marksQuery = "SELECT * FROM marks WHERE rollno = ?";

        try (Conn c = new Conn();
             PreparedStatement pstSubject = c.c.prepareStatement(subjectQuery);
             PreparedStatement pstMarks = c.c.prepareStatement(marksQuery)) {

            // Load subjects
            pstSubject.setString(1, rollno);
            ResultSet rsSubject = pstSubject.executeQuery();

            if (rsSubject.next()) {
                sub1.setText(rsSubject.getString("subject1"));
                sub2.setText(rsSubject.getString("subject2"));
                sub3.setText(rsSubject.getString("subject3"));
                sub4.setText(rsSubject.getString("subject4"));
                sub5.setText(rsSubject.getString("subject5"));
            } else {
                JOptionPane.showMessageDialog(this, "No subject data found for roll number: " + rollno);
            }

            // Load marks
            pstMarks.setString(1, rollno);
            ResultSet rsMarks = pstMarks.executeQuery();

            if (rsMarks.next()) {
                sub1.setText(sub1.getText() + " ------------ " + rsMarks.getString("marks1"));
                sub2.setText(sub2.getText() + " ------------ " + rsMarks.getString("marks2"));
                sub3.setText(sub3.getText() + " ------------ " + rsMarks.getString("marks3"));
                sub4.setText(sub4.getText() + " ------------ " + rsMarks.getString("marks4"));
                sub5.setText(sub5.getText() + " ------------ " + rsMarks.getString("marks5"));
                lblsemester.setText("Semester: " + rsMarks.getString("semester"));
            } else {
                JOptionPane.showMessageDialog(this, "No marks data found for roll number: " + rollno);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while loading data.");
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        dispose();
    }

    public static void main(String[] args) {
        new Marks("");
    }
}