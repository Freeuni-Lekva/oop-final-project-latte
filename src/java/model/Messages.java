package model;

import java.sql.Timestamp;

public class Messages {
    private int id;
    private int senderId;
    private int receiverId;
    private MessageType messageType;
    private String message;
    private int quizId;
    private Timestamp sentAt;

    public Messages(int id, int senderId, int receiverId, MessageType messageType, String message, int quizId, Timestamp sentAt) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageType = messageType;
        this.message = message;
        this.quizId = quizId;
        this.sentAt = sentAt;
    }

    public Messages(int senderId, int receiverId, MessageType messageType, String message, int quizId, Timestamp sentAt) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageType = messageType;
        this.message = message;
        this.quizId = quizId;
        this.sentAt = sentAt;
    }

    public Messages() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", messageType=" + messageType +
                ", message='" + message + '\'' +
                ", quizId=" + quizId +
                ", sentAt=" + sentAt +
                '}';
    }
}
