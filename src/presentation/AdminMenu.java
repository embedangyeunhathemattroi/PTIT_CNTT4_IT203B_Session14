package presentation;

import model.PC;
import model.User;
import model.Food;
import model.Drink;
import service.AdminService;
import service.DrinkService;
import service.FoodService;
import service.PCService;
import util.InputValidate;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static final PCService pcService = new PCService();
    private static final FoodService foodService = new FoodService();
    private static final DrinkService drinkService = new DrinkService();

    public static void show(Scanner sc) {
        while (true) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Quản lý PC");
            System.out.println("2. Quản lý Food");
            System.out.println("3. Quản lý Drink");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1 -> pcMenu(sc);
                case 2 -> foodMenu(sc);
                case 3 -> drinkMenu(sc);
                case 0 -> {
                System.out.println("Thoát Admin Menu");
                return;
                }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private static void pcMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ PC ---");
            System.out.println("1. Hiển thị PC");
            System.out.println("2. Thêm PC");
            System.out.println("3. Sửa PC");
            System.out.println("4. Xóa PC");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int c = Integer.parseInt(sc.nextLine().trim());

            switch (c) {
                case 1 -> displayPCs();
                case 2 -> addPC(sc);
                case 3 -> updatePC(sc);
                case 4 -> deletePC(sc);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void displayPCs() {
        List<PC> list = pcService.getAllPCs();
        if (list.isEmpty()) {
            System.out.println("Chưa có PC nào!");
            return;
        }
        System.out.printf("%-3s | %-15s | %-10s | %-25s | %-10s\n",
                "ID", "Tên máy", "Loại", "Cấu hình", "Giá/h");
        list.forEach(pc -> System.out.printf("%-3d | %-15s | %-10s | %-25s | %-10.0f\n",
                pc.getId(),
                pc.getName(),
                (pc.getCategoryId() == 1 ? "VIP" : "Standard"),
                pc.getConfig(),
                pc.getPrice()));
    }

    private static void addPC(Scanner sc) {
        System.out.print("Tên máy: ");
        String name = InputValidate.inputString(sc);
        if (pcService.isPCNameExists(name)) {
            System.out.println("Tên máy đã tồn tại!");
            return;
        }

        int category;
        while (true) {
            try {
                System.out.print("Loại máy (1-VIP, 2-Standard): ");
                category = InputValidate.inputInteger(sc);
                if (category == 1 || category == 2)
                    break;
                System.out.println("Chỉ nhập 1 hoặc 2!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        System.out.print("Cấu hình: ");
        String config = InputValidate.inputString(sc);

        double price;
        while (true) {
            try {
                System.out.print("Giá/h: ");
                price = InputValidate.inputDouble(sc);
                if (price > 0)
                    break;
                System.out.println("Giá phải > 0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }
        boolean added = pcService.addPC(name, category, config, price, "AVAILABLE");
        System.out.println(added ? "Thêm PC thành công!" : " Thêm thất bại!");
    }

    private static void updatePC(Scanner sc) {
        displayPCs();
        System.out.print("Nhập ID PC cần sửa: ");
        int id = InputValidate.inputInteger(sc);
        PC pc = pcService.getPCById(id);
        if (pc == null) {
            System.out.println("Không tìm thấy PC!");
            return;
        }

        System.out.print("Tên mới: ");
        String newName = InputValidate.inputString(sc);
        if (!newName.equalsIgnoreCase(pc.getName()) && pcService.isPCNameExists(newName)) {
            System.out.println("Tên máy đã tồn tại!");
            return;
        }

        System.out.print("Cấu hình mới: ");
        String newConfig = InputValidate.inputString(sc);

        double newPrice;
        while (true) {
            try {
                System.out.print("Giá/h mới: ");
                newPrice = InputValidate.inputDouble(sc);
                if (newPrice > 0)
                    break;
                System.out.println("Giá phải > 0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        boolean updated = pcService.updatePC(id, newName, newConfig, newPrice);
        System.out.println(updated ? " Cập nhật thành công!" : " Cập nhật thất bại!");
    }

    private static void deletePC(Scanner sc) {
        displayPCs();
        System.out.print("Nhập ID PC cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean deleted = pcService.deletePC(id);
        System.out.println(deleted ? " Xóa thành công!" : " Xóa thất bại!");
    }

    private static void foodMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ FOOD ---");
            System.out.println("1. Hiển thị Food");
            System.out.println("2. Thêm Food");
            System.out.println("3. Sửa Food");
            System.out.println("4. Xóa Food");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int c = InputValidate.inputInteger(sc);

            switch (c) {
                case 1 -> displayFoods();
                case 2 -> addFood(sc);
                case 3 -> updateFood(sc);
                case 4 -> deleteFood(sc);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private static void displayFoods() {
        List<Food> list = foodService.getAllFoods();
        if (list.isEmpty()) {
            System.out.println("Chưa có món nào!");
            return;
        }
        System.out.printf("%-3s | %-15s | %-20s | %-10s | %-5s\n", "ID", "Tên", "Mô tả",
                "Giá", "SL");
        for (Food f : list) {
            System.out.printf("%-3d | %-15s | %-20s | %-10.0f | %-5d\n",
                    f.getFoodId(), f.getName(), f.getDescription(),
                    f.getPrice(), f.getStock());
        }
    }

    private static void addFood(Scanner sc) {
        System.out.print("Tên món: ");
        String name = InputValidate.inputString(sc);
        if (foodService.isFoodNameExists(name)) {
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

        boolean added = foodService.addFood(name, desc, price, stock);
        System.out.println(added ? " Thêm thành công!" : " Thêm thất bại!");
    }

    private static void updateFood(Scanner sc) {
        displayFoods();
        System.out.print("Nhập ID món cần sửa: ");
        int id = Integer.parseInt(sc.nextLine());
        Food f = foodService.getFoodById(id);
        if (f == null) {
            System.out.println("Không tìm thấy món!");
            return;
        }

        System.out.print("Tên mới: ");
        String newName = sc.nextLine().trim();
        if (!newName.equalsIgnoreCase(f.getName()) &&
                foodService.isFoodNameExists(newName)) {
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

        boolean updated = foodService.updateFood(id, newName, newDesc, newPrice,
                newStock);
        System.out.println(updated ? " Cập nhật thành công!" : " Cập nhật thất bại!");
    }

    private static void deleteFood(Scanner sc) {
        displayFoods();
        System.out.print("Nhập ID món cần xóa: ");
        int id = InputValidate.inputInteger(sc);
        boolean deleted = foodService.deleteFood(id);
        System.out.println(deleted ? " Xóa thành công!" : " Xóa thất bại!");
    }

    private static void drinkMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ DRINK ---");
            System.out.println("1. Hiển thị Drink");
            System.out.println("2. Thêm Drink");
            System.out.println("3. Sửa Drink");
            System.out.println("4. Xóa Drink");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            Integer choice = InputValidate.inputInteger(sc);

            switch (choice) {
                case 1 -> displayDrinks();
                case 2 -> addDrink(sc);
                case 3 -> updateDrink(sc);
                case 4 -> deleteDrink(sc);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    private static void displayDrinks() {
        List<Drink> list = drinkService.getAllDrinks();
        if (list.isEmpty()) {
            System.out.println("Chưa có drink nào!");
            return;
        }
        System.out.printf("%-3s | %-15s | %-20s | %-10s | %-5s\n", "ID", "Tên", "Mô tả",
                "Giá", "SL");
        for (Drink d : list) {
            System.out.printf("%-3d | %-15s | %-20s | %-10.0f | %-5d\n",
                    d.getDrinkId(), d.getName(), d.getDescription().toUpperCase(),
                    d.getPrice(), d.getStock());
        }
    }

    private static void addDrink(Scanner sc) {
        System.out.print("Tên drink: ");
        String name = InputValidate.inputString(sc);
        if (drinkService.isDrinkNameExists(name)) {
            System.out.println("Tên drink đã tồn tại!");
            return;
        }

        System.out.print("Mô tả: ");
        String desc = InputValidate.inputString(sc);

        double price;
        while (true) {
            try {
                System.out.print("Giá: ");
                price = InputValidate.inputDouble(sc);
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
                stock = InputValidate.inputInteger(sc);
                if (stock >= 0)
                    break;
                System.out.println("Số lượng >=0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        boolean added = drinkService.addDrink(name, desc, price, stock);
        System.out.println(added ? "Thêm thành công!" : " Thêm thất bại!");
    }

    private static void updateDrink(Scanner sc) {
        displayDrinks();
        System.out.print("Nhập ID drink cần sửa: ");
        int id = InputValidate.inputInteger(sc);
        Drink d = drinkService.getDrinkById(id);
        if (d == null) {
            System.out.println("Không tìm thấy drink!");
            return;
        }

        System.out.print("Tên mới: ");
        String newName = InputValidate.inputString(sc);
        if (!newName.equalsIgnoreCase(d.getName()) &&
                drinkService.isDrinkNameExists(newName)) {
            System.out.println("Tên drink đã tồn tại!");
            return;
        }

        System.out.print("Mô tả mới: ");
        String newDesc = InputValidate.inputString(sc);

        double newPrice;
        while (true) {
            try {
                System.out.print("Giá mới: ");
                newPrice = InputValidate.inputDouble(sc);
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
                newStock = InputValidate.inputInteger(sc);
                if (newStock >= 0)
                    break;
                System.out.println("Số lượng >=0!");
            } catch (NumberFormatException e) {
                System.out.println("Nhập số hợp lệ!");
            }
        }

        boolean updated = drinkService.updateDrink(id, newName, newDesc, newPrice,
                newStock);
        System.out.println(updated ? " Cập nhật thành công!" : " Cập nhật thất bại!");
    }

    private static void deleteDrink(Scanner sc) {
        displayDrinks();
        System.out.print("Nhập ID drink cần xóa: ");
        int id = InputValidate.inputInteger(sc);
        boolean deleted = drinkService.deleteDrink(id);
        System.out.println(deleted ? " Xóa thành công!" : " Xóa thất bại!");
    }
}
