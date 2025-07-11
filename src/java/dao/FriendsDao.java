package dao;

import model.Friend;
import model.StatusType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendsDao {
    private final Connection connection;

    public FriendsDao(Connection connection) {
        this.connection = connection;
    }

    public void addFriend(Friend friend) throws SQLException {
        String sql = "INSERT INTO Friends (user_id, friend_id, status, requested_at) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, friend.getUserId());
            stmt.setInt(2, friend.getFriendId());
            stmt.setString(3, friend.getStatus().getType()); // store enum as lowercase string
            stmt.setTimestamp(4, friend.getTimestamp());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    friend.setId(keys.getInt(1));
                }
            }
        }
    }

    public Friend getFriendById(int id) throws SQLException {
        String sql = "SELECT * FROM Friends WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractFriendFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Friend> getFriendsByUserId(int userId) throws SQLException {
        List<Friend> friends = new ArrayList<>();
        String sql = "SELECT * FROM Friends WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    friends.add(extractFriendFromResultSet(rs));
                }
            }
        }
        return friends;
    }

    public void updateStatus(int friendId, StatusType newStatus) throws SQLException {
        String sql = "UPDATE Friends SET status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newStatus.getType());
            stmt.setInt(2, friendId);

            stmt.executeUpdate();
        }
    }

    public void deleteFriend(int id) throws SQLException {
        String sql = "DELETE FROM Friends WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Friend extractFriendFromResultSet(ResultSet rs) throws SQLException {
        return new Friend(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("friend_id"),
                StatusType.valueOf(rs.getString("status").toLowerCase()),
                rs.getTimestamp("requested_at")
        );
    }
}
