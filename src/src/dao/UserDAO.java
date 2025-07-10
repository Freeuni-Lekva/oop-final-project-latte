package dao;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import helpers.connector;

public class UserDAO {
    private static final String FIND_BY_USERNAME_SQL =
            "SELECT username, id, password_hash, hash, join_date " +
                    "FROM Users WHERE username = ?";

    private static final String INSERT_USER_SQL =
            "INSERT INTO Users (username, password_hash, hash) VALUES (?, ?, ?)";


    public User findByUsername(String username) {
        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_BY_USERNAME_SQL)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String userName = rs.getString("username");
                    int id = rs.getInt("id");
                    String hashedPasword = rs.getString("password_hash");
                    String hash = rs.getString("hash");
                    java.sql.Timestamp ts = rs.getTimestamp("join_date");
                    return new User(userName, id, hashedPasword, hash, ts);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertUser(User user) {
        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_USER_SQL)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getHashedPassword());
            stmt.setString(3, user.getHash());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}




