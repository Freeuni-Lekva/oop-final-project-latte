package tests;

import helpers.PasswordHasher;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordHasherTest {

    @Test
    public void testGenerateHashIsNotNull() {
        String hash = PasswordHasher.generateHash();
        assertNotNull(hash, "Generated hash should not be null");
    }

    @Test
    public void testGenerateHashIsRandom() {
        String hash1 = PasswordHasher.generateHash();
        String hash2 = PasswordHasher.generateHash();
        assertNotEquals(hash1, hash2, "Two generated hashes should not be the same");
    }

    @Test
    public void testHashPasswordConsistency() {
        String password = "securePassword123";
        String salt = PasswordHasher.generateHash();
        String hashed1 = PasswordHasher.hashPassword(password, salt);
        String hashed2 = PasswordHasher.hashPassword(password, salt);
        assertEquals(hashed1, hashed2, "Hashing same password with same salt should return the same hash");
    }

    @Test
    public void testVerifyPasswordSuccess() {
        String password = "mySecret123";
        String salt = PasswordHasher.generateHash();
        String hashed = PasswordHasher.hashPassword(password, salt);

        boolean result = PasswordHasher.verifyPassword(password, hashed, salt);
        assertTrue(result, "Correct password should verify successfully");
    }

    @Test
    public void testVerifyPasswordFailure() {
        String password = "correctPassword";
        String wrongPassword = "wrongPassword";
        String salt = PasswordHasher.generateHash();
        String hashed = PasswordHasher.hashPassword(password, salt);

        boolean result = PasswordHasher.verifyPassword(wrongPassword, hashed, salt);
        assertFalse(result, "Incorrect password should not verify");
    }
}
