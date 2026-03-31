package DAO;

import model.Food;
import java.util.List;

public interface FoodDAO {

    // Thêm món
    boolean insert(Food food);

    // Lấy tất cả món
    List<Food> findAll();

    // Lấy các món đang pending
    List<Food> findPending();

    // Tìm theo ID
    Food findById(int id);

    // Cập nhật trạng thái món
    boolean updateStatus(int foodId, String status);

    // Cập nhật thông tin món
    boolean update(Food food);

    // Xóa món
    boolean delete(int id);

    // Thêm món vào booking
    boolean addBookingFood(int bookingId, int foodId, int quantity);

    // Lấy danh sách món pending trong booking
    List<String> getPendingBookingFoods();
}