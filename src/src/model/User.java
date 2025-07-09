package model;

import java.sql.Timestamp;

public class User {
    private String username;
    private int id;
    private String HashedPasword;
    private String hash;
    private Timestamp timestamp;

    public User(String username, int id, String hashedPasword, String hash, Timestamp timestamp) {
        this.username = username;
        this.id = id;
        this.HashedPasword = hashedPasword;
        this.hash = hash;
        this.timestamp = timestamp;
    }

    public User(String hashedPasword, String hash, Timestamp timestamp, String username) {
        this.HashedPasword = hashedPasword;
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

    public String getHashedPasword() {
        return HashedPasword;
    }

    public void setHashedPasword(String hashedPasword) {
        HashedPasword = hashedPasword;
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
                ", HashedPasword='" + HashedPasword + '\'' +
                ", hash='" + hash + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}
