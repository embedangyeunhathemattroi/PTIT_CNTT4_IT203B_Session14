package presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import model.User;
import service.UserService;
import util.InputValidate;

// ===================== MODEL CLASSES =====================
// class User {
//     private String username;
//     private String password;
//     private String role; // ADMIN, CUSTOMER

//     public User(String username, String password, String role) {
//         this.username = username;
//         this.password = password;
//         this.role = role;
//     }

//     public String getUsername() {
//         return username;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public String getRole() {
//         return role;
//     }
// }

// class PC {
//     private int id;
//     private String name;
//     private String category;
//     private String config;
//     private double price;

//     public PC(int id, String name, String category, String config, double price) {
//         this.id = id;
//         this.name = name;
//         this.category = category;
//         this.config = config;
//         this.price = price;
//     }

//     public int getId() {
//         return id;
//     }

//     public String getName() {
//         return name;
//     }

//     public String getCategory() {
//         return category;
//     }

//     public String getConfig() {
//         return config;
//     }

//     public double getPrice() {
//         return price;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public void setCategory(String category) {
//         this.category = category;
//     }

//     public void setConfig(String config) {
//         this.config = config;
//     }

//     public void setPrice(double price) {
//         this.price = price;
//     }
// }

// class Food {
//     private int id;
//     private String name;
//     private String description;
//     private double price;
//     private int stock;

//     public Food(int id, String name, String description, double price, int stock) {
//         this.id = id;
//         this.name = name;
//         this.description = description;
//         this.price = price;
//         this.stock = stock;
//     }

//     public int getId() {
//         return id;
//     }

//     public String getName() {
//         return name;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public double getPrice() {
//         return price;
//     }

//     public int getStock() {
//         return stock;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public void setPrice(double price) {
//         this.price = price;
//     }

//     public void setStock(int stock) {
//         this.stock = stock;
//     }
// }

// class Drink {
//     private int id;
//     private String name;
//     private String description;
//     private double price;
//     private int stock;

//     public Drink(int id, String name, String description, double price, int stock) {
//         this.id = id;
//         this.name = name;
//         this.description = description;
//         this.price = price;
//         this.stock = stock;
//     }

//     public int getId() {
//         return id;
//     }

//     public String getName() {
//         return name;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public double getPrice() {
//         return price;
//     }

//     public int getStock() {
//         return stock;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public void setPrice(double price) {
//         this.price = price;
//     }

//     public void setStock(int stock) {
//         this.stock = stock;
//     }
// }

// ===================== MAIN APP =====================
public class MainApp {
    // static List<User> users = new ArrayList<>();
    // static List<PC> pcs = new ArrayList<>();
    // static List<Food> foods = new ArrayList<>();
    // static List<Drink> drinks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static User user;

