package dao;

import model.Question;
import model.QuestionType;
import helpers.connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    public boolean createQuestion(Question question) {
        String sql = "INSERT INTO Questions (quiz_id, type, prompt, answer, position) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, question.getQuizId());
            stmt.setString(2, question.getQuestionType().toString());
            stmt.setString(3, question.getQuestion());
            stmt.setString(4, question.getAnswer());
            stmt.setInt(5, 0);

            int affected = stmt.executeUpdate();
            if (affected == 0) return false;

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                question.setId(rs.getInt(1));
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean addQuestion(Question question) {
        String sql = "INSERT INTO Questions (quiz_id, type, prompt, answer) VALUES (?, ?, ?, ?)";

        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, question.getQuizId());
            stmt.setString(2, question.getQuestionType().toString());
            stmt.setString(3, question.getQuestion());
            stmt.setString(4, question.getAnswer());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Question> getQuestionsByQuizId(int quizId) {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM Questions WHERE quiz_id = ?";

        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Question q = new Question(
                        rs.getInt("id"),
                        rs.getInt("quiz_id"),
                        QuestionType.valueOf(rs.getString("type")),
                        rs.getString("prompt"),
                        rs.getString("answer")
                );
                list.add(q);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
