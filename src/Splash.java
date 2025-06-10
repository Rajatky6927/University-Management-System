package university.management.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {

    private Thread animationThread;

    public Splash() {
        setTitle("University Management System - Splash Screen");

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        add(image);

        // Start animation thread
        animationThread = new Thread(this);
        animationThread.start();

        setVisible(true);
        animateWindow();
    }

    // Splash animation logic
    private void animateWindow() {
        int x = 1;
        for (int i = 2; i <= 600; i += 4, x += 1) {
            setLocation(600 - ((i + x) / 2), 350 - (i / 2));
            setSize(i + 3 * x, i + x / 2);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                showError("Animation interrupted.");
            }
        }
    }

    // Background thread logic
    public void run() {
        try {
            Thread.sleep(7000);
            setVisible(false);

            // Launch Login frame
            new Login();

        } catch (InterruptedException e) {
            showError("Splash thread interrupted.");
        }
    }

    // Error dialog helper
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new Splash();
    }
}