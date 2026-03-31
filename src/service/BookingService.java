package service;

import model.Booking;
import model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.BookingDAO;
import DAO.BookingDAOImpl;

public class BookingService {

    private static List<Booking> bookings = new ArrayList<>();
    private static BookingDAO bookingDAO = new BookingDAOImpl();

    private static int nextId = 1;

    public void confirmReceived(int bookingId) {
        bookingDAO.updateStatus(bookingId, "CONFIRMED"); // đúng ENUM
    }

    public Booking getBookingById(int id) {
        return bookingDAO.findById(id);
    }

    public Booking createBooking(int userId, int pcId, LocalDateTime startTime,
            LocalDateTime endTime, String description, double price) {

        // 1. Lấy đối tượng User từ userId
        User customer = UserService.getUserById(userId);
        if (customer == null) {
            System.out.println("Không tìm thấy khách hàng!");
            return null;
        }

        // 2. Tạo booking mới
        Booking b = new Booking(customer, description, price);
        b.setId(nextId++);
        b.setPcId(pcId);
        b.setStartTime(startTime);
        b.setEndTime(endTime);
        b.setStatus("ACTIVE");

        // 3. Lưu booking
        bookings.add(b);
        customer.getBookingHistory().add(b);

        System.out.println("Đơn hàng tạo thành công! ID: " + b.getId() + ", trạng thái: " + b.getStatus());
        return b;
    }

    // Staff: xem tất cả đơn
    public static void showAllBookings() {
        bookings = bookingDAO.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("Chưa có đơn hàng nào.");
            return;
        }
        System.out.println("\n===== DANH SÁCH ĐƠN HÀNG =====");
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    // Staff: cập nhật trạng thái đơn
    public static void updateStatus(int bookingId) {
        Booking b = null;
        for (Booking bk : bookings) {
            if (bk.getId() == bookingId) {
                b = bk;
                break;
            }
        }
        if (b == null) {
            System.out.println("Không tìm thấy đơn hàng!");
            return;
        }

        switch (b.getStatus()) {
            case "Chờ xác nhận":
                b.setStatus("Đang phục vụ");
                System.out.println("Đơn ID " + b.getId() + " -> Đang phục vụ");
                break;
            case "Đang phục vụ":
                // Trừ ví khách hàng khi hoàn thành
                User customer = b.getCustomer();
                if (customer.getWallet() >= b.getPrice()) {
                    b.setStatus("Hoàn thành"); // setStatus sẽ trừ tiền tự động
                    System.out.println("Đơn ID " + b.getId() + " -> Hoàn thành");
                    System.out.println("Đã trừ " + b.getPrice() + " từ ví khách hàng. Số dư: " + customer.getWallet());
                } else {
                    System.out.println("Ví khách hàng không đủ tiền để hoàn tất đơn!");
                }
                break;
            case "Hoàn thành":
                System.out.println("Đơn hàng đã hoàn thành.");
                break;
            default:
                System.out.println("Trạng thái không hợp lệ!");
        }
    }

    // Customer: xem lịch sử đơn hàng
    public static void showHistory(User user) {
        if (user.getBookingHistory().isEmpty()) {
            System.out.println("Bạn chưa có đơn hàng nào.");
            return;
        }
        System.out.println("\n===== LỊCH SỬ ĐƠN HÀNG CỦA " + user.getUsername() + " =====");
        for (Booking b : user.getBookingHistory()) {
            System.out.println(b);
        }
    }

    // Lấy tất cả đơn (dành cho Staff/Admin)
    public static List<Booking> getAllBookingsList() {
        return bookings;
    }

    public int getNextId() {
        return nextId++;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookingsByUser(model.User user) {
        List<Booking> result = new ArrayList<>();
        for (Booking b : bookings) {
            if (b.getCustomer().getId() == user.getId()) {
                result.add(b);
            }
        }
        return result;
    }
}
