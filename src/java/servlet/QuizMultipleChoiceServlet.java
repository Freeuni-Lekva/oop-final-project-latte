package servlet;

import dao.QuestionDAO;
import model.Question;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/quiz-multiple-choice")
public class QuizMultipleChoiceServlet extends HttpServlet {

    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String quizIdParam = req.getParameter("quizId");
        if (quizIdParam == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "quizId required");
            return;
        }
        int quizId;
        try { quizId = Integer.parseInt(quizIdParam); }
        catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "invalid quizId");
            return;
        }

        List<Question> all = questionDAO.getQuestionsByQuizId(quizId)
                .stream()
                .filter(q -> "multiple_choice".equals(q.getQuestionType()))
                .collect(Collectors.toList());

        if (all.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "no MCQs");
            return;
        }

        boolean onePerPage = "true".equalsIgnoreCase(req.getParameter("onePerPage"));
        if (onePerPage) {
            int index = 0;
            try {
                index = Integer.parseInt(req.getParameter("index"));
            } catch (NumberFormatException ignored) {}
            if (index < 0 || index >= all.size()) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "bad index");
                return;
            }
            req.setAttribute("question", all.get(index));
            req.setAttribute("index", index);
            req.setAttribute("total", all.size());
            req.setAttribute("quizId", quizId);
            req.getRequestDispatcher("/auth/mc-one.jsp").forward(req, resp);
        } else {
            req.setAttribute("questions", all);
            req.setAttribute("quizId", quizId);
            req.getRequestDispatcher("/auth/mc-many.jsp").forward(req, resp);
        }
    }
}
