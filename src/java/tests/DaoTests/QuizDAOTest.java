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
    public void testGetQuizById() {
        UserDAO userDAO = new UserDAO();

        // This username must already exist in the database
        User user = userDAO.findByUsername("Tini");

        assertNotNull(user, "User should be found");
        assertEquals("Tini", user.getUsername());
        assertNotNull(user.getHashedPassword());
        assertNotNull(user.getHash());
        assertTrue(user.getId() > 0);
    }

    @Test
    public void testGetAllQuizzes() {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByUsername("nonexistent_user_xyz");

        assertNull(user, "User should not be found");
    }

    @Test
    public void testGetQuizById2() {
        QuizDao quizDao = new QuizDao();
        assertNotNull(quizDao.getQuizById(0), "Test should be found");
    }

}