package util;

import java.util.Scanner;

public class InputValidate {
    public static int inputInteger(Scanner scanner) {
        while (true) {
            try {
                String line = scanner.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Nhập só nguyên hợp lệ.");
            }
        }
    }

    public static double inputDouble(Scanner scanner) {
        while (true) {
            try {
                String line = scanner.nextLine();
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Nhập só thực hợp lệ.");
            }
        }
    }

    public static String inputString(Scanner scanner) {
        while (true) {
            try {
                String line = scanner.nextLine();
                if (line == "" | line.trim().isEmpty())
                    throw new IllegalArgumentException();
                return line;
            } catch (IllegalArgumentException e) {
                System.out.println("Không được để trống.");
            }
        }
    }
}