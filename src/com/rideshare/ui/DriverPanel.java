package com.rideshare.ui;

import com.rideshare.dispatcher.DispatchService;
import com.rideshare.model.Driver;
import com.rideshare.service.UserService;
import javax.swing.*;
import java.awt.*;

public class DriverPanel extends JPanel {

    private MainFrame mainFrame;
    private UserService userService;
    private DispatchService dispatchService;

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField vehicleField;
    private JTextField areaField;
    private JTextArea outputArea;

    public DriverPanel(MainFrame mainFrame, UserService userService,
                       DispatchService dispatchService) {
        this.mainFrame = mainFrame;
        this.userService = userService;
        this.dispatchService = dispatchService;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        // Title
        JLabel title = new JLabel("Register as Driver", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(33, 150, 243));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(new Color(30, 30, 30));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        nameField = new JTextField();
        phoneField = new JTextField();
        vehicleField = new JTextField();
        areaField = new JTextField();

        formPanel.add(createLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(createLabel("Phone:"));
        formPanel.add(phoneField);
        formPanel.add(createLabel("Vehicle Number:"));
        formPanel.add(vehicleField);
        formPanel.add(createLabel("Area:"));
        formPanel.add(areaField);

        // Output Area
        outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(45, 45, 45));
        outputArea.setForeground(Color.WHITE);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Button Panel
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        btnPanel.setBackground(new Color(30, 30, 30));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JButton registerBtn = new JButton("Register Driver");
        registerBtn.setBackground(new Color(33, 150, 243));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
        registerBtn.setFocusPainted(false);
        registerBtn.setBorderPainted(false);

        JButton backBtn = new JButton("Back to Home");
        backBtn.setBackground(new Color(100, 100, 100));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);

        registerBtn.addActionListener(e -> registerDriver());
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

    private void registerDriver() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String vehicle = vehicleField.getText().trim();
        String area = areaField.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || 
            vehicle.isEmpty() || area.isEmpty()) {
            outputArea.setText("Please fill all fields!");
            return;
        }

        Driver driver = userService.registerDriver(name, phone, vehicle, area);
        dispatchService.addDriver(driver);

        outputArea.setText("Driver registered successfully!\n" +
                          "ID      : " + driver.getUserId() + "\n" +
                          "Name    : " + driver.getName() + "\n" +
                          "Vehicle : " + driver.getVehicleNumber() + "\n" +
                          "Area    : " + driver.getArea() + "\n" +
                          "Status  : Available");

        nameField.setText("");
        phoneField.setText("");
        vehicleField.setText("");
        areaField.setText("");
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }
}
