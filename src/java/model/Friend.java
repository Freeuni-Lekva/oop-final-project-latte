package model;

import java.sql.Timestamp;

public class Friend {
    private int id;
    private int userId;
    private int friendId;
    private StatusType status;
    private Timestamp timestamp;

    public Friend(int id, int userId, int friendId, StatusType status, Timestamp timestamp) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Friend(int userId, int friendId, StatusType status, Timestamp timestamp) {
        this.userId = userId;
        this.friendId = friendId;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Friend() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", userId=" + userId +
                ", friendId=" + friendId +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }


}
