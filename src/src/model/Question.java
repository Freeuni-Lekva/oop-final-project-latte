package model;

public class Question {
    private int id;
    private int quizId;
    private String type;
    private String question;
    private String answer;

    public Question(int id, int quizId, String type, String question, String answer) {
        this.id = id;
        this.quizId = quizId;
        this.type = type;
        this.question = question;
        this.answer = answer;
    }

    public Question(int quizId, String type, String question, String answer) {
        this.quizId = quizId;
        this.type = type;
        this.question = question;
        this.answer = answer;
    }

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", type='" + type + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
