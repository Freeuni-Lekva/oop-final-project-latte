package tests;

import model.Question;
import model.QuestionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    @Test
    public void testFullConstructor() {
        Question q = new Question(1, 101, QuestionType.multiple_choice, "What is 2+2?", 4);

        assertEquals(1, q.getId());
        assertEquals(101, q.getQuizId());
        assertEquals(QuestionType.multiple_choice, q.getQuestionType());
        assertEquals("What is 2+2?", q.getQuestion());
        assertEquals(4, q.getPosition());
    }

    @Test
    public void testConstructorWithoutId() {
        Question q = new Question(101, QuestionType.fill_in_the_blank, "Univercity name is ___", 8);

        assertEquals(0, q.getId()); // default int
        assertEquals(101, q.getQuizId());
        assertEquals(QuestionType.fill_in_the_blank, q.getQuestionType());
        assertEquals("Univercity name is ___", q.getQuestion());
        assertEquals(8, q.getPosition());
    }

    @Test
    public void testSettersAndGetters() {
        Question q = new Question(0, 0, null, "", 0);

        q.setId(5);
        q.setQuizId(200);
        q.setQuestionType(QuestionType.question_response);
        q.setQuestion("OOP");
        q.setPosition(0);

        assertEquals(5, q.getId());
        assertEquals(200, q.getQuizId());
        assertEquals(QuestionType.question_response, q.getQuestionType());
        assertEquals("OOP", q.getQuestion());
        assertEquals(0, q.getPosition());
    }

    @Test
    public void testToString() {
        Question q = new Question(7, 303, QuestionType.picture_response_questions, "What's shown in the image?", 5);

        String output = q.toString();

        assertTrue(output.contains("id=7"));
        assertTrue(output.contains("quizId=303"));
        assertTrue(output.contains("questionType=picture_response_questions"));
        assertTrue(output.contains("question='What's shown in the image?'"));
        assertTrue(output.contains("answer=5"));
    }

}