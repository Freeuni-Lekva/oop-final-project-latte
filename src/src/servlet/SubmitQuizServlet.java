package servlet;

import dao.QuestionDAO;
import model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/submit-quiz")
public class SubmitQuizServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int quizId = Integer.parseInt(request.getParameter("quizId"));
        List<Question> questions = QuestionDAO.getQuestionsByQuizId(quizId);

        int correct = 0;
        for (Question q : questions) {
            String userAnswer = request.getParameter("question_" + q.getId());
            if (userAnswer != null && userAnswer.trim().equalsIgnoreCase(q.getAnswer().trim())) {
                correct++;
            }
        }

        int total = questions.size();
        request.setAttribute("score", correct);
        request.setAttribute("total", total);

        request.getRequestDispatcher("results.jsp").forward(request, response);
    }
}
