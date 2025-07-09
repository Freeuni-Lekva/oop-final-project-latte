package servlet;
import dao.QuizDAO;
import model.Quiz;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/create-quiz")
public class CreateQuizServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer creatorId = (Integer) session.getAttribute("userId");

        if (creatorId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        boolean isRandom = request.getParameter("random") != null;
        boolean isOnePage = request.getParameter("onePage") != null;
        boolean isImmediate = request.getParameter("immediate") != null;
        boolean isPractice = request.getParameter("practice") != null;

        Quiz quiz = new Quiz(title, description, creatorId, isRandom, isOnePage, isImmediate, isPractice);

        QuizDAO quizDAO = new QuizDAO();
        boolean success = quizDAO.createQuiz(quiz);

        if (success) {
            session.setAttribute("currentQuizId", quiz.getId());
            response.sendRedirect("addQuestion.jsp");
        } else {
            request.setAttribute("error", "Quiz creation failed.");
            request.getRequestDispatcher("createQuiz.jsp").forward(request, response);
        }
    }
}
