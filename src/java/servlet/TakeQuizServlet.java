package servlet;

import dao.QuestionDAO;
import dao.QuizDAO;
import model.Question;
import model.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/take-quiz")
public class TakeQuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String quizIdParam = request.getParameter("id");
        if (quizIdParam == null) {
            response.sendRedirect("dashboard.jsp");
            return;
        }

        int quizId = Integer.parseInt(quizIdParam);
        Quiz quiz = QuizDAO.getQuizById(quizId);
        List<Question> questions = QuestionDAO.getQuestionsByQuizId(quizId);

        request.setAttribute("quiz", quiz);
        request.setAttribute("questions", questions);

        request.getRequestDispatcher("/takeQuiz.jsp").forward(request, response);
    }
}
