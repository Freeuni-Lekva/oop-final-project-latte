package model;

public class Question {
    private int id;
    private int quizId;
    private QuestionType questionType;
    private String question;
    private int position;

    public Question(int id, int quizId, QuestionType questionType, String question, int position) {
        this.id = id;
        this.quizId = quizId;
        this.questionType = questionType;
        this.question = question;
        this.position = position;
    }

    public Question(int quizId, QuestionType questionType, String question, int position) {
        this.quizId = quizId;
        this.questionType = questionType;
        this.question = question;
        this.position = position;
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

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", questionType=" + questionType +
                ", question='" + question + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