    public static void main(String[] args) {
        // ====== Khởi tạo dữ liệu mẫu ======
        // users.add(new User("admin", "admin", "ADMIN"));

        // pcs.add(new PC(1, "PC01", "Gaming", "Intel i7, 16GB RAM, RTX 3060", 50000));
        // pcs.add(new PC(2, "PC02", "Office", "Intel i5, 8GB RAM, Integrated GPU",
        // 20000));

        // foods.add(new Food(1, "Pizza", "Cheese & Tomato", 150, 10));
        // foods.add(new Food(2, "Burger", "Beef Burger", 120, 15));

        // drinks.add(new Drink(1, "Coke", "Soft Drink", 20, 30));
        // drinks.add(new Drink(2, "Orange Juice", "Fresh Juice", 25, 20));
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
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    // ===================== ĐĂNG KÝ / ĐĂNG NHẬP =====================
    // private static void register() {
    // System.out.print("Nhập username: ");
    // String username = sc.nextLine();
    // boolean exists = users.stream().anyMatch(u ->
    // u.getUsername().equals(username));
    // if (exists) {
    // System.out.println("Username đã tồn tại!");
    // return;
    // }
    // System.out.print("Nhập password: ");
    // String password = sc.nextLine();
    // users.add(new User(username, password, "CUSTOMER"));
    // System.out.println("Đăng ký thành công!");
    // }

    // private static User login() {
    // System.out.print("Username: ");
    // String username = sc.nextLine();
    // System.out.print("Password: ");
    // String password = sc.nextLine();
    // for (User u : users) {
    // if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
    // System.out.println("Đăng nhập thành công!");
    // return u;
    // }
    // }
    // System.out.println("Sai username hoặc password!");
    // return null;
    // }

    // ===================== ADMIN MENU =====================
    // private static void adminMenu() {
    // while (true) {
    // System.out.println("\n===== ADMIN MENU =====");
    // System.out.println("1. Quản lý PC");
    // System.out.println("2. Quản lý Food");
    // System.out.println("3. Quản lý Drink");
    // System.out.println("0. Đăng xuất");
    // System.out.print("Chọn: ");
    // int choice = Integer.parseInt(sc.nextLine());

    // switch (choice) {
    // case 1:
    // managePC();
    // break;
    // case 2:
    // manageFood();
    // break;
    // case 3:
    // manageDrink();
    // break;
    // case 0:
    // return;
    // default:
    // System.out.println("Lựa chọn không hợp lệ!");
    // }
    // }
    // }

    // // ===================== CUSTOMER MENU =====================
    // private static void customerMenu(User user) {
    // while (true) {
    // System.out.println("\n===== MENU KHÁCH HÀNG =====");
    // System.out.println("1. Xem danh sách PC");
    // System.out.println("2. Xem danh sách Food");
    // System.out.println("3. Xem danh sách Drink");
    // System.out.println("0. Đăng xuất");
    // System.out.print("Chọn: ");
    // int choice = Integer.parseInt(sc.nextLine());

    // switch (choice) {
    // case 1:
    // showPC();
    // break;
    // case 2:
    // showFood();
    // break;
    // case 3:
    // showDrink();
    // break;
    // case 0:
    // return;
    // default:
    // System.out.println("Lựa chọn không hợp lệ!");
    // }
    // }
    // }

    // // ===================== PC MENU =====================
    // private static void managePC() {
    // while (true) {
    // System.out.println("\n--- QUẢN LÝ PC ---");
    // System.out.println("1. Hiển thị PC");
    // System.out.println("2. Thêm PC");
    // System.out.println("3. Sửa PC");
    // System.out.println("4. Xóa PC");
    // System.out.println("0. Quay lại");
    // System.out.print("Chọn: ");
    // int choice = Integer.parseInt(sc.nextLine());

    // switch (choice) {
    // case 1:
    // showPC();
    // break;
    // case 2:
    // addPC();
    // break;
    // case 3:
    // editPC();
    // break;
    // case 4:
    // deletePC();
    // break;
    // case 0:
    // return;
    // default:
    // System.out.println("Lựa chọn không hợp lệ!");
    // }
    // }
    // }

    // private static void showPC() {
    // if (pcs.isEmpty()) {
    // System.out.println("Chưa có PC nào!");
    // return;
    // }
    // for (PC pc : pcs) {
    // System.out.println(pc.getId() + " | " + pc.getName() + " | " +
    // pc.getCategory() + " | " + pc.getConfig()
    // + " | " + pc.getPrice());
    // }
    // }

    // private static void addPC() {
    // int id = pcs.size() + 1;
    // System.out.print("Tên PC: ");
    // String name = sc.nextLine();
    // System.out.print("Loại: ");
    // String category = sc.nextLine();
    // System.out.print("Cấu hình: ");
    // String config = sc.nextLine();
    // System.out.print("Giá: ");
    // double price = Double.parseDouble(sc.nextLine());
    // pcs.add(new PC(id, name, category, config, price));
    // System.out.println("Thêm PC thành công!");
    // }

    // private static void editPC() {
    // System.out.print("Nhập ID PC cần sửa: ");
    // int id = Integer.parseInt(sc.nextLine());
    // PC pc = pcs.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    // if (pc == null) {
    // System.out.println("Không tìm thấy PC!");
    // return;
    // }
    // System.out.print("Tên mới: ");
    // pc.setName(sc.nextLine());
    // System.out.print("Loại mới: ");
    // pc.setCategory(sc.nextLine());
    // System.out.print("Cấu hình mới: ");
    // pc.setConfig(sc.nextLine());
    // System.out.print("Giá mới: ");
    // pc.setPrice(Double.parseDouble(sc.nextLine()));
    // System.out.println("Cập nhật thành công!");
    // }

    // private static void deletePC() {
    // System.out.print("Nhập ID PC cần xóa: ");
    // int id = Integer.parseInt(sc.nextLine());
    // pcs.removeIf(p -> p.getId() == id);
    // System.out.println("Xóa thành công!");
    // }

    // ===================== FOOD MENU =====================
    // private static void manageFood() {
    // while (true) {
    // System.out.println("\n--- QUẢN LÝ FOOD ---");
    // System.out.println("1. Hiển thị Food");
    // System.out.println("2. Thêm Food");
    // System.out.println("3. Sửa Food");
    // System.out.println("4. Xóa Food");
    // System.out.println("0. Quay lại");
    // System.out.print("Chọn: ");
    // int choice = Integer.parseInt(sc.nextLine());

    // switch (choice) {
    // case 1:
    // showFood();
    // break;
    // case 2:
    // addFood();
    // break;
    // case 3:
    // editFood();
    // break;
    // case 4:
    // deleteFood();
    // break;
    // case 0:
    // return;
    // default:
    // System.out.println("Lựa chọn không hợp lệ!");
    // }
    // }
    // }

    // private static void showFood() {
    // if (foods.isEmpty()) {
    // System.out.println("Chưa có món nào!");
    // return;
    // }
    // for (Food f : foods) {
    // System.out.println(f.getId() + " | " + f.getName() + " | " +
    // f.getDescription() + " | " + f.getPrice()
    // + " | Stock: " + f.getStock());
    // }
    // }

    // private static void addFood() {
    // int id = foods.size() + 1;
    // System.out.print("Tên Food: ");
    // String name = sc.nextLine();
    // System.out.print("Mô tả: ");
    // String desc = sc.nextLine();
    // System.out.print("Giá: ");
    // double price = Double.parseDouble(sc.nextLine());
    // System.out.print("Stock: ");
    // int stock = Integer.parseInt(sc.nextLine());
    // foods.add(new Food(id, name, desc, price, stock));
    // System.out.println("Thêm Food thành công!");
    // }

    // private static void editFood() {
    // System.out.print("Nhập ID Food cần sửa: ");
    // int id = Integer.parseInt(sc.nextLine());
    // Food f = foods.stream().filter(food -> food.getId() ==
    // id).findFirst().orElse(null);
    // if (f == null) {
    // System.out.println("Không tìm thấy Food!");
    // return;
    // }
    // System.out.print("Tên mới: ");
    // f.setName(sc.nextLine());
    // System.out.print("Mô tả mới: ");
    // f.setDescription(sc.nextLine());
    // System.out.print("Giá mới: ");
    // f.setPrice(Double.parseDouble(sc.nextLine()));
    // System.out.print("Stock mới: ");
    // f.setStock(Integer.parseInt(sc.nextLine()));
    // System.out.println("Cập nhật thành công!");
    // }

    // private static void deleteFood() {
    // System.out.print("Nhập ID Food cần xóa: ");
    // int id = Integer.parseInt(sc.nextLine());
    // foods.removeIf(f -> f.getId() == id);
    // System.out.println("Xóa thành công!");
    // }

    // // ===================== DRINK MENU =====================
    // private static void manageDrink() {
    // while (true) {
    // System.out.println("\n--- QUẢN LÝ DRINK ---");
    // System.out.println("1. Hiển thị Drink");
    // System.out.println("2. Thêm Drink");
    // System.out.println("3. Sửa Drink");
    // System.out.println("4. Xóa Drink");
    // System.out.println("0. Quay lại");
    // System.out.print("Chọn: ");
    // int choice = Integer.parseInt(sc.nextLine());

    // switch (choice) {
    // case 1:
    // showDrink();
    // break;
    // case 2:
    // addDrink();
    // break;
    // case 3:
    // editDrink();
    // break;
    // case 4:
    // deleteDrink();
    // break;
    // case 0:
    // return;
    // default:
    // System.out.println("Lựa chọn không hợp lệ!");
    // }
    // }
    // }

    // private static void showDrink() {
    // if (drinks.isEmpty()) {
    // System.out.println("Chưa có Drink nào!");
    // return;
    // }
    // for (Drink d : drinks) {
    // System.out.println(d.getId() + " | " + d.getName() + " | " +
    // d.getDescription() + " | " + d.getPrice()
    // + " | Stock: " + d.getStock());
    // }
    // }

    // private static void addDrink() {
    // int id = drinks.size() + 1;
    // System.out.print("Tên Drink: ");
    // String name = sc.nextLine();
    // System.out.print("Mô tả: ");
    // String desc = sc.nextLine();
    // System.out.print("Giá: ");
    // double price = Double.parseDouble(sc.nextLine());
    // System.out.print("Stock: ");
    // int stock = Integer.parseInt(sc.nextLine());
    // drinks.add(new Drink(id, name, desc, price, stock));
    // System.out.println("Thêm Drink thành công!");
    // }

    // private static void editDrink() {
    // System.out.print("Nhập ID Drink cần sửa: ");
    // int id = Integer.parseInt(sc.nextLine());
    // Drink d = drinks.stream().filter(drink -> drink.getId() ==
    // id).findFirst().orElse(null);
    // if (d == null) {
    // System.out.println("Không tìm thấy Drink!");
    // return;
    // }
    // System.out.print("Tên mới: ");
    // d.setName(sc.nextLine());
    // System.out.print("Mô tả mới: ");
    // d.setDescription(sc.nextLine());
    // System.out.print("Giá mới: ");
    // d.setPrice(Double.parseDouble(sc.nextLine()));
    // System.out.print("Stock mới: ");
    // d.setStock(Integer.parseInt(sc.nextLine()));
    // System.out.println("Cập nhật thành công!");
    // }

    // private static void deleteDrink() {
    // System.out.print("Nhập ID Drink cần xóa: ");
    // int id = Integer.parseInt(sc.nextLine());
    // drinks.removeIf(d -> d.getId() == id);
    // System.out.println("Xóa thành công!");
    // }
}