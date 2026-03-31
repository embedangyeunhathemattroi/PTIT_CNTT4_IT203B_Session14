package util;

import util.HashUtil;

public class TestHash {
    public static void main(String[] args) {
        String password = "123456";
        String hashed = HashUtil.hashPassword(password);

        System.out.println("Hashed password: " + hashed);
    }
}