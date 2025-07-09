package model;

import java.sql.Timestamp;

public class Attemps {
    private int id;
    private int userId;
    private int quizId;
    private double score;
    private int duration;
    private Timestamp taken;

    public Attemps(int id, int userId, int quizId, double score, int duration, Timestamp taken) {
        this.id = id;
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
        this.duration = duration;
        this.taken = taken;
    }

    public Attemps(int userId, int quizId, double score, int duration, Timestamp taken) {
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
        this.duration = duration;
        this.taken = taken;
    }

    public Attemps() {
    }

    public int getId() {
        return id;
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

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Timestamp getTaken() {
        return taken;
    }

    public void setTaken(Timestamp taken) {
        this.taken = taken;
    }

    @Override
    public String toString() {
        return "Attemps{" +
                "id=" + id +
                ", userId=" + userId +
                ", quizId=" + quizId +
                ", score=" + score +
                ", duration=" + duration +
                ", taken=" + taken +
                '}';
    }
}
