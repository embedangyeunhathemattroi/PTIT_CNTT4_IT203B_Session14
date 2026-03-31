package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidate {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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

    public static String inputString(Scanner scanner, boolean allowEmpty) {
        while (true) {
            try {
                String line = scanner.nextLine();
                if (!allowEmpty && (line == "" || line.trim().isEmpty()))
                    throw new IllegalArgumentException();
                return line;
            } catch (IllegalArgumentException e) {
                System.out.println("Không được để trống.");
            }
        }
    }

    public static LocalDateTime inputDateTime(Scanner sc) {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println(" Định dạng thời gian không đúng, vui lòng nhập lại (yyyy-MM-dd HH:mm)");
            }
        }
    }
}
