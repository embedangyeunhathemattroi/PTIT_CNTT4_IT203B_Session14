package service;

import model.PC;
import model.Wallet;

public class WalletService {

    // Trừ tiền theo giờ chơi
    public boolean payForSession(Wallet wallet, PC pc, int hoursPlayed) {
        double cost = pc.getPricePerHour() * hoursPlayed;
        if (wallet.withdraw(cost)) {
            System.out.println("Thanh toán thành công: " + cost);
            return true;
        } else {
            System.out.println("Không đủ số dư trong ví.");
            return false;
        }
    }
}