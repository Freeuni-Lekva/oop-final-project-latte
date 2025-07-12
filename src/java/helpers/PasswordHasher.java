package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {

    public static String hashPassword(String password, String hash) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String hashedPassword = hash + password;
            byte[] hashedBytes = md.digest(hashedPassword.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found", e);
        }
    }

    public static String generateHash() {
        SecureRandom random = new SecureRandom();
        byte[] hash = new byte[16];
        random.nextBytes(hash);
        return Base64.getEncoder().encodeToString(hash);
    }

    public static boolean verifyPassword(String enteredPassword, String storedHash, String hash) {
        String hashedInput = hashPassword(enteredPassword, hash);
        return hashedInput.equals(storedHash);
    }
}
