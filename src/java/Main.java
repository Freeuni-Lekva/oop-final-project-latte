

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import helpers.connector;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = connector.getConnection();
             Statement stmt = conn.createStatement()) {
            String query = "SELECT id, username FROM Users LIMIT 5;";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Successfully connected to database");

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                System.out.println("ID: " + id + " | Username: " + username);
            }

        } catch (Exception e) {
            System.err.println("Failed to connect to database");
            e.printStackTrace();
        }
    }
}