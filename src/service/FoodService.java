package service;

import DAO.DrinkDAO;
import DAO.FoodDAO;
import model.Food;

import java.util.List;

public class FoodService {
    private FoodDAO foodDAO = new FoodDAO();

    // Lấy danh sách tất cả món
    public List<Food> getAllFoods() {
        return foodDAO.findAll();
    }

    public Food getFoodById(int id) {
        return foodDAO.findById(id);
    }

    public boolean isFoodNameExists(String name) {
        return foodDAO.isFoodNameExists(name);
    }

    public boolean updateFood(int id, String name, String desc, double price, int stock) {
        return foodDAO.update(new Food(id, name, desc, price, stock));
    }

    public boolean markAsPrepared(int foodId) {
        return foodDAO.updateStatus(foodId, "PREPARED"); // gọi DAO để update status
    }

    public List<Food> getPendingOrders() {
        return foodDAO.findPending(); // gọi đến DAO để lấy dữ liệu
    }

    // Thêm món mới
    public boolean addFood(String name, String desc, double price, int stock) {
        Food food = new Food(name, desc, price, stock);
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
