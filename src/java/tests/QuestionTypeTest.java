package tests;

import model.QuestionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTypeTest {
    @Test
    public void testGetType() {
        assertEquals("question_response", QuestionType.question_response.getType());
        assertEquals("fill_in_the_blank", QuestionType.fill_in_the_blank.getType());
        assertEquals("multiple_choice", QuestionType.multiple_choice.getType());
        assertEquals("picture_response_questions", QuestionType.picture_response_questions.getType());
    }

    @Test
    public void testFromStringValidInputs() {
        assertEquals(QuestionType.question_response, QuestionType.fromString("QUESTION_RESPONSE"));
        assertEquals(QuestionType.fill_in_the_blank, QuestionType.fromString("fill_in_the_blank"));
        assertEquals(QuestionType.multiple_choice, QuestionType.fromString("Multiple_Choice"));
        assertEquals(QuestionType.picture_response_questions, QuestionType.fromString("picture_response_questions"));
    }

    @Test
    public void testFromStringInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuestionType.fromString("not_a_real_type");
        });
    }
}