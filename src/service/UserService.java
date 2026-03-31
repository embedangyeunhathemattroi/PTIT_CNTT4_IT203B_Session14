package service;

import java.util.Scanner;

import DAO.UserDAO;
import model.User;
import util.InputValidate;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public boolean register(Scanner sc) {
        while (true) {
            System.out.print("Nhập username: ");
            String username = InputValidate.inputString(sc);
            System.out.print("Nhập password: ");
            String password = InputValidate.inputString(sc);

            if (password.length() < 6) {
                System.out.println(" Mật khẩu phải >= 6 ký tự");
                return false;
            }
            User user = new User(username, password, "CUSTOMER");
            return userDAO.register(user);
        }
    }

    public User login(Scanner sc) {
        while (true) {
            System.out.print("Nhập username: ");
            String username = InputValidate.inputString(sc);
            System.out.print("Nhập password: ");
            String password = InputValidate.inputString(sc);
            User user = userDAO.login(username, password);
            if (user != null)
                return user;
            System.out.println("Đăng nhập thất bại! Sai username hoặc password.");
        }
    }

    public User getUserById(int id) {
        return userDAO.findById(id);
    }
}
