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
        Quiz quiz = quizDAO.getQuizById(quizId);
        List<Question> questions = new QuestionDAO().getQuestionsByQuizId(quizId);

        int correct = 0;
        for (Question q : questions) {
            String userAnswer = request.getParameter("answer_" + question.getId());
            List<String> correctAnswers = answerDAO.getCorrectAnswersByQuestionId(question.getId());

            if (userAnswer != null && correctAnswers.contains(userAnswer.trim())) {
                correctCount++;
            }
        }

        if (!quiz.isPractice()) {
            request.setAttribute("quiz", quiz);
            request.setAttribute("totalQuestions", questions.size());
            request.setAttribute("correctAnswers", correctCount);

            request.getRequestDispatcher("/auth/quiz-result.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        }
    }
}
