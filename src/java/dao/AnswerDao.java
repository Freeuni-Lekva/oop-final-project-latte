package dao;

import model.Answer;
import model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDao {
    private final Connection conn;

    public AnswerDao(Connection conn) {
        this.conn = conn;
    }
    public void insertAnswer(Answer answer) throws SQLException {
        String sql = "INSERT INTO Answers (question_id, text, is_ordered) VALUES (?, ?, ?)";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, answer.getQuestionId());
            preparedStatement.setString(2, answer.getText());
            preparedStatement.setBoolean(3, answer.isOrdered());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    answer.setId(resultSet.getInt(1));
                }
            }
        }
    }
    public List<Answer> getAnswerByQuestionId(int questionId) throws SQLException {
        String sql = "SELECT * FROM Answers WHERE question_id = ?";
        List<Answer> answers = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, questionId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    answers.add(new Answer(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3)));
                }
            }
        }
        return answers;
    }
    public Answer getAnswerById(int id) throws SQLException {
        String sql = "SELECT * FROM Answers WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Answer(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3));
                }
            }
        }
        return null;
    }

    public void updateAnswer(Answer answer) throws SQLException {
        String sql = "UPDATE Answers SET text = ?, is_ordered = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, answer.getText());
            preparedStatement.setBoolean(2, answer.isOrdered());
            preparedStatement.setInt(3, answer.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteAnswer(Answer answer) throws SQLException {
        String sql = "DELETE FROM Answers WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, answer.getId());
            preparedStatement.executeUpdate();
        }
    }
    public boolean isAnswerCorrect(int questionId, String userAnswer) {
        String sql = "SELECT text FROM Answers WHERE question_id = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, questionId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("text").equalsIgnoreCase(userAnswer.trim())) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getCorrectAnswersByQuestionId(int questionId) {
        List<String> correctAnswers = new ArrayList<>();
        String sql = "SELECT text FROM Answers WHERE question_id = ? ";

        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, questionId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                correctAnswers.add(rs.getString("text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return correctAnswers;
    }
    public List<String> getCorrectAnswersByQuestionIdOrdered(int questionId) {
        List<String> correctAnswers = new ArrayList<>();
        String sql = "SELECT text FROM Answers WHERE question_id = ? ORDER BY position ASC";

        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, questionId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                correctAnswers.add(rs.getString("text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return correctAnswers;
    }


}
