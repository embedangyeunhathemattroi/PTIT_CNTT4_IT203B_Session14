package DAO;

import model.PC;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PCDAO {

    public boolean insert(PC pc) {
        String sql = "INSERT INTO pcs (name, category_id, config, price, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pc.getName());
            ps.setInt(2, pc.getCategoryId());
            ps.setString(3, pc.getConfig());
            ps.setDouble(4, pc.getPrice());
            ps.setString(5, pc.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PC> findAll() {
        List<PC> pcs = new ArrayList<>();
        String sql = "SELECT * FROM pcs";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PC pc = new PC(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("category_id"),
                        rs.getString("config"),
                        rs.getDouble("price"),
                        rs.getString("status"));
                pcs.add(pc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pcs;
    }

    public boolean update(int id, String name, String config, double price) {
        String sql = "UPDATE pcs SET name=?, category_id=?, config=?, price=? WHERE id=?";

        try {
            try (Connection conn = DBConnection.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, name);
                ps.setInt(2, id);
                ps.setString(3, config);
                ps.setDouble(4, price);
                ps.setInt(5, id);

                return ps.executeUpdate() > 0;

            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM pcs WHERE id=?";

        try {
            try (Connection conn = DBConnection.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, id);
                return ps.executeUpdate() > 0;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public PC findById(int id) {
        String sql = "SELECT * FROM pcs WHERE id=?";
        PC pc = null;

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pc = mapResultSet(rs);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pc;
    }

    public List<PC> findAvailable() {
        List<PC> pcs = new ArrayList<>();
        String sql = "SELECT * FROM pcs WHERE status='AVAILABLE'";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pcs.add(mapResultSet(rs));
            }

            // đóng tài nguyên
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) { // bắt Exception chung
            e.printStackTrace();
        }

        return pcs;
    }

    public boolean isPCNameExists(String name) {
        String sql = "SELECT * FROM pcs WHERE name = ?;";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

            // đóng tài nguyên
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) { // bắt Exception chung
            e.printStackTrace();
        }

        return false;
    }

    private PC mapResultSet(ResultSet rs) throws SQLException {
        PC pc = new PC();
        pc.setId(rs.getInt("id"));
        pc.setName(rs.getString("name"));
        pc.setCategoryId(rs.getInt("category_id"));
        pc.setConfig(rs.getString("config"));
        pc.setPrice(rs.getDouble("price"));
        pc.setStatus(rs.getString("status"));
        return pc;
    }
}
