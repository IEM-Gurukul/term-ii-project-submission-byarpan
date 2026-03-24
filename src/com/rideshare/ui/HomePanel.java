package com.rideshare.ui;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    private MainFrame mainFrame;

    public HomePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        // Title Panel
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(new Color(30, 30, 30));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

        JLabel titleLabel = new JLabel("RideShare Simulation", 
                                        SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(255, 200, 0));

        JLabel subtitleLabel = new JLabel(
                "OOP Lab Project - Arpan Ghosh", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(180, 180, 180));

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 15));
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 100, 40, 100));

        JButton registerRiderBtn = createButton(
                "Register as Rider", new Color(0, 150, 136));
        JButton bookRideBtn = createButton(
                "Book a Ride", new Color(255, 152, 0));
        JButton driverBtn = createButton(
                "Register as Driver", new Color(33, 150, 243));
        JButton historyBtn = createButton(
                "View Trip History", new Color(156, 39, 176));
        JButton exitBtn = createButton(
                "Exit", new Color(244, 67, 54));

        registerRiderBtn.addActionListener(
                e -> mainFrame.showRegisterRiderPanel());
        bookRideBtn.addActionListener(
                e -> mainFrame.showBookRidePanel());
        driverBtn.addActionListener(
                e -> mainFrame.showDriverPanel());
        historyBtn.addActionListener(
                e -> mainFrame.showTripHistory());
        exitBtn.addActionListener(e -> System.exit(0));

        buttonPanel.add(registerRiderBtn);
        buttonPanel.add(bookRideBtn);
        buttonPanel.add(driverBtn);
        buttonPanel.add(historyBtn);
        buttonPanel.add(exitBtn);

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}