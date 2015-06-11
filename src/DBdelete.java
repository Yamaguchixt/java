import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBdelete {
    public static void main(String[] args) {
        System.out.println("input id that you want to delete");
        int id = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            id = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            String sql = "DELETE FROM users WHERE id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            int count = pst.executeUpdate();
            System.out.println(count + "件の削除に成功しました");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}