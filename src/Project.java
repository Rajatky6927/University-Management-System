package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class Project extends JFrame implements ActionListener {

    private Map<String, Runnable> actionMap; // For cleaner action handling

    Project() {
        setTitle("University Management System");
        setSize(1540, 850);
        setLocationRelativeTo(null); // center window

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        add(image);

        // Initialize Action Map
        initActionMap();

        // Menu Bar
        JMenuBar mb = new JMenuBar();

        // 1️⃣ New Information
        mb.add(createMenu("New Information", Color.BLUE, 
            "New Faculty Information", "New Student Information"));

        // 2️⃣ View Details
        mb.add(createMenu("View Details", Color.RED, 
            "View Faculty Details", "View Student Details"));

        // 3️⃣ Apply Leave
        mb.add(createMenu("Apply Leave", Color.BLUE, 
            "Faculty Leave", "Student Leave"));

        // 4️⃣ Leave Details
        mb.add(createMenu("Leave Details", Color.RED, 
            "Faculty Leave Details", "Student Leave Details"));

        // 5️⃣ Examination
        mb.add(createMenu("Examination", Color.BLUE, 
            "Examination Results", "Enter Marks"));

        // 6️⃣ Update Details
        mb.add(createMenu("Update Details", Color.RED, 
            "Update Faculty Details", "Update Student Details"));

        // 7️⃣ Fee Details
        mb.add(createMenu("Fee Details", Color.BLUE, 
            "Fee Structure", "Student Fee Form"));

        // 8️⃣ Utility
        mb.add(createMenu("Utility", Color.RED, 
            "Notepad", "Calculator"));

        // 9️⃣ About
        mb.add(createMenu("About", Color.BLUE, 
            "About"));

        // 🔟 Exit
        mb.add(createMenu("Exit", Color.RED, 
            "Exit"));

        // Add Menu Bar
        setJMenuBar(mb);

        setVisible(true);
    }

    // Creates a JMenu with JMenuItems and assigns ActionListener
    private JMenu createMenu(String title, Color color, String... items) {
        JMenu menu = new JMenu(title);
        menu.setForeground(color);

        for (String itemName : items) {
            JMenuItem item = new JMenuItem(itemName);
            item.setBackground(Color.WHITE);
            item.addActionListener(this);
            menu.add(item);
        }

        return menu;
    }

    // Initialize the action map to make code cleaner
    private void initActionMap() {
        actionMap = new HashMap<>();

        // Exit
        actionMap.put("Exit", () -> dispose());

        // Utility
        actionMap.put("Calculator", this::openCalculator);
        actionMap.put("Notepad", this::openNotepad);

        // Faculty / Student Info
        actionMap.put("New Faculty Information", () -> new AddTeacher());
        actionMap.put("New Student Information", () -> new AddStudent());
        actionMap.put("View Faculty Details", () -> new TeacherDetails());
        actionMap.put("View Student Details", () -> new StudentDetails());
        actionMap.put("Faculty Leave", () -> new TeacherLeave());
        actionMap.put("Student Leave", () -> new StudentLeave());
        actionMap.put("Faculty Leave Details", () -> new TeacherLeaveDetails());
        actionMap.put("Student Leave Details", () -> new StudentLeaveDetails());
        actionMap.put("Update Faculty Details", () -> new UpdateTeacher());
        actionMap.put("Update Student Details", () -> new UpdateStudent());
        actionMap.put("Enter Marks", () -> new EnterMarks());
        actionMap.put("Examination Results", () -> new ExaminationDetails());
        actionMap.put("Fee Structure", () -> new FeeStructure());
        actionMap.put("About", () -> new About());
        actionMap.put("Student Fee Form", () -> new StudentFeeForm());
    }

    // Handle actions
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        // Check if command exists in map
        Runnable action = actionMap.get(command);

        if (action != null) {
            action.run();
        } else {
            JOptionPane.showMessageDialog(this, "Unknown action: " + command, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Utility methods
    private void openCalculator() {
        try {
            Runtime.getRuntime().exec("calc.exe");
        } catch (Exception e) {
            showError("Unable to open Calculator");
        }
    }

    private void openNotepad() {
        try {
            Runtime.getRuntime().exec("notepad.exe");
        } catch (Exception e) {
            showError("Unable to open Notepad");
        }
    }

    // Error dialog helper
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Main method
    public static void main(String[] args) {
        new Project();
    }
}