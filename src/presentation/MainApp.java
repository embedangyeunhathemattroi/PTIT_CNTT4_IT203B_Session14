package presentation;

import java.util.Scanner;
import model.User;
import service.UserService;
import util.InputValidate;

// ===================== MAIN APP =====================
public class MainApp {
    
    static Scanner sc = new Scanner(System.in);
    static User user;

    public static void main(String[] args) {
        UserService userService = new UserService();
        // ====== Menu chính: Đăng ký / Đăng nhập ======
        while (true) {
            System.out.println("\n===== MENU CHÍNH =====");
            System.out.println("1. Đăng ký");
            System.out.println("2. Đăng nhập");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            int choice = InputValidate.inputInteger(sc);

            switch (choice) {
                case 1:
                    userService.register(sc);
                    break;
                case 2:
                    user = userService.login(sc);
                    if (user.getRole().equals("ADMIN")) {
                        AdminMenu.show(sc);
                    } else if (user.getRole().equals("STAFF")) {
                        StaffMenu.show(sc);
                    } else if (user.getRole().equals("CUSTOMER")) {
                        CustomerMenu.show(sc, user);
                    }
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

}
