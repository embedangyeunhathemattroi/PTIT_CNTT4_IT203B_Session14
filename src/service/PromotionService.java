package service;

import java.time.LocalTime;

public class PromotionService {

    // Mã giảm giá
    public double applyDiscountCode(String code, double total) {
        if (code == null)
            return total;

        if (code.equalsIgnoreCase("SALE10")) {
            return total * 0.9;
        }

        return total;
    }

    // Khung giờ vàng
    public double applyHappyHour(double total) {
        LocalTime now = LocalTime.now();

        if (now.isAfter(LocalTime.of(22, 0))) {
            return total * 0.8; // giảm 20%
        }

        return total;
    }

    // Gộp cả 2
    public double applyAll(String code, double total) {
        total = applyDiscountCode(code, total);
        total = applyHappyHour(total);
        return total;
    }
}