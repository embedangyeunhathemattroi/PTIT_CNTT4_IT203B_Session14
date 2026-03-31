package service;

import model.PC;
import java.util.ArrayList;
import java.util.List;

public class PCService {

    private List<PC> pcs; // khai báo biến pcs

    public PCService() {
        pcs = new ArrayList<>();
        pcs.add(new PC(1, "PC Asus", 1, "i7, RTX 2060, 16GB RAM", 15000, "AVAILABLE"));
        pcs.add(new PC(2, "Mac", 1, "M1, 16GB", 200000, "AVAILABLE"));

    }

    // Lấy danh sách tất cả máy
    public List<PC> getAllPCs() {
        return pcs;
    }

    // Lấy danh sách máy trống
    public List<PC> getAvailablePCs() {
        List<PC> danhSach = new ArrayList<>();
        for (PC pc : pcs) {
            if ("AVAILABLE".equalsIgnoreCase(pc.getStatus())) {
                danhSach.add(pc);
            }
        }
        return danhSach;
    }

    // Lấy giá theo ID
    public double getPrice(int pcId) {
        for (PC pc : pcs) {
            if (pc.getId() == pcId) {
                return pc.getPrice();
            }
        }
        System.out.println(" Không tìm thấy PC ID " + pcId + ", mặc định giá = 0");
        return 0;
    }

    // Cập nhật trạng thái máy
    public void updateStatus(int pcId, String status) {
        for (PC pc : pcs) {
            if (pc.getId() == pcId) {
                pc.setStatus(status);
                System.out.println("Máy " + pcId + " đã được cập nhật trạng thái: " + status);
                return;
            }
        }
        System.out.println("Không tìm thấy máy với ID: " + pcId);
    }

    // Thêm máy mới
    public boolean addPC(String name, int categoryId, String config, double price, String status) {
        int newId = pcs.size() + 1; // tự tăng id
        PC pc = new PC(newId, name, categoryId, config, price, status);
        pcs.add(pc);
        return true;
    }
}