package tests;

import helpers.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @Test
    public void testIsNotEmptyWithNull() {
        assertFalse(Validator.isNotEmpty(null));
    }

    @Test
    public void testIsNotEmptyWithEmptyString() {
        assertFalse(Validator.isNotEmpty(""));
    }

    @Test
    public void testIsNotEmptyWithWhitespace() {
        assertFalse(Validator.isNotEmpty("   "));
    }

    @Test
    public void testIsNotEmptyWithValidString() {
        assertTrue(Validator.isNotEmpty("abc"));
    }

    // --- isValidUsername Tests ---

    @Test
    public void testValidUsername() {
        assertTrue(Validator.isValidUsername("user_123"));
    }

    @Test
    public void testUsernameTooShort() {
        assertFalse(Validator.isValidUsername("usr"));
    }

    @Test
    public void testUsernameWithInvalidChars() {
        assertFalse(Validator.isValidUsername("user!"));
    }

    @Test
    public void testUsernameNull() {
        assertFalse(Validator.isValidUsername(null));
    }

    // --- isValidPassword Tests ---

    @Test
    public void testValidPassword() {
        assertTrue(Validator.isValidPassword("abc123"));
    }

    @Test
    public void testPasswordTooShort() {
        assertFalse(Validator.isValidPassword("a1b2"));
    }

    @Test
    public void testPasswordOnlyLetters() {
        assertFalse(Validator.isValidPassword("abcdef"));
    }

    @Test
    public void testPasswordOnlyNumbers() {
        assertFalse(Validator.isValidPassword("123456"));
    }

    @Test
    public void testPasswordNull() {
        assertFalse(Validator.isValidPassword(null));
    }

//    @Test
//    public void testValidEmail() {
//        assertTrue(Validator.isValidEmail("user@example.com"));
//    }

//    @Test
//    public void testEmailWithoutAtSymbol() {
//        assertFalse(Validator.isValidEmail("userexample.com"));
//    }

//    @Test
//    public void testEmailWithoutDomain() {
//        assertFalse(Validator.isValidEmail("user@"));
//    }

//    @Test
//    public void testEmailNull() {
//        assertFalse(Validator.isValidEmail(null));
//    }
}
