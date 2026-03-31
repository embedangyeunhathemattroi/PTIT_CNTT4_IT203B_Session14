package presentation;

import model.User;
import model.Food;
import service.PCService;
import util.InputValidate;
import service.FoodService;
import service.BookingService;

import java.util.List;
import java.util.Scanner;

public class StaffMenu {

    public static void show(Scanner sc) {

        // ===== TẠO INSTANCE CỦA SERVICE =====
        PCService pcService = new PCService();
        FoodService foodService = new FoodService();
        BookingService bookingService = new BookingService();

        while (true) {
            System.out.println("\n===== STAFF MENU =====");
            System.out.println("1. Xem danh sách đặt máy");
            System.out.println("2. Cập nhật trạng thái máy");
            System.out.println("3. Xem và chuẩn bị đơn F&B");
            System.out.println("4. Xác nhận khách hàng đã nhận máy");
            System.out.println("0. Đăng xuất");
            System.out.print("Chọn: ");

            int choice = InputValidate.inputInteger(sc);

            switch (choice) {
                case 1:
                    System.out.println("\n=== DANH SÁCH ĐƠN HÀNG ===");
                    bookingService.showAllBookings();
                    break;

                case 2:
                    try {
                        System.out.print("\nNhập ID máy cần cập nhật trạng thái: ");
                        int pcId = InputValidate.inputInteger(sc);

                        System.out.print("Chọn trạng thái (1. AVAILABLE, 2. IN_USE, 3. MAINTENANCE): ");
                        int statusChoice = InputValidate.inputInteger(sc);
                        String newStatus = switch (statusChoice) {
                            case 1 -> "AVAILABLE";
                            case 2 -> "IN_USE";
                            case 3 -> "MAINTENANCE";
                            default -> null;
                        };

                        if (newStatus != null) {
                            pcService.updateStatus(pcId, newStatus);
                            System.out.println("Cập nhật trạng thái thành công!");
                        } else {
                            System.out.println("Trạng thái không hợp lệ!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID không hợp lệ!");
                    }
                    break;
                case 3:
                    System.out.println("\n=== DANH SÁCH ĐƠN F&B CHỜ XỬ LÝ ===");

                    // gọi service đúng
                    List<String> orders = foodService.getPendingBookingFoods();

                    if (orders.isEmpty()) {
                        System.out.println("Không có đơn hàng nào!");
                    } else {
                        for (String o : orders) {
                            System.out.println(o);
                        }

                        try {
                            System.out.print("Nhập ID food cần đánh dấu đã chuẩn bị: ");
                            int foodId = InputValidate.inputInteger(sc);

                            foodService.markAsPrepared(foodId);
                            System.out.println("Đơn hàng đã được chuẩn bị!");
                        } catch (NumberFormatException e) {
                            System.out.println("ID không hợp lệ!");
                        }
                    }
                    break;

                case 4:
                    try {
                        System.out.print("\nNhập ID đơn hàng khách đã nhận: ");
                        int bookingId = InputValidate.inputInteger(sc);
                        bookingService.confirmReceived(bookingId);
                        System.out.println("Đã xác nhận khách nhận máy!");
                    } catch (NumberFormatException e) {
                        System.out.println("ID không hợp lệ!");
                    }
                    break;

                case 0:
                    System.out.println("Đăng xuất Staff...");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
