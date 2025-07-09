package tests;

import model.Quiz;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {
    @Test
    public void ConstructorsAndGetters() {
        Quiz quiz = new Quiz("id", "title", "description", "creatorId", true, true, true, true);
        assertEquals("id", quiz.getId());
        assertEquals("title", quiz.getTitle());
        assertEquals("description", quiz.getDescription());
        assertEquals("creatorId", quiz.getCreatorId());
        assertTrue(quiz.isImmediateCorrection());
        assertTrue(quiz.isOnePage());
        assertTrue(quiz.isRandomOrdered());
        assertTrue(quiz.isPractice());
    }
    @Test
    public void settersAndGetters() {
        Quiz quiz = new Quiz();
        quiz.setId("id");
        quiz.setTitle("title");
        quiz.setDescription("description");
        quiz.setCreatorId("creatorId");
        quiz.setImmediateCorrection(true);
        quiz.setOnePage(true);
        quiz.setRandomOrdered(true);
        quiz.setPractice(true);
        assertEquals("id", quiz.getId());
        assertEquals("title", quiz.getTitle());
        assertEquals("description", quiz.getDescription());
        assertEquals("creatorId", quiz.getCreatorId());
        assertTrue(quiz.isImmediateCorrection());
        assertTrue(quiz.isOnePage());
        assertTrue(quiz.isRandomOrdered());
        assertTrue(quiz.isPractice());
    }

    @Test
    public void testToString() {
        Quiz quiz = new Quiz("1", "name", "text", "2", true, true, true, true);
        assertTrue(quiz.toString().contains("1"));
        assertTrue(quiz.toString().contains("name"));
        assertTrue(quiz.toString().contains("text"));
        assertTrue(quiz.toString().contains("2"));
    }
}