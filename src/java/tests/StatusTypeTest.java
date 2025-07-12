package tests;

import model.StatusType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTypeTest {
    @Test
    public void testFromStringValidValues() {
        assertEquals(StatusType.waiting, StatusType.fromString("WAITING"));
        assertEquals(StatusType.accepted, StatusType.fromString("accepted"));
        assertEquals(StatusType.rejected, StatusType.fromString("ReJeCtEd"));
    }

    @Test
    public void testGetTypeReturnsLowercase() {
        assertEquals("waiting", StatusType.waiting.getType());
        assertEquals("accepted", StatusType.accepted.getType());
        assertEquals("rejected", StatusType.rejected.getType());
    }

    @Test
    public void testFromStringInvalidThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            StatusType.fromString("unknown_status");
        });
    }

}