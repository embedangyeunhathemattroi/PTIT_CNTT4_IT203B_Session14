package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import model.User;
import util.InputValidate;

public class UserService {

    private UserDAO userDAO = new UserDAOImpl();

    public boolean register(Scanner sc) {
        while (true) {
            System.out.println("Nhập username: ");
            String username = InputValidate.inputString(sc);
            System.out.println("Nhập password: ");
            String password = InputValidate.inputString(sc);

            if (password.length() < 6) {
                System.out.println(" Mật khẩu phải >= 6 ký tự");
                return false;
            }
            User user = new User(username, password, "CUSTOMER");
            return userDAO.register(user);
        }
    }

    // REGISTER CÓ ROLE (ADMIN / STAFF / CUSTOMER)
    // public boolean register(String username, String password, String role) {

    // if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
    // System.out.println(" Không được để trống!");
    // return false;
    // }

    // if (password.length() < 6) {
    // System.out.println(" Mật khẩu phải >= 6 ký tự");
    // return false;
    // }
    // role = role.toUpperCase();
    // if (!role.equals("ADMIN") && !role.equals("STAFF") &&
    // !role.equals("CUSTOMER")) {
    // System.out.println("Role không hợp lệ!");
    // return false;
    // }

    // User user = new User(username, password, role);
    // return userDAO.register(user);
    // }

    public User login(Scanner sc) {
        while (true) {
            System.out.println("Nhập username: ");
            String username = InputValidate.inputString(sc);
            System.out.println("Nhập password: ");
            String password = InputValidate.inputString(sc);
            User user = userDAO.login(username, password);
            if (user != null)
                return user;
        }
    }

}