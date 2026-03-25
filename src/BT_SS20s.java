import java.sql.*;

public class BT_SS20s {

    private static final String URL = "jdbc:mysql://localhost:3306/BT_SS20";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public void transfer(String senderId, String receiverId, double amount) {

        String checkSQL = "SELECT balance FROM Accounts WHERE AccountId = ?";
        String resultSQL = "SELECT * FROM Accounts WHERE AccountId IN (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            conn.setAutoCommit(false);

            try (
                    PreparedStatement checkStmt = conn.prepareStatement(checkSQL);
                    CallableStatement callStmt = conn.prepareCall("{CALL sp_UpdateBalance(?, ?)}");
                    PreparedStatement resultStmt = conn.prepareStatement(resultSQL)) {

                checkStmt.setString(1, senderId);
                ResultSet rs = checkStmt.executeQuery();

                if (!rs.next()) {
                    throw new SQLException("Tai khoan nguoi gui khong ton tai");
                }

                double balance = rs.getDouble("balance");

                if (balance < amount) {
                    throw new SQLException("Không du tien trong tai khoan nguoi gui");
                }

                callStmt.setString(1, senderId);
                callStmt.setDouble(2, -amount);
                callStmt.execute();
                callStmt.setString(1, receiverId);
                callStmt.setDouble(2, amount);
                callStmt.execute();
                conn.commit();
                System.out.println("Chuyen khoan tien thanh cong!");

                resultStmt.setString(1, senderId);
                resultStmt.setString(2, receiverId);

                ResultSet result = resultStmt.executeQuery();
                while (result.next()) {
                    System.out.println(
                            result.getString("AccountId") + " | " +
                                    result.getString("FullName") + " | " +
                                    result.getDouble("balance"));
                }

            } catch (Exception e) {
                conn.rollback();
                System.out.println("Loi -> Rollback!");
                System.out.println("Chi tiết: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BT_SS20s bt = new BT_SS20s();
        bt.transfer("A001", "A002", 500);
    }
}