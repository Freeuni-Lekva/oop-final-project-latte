package servlet;

import dao.QuestionDAO;
import model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/question-response")
public class QuizQuestionsServlet extends HttpServlet {

    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = 1; // Your hardcoded value for now

        List<Question> questions = questionDAO.getQuestionsByQuizId(quizId);
        System.out.println("quizId: " + quizId);
        System.out.println("Questions found: " + questions.size());
        for (Question q : questions) {
            System.out.println("Question ID: " + q.getId() + ", Question: " + q.getQuestion());
        }

        request.setAttribute("questions", questions);
        request.setAttribute("quizId", quizId);

        request.getRequestDispatcher("/auth/question-response.jsp").forward(request, response);
    }
}
