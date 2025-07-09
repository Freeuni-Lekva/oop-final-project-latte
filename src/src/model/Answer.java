package model;

public class Answer {
    private int id;
    private int questionId;
    private String text;
    private boolean isOrdered;

    public Answer(int id, int questionId, String text, boolean isOrdered) {
        this.id = id;
        this.questionId = questionId;
        this.text = text;
        this.isOrdered = isOrdered;
    }

    public Answer(int questionId, String text, boolean isOrdered) {
        this.questionId = questionId;
        this.text = text;
        this.isOrdered = isOrdered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", text='" + text + '\'' +
                ", isOrdered=" + isOrdered +
                '}';
    }
}
