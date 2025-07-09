package tests;

import model.MessageType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTypeTest {
    @Test
    public void testFromStringValidValues() {
        assertEquals(MessageType.friend_request, MessageType.fromString("FRIEND_REQUEST"));
        assertEquals(MessageType.challenge, MessageType.fromString("challenge"));
        assertEquals(MessageType.text, MessageType.fromString("Text"));
    }

    @Test
    public void testGetType() {
        assertEquals("friend_request", MessageType.friend_request.getType());
        assertEquals("challenge", MessageType.challenge.getType());
        assertEquals("text", MessageType.text.getType());
    }

    @Test
    public void testFromStringInvalidValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            MessageType.fromString("invalid_type");
        });
    }


}