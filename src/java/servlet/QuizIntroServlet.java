package servlet;

import dao.QuizDAO;
import model.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/quiz")
public class QuizIntroServlet extends HttpServlet {
    private final QuizDAO quizDAO = new QuizDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quiz ID is missing.");
            return;
        }

        int quizId;
        try {
            quizId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid quiz ID.");
            return;
        }

        Quiz quiz = quizDAO.getQuizById(quizId);
        if (quiz == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Quiz not found.");
            return;
        }
        if(quiz.getTitle()==null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Title is missing.");
        }
        request.setAttribute("title", quiz.getTitle());
        request.setAttribute("description", quiz.getDescription());
        request.setAttribute("quizId", quizId);

        request.getRequestDispatcher("auth/quiz-intro.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String quizIdParam = request.getParameter("quizId");
        String startFlag = request.getParameter("start");

        if (quizIdParam == null || !"true".equalsIgnoreCase(startFlag)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid start request.");
            return;
        }

        int quizId;
        try {
            quizId = Integer.parseInt(quizIdParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid quiz ID.");
            return;
        }

        Quiz quiz = quizDAO.getQuizById(quizId);
        if (quiz == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Quiz not found.");
            return;
        }

        String redirectUrl = request.getContextPath() + "/take-quiz?id=" + quizId;

        if (quiz.isOnePage()) {
            redirectUrl += "&mode=all";
        } else {
            redirectUrl += "&mode=single&index=0";
        }

        response.sendRedirect(redirectUrl);
    }

}
}


