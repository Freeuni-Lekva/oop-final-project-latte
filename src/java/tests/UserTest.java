package tests;

import model.User;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testConstructorAndGetters() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        User user = new User("testuser", 1, "hashedPass", "saltHash", ts);

        assertEquals("testuser", user.getUsername());
        assertEquals(1, user.getId());
        assertEquals("hashedPass", user.getHashedPassword());
        assertEquals("saltHash", user.getHash());
        assertEquals(ts, user.getTimestamp());
    }

    @Test
    public void testAlternativeConstructor() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        User user = new User("hashedPass2", "saltHash2", ts, "user2");

        assertEquals("user2", user.getUsername());
        assertEquals("hashedPass2", user.getHashedPassword());
        assertEquals("saltHash2", user.getHash());
        assertEquals(ts, user.getTimestamp());
    }

    @Test
    public void testSetters() {
        User user = new User("temp", 0, "tempPass", "tempHash", new Timestamp(0));

        user.setUsername("updatedUser");
        user.setId(42);
        user.setHashedPassword("newPass");
        user.setHash("newSalt");
        Timestamp newTs = new Timestamp(System.currentTimeMillis());
        user.setTimestamp(newTs);

        assertEquals("updatedUser", user.getUsername());
        assertEquals(42, user.getId());
        assertEquals("newPass", user.getHashedPassword());
        assertEquals("newSalt", user.getHash());
        assertEquals(newTs, user.getTimestamp());
    }

    @Test
    public void testToString() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        User user = new User("testuser", 1, "pass", "salt", ts);
        String expected = "user{username='testuser', id=1, HashedPasword='pass', hash='salt', timestamp=" + ts + '}';
        assertEquals(expected, user.toString());
    }
}
