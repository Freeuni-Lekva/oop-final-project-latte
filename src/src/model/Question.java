package model;

public class Question {
    private int id;
    private int quizId;
    private QuestionType questionType;
    private String question;
    private String answer;

    public Question(int id, int quizId, QuestionType questionType, String question, String answer) {
        this.id = id;
        this.quizId = quizId;
        this.questionType = questionType;
        this.question = question;
        this.answer = answer;
    }

    public Question(int quizId, QuestionType questionType, String question, String answer) {
        this.quizId = quizId;
        this.questionType = questionType;
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
                ", questionType=" + questionType +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
