package service;

import model.PC;
import java.util.ArrayList;
import java.util.List;

import DAO.PCDAO;

public class PCService {

    private static PCDAO pcDAO = new PCDAO();
    // Lấy danh sách tất cả máy
    public List<PC> getAllPCs() {
        return pcDAO.findAll();
    }

    public PC getPCById(int id) {
        return pcDAO.findById(id);
    }

    // Lấy danh sách máy trống
    public List<PC> getAvailablePCs() {
        return pcDAO.findAvailable();
    }

    public boolean deletePC(int id) {
        return pcDAO.delete(id);
    }

    public boolean updatePC(int id, String name, String config, double price) {
        try {
            return pcDAO.update(id, name, config, price);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật trạng thái máy
    public void updateStatus(int pcId, String status) {
        // not implemented yet
    }

    // Thêm máy mới
    public boolean addPC(String name, int categoryId, String config, double price, String status) {
        return pcDAO.insert(new PC(name, categoryId, config, price, status));
    }

    public boolean isPCNameExists(String name) {
        return pcDAO.isPCNameExists(name);
    }
}
