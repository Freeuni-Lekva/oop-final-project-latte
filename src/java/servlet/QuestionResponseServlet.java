package servlet;

import dao.QuestionDAO;
import model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/question-response")
public class QuizQuestionServlet extends HttpServlet {

    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String quizIdParam = req.getParameter("quizId");
        if (quizIdParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "quizId is required");
            return;
        }
        int quizId;
        try {
            quizId = Integer.parseInt(quizIdParam);
        } catch (NumberFormatException ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "quizId must be an integer");
            return;
        }

        int index = 0;
        String indexParam = req.getParameter("index");
        if (indexParam != null) {
            try {
                index = Integer.parseInt(indexParam);
            } catch (NumberFormatException ignore) {

            }
        }

        List<Question> all = questionDAO.getQuestionsByQuizId(quizId);
        if (index < 0 || index >= all.size()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No question at that index");
            return;
        }
        Question question = all.get(index);

        req.setAttribute("question", question);
        req.setAttribute("quizId",  quizId);
        req.setAttribute("index",   index);
        req.setAttribute("total",   all.size());

        req.getRequestDispatcher("/auth/question-response.jsp")
                .forward(req, resp);
    }
}
