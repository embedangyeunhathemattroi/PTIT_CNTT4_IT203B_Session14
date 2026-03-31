package presentation;

import model.User;
import model.Booking;
import model.PC;
import service.BookingService;
import service.FoodService;
import service.PCService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {

    private static final Scanner sc = new Scanner(System.in);
    private static final PCService pcService = new PCService();
    private static final FoodService foodService = new FoodService();
    private static final BookingService bookingService = new BookingService();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void menu(User user) {
        while (true) {
            System.out.println("\n===== CUSTOMER MENU =====");
            System.out.println("1. Xem danh sách máy trống");
            System.out.println("2. Đặt máy");
            System.out.println("3. Xem đồ ăn/uống");
            System.out.println("4. Đặt đồ ăn kèm máy");
            System.out.println("5. Xem lịch sử đơn hàng");
            System.out.println("6. Xem số dư ví");
            System.out.println("0. Thoát");

            System.out.print("Chọn: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> showAvailablePCs();
                case "2" -> bookPC(user);
                case "3" -> showFoods();
                case "4" -> bookFoodForPC(user);
                case "5" -> showHistory(user);
                case "6" -> System.out.println("Số dư ví hiện tại: " + user.getWallet() + " VNĐ");
                case "0" -> {
                    System.out.println("Thoát menu khách hàng.");
                    return;
                }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private static void showAvailablePCs() {
        List<PC> pcs = pcService.getAllPCs();
        System.out.printf("%-3s %-15s %-10s %-20s %-10s\n",
                "ID", "Tên máy", "Loại", "Cấu hình", "Giá/h");
        for (PC p : pcs) {
            if ("AVAILABLE".equalsIgnoreCase(p.getStatus())) {
                System.out.printf("%-3d %-15s %-10s %-20s %-10.0f\n",
                        p.getId(),
                        p.getName(),
                        (p.getCategoryId() == 1 ? "VIP" : "Standard"),
                        p.getConfig(),
                        p.getPrice());
            }
        }
    }

    private static void showFoods() {
        List<model.Food> foods = foodService.getAllFoods();
        System.out.printf("%-3s %-20s %-10s %-5s\n", "ID", "Tên", "Giá", "SL");
        for (model.Food f : foods) {
            System.out.printf("%-3d %-20s %-10.0f %-5d\n",
                    f.getFoodId(), f.getName(), f.getPrice(), f.getStock());
        }
    }

    private static void bookPC(User user) {
        showAvailablePCs();

        int pcId = readInt("Nhập ID máy muốn đặt: ");

        LocalDateTime startTime = readDateTime("Nhập thời gian bắt đầu (yyyy-MM-dd HH:mm): ");
        LocalDateTime endTime;
        while (true) {
            endTime = readDateTime("Nhập thời gian kết thúc (yyyy-MM-dd HH:mm): ");
            if (endTime.isAfter(startTime))
                break;
            System.out.println(" Thời gian kết thúc phải sau thời gian bắt đầu!");
        }

        String desc = readString("Nhập mô tả (nếu có): ");

        // Tính số giờ và giá
        long hours = Duration.between(startTime, endTime).toHours();
        if (hours == 0)
            hours = 1; // tối thiểu 1 giờ
        double price = pcService.getPrice(pcId) * hours;

        // Tạo booking
        Booking booking = new Booking();
        booking.setId(bookingService.getNextId());
        booking.setCustomer(user);
        booking.setPcId(pcId);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setDescription(desc);
        booking.setPrice(price);
        booking.setStatus("ACTIVE");

        // Lưu vào user và bookingService
        user.addBooking(booking);
        bookingService.addBooking(booking);

        System.out.println("Đặt máy ID " + booking.getId() + " thành công! Tổng tiền: " + price + " VNĐ");
    }

    private static void bookFoodForPC(User user) {
        int bookingId = readInt("Nhập ID booking đã tạo: ");
        showFoods();
        int foodId = readInt("Nhập ID món muốn đặt: ");
        int qty = readInt("Nhập số lượng: ");

        boolean success = foodService.addBookingFood(bookingId, foodId, qty);
        System.out.println(success ? " Đặt món thành công!" : " Đặt món thất bại! Kiểm tra tồn kho.");
    }

    private static void showHistory(User user) {
        List<Booking> bookings = bookingService.getBookingsByUser(user);
        if (bookings.isEmpty()) {
            System.out.println("Bạn chưa có đơn hàng nào.");
            return;
        }

        System.out.printf("%-3s %-20s %-15s %-10s\n", "ID", "Mô tả", "Trạng thái", "Giá (VNĐ)");
        for (Booking b : bookings) {
            System.out.printf("%-3d %-20s %-15s %-10.0f\n",
                    b.getId(),
                    b.getDescription(),
                    b.getStatus(),
                    b.getPrice());
        }
    }

    // ================= Helper methods =================

    private static int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(" Giá trị không hợp lệ, vui lòng nhập lại!");
            }
        }
    }

    private static double readDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(" Giá trị không hợp lệ, vui lòng nhập lại!");
            }
        }
    }

    private static String readString(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private static LocalDateTime readDateTime(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String input = sc.nextLine().trim();
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println(" Định dạng thời gian không đúng, vui lòng nhập lại (yyyy-MM-dd HH:mm)");
            }
        }
    }
}