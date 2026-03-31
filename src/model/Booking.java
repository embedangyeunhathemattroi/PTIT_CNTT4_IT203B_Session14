package model;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class Booking {

    private static int autoId = 1;

    private int id;
    private int pcId;
    private int userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private double price;
    private String status;

    public Booking() {
    } // Quan trọng cho DAO

    // Tạo booking mới từ user
    public Booking(User user, String description, double price) {
        this.id = autoId++;
        this.userId = user.getId();
        this.description = description;
        this.price = price;
        this.status = "ACTIVE";
    }

    // Load booking từ DB
    public Booking(int id, int userId, int pcId,
            LocalDateTime start, LocalDateTime end,
            double price, String status) {
        this.id = id;
        this.userId = userId;
        this.pcId = pcId;
        this.startTime = start;
        this.endTime = end;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPcId() {
        return pcId;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    // Chỉ giữ logic khi status là "Hoàn thành"
    public void setStatus(String status) {
        this.status = status;
        // if (customer != null && "Hoàn thành".equalsIgnoreCase(status)) {
        //     customer.deductWallet(price);
        // }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return "ID: " + id +
                " | PC: " + pcId +
                " | UserID: " + userId +
                " | Mô tả: " + (description != null ? description : "-") +
                " | Giá: " + df.format(price) +
                " | Trạng thái: " + status +
                " | Start: " + startTime +
                " | End: " + endTime;
    }
}
