package helpers;

import java.sql.*;

public class connector {
    private static final String URL = "jdbc:mysql://localhost:3306/quizWebsite?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public boolean dropQuizzesTable() {
        String sql = "DROP TABLE IF EXISTS Quizzes";

        try (Connection conn = connector.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
