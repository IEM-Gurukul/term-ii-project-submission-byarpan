package com.rideshare.ui;

import com.rideshare.model.Rider;
import com.rideshare.service.UserService;
import javax.swing.*;
import java.awt.*;

public class RegisterRiderPanel extends JPanel {

    private MainFrame mainFrame;
    private UserService userService;

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField areaField;
    private JTextArea outputArea;

    public RegisterRiderPanel(MainFrame mainFrame, UserService userService) {
        this.mainFrame = mainFrame;
        this.userService = userService;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        // Title
        JLabel title = new JLabel("Register as Rider", 
                                   SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(0, 150, 136));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 15));
        formPanel.setBackground(new Color(30, 30, 30));
        formPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 80, 20, 80));

        nameField = new JTextField();
        phoneField = new JTextField();
        areaField = new JTextField();

        formPanel.add(createLabel("Full Name:"));
        formPanel.add(nameField);
        formPanel.add(createLabel("Phone Number:"));
        formPanel.add(phoneField);
        formPanel.add(createLabel("Your Area:"));
        formPanel.add(areaField);

        // Output Area
        outputArea = new JTextArea(4, 30);
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(45, 45, 45));
        outputArea.setForeground(Color.WHITE);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputArea.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(
                BorderFactory.createEmptyBorder(0, 80, 0, 80));

        // Button Panel
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        btnPanel.setBackground(new Color(30, 30, 30));
        btnPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 80, 20, 80));

        JButton registerBtn = createButton("Register", 
                                            new Color(0, 150, 136));
        JButton backBtn = createButton("Back to Home", 
                                        new Color(100, 100, 100));

        registerBtn.addActionListener(e -> registerRider());
        backBtn.addActionListener(e -> mainFrame.showHome());

        btnPanel.add(registerBtn);
        btnPanel.add(backBtn);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(30, 30, 30));
        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(btnPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void registerRider() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String area = areaField.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || area.isEmpty()) {
            outputArea.setText("Please fill all fields!");
            return;
        }

        Rider rider = userService.registerRider(name, phone, area);

        outputArea.setText(
            "Rider registered successfully!\n" +
            "ID    : " + rider.getUserId() + "\n" +
            "Name  : " + rider.getName() + "\n" +
            "Phone : " + rider.getPhone() + "\n" +
            "Area  : " + rider.getArea()
        );

        nameField.setText("");
        phoneField.setText("");
        areaField.setText("");
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
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