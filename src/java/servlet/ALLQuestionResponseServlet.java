package servlet;

import dao.QuestionDAO;
import model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/all-questions-response")
public class AllQuestionsServlet extends HttpServlet {
    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = 1; // Hardcoded for now

        List<Question> questions = questionDAO.getQuestionsByQuizId(quizId);
        request.setAttribute("questions", questions);
        request.setAttribute("quizId", quizId);

        request.getRequestDispatcher("/auth/all-questions-response.jsp").forward(request, response);
    }
}
