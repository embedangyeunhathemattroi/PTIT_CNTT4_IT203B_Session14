package service;

import DAO.DrinkDAO;
import DAO.DrinkDAO;
import model.Drink;

import java.util.List;

public class DrinkService {
    private DrinkDAO DrinkDAO = new DrinkDAO();

    // Lấy danh sách tất cả món
    public List<Drink> getAllDrinks() {
        return DrinkDAO.findAll();
    }

    public Drink getDrinkById(int id) {
        return DrinkDAO.findById(id);
    }

    public boolean isDrinkNameExists(String name) {
        return DrinkDAO.isDrinkNameExists(name);
    }

    public boolean updateDrink(int id, String name, String desc, double price, int stock) {
        return DrinkDAO.update(new Drink(id, name, desc, price, stock));
    }

    public boolean markAsPrepared(int DrinkId) {
        return DrinkDAO.updateStatus(DrinkId, "PREPARED"); // gọi DAO để update status
    }

    public List<Drink> getPendingOrders() {
        return DrinkDAO.findPending(); // gọi đến DAO để lấy dữ liệu
    }

    // Thêm món mới
    public boolean addDrink(String name, String desc, double price, int stock) {
        Drink Drink = new Drink(name, desc, price, stock);
        return DrinkDAO.insert(Drink);
    }

    // Đặt món cho booking
    public boolean addBookingDrink(int bookingId, int DrinkId, int quantity) {
        return DrinkDAO.addBookingDrink(bookingId, DrinkId, quantity);
    }

    public boolean deleteDrink(int id) {
        return DrinkDAO.delete(id);
    }

    public List<String> getPendingBookingDrinks() {
        return DrinkDAO.getPendingBookingDrinks();
    }
}