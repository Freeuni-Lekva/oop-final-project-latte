package tests;

import model.Attemps;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Timestamp;
class AttempsTest {
    @Test
    public void testFullConstructor() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Attemps attempt = new Attemps(1, 2, 3, 95.5, 120, now);

        assertEquals(1, attempt.getId());
        assertEquals(2, attempt.getUserId());
        assertEquals(3, attempt.getQuizId());
        assertEquals(95.5, attempt.getScore(), 0.001);
        assertEquals(120, attempt.getDuration());
        assertEquals(now, attempt.getTaken());
    }

    @Test
    public void testConstructorWithoutId() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Attemps attempt = new Attemps(10, 20, 87.3, 300, now);

        assertEquals(0, attempt.getId()); // default
        assertEquals(10, attempt.getUserId());
        assertEquals(20, attempt.getQuizId());
        assertEquals(87.3, attempt.getScore(), 0.001);
        assertEquals(300, attempt.getDuration());
        assertEquals(now, attempt.getTaken());
    }

    @Test
    public void testSettersAndGetters() {
        Attemps attempt = new Attemps();
        Timestamp timestamp = Timestamp.valueOf("2025-01-01 12:00:00");

        attempt.setId(5);
        attempt.setUserId(42);
        attempt.setQuizId(99);
        attempt.setScore(72.0);
        attempt.setDuration(180);
        attempt.setTaken(timestamp);

        assertEquals(5, attempt.getId());
        assertEquals(42, attempt.getUserId());
        assertEquals(99, attempt.getQuizId());
        assertEquals(72.0, attempt.getScore(), 0.001);
        assertEquals(180, attempt.getDuration());
        assertEquals(timestamp, attempt.getTaken());
    }

    @Test
    public void testToStringContainsAllFields() {
        Timestamp timestamp = Timestamp.valueOf("2025-01-01 12:00:00");
        Attemps attempt = new Attemps(1, 2, 3, 88.0, 150, timestamp);

        String str = attempt.toString();
        assertTrue(str.contains("id=1"));
        assertTrue(str.contains("userId=2"));
        assertTrue(str.contains("quizId=3"));
        assertTrue(str.contains("score=88.0"));
        assertTrue(str.contains("duration=150"));
        assertTrue(str.contains("taken=" + timestamp.toString()));
    }

}