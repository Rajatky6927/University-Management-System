package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame {

    public About() {
        setTitle("About - University Management System");
        setSize(700, 500);
        setLocation(400, 150);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        initUI();

        setVisible(true);
    }

    private void initUI() {
        // Load image with error handling
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/about.jpg"));
            Image i2 = i1.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(350, 0, 300, 200);
            image.setToolTipText("University Management System Logo");
            add(image);
        } catch (Exception e) {
            System.out.println("Image not found: " + e.getMessage());
        }

        // Heading
        JLabel heading = new JLabel("<html>University<br/>Management System</html>");
        heading.setBounds(70, 20, 300, 130);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);

        // Developer Info
        JLabel name = new JLabel("Developed By: Code for Interview");
        name.setBounds(70, 220, 550, 40);
        name.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(name);

        JLabel rollno = new JLabel("Roll number: 1533146");
        rollno.setBounds(70, 280, 550, 40);
        rollno.setFont(new Font("Tahoma", Font.PLAIN, 30));
        add(rollno);

        JLabel contact = new JLabel("Contact: codeforinterview03@gmail.com");
        contact.setBounds(70, 340, 550, 40);
        contact.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(contact);

        // Close Button with Event Handling
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(300, 400, 100, 30);
        closeButton.setToolTipText("Click to close this window");
        add(closeButton);

        // Event Handling: Close window on button click
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closes this window
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new About());
    }
}