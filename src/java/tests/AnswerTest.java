package tests;

import model.Answer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {
    @Test
    public void testFullConstructor() {
        Answer answer = new Answer(1, 101, "42", true);

        assertEquals(1, answer.getId());
        assertEquals(101, answer.getQuestionId());
        assertEquals("42", answer.getText());
        assertTrue(answer.isOrdered());
    }

    @Test
    public void testConstructorWithoutId() {
        Answer answer = new Answer(202, "Blue", false);

        assertEquals(0, answer.getId());
        assertEquals(202, answer.getQuestionId());
        assertEquals("Blue", answer.getText());
        assertFalse(answer.isOrdered());
    }

    @Test
    public void testSettersAndGetters() {
        Answer answer = new Answer(0, "", false);

        answer.setId(10);
        answer.setQuestionId(999);
        answer.setText("Correct answer");
        answer.setOrdered(true);

        assertEquals(10, answer.getId());
        assertEquals(999, answer.getQuestionId());
        assertEquals("Correct answer", answer.getText());
        assertTrue(answer.isOrdered());
    }

    @Test
    public void testToString() {
        Answer answer = new Answer(3, 77, "Yes", false);
        String str = answer.toString();

        assertTrue(str.contains("id=3"));
        assertTrue(str.contains("questionId=77"));
        assertTrue(str.contains("text='Yes'"));
        assertTrue(str.contains("isOrdered=false"));
    }
}