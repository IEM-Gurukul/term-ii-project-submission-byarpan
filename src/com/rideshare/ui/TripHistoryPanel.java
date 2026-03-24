package com.rideshare.ui;

import com.rideshare.model.Trip;
import com.rideshare.service.RideService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TripHistoryPanel extends JPanel {

    private MainFrame mainFrame;
    private RideService rideService;
    private DefaultTableModel tableModel;

    public TripHistoryPanel(MainFrame mainFrame, RideService rideService) {
        this.mainFrame = mainFrame;
        this.rideService = rideService;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        // Title
        JLabel title = new JLabel("Trip History", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(156, 39, 176));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // Table
        String[] columns = {"Trip ID", "Rider", "Driver", 
                            "From", "To", "Fare", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        table.setBackground(new Color(45, 45, 45));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(25);
        table.getTableHeader().setBackground(new Color(156, 39, 176));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.setGridColor(new Color(80, 80, 80));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(30, 30, 30));
        add(scrollPane, BorderLayout.CENTER);

        // Back Button
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(30, 30, 30));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton backBtn = new JButton("Back to Home");
        backBtn.setBackground(new Color(100, 100, 100));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.addActionListener(e -> mainFrame.showHome());

        btnPanel.add(backBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }

    public void refreshHistory() {
        tableModel.setRowCount(0);
        ArrayList<Trip> trips = rideService.getTripHistory();
        for (Trip trip : trips) {
            tableModel.addRow(new Object[]{
                trip.getTripId(),
                trip.getRider().getName(),
                trip.getDriver().getName(),
                trip.getPickup().getArea(),
                trip.getDestination().getArea(),
                "Rs." + trip.getFare(),
                trip.getStatus()
            });
        }
    }
}