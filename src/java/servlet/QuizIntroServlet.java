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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("message", "Welcome to the Quiz!");

        // Forward to JSP page
        request.getRequestDispatcher("/auth/quiz-intro.jsp").forward(request, response);

    }

   // public QuizIntroServlet() {
        QuizDAO quizDAO = new QuizDAO();
    }

/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String id = request.getParameter("Id");
        if(id == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        assert id != null;*/
       /* int quizId = 0//Integer.parseInt(id);
        Quiz quiz = QuizDAO.getQuizById(quizId);
        assert quiz != null;
        String title = quiz.getTitle();
        String description = quiz.getDescription();
        request.setAttribute("title", title);
        request.setAttribute("description", description);
        request.getRequestDispatcher("auth/quiz-intro.jsp").forward(request, response);
    }*/

}
