package com.rideshare.ui;

import com.rideshare.dispatcher.DispatchService;
import com.rideshare.observer.DriverNotifier;
import com.rideshare.observer.RiderNotifier;
import com.rideshare.service.RideService;
import com.rideshare.service.UserService;
import javax.swing.*;

public class MainFrame extends JFrame {

    private UserService userService;
    private RideService rideService;
    private DispatchService dispatchService;

    private HomePanel homePanel;
    private RegisterRiderPanel registerRiderPanel;
    private BookRidePanel bookRidePanel;
    private DriverPanel driverPanel;
    private TripHistoryPanel tripHistoryPanel;

    public MainFrame() {
        initServices();
        initUI();
    }

    private void initServices() {
        dispatchService = new DispatchService();
        userService = new UserService(dispatchService);
        rideService = new RideService(dispatchService);
        rideService.addObserver(new RiderNotifier());
        rideService.addObserver(new DriverNotifier());
    }

    private void initUI() {
        setTitle("RideShare Simulation System");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        homePanel = new HomePanel(this);
        registerRiderPanel = new RegisterRiderPanel(this, userService);
        bookRidePanel = new BookRidePanel(this, userService,
                                          rideService, dispatchService);
        driverPanel = new DriverPanel(this, userService, dispatchService);
        tripHistoryPanel = new TripHistoryPanel(this, rideService);

        showHome();
        setVisible(true);
    }

    public void showHome() {
        setContentPane(homePanel);
        revalidate();
        repaint();
    }

    public void showRegisterRiderPanel() {
        setContentPane(registerRiderPanel);
        revalidate();
        repaint();
    }

    public void showBookRidePanel() {
        bookRidePanel.refreshRiderList();
        setContentPane(bookRidePanel);
        revalidate();
        repaint();
    }

    public void showDriverPanel() {
        setContentPane(driverPanel);
        revalidate();
        repaint();
    }

    public void showTripHistory() {
        tripHistoryPanel.refreshHistory();
        setContentPane(tripHistoryPanel);
        revalidate();
        repaint();
    }
}