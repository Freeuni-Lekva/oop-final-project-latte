package servlet;

import dao.QuestionDAO;
import model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/fill-in-the-blank")
public class QuizQuestionServlet extends HttpServlet {
    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String quizIdParam = req.getParameter("quizId");
        String onePerPageParam = req.getParameter("onePerPage");
        int quizId;

        if (quizIdParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "quizId required");
            return;
        }

        try {
            quizId = Integer.parseInt(quizIdParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid quizId");
            return;
        }

        List<Question> all = questionDAO.getQuestionsByQuizId(quizId);

        if ("true".equalsIgnoreCase(onePerPageParam)) {
            int index = 0;
            String indexParam = req.getParameter("index");
            if (indexParam != null) {
                try {
                    index = Integer.parseInt(indexParam);
                } catch (NumberFormatException ignored) {}
            }

            if (index < 0 || index >= all.size()) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid question index");
                return;
            }

            req.setAttribute("question", all.get(index));
            req.setAttribute("quizId", quizId);
            req.setAttribute("index", index);
            req.setAttribute("total", all.size());

            req.getRequestDispatcher("/auth/one-question-fill-in-the-blank.jsp").forward(req, resp);
        } else {
            req.setAttribute("questions", all);
            req.setAttribute("quizId", quizId);

            req.getRequestDispatcher("/auth/multiple-questions-fill-in-the-blank.jsp").forward(req, resp);
        }
    }
}

