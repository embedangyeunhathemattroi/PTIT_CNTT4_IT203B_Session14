package presentation;

import model.User;
import model.Booking;
import model.Drink;
import model.Food;
import model.PC;
import service.BookingService;
import service.DrinkService;
import service.FoodService;
import service.PCService;
import util.InputValidate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {

    private static final PCService pcService = new PCService();
    private static final FoodService foodService = new FoodService();
    private static final DrinkService drinkService = new DrinkService();
    private static final BookingService bookingService = new BookingService();
    

    public static void show(Scanner sc, User user) {
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
            int choice = InputValidate.inputInteger(sc);

            switch (choice) {
                case 1 -> showAvailablePCs();
                case 2 -> bookPC(user, sc);
                case 3 -> showFoodsAndDrinks();
                case 4 -> bookFoodForPC(user, sc);
                case 5 -> showHistory(user);
                case 6 -> System.out.println("Số dư ví hiện tại: " + user.getBalance() + " VNĐ");
                case 0 -> {
                    System.out.println("Thoát menu khách hàng.");
                    return;
                }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private static void showAvailablePCs() {
        List<PC> pcs = pcService.getAllPCs();
        System.out.printf("%-3s | %-15s | %-10s | %-25s | %-10s\n",
                "ID", "Tên máy", "Loại", "Cấu hình", "Giá/h");
        for (PC p : pcs) {
            if ("AVAILABLE".equalsIgnoreCase(p.getStatus())) {
                System.out.printf("%-3d | %-15s | %-10s | %-25s | %-10.0f\n",
                        p.getId(),
                        p.getName(),
                        (p.getCategoryId() == 1 ? "VIP" : "Standard"),
                        p.getConfig(),
                        p.getPrice());
            }
        }
    }

    private static void showFoodsAndDrinks() {
        List<Food> foods = foodService.getAllFoods();
        System.out.println("\n=== DANH SÁCH ĐỒ ĂN ===");
        System.out.printf("%-3s | %-20s | %-10s | %-5s\n", "ID", "Tên", "Giá", "SL");
        for (Food f : foods) {
            System.out.printf("%-3d | %-20s | %-10.0f | %-5d\n",
                    f.getFoodId(), f.getName(), f.getPrice(), f.getStock());
        }
        List<Drink> drinks = drinkService.getAllDrinks();
        System.out.println("\n=== DANH SÁCH ĐỒ UỐNG ===");
        System.out.printf("%-3s | %-20s | %-10s | %-5s\n", "ID", "Tên", "Giá", "SL");
        for (Drink d : drinks) {
            System.out.printf("%-3d | %-20s | %-10.0f | %-5d\n",
                    d.getDrinkId(), d.getName(), d.getPrice(), d.getStock());
        }
    }

    private static void bookPC(User user, Scanner sc) {
        showAvailablePCs();
        System.out.print("Nhập ID máy muốn đặt: ");
        int pcId = InputValidate.inputInteger(sc);
        System.out.print("Nhập thời gian bắt đầu (yyyy-MM-dd HH:mm): ");
        LocalDateTime startTime = InputValidate.inputDateTime(sc);
        LocalDateTime endTime;
        while (true) {
            System.out.print("Nhập thời gian kết thúc (yyyy-MM-dd HH:mm): ");
            endTime = InputValidate.inputDateTime(sc);
            if (endTime.isAfter(startTime))
                break;
            System.out.println(" Thời gian kết thúc phải sau thời gian bắt đầu!");
        }

        System.out.print("Nhập mô tả (nếu có): ");
        String desc = InputValidate.inputString(sc, true);

        // Tính số giờ và giá
        long hours = Duration.between(startTime, endTime).toMinutes() / 60;
        double price = pcService.getPCById(pcId).getPrice() * hours;

        // Tạo booking
        Booking booking = new Booking();
        booking.setUserId(user.getId());
        booking.setPcId(pcId);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setDescription(desc);
        booking.setPrice(price);
        booking.setStatus("ACTIVE");

        // Lưu vào user và bookingService
        user.addBooking(booking);
        bookingService.addBooking(booking);

        System.out.println("Đặt máy ID " + booking.getPcId() + " thành công! Tổng tiền: " + price + " VNĐ");
    }

    private static void bookFoodForPC(User user, Scanner sc) {
        showFoodsAndDrinks();
        System.out.print("Nhập ID booking đã tạo: ");
        int bookingId = InputValidate.inputInteger(sc);
        System.out.print("Nhập ID món muốn đặt: ");
        int foodId = InputValidate.inputInteger(sc);
        System.out.print("Nhập số lượng: ");
        int qty = InputValidate.inputInteger(sc);

        boolean success = foodService.addBookingFood(bookingId, foodId, qty);
        System.out.println(success ? " Đặt món thành công!" : " Đặt món thất bại! Kiểm tra tồn kho.");
    }

    private static void showHistory(User user) {
        List<Booking> bookings = bookingService.getBookingsByUser(user);
        if (bookings.isEmpty()) {
            System.out.println("Bạn chưa có đơn hàng nào.");
            return;
        }

        System.out.printf("%-3s | %-20s | %-15s | %-10s\n", "ID", "Mô tả", "Trạng thái", "Giá (VNĐ)");
        for (Booking b : bookings) {
            System.out.printf("%-3d | %-20s | %-15s | %-10.0f\n",
                    b.getId(),
                    b.getDescription(),
                    b.getStatus(),
                    b.getPrice());
        }
    }
}
