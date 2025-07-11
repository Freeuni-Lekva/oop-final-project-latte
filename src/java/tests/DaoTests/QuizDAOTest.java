package tests.DaoTests;

import dao.QuizDAO;
import dao.UserDAO;
import model.Quiz;
import model.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizDAOTest {
    @BeforeEach
    public void clearTable() {
        try (Connection conn = helpers.connector.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM Quizzes"); // clear before each test
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Could not clear table");
        }
    }

    @Test
    public void testCreateQuiz() {

        //User(String username, int id, String hashedPassword, String hash, Timestamp timestamp)
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        User user = new User("name2",2, "aa", "asa", timestamp);
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(user);
        Quiz quiz = new Quiz(0, "Sample Title", "Sample Description", 2, true, false, true, false);
        QuizDAO dao = new QuizDAO();

        boolean success = dao.createQuiz(quiz);

        assertTrue(success, "Quiz should be created");
        assertTrue(quiz.getId() > 0, "ID should be set after insert");
    }

    @Test
    public void testGetQuizById() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User user = new User("name42",42, "aa", "asa", timestamp);
        UserDAO userDAO = new UserDAO();
        userDAO.insertUser(user);
        QuizDAO dao = new QuizDAO();
        Quiz quiz = new Quiz(0, "Find Me", "Test", 42, false, true, false, true);
        dao.createQuiz(quiz);

        Quiz loaded = QuizDAO.getQuizById(quiz.getId());

        assertNotNull(loaded, "Quiz should be found by ID");
        assertEquals("Find Me", loaded.getTitle());
        assertEquals(42, loaded.getCreatorId());
    }

    @Test
    public void testGetAllQuizzes() {
        QuizDAO dao = new QuizDAO();
        dao.createQuiz(new Quiz(0, "Q1", "D1", 1, false, false, false, false));
        dao.createQuiz(new Quiz(0, "Q2", "D2", 2, true, true, true, true));

        List<Quiz> quizzes = dao.getAllQuizzes();

        assertEquals(2, quizzes.size(), "Should return 2 quizzes");
        assertEquals("Q1", quizzes.get(0).getTitle());
        assertEquals("Q2", quizzes.get(1).getTitle());
    }

}