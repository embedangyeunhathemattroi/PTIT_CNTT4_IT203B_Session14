package presentation;

import model.PC;
import model.Food;
import model.Drink;
import service.AdminService;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private static final Scanner sc = new Scanner(System.in);
    private static final AdminService service = new AdminService();

    public static void menu() {
        while (true) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Quản lý PC");
            System.out.println("2. Quản lý Food");
            System.out.println("3. Quản lý Drink");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> pcMenu();
                case "2" -> foodMenu();
                case "3" -> drinkMenu();
                case "0" -> {
                    System.out.println("Thoát Admin Menu");
                    return;
                }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private static void pcMenu() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ PC ---");
            System.out.println("1. Hiển thị PC");
            System.out.println("2. Thêm PC");
            System.out.println("3. Sửa PC");
            System.out.println("4. Xóa PC");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String c = sc.nextLine().trim();

            switch (c) {
                case "1" -> displayPCs();
                case "2" -> addPC();
                case "3" -> updatePC();
                case "4" -> deletePC();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private static void displayPCs() {
        List<PC> list = service.getAllPCs();
        if (list.isEmpty()) {
            System.out.println("Chưa có PC nào!");
            return;
        }
        System.out.printf("%-3s %-15s %-10s %-20s %-10s\n",
                "ID", "Tên máy", "Loại", "Cấu hình", "Giá/h");
        list.forEach(pc -> System.out.printf("%-3d %-15s %-10s %-20s %-10.0f\n",
                pc.getId(),
                pc.getName(),
                (pc.getCategoryId() == 1 ? "VIP" : "Standard"),
                pc.getConfig(),
                pc.getPrice()));
    }

    private static void addPC() {
        System.out.print("Tên máy: ");
        String name = capitalize(sc.nextLine().trim());
        if (service.isPCNameExists(name)) {
            System.out.println("Tên máy đã tồn tại!");
            return;
        }

        int category;
        while (true) {
            try {
                System.out.print("Loại máy (1-VIP, 2-Standard): ");
                category = Integer.parseInt(sc.nextLine());
                if (category == 1 || category == 2)
                    break;
                System.out.println("Chỉ nhập 1 hoặc 2!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        System.out.print("Cấu hình: ");
        String config = sc.nextLine().trim();

        double price;
        while (true) {
            try {
                System.out.print("Giá/h: ");
                price = Double.parseDouble(sc.nextLine());
                if (price > 0)
                    break;
                System.out.println("Giá phải > 0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        boolean added = service.addPC(name, category, config, price, "AVAILABLE");
        System.out.println(added ? "Thêm PC thành công!" : " Thêm thất bại!");
    }

    private static void updatePC() {
        displayPCs();
        System.out.print("Nhập ID PC cần sửa: ");
        int id = Integer.parseInt(sc.nextLine());
        PC pc = service.getPCById(id);
        if (pc == null) {
            System.out.println("Không tìm thấy PC!");
            return;
        }

        System.out.print("Tên mới: ");
        String newName = capitalize(sc.nextLine().trim());
        if (!newName.equalsIgnoreCase(pc.getName()) && service.isPCNameExists(newName)) {
            System.out.println("Tên máy đã tồn tại!");
            return;
        }

        System.out.print("Cấu hình mới: ");
        String newConfig = sc.nextLine().trim();

        double newPrice;
        while (true) {
            try {
                System.out.print("Giá/h mới: ");
                newPrice = Double.parseDouble(sc.nextLine());
                if (newPrice > 0)
                    break;
                System.out.println("Giá phải > 0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        boolean updated = service.updatePC(id, newName, newConfig, newPrice);
        System.out.println(updated ? " Cập nhật thành công!" : " Cập nhật thất bại!");
    }

    private static void deletePC() {
        displayPCs();
        System.out.print("Nhập ID PC cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean deleted = service.deletePC(id);
        System.out.println(deleted ? " Xóa thành công!" : " Xóa thất bại!");
    }

    private static void foodMenu() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ FOOD ---");
            System.out.println("1. Hiển thị Food");
            System.out.println("2. Thêm Food");
            System.out.println("3. Sửa Food");
            System.out.println("4. Xóa Food");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String c = sc.nextLine().trim();

            switch (c) {
                case "1" -> displayFoods();
                case "2" -> addFood();
                case "3" -> updateFood();
                case "4" -> deleteFood();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private static void displayFoods() {
        List<Food> list = service.getAllFoodsSorted();
        if (list.isEmpty()) {
            System.out.println("Chưa có món nào!");
            return;
        }
        System.out.printf("%-3s %-15s %-20s %-10s %-5s\n", "ID", "Tên", "Mô tả", "Giá", "SL");
        for (Food f : list) {
            System.out.printf("%-3d %-15s %-20s %-10.0f %-5d\n",
                    f.getFoodId(), f.getName(), toTitleCase(f.getDescription()),
                    f.getPrice(), f.getStock());
        }
    }

    private static void addFood() {
        System.out.print("Tên món: ");
        String name = capitalize(sc.nextLine().trim());
        if (service.isFoodNameExists(name)) {
            System.out.println("Tên món đã tồn tại!");
            return;
        }

        System.out.print("Mô tả: ");
        String desc = sc.nextLine().trim();

        double price;
        while (true) {
            try {
                System.out.print("Giá: ");
                price = Double.parseDouble(sc.nextLine());
                if (price > 0)
                    break;
                System.out.println("Giá phải > 0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        int stock;
        while (true) {
            try {
                System.out.print("Số lượng: ");
                stock = Integer.parseInt(sc.nextLine());
                if (stock >= 0)
                    break;
                System.out.println("Số lượng >=0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        boolean added = service.addFood(name, desc, price, stock);
        System.out.println(added ? " Thêm thành công!" : " Thêm thất bại!");
    }

    private static void updateFood() {
        displayFoods();
        System.out.print("Nhập ID món cần sửa: ");
        int id = Integer.parseInt(sc.nextLine());
        Food f = service.getFoodById(id);
        if (f == null) {
            System.out.println("Không tìm thấy món!");
            return;
        }

        System.out.print("Tên mới: ");
        String newName = capitalize(sc.nextLine().trim());
        if (!newName.equalsIgnoreCase(f.getName()) && service.isFoodNameExists(newName)) {
            System.out.println("Tên món đã tồn tại!");
            return;
        }

        System.out.print("Mô tả mới: ");
        String newDesc = sc.nextLine().trim();

        double newPrice;
        while (true) {
            try {
                System.out.print("Giá mới: ");
                newPrice = Double.parseDouble(sc.nextLine());
                if (newPrice > 0)
                    break;
                System.out.println("Giá phải > 0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        int newStock;
        while (true) {
            try {
                System.out.print("Số lượng mới: ");
                newStock = Integer.parseInt(sc.nextLine());
                if (newStock >= 0)
                    break;
                System.out.println("Số lượng >=0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        boolean updated = service.updateFood(id, newName, newDesc, newPrice, newStock);
        System.out.println(updated ? " Cập nhật thành công!" : " Cập nhật thất bại!");
    }

    private static void deleteFood() {
        displayFoods();
        System.out.print("Nhập ID món cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean deleted = service.deleteFood(id);
        System.out.println(deleted ? " Xóa thành công!" : " Xóa thất bại!");
    }

    private static void drinkMenu() {
        while (true) {
            System.out.println("\n--- QUẢN LÝ DRINK ---");
            System.out.println("1. Hiển thị Drink");
            System.out.println("2. Thêm Drink");
            System.out.println("3. Sửa Drink");
            System.out.println("4. Xóa Drink");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String c = sc.nextLine().trim();

            switch (c) {
                case "1" -> displayDrinks();
                case "2" -> addDrink();
                case "3" -> updateDrink();
                case "4" -> deleteDrink();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private static void displayDrinks() {
        List<Drink> list = service.getAllDrinksSorted();
        if (list.isEmpty()) {
            System.out.println("Chưa có drink nào!");
            return;
        }
        System.out.printf("%-3s %-15s %-20s %-10s %-5s\n", "ID", "Tên", "Mô tả", "Giá", "SL");
        for (Drink d : list) {
            System.out.printf("%-3d %-15s %-20s %-10.0f %-5d\n",
                    d.getDrinkId(), d.getName(), d.getDescription().toUpperCase(),
                    d.getPrice(), d.getStock());
        }
    }

    private static void addDrink() {
        System.out.print("Tên drink: ");
        String name = capitalize(sc.nextLine().trim());
        if (service.isDrinkNameExists(name)) {
            System.out.println("Tên drink đã tồn tại!");
            return;
        }

        System.out.print("Mô tả: ");
        String desc = sc.nextLine().trim();

        double price;
        while (true) {
            try {
                System.out.print("Giá: ");
                price = Double.parseDouble(sc.nextLine());
                if (price > 0)
                    break;
                System.out.println("Giá phải > 0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        int stock;
        while (true) {
            try {
                System.out.print("Số lượng: ");
                stock = Integer.parseInt(sc.nextLine());
                if (stock >= 0)
                    break;
                System.out.println("Số lượng >=0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        boolean added = service.addDrink(name, desc, price, stock);
        System.out.println(added ? "Thêm thành công!" : " Thêm thất bại!");
    }

    private static void updateDrink() {
        displayDrinks();
        System.out.print("Nhập ID drink cần sửa: ");
        int id = Integer.parseInt(sc.nextLine());
        Drink d = service.getDrinkById(id);
        if (d == null) {
            System.out.println("Không tìm thấy drink!");
            return;
        }

        System.out.print("Tên mới: ");
        String newName = capitalize(sc.nextLine().trim());
        if (!newName.equalsIgnoreCase(d.getName()) && service.isDrinkNameExists(newName)) {
            System.out.println("Tên drink đã tồn tại!");
            return;
        }

        System.out.print("Mô tả mới: ");
        String newDesc = sc.nextLine().trim();

        double newPrice;
        while (true) {
            try {
                System.out.print("Giá mới: ");
                newPrice = Double.parseDouble(sc.nextLine());
                if (newPrice > 0)
                    break;
                System.out.println("Giá phải > 0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        int newStock;
        while (true) {
            try {
                System.out.print("Số lượng mới: ");
                newStock = Integer.parseInt(sc.nextLine());
                if (newStock >= 0)
                    break;
                System.out.println("Số lượng >=0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        boolean updated = service.updateDrink(id, newName, newDesc, newPrice, newStock);
        System.out.println(updated ? " Cập nhật thành công!" : " Cập nhật thất bại!");
    }

    private static void deleteDrink() {
        displayDrinks();
        System.out.print("Nhập ID drink cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean deleted = service.deleteDrink(id);
        System.out.println(deleted ? " Xóa thành công!" : " Xóa thất bại!");
    }

    private static String capitalize(String str) {
        if (str.isEmpty())
            return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private static String toTitleCase(String str) {
        if (str == null || str.isEmpty())
            return str;
        String[] words = str.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1)
                    sb.append(word.substring(1).toLowerCase());
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}