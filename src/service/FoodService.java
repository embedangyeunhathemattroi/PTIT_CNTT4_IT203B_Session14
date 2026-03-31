package service;

import DAO.FoodDAO;
import DAO.FoodDAOImpl;
import model.Food;

import java.util.List;

public class FoodService {
    private FoodDAO foodDAO = new FoodDAOImpl();
    private int foodIdCounter = 1; // ← khai báo counter cho Food
    private int drinkIdCounter = 1; // ← khai báo counter cho Drink
    private int pcIdCounter = 1; // ← khai báo counter cho PC

    // Lấy danh sách tất cả món
    public List<Food> getAllFoods() {
        return foodDAO.findAll();
    }

    public boolean markAsPrepared(int foodId) {
        return foodDAO.updateStatus(foodId, "PREPARED"); // gọi DAO để update status
    }

    public List<Food> getPendingOrders() {
        return foodDAO.findPending(); // gọi đến DAO để lấy dữ liệu
    }

    // Thêm món mới
    public boolean addFood(String name, String desc, double price, int stock) {
        Food food = new Food(foodIdCounter++, name, desc, price, stock);
        return foodDAO.insert(food);
    }

    // Đặt món cho booking
    public boolean addBookingFood(int bookingId, int foodId, int quantity) {
        return foodDAO.addBookingFood(bookingId, foodId, quantity);
    }

    public boolean deleteFood(int id) {
        return foodDAO.delete(id);
    }

    public List<String> getPendingBookingFoods() {
        return foodDAO.getPendingBookingFoods();
    }
}