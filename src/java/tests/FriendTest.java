package tests;

import model.Friend;
import model.StatusType;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
class FriendTest {
    @Test
    public void testFullConstructor() {
        Timestamp ts = Timestamp.valueOf("2025-01-01 10:00:00");
        Friend friend = new Friend(1, 10, 20, StatusType.accepted, ts);

        assertEquals(1, friend.getId());
        assertEquals(10, friend.getUserId());
        assertEquals(20, friend.getFriendId());
        assertEquals(StatusType.accepted, friend.getStatus());
        assertEquals(ts, friend.getTimestamp());
    }

    @Test
    public void testConstructorWithoutId() {
        Timestamp ts = Timestamp.valueOf("2025-01-01 12:00:00");
        Friend friend = new Friend(30, 40, StatusType.rejected, ts);

        assertEquals(0, friend.getId()); // default value
        assertEquals(30, friend.getUserId());
        assertEquals(40, friend.getFriendId());
        assertEquals(StatusType.rejected, friend.getStatus());
        assertEquals(ts, friend.getTimestamp());
    }

    @Test
    public void testSettersAndGetters() {
        Friend friend = new Friend();
        Timestamp ts = Timestamp.valueOf("2025-01-02 09:00:00");

        friend.setId(100);
        friend.setUserId(101);
        friend.setFriendId(202);
        friend.setStatus(StatusType.waiting);
        friend.setTimestamp(ts);

        assertEquals(100, friend.getId());
        assertEquals(101, friend.getUserId());
        assertEquals(202, friend.getFriendId());
        assertEquals(StatusType.waiting, friend.getStatus());
        assertEquals(ts, friend.getTimestamp());
    }

    @Test
    public void testToStringFormat() {
        Timestamp ts = Timestamp.valueOf("2025-01-03 15:30:00");
        Friend friend = new Friend(5, 6, 7, StatusType.waiting, ts);

        String str = friend.toString();

        assertTrue(str.contains("id=5"));
        assertTrue(str.contains("userId=6"));
        assertTrue(str.contains("friendId=7"));
        assertTrue(str.contains("status=waiting"));
        assertTrue(str.contains("timestamp=" + ts.toString()));
    }
}