package servlet;

import dao.AnswerDAO;
import dao.QuestionDAO;
import dao.QuizDAO;
import model.Question;
import model.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/take-quiz")
public class TakeQuizServlet extends HttpServlet {
    private final QuestionDAO questionDAO = new QuestionDAO();
    private final AnswerDAO answerDAO = new AnswerDAO();  // DAO to get correct answers

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String quizIdParam = request.getParameter("id");
        String mode = request.getParameter("mode");

        if (quizIdParam == null) {
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            return;
        }

        int quizId;
        try {
            quizId = Integer.parseInt(quizIdParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            return;
        }

        Quiz quiz = QuizDAO.getQuizById(quizId);
        if (quiz == null) {
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            return;
        }

        List<Question> questions;
        if (quiz.isRandomOrdered()) {
            questions = questionDAO.getQuestionsByQuizId(quizId);
            Collections.shuffle(questions);
        } else {
            questions = questionDAO.getQuestionsByQuizIdOrdered(quizId);
        }

        if (questions.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            return;
        }

        request.setAttribute("quiz", quiz);
        request.setAttribute("questions", questions);
        request.setAttribute("isImmediateCorrection", quiz.is_immediate_correction());

        if ("single".equalsIgnoreCase(mode)) {
            handleSingleQuestionMode(request, response, questions);
            return;
        }

        if (quiz.is_immediate_correction()) {
            HttpSession session = request.getSession(false);
            Map<Integer, String> userAnswers = session != null
                    ? (Map<Integer, String>) session.getAttribute("userAnswers")
                    : null;

            Map<Integer, Boolean> correctnessMap = new HashMap<>();

            if (userAnswers != null) {
                for (Question q : questions) {
                    String userAnswer = userAnswers.get(q.getId());
                    if (userAnswer != null) {

                        if(quiz.is_random_order()){
                            List<String> correctAnswers = answerDAO.getCorrectAnswersByQuestionId(q.getId());
                        }else{
                            List<String> correctAnswers = answerDAO.getCorrectAnswersByQuestionIdOrdered(q.getId());
                        }
                        boolean isCorrect = correctAnswers.stream()
                                .anyMatch(ans -> ans.equalsIgnoreCase(userAnswer.trim()));
                        correctnessMap.put(q.getId(), isCorrect);
                    }
                }
            }

            request.setAttribute("correctnessMap", correctnessMap);
        }

        request.getRequestDispatcher("/auth/all-questions.jsp").forward(request, response);
    }

    private void handleSingleQuestionMode(HttpServletRequest request, HttpServletResponse response, List<Question> questions)
            throws ServletException, IOException {

        int currentIndex = 0;
        String indexParam = request.getParameter("index");

        if (indexParam != null) {
            try {
                currentIndex = Integer.parseInt(indexParam);
                if (currentIndex < 0 || currentIndex >= questions.size()) {
                    currentIndex = 0;
                }
            } catch (NumberFormatException ignored) {}
        }

        Question currentQuestion = questions.get(currentIndex);

        request.setAttribute("currentQuestion", currentQuestion);
        request.setAttribute("currentIndex", currentIndex);
        request.setAttribute("totalQuestions", questions.size());

        request.getRequestDispatcher("/auth/single-question.jsp").forward(request, response);
    }
}
