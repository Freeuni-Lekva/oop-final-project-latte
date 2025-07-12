package dao;

import model.Quiz;
import helpers.connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {

    public boolean createQuiz(Quiz quiz) {
        String sql = "INSERT INTO Quizzes (title, description, creator_id, is_random_order, is_one_page, is_immediate_correction, is_practice) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, quiz.getTitle());
            stmt.setString(2, quiz.getDescription());
            stmt.setInt(3, quiz.getCreatorId());
            stmt.setBoolean(4, quiz.isRandomOrdered());
            stmt.setBoolean(5, quiz.isOnePage());
            stmt.setBoolean(6, quiz.isImmediateCorrection());
            stmt.setBoolean(7, quiz.isPractice());

            int affected = stmt.executeUpdate();
            if (affected == 0) return false;

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                quiz.setId(keys.getInt(1));
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Quiz getQuizById(int id) {
        String sql = "SELECT * FROM Quizzes WHERE id = ?";
        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Quiz(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("creator_id"),
                        rs.getBoolean("is_random_order"),
                        rs.getBoolean("is_one_page"),
                        rs.getBoolean("is_immediate_correction"),
                        rs.getBoolean("is_practice")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Quiz> getPopularQuizzes(){
        List<Quiz> popularQuizzes = new ArrayList<>();
        String sql = "SELECT q.*, COUNT(a.id) as attempt_count FROM Quizzes q LEFT JOIN Attempts a ON q.id = a.quiz_id GROUP BY q.id ORDER BY attempt_count DESC LIMIT 10";
        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){
                popularQuizzes.add(new Quiz(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("creator_id"),
                        rs.getBoolean("is_random_order"),
                        rs.getBoolean("is_one_page"),
                        rs.getBoolean("is_immediate_correction"),
                        rs.getBoolean("is_practice")
                ));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return popularQuizzes;
    }

    public List<Quiz> getRecentQuizzes(){
        List<Quiz> recentQuizzes = new ArrayList<>();
        String sql = "SELECT * FROM Quizzes ORDER BY created_at DESC LIMIT 10";
        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){
                recentQuizzes.add(new Quiz(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("creator_id"),
                        rs.getBoolean("is_random_order"),
                        rs.getBoolean("is_one_page"),
                        rs.getBoolean("is_immediate_correction"),
                        rs.getBoolean("is_practice")
                ));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return recentQuizzes;
    }


    public List<Quiz> getAllQuizzes() {
        List<Quiz> list = new ArrayList<>();
        String sql = "SELECT * FROM Quizzes";

        try (Connection conn = connector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Quiz quiz = new Quiz(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("creator_id"),
                        rs.getBoolean("is_random_order"),
                        rs.getBoolean("is_one_page"),
                        rs.getBoolean("is_immediate_correction"),
                        rs.getBoolean("is_practice")
                );
                list.add(quiz);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Quiz> getUserCreatedQuizzes(int id){
        List<Quiz> list = new ArrayList<>();
        String sql = "SELECT * FROM Quizzes WHERE id = ? ORDER BY id DESC LIMIT 10";

        try (Connection conn = connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Quiz quiz = new Quiz(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getInt("creator_id"),
                            rs.getBoolean("is_random_order"),
                            rs.getBoolean("is_one_page"),
                            rs.getBoolean("is_immediate_correction"),
                            rs.getBoolean("is_practice")
                    );
                    list.add(quiz);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
