package com.rideshare.ui;

import com.rideshare.dispatcher.DispatchService;
import com.rideshare.exception.DriverNotFoundException;
import com.rideshare.exception.InvalidLocationException;
import com.rideshare.model.*;
import com.rideshare.service.RideService;
import com.rideshare.service.UserService;
import com.rideshare.strategy.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BookRidePanel extends JPanel {

    private MainFrame mainFrame;
    private UserService userService;
    private RideService rideService;
    private DispatchService dispatchService;

    private JRadioButton bookForMeBtn;
    private JRadioButton bookForOtherBtn;
    private JComboBox<String> registeredRiderCombo;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField areaField;
    private JTextField pickupField;
    private JTextField destinationField;
    private JTextField distanceField;
    private JComboBox<String> fareCombo;
    private JTextArea outputArea;

    private JPanel forMePanel;
    private JPanel forOtherPanel;

    public BookRidePanel(MainFrame mainFrame, UserService userService,
                         RideService rideService, 
                         DispatchService dispatchService) {
        this.mainFrame = mainFrame;
        this.userService = userService;
        this.rideService = rideService;
        this.dispatchService = dispatchService;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        // Title
        JLabel title = new JLabel("Book a Ride", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(255, 152, 0));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        // Radio Button Panel
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.setBackground(new Color(30, 30, 30));

        bookForMeBtn = new JRadioButton("Book for Me");
        bookForOtherBtn = new JRadioButton("Book for Someone Else");

        bookForMeBtn.setForeground(Color.WHITE);
        bookForOtherBtn.setForeground(Color.WHITE);
        bookForMeBtn.setBackground(new Color(30, 30, 30));
        bookForOtherBtn.setBackground(new Color(30, 30, 30));
        bookForMeBtn.setFont(new Font("Arial", Font.BOLD, 13));
        bookForOtherBtn.setFont(new Font("Arial", Font.BOLD, 13));
        bookForMeBtn.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(bookForMeBtn);
        group.add(bookForOtherBtn);

        radioPanel.add(bookForMeBtn);
        radioPanel.add(bookForOtherBtn);

        // For Me Panel
        forMePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        forMePanel.setBackground(new Color(30, 30, 30));
        forMePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        registeredRiderCombo = new JComboBox<>();
        registeredRiderCombo.setBackground(new Color(45, 45, 45));
        registeredRiderCombo.setForeground(Color.WHITE);

        forMePanel.add(createLabel("Select Registered Rider:"));
        forMePanel.add(registeredRiderCombo);

        // For Other Panel
        forOtherPanel = new JPanel(new GridLayout(3, 2, 10, 8));
        forOtherPanel.setBackground(new Color(30, 30, 30));
        forOtherPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 0, 5, 0));
        forOtherPanel.setVisible(false);

        nameField = new JTextField();
        phoneField = new JTextField();
        areaField = new JTextField();

        forOtherPanel.add(createLabel("Rider Name:"));
        forOtherPanel.add(nameField);
        forOtherPanel.add(createLabel("Phone:"));
        forOtherPanel.add(phoneField);
        forOtherPanel.add(createLabel("Area:"));
        forOtherPanel.add(areaField);

        // Trip Details Panel
        JPanel tripPanel = new JPanel(new GridLayout(4, 2, 10, 8));
        tripPanel.setBackground(new Color(30, 30, 30));
        tripPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        pickupField = new JTextField();
        destinationField = new JTextField();
        distanceField = new JTextField();
        fareCombo = new JComboBox<>(new String[]{
            "Flat Fare (Rs.50 fixed)",
            "Per KM Fare (Rs.10/km)",
            "Surge Fare (Rs.20/km)"
        });
        fareCombo.setBackground(new Color(45, 45, 45));
        fareCombo.setForeground(Color.WHITE);

        tripPanel.add(createLabel("Pickup Location:"));
        tripPanel.add(pickupField);
        tripPanel.add(createLabel("Destination:"));
        tripPanel.add(destinationField);
        tripPanel.add(createLabel("Distance (KM):"));
        tripPanel.add(distanceField);
        tripPanel.add(createLabel("Fare Type:"));
        tripPanel.add(fareCombo);

        // Output Area
        outputArea = new JTextArea(4, 30);
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(45, 45, 45));
        outputArea.setForeground(Color.WHITE);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Button Panel
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        btnPanel.setBackground(new Color(30, 30, 30));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton bookBtn = createButton("Book Ride", new Color(255, 152, 0));
        JButton backBtn = createButton("Back to Home", 
                                        new Color(100, 100, 100));

        bookBtn.addActionListener(e -> bookRide());
        backBtn.addActionListener(e -> mainFrame.showHome());

        btnPanel.add(bookBtn);
        btnPanel.add(backBtn);

        // Radio listeners
        bookForMeBtn.addActionListener(e -> {
            forMePanel.setVisible(true);
            forOtherPanel.setVisible(false);
            refreshRiderList();
            revalidate();
            repaint();
        });

        bookForOtherBtn.addActionListener(e -> {
            forMePanel.setVisible(false);
            forOtherPanel.setVisible(true);
            revalidate();
            repaint();
        });

        // Combine all form panels
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(30, 30, 30));
        formPanel.add(radioPanel);
        formPanel.add(forMePanel);
        formPanel.add(forOtherPanel);
        formPanel.add(Box.createVerticalStrut(5));
        formPanel.add(tripPanel);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    public void refreshRiderList() {
        registeredRiderCombo.removeAllItems();
        ArrayList<Rider> riders = userService.getAllRiders();
        if (riders.isEmpty()) {
            registeredRiderCombo.addItem("No riders registered yet");
        } else {
            for (Rider rider : riders) {
                registeredRiderCombo.addItem(
                    rider.getName() + " - " + rider.getArea());
            }
        }
    }

    private void bookRide() {
        if (bookForMeBtn.isSelected() && userService.getAllRiders().isEmpty()) {
    outputArea.setText(
        "No riders registered!\n" +
        "Please go back and register as a rider first."
    );
    return;
}
        String pickup = pickupField.getText().trim();
        String destination = destinationField.getText().trim();
        String distanceText = distanceField.getText().trim();

        if (pickup.isEmpty() || destination.isEmpty() || 
            distanceText.isEmpty()) {
            outputArea.setText("Please fill all trip details!");
            return;
        }

        try {
            double distance = Double.parseDouble(distanceText);
            Rider rider;

            if (bookForMeBtn.isSelected()) {
                ArrayList<Rider> riders = userService.getAllRiders();
                if (riders.isEmpty()) {
                    outputArea.setText(
                        "No registered riders found!\n" +
                        "Please register as a rider first.");
                    return;
                }
                int selectedIndex = registeredRiderCombo.getSelectedIndex();
                rider = riders.get(selectedIndex);
            } else {
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                String area = areaField.getText().trim();
                if (name.isEmpty() || phone.isEmpty() || area.isEmpty()) {
                    outputArea.setText("Please fill all rider details!");
                    return;
                }
                rider = userService.registerRider(name, phone, area);
            }

            IFareStrategy strategy;
            int selectedFare = fareCombo.getSelectedIndex();
            if (selectedFare == 0) {
                strategy = new FlatFareStrategy();
            } else if (selectedFare == 1) {
                strategy = new PerKmFareStrategy();
            } else {
                strategy = new SurgeFareStrategy();
            }

            Location pickupLoc = new Location(pickup, pickup);
            Location destLoc = new Location(destination, destination);

            Trip trip = rideService.bookRide(rider, pickupLoc,
                                             destLoc, distance, strategy);

          outputArea.setText(
    "Ride Booked Successfully!\n" +
    "--------------------------------\n" +
    "Trip ID    : " + trip.getTripId() + "\n" +
    "Rider      : " + trip.getRider().getName() + "\n" +
    "Driver     : " + trip.getDriver().getName() + "\n" +
    "From       : " + trip.getPickup().getArea() + "\n" +
    "To         : " + trip.getDestination().getArea() + "\n" +
    "Distance   : " + distance + " km\n" +
    "Fare Type  : " + strategy.getStrategyName() + "\n" +
    "Fare       : Rs." + trip.getFare() + "\n" +
    "--------------------------------\n" +
    "Notification: Driver " + trip.getDriver().getName() +
    " was assigned and reached successfully!\n" +
    "Status     : Trip Completed!"
);

        } catch (NumberFormatException ex) {
            outputArea.setText("Please enter a valid distance!");
        } catch (DriverNotFoundException ex) {
            outputArea.setText(
                "No driver available!\n" +
                "Please register a driver first.");
        } catch (InvalidLocationException ex) {
            outputArea.setText("Invalid location entered!");
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
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