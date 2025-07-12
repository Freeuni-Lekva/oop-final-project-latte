package dao;

import model.Attemps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttempsDao {
    private final Connection connection;

    public AttempsDao(Connection connection) {
        this.connection = connection;
    }

    public void addAttemp(Attemps attemp) throws SQLException {
        String sql = "INSERT INTO Attempts (user_id, quiz_id, score, duration_seconds, taken_at) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, attemp.getUserId());
            stmt.setInt(2, attemp.getQuizId());
            stmt.setDouble(3, attemp.getScore());
            stmt.setInt(4, attemp.getDuration());
            stmt.setTimestamp(5, attemp.getTaken());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    attemp.setId(keys.getInt(1));
                }
            }
        }
    }

    public Attemps getAttempById(int id) throws SQLException {
        String sql = "SELECT * FROM Attempts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractAttempFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Attemps> getAttemptsByUserId(int userId) throws SQLException {
        List<Attemps> list = new ArrayList<>();
        String sql = "SELECT * FROM Attempts WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(extractAttempFromResultSet(rs));
                }
            }
        }
        return list;
    }

    public List<Attemps> getAttemptsByQuizId(int quizId) throws SQLException {
        List<Attemps> list = new ArrayList<>();
        String sql = "SELECT * FROM Attempts WHERE quiz_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quizId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(extractAttempFromResultSet(rs));
                }
            }
        }
        return list;
    }

    private Attemps extractAttempFromResultSet(ResultSet rs) throws SQLException {
        return new Attemps(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("quiz_id"),
                rs.getDouble("score"),
                rs.getInt("duration_seconds"),
                rs.getTimestamp("taken_at")
        );
    }
}
