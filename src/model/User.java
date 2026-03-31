package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private double balance;
    private List<Booking> bookingHistory;

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role.toUpperCase();
        this.bookingHistory = new ArrayList<Booking>();
    }

    public User(int id, String username, String password, String role, double balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role.toUpperCase();
        this.balance = balance;
        this.bookingHistory = new ArrayList<Booking>();
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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void deductBalance(double amount) {
        this.balance -= amount;
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
