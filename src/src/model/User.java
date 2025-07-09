package model;

import java.sql.Timestamp;

public class User {
    private String username;
    private int id;
    private String HashedPassword;
    private String hash;
    private Timestamp timestamp;

    public User(String username, int id, String hashedPassword, String hash, Timestamp timestamp) {
        this.username = username;
        this.id = id;
        this.HashedPassword = hashedPassword;
        this.hash = hash;
        this.timestamp = timestamp;
    }

    public User(String hashedPassword, String hash, Timestamp timestamp, String username) {
        this.HashedPassword = hashedPassword;
        this.hash = hash;
        this.timestamp = timestamp;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashedPassword() {
        return HashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        HashedPassword = hashedPassword;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "user{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", HashedPasword='" + HashedPassword + '\'' +
                ", hash='" + hash + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}
