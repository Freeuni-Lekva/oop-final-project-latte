package tests;

import model.MessageType;
import model.Messages;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class MessagesTest {
    @Test
    public void testFullConstructor() {
        Timestamp ts = Timestamp.valueOf("2025-01-01 10:30:00");
        Messages message = new Messages(
                1,         // id
                100,       // senderId
                200,       // receiverId
                MessageType.challenge,
                "You have been challenged!",
                5,         // quizId
                ts
        );

        assertEquals(1, message.getId());
        assertEquals(100, message.getSenderId());
        assertEquals(200, message.getReceiverId());
        assertEquals(MessageType.challenge, message.getMessageType());
        assertEquals("You have been challenged!", message.getMessage());
        assertEquals(5, message.getQuizId());
        assertEquals(ts, message.getSentAt());
    }

    @Test
    public void testConstructorWithoutId() {
        Timestamp ts = Timestamp.valueOf("2025-01-02 12:00:00");
        Messages message = new Messages(
                300,
                400,
                MessageType.text,
                "Don't forget the test tomorrow.",
                10,
                ts
        );

        assertEquals(0, message.getId());  // default
        assertEquals(300, message.getSenderId());
        assertEquals(400, message.getReceiverId());
        assertEquals(MessageType.text, message.getMessageType());
        assertEquals("Don't forget the test tomorrow.", message.getMessage());
        assertEquals(10, message.getQuizId());
        assertEquals(ts, message.getSentAt());
    }

    @Test
    public void testSettersAndGetters() {
        Messages message = new Messages();
        Timestamp ts = Timestamp.valueOf("2025-01-03 08:00:00");

        message.setId(7);
        message.setSenderId(10);
        message.setReceiverId(20);
        message.setMessageType(MessageType.friend_request);
        message.setMessage("Wanna be friends?");
        message.setQuizId(0);
        message.setSentAt(ts);

        assertEquals(7, message.getId());
        assertEquals(10, message.getSenderId());
        assertEquals(20, message.getReceiverId());
        assertEquals(MessageType.friend_request, message.getMessageType());
        assertEquals("Wanna be friends?", message.getMessage());
        assertEquals(0, message.getQuizId());
        assertEquals(ts, message.getSentAt());
    }

    @Test
    public void testToStringContainsAllFields() {
        Timestamp ts = Timestamp.valueOf("2025-01-04 15:45:00");
        Messages message = new Messages(1, 2, 3, MessageType.text, "Hello", 8, ts);

        String result = message.toString();
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("senderId=2"));
        assertTrue(result.contains("receiverId=3"));
        assertTrue(result.contains("messageType=text"));
        assertTrue(result.contains("message='Hello'"));
        assertTrue(result.contains("quizId=8"));
        assertTrue(result.contains("sentAt=" + ts.toString()));
    }

}