package servlet;

import dao.QuestionDAO;
import model.Question;
import model.QuestionType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/add-question")
public class AddQuestionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer quizId = (Integer) session.getAttribute("currentQuizId");

        if (quizId == null) {
            response.sendRedirect("createQuiz.jsp");
            return;
        }

        String questionText = request.getParameter("question");
        String answerText = request.getParameter("answer");
        String typeStr = request.getParameter("type");

        QuestionType type;
        try {
            type = QuestionType.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid question type");
            request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
            return;
        }

        Question question = new Question(quizId, type, questionText, answerText);

        QuestionDAO questionDAO = new QuestionDAO();
        boolean success = questionDAO.addQuestion(question);

        if (success) {
            request.setAttribute("message", "question added successfully");
            request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "question could not be added");
            request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
        }
    }
}
