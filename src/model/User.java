package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private double wallet;
    private List<Booking> bookingHistory;

    public User() {
        this.bookingHistory = new ArrayList<>();
        this.wallet = 1000; // mặc định
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role.toUpperCase();
        this.wallet = 1000;
        this.bookingHistory = new ArrayList<>();
    }

    public User(int id, String username, String password, String role, double wallet) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role.toUpperCase();
        this.wallet = wallet;
        this.bookingHistory = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role.toUpperCase();
    }

    public double getWallet() {
        return wallet;
    }

    public void addWallet(double amount) {
        this.wallet += amount;
    }

    public void deductWallet(double amount) {
        this.wallet -= amount;
    }

    public List<Booking> getBookingHistory() {
        return bookingHistory;
    }

    public void setBookingHistory(List<Booking> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

    public void addBooking(Booking booking) {
        if (bookingHistory == null)
            bookingHistory = new ArrayList<>();
        bookingHistory.add(booking);
    }
}