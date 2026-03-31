package service;

import java.util.ArrayList;
import java.util.List;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import model.User;

public class UserService {

    private UserDAO userDAO = new UserDAOImpl();

    public boolean register(String username, String password) {

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println(" Không được để trống!");
            return false;
        }

        if (password.length() < 6) {
            System.out.println(" Mật khẩu phải >= 6 ký tự");
            return false;
        }

        User user = new User(username, password, "CUSTOMER");
        return userDAO.register(user);
    }

    // REGISTER CÓ ROLE (ADMIN / STAFF / CUSTOMER)
    public boolean register(String username, String password, String role) {

        if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            System.out.println(" Không được để trống!");
            return false;
        }

        if (password.length() < 6) {
            System.out.println(" Mật khẩu phải >= 6 ký tự");
            return false;
        }
        role = role.toUpperCase();
        if (!role.equals("ADMIN") && !role.equals("STAFF") && !role.equals("CUSTOMER")) {
            System.out.println("Role không hợp lệ!");
            return false;
        }

        User user = new User(username, password, role);
        return userDAO.register(user);
    }

    public User login(String username, String password) {

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println(" Không được để trống!");
            return null;
        }

        return userDAO.login(username, password);
    }

    private static List<User> users = new ArrayList<>(); // danh sách demo

    // Lấy User theo ID
    public static User getUserById(int userId) {
        return users.stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .orElse(null);
    }

    // Demo: thêm user
    public static void addUser(User user) {
        users.add(user);

    }

}