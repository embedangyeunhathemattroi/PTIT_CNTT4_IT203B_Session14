package DAO;

import model.User;

public interface UserDAO {
    boolean register(User user);

    User login(String username, String password);
}