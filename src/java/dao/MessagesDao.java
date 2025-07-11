package dao;

import model.MessageType;
import model.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesDao {
    private final Connection connection;

    public MessagesDao(Connection connection) {
        this.connection = connection;
    }

    public void addMessage(Messages message) throws SQLException {
        String sql = "INSERT INTO Messages (sender_id, receiver_id, type, content, related_quiz_id, sent_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, message.getSenderId());
            stmt.setInt(2, message.getReceiverId());
            stmt.setString(3, message.getMessageType().getType()); // enum stored as lowercase string
            stmt.setString(4, message.getMessage());
            if (message.getQuizId() > 0) {
                stmt.setInt(5, message.getQuizId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.setTimestamp(6, message.getSentAt());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    message.setId(keys.getInt(1));
                }
            }
        }
    }

    public Messages getMessageById(int id) throws SQLException {
        String sql = "SELECT * FROM Messages WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractMessageFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Messages> getMessagesBySenderId(int senderId) throws SQLException {
        List<Messages> list = new ArrayList<>();
        String sql = "SELECT * FROM Messages WHERE sender_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, senderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(extractMessageFromResultSet(rs));
                }
            }
        }
        return list;
    }

    public List<Messages> getMessagesByReceiverId(int receiverId) throws SQLException {
        List<Messages> list = new ArrayList<>();
        String sql = "SELECT * FROM Messages WHERE receiver_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, receiverId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(extractMessageFromResultSet(rs));
                }
            }
        }
        return list;
    }

    public void updateMessage(Messages message) throws SQLException {
        String sql = "UPDATE Messages SET sender_id = ?, receiver_id = ?, type = ?, content = ?, related_quiz_id = ?, sent_at = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, message.getSenderId());
            stmt.setInt(2, message.getReceiverId());
            stmt.setString(3, message.getMessageType().getType());
            stmt.setString(4, message.getMessage());
            if (message.getQuizId() > 0) {
                stmt.setInt(5, message.getQuizId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.setTimestamp(6, message.getSentAt());
            stmt.setInt(7, message.getId());

            stmt.executeUpdate();
        }
    }

    public void deleteMessage(int id) throws SQLException {
        String sql = "DELETE FROM Messages WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Messages extractMessageFromResultSet(ResultSet rs) throws SQLException {
        return new Messages(
                rs.getInt("id"),
                rs.getInt("sender_id"),
                rs.getInt("receiver_id"),
                MessageType.valueOf(rs.getString("type").toLowerCase()),
                rs.getString("content"),
                rs.getInt("related_quiz_id"),
                rs.getTimestamp("sent_at")
        );
    }
}
