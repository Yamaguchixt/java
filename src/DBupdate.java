

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBupdate {
    public static void main(String[] args) throws SQLException {

        System.out.println("ドライバ読み込み開始");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("success reading driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/playsample", "root", "");
            String sql = "INSERT INTO users (id,name,belong) values(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, 1);
            pst.setString(2, "yuta");
            pst.setString(3, "HAL");
            int count = pst.executeUpdate();
            System.out.println(count + "件更新しました");
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}