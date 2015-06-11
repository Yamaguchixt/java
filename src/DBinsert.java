import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//test

public class DBinsert {
    public static void main(String[] args) throws SQLException {


        int id = 0;
        String name = "";
        String belong = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("ユーザデータを追加します\n");
            System.out.println("input user ID\n");
            id = Integer.parseInt(br.readLine());

            System.out.println("input user name\n");
            name = br.readLine();
            System.out.println("input user belong");
            belong = br.readLine();
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
            String sql = "INSERT INTO users value(?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, belong);

            int count = pst.executeUpdate();
            System.out.println(count + "件 成功しました\n");

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