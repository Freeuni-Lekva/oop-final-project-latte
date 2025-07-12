package servlet;

import model.*;
import dao.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if(user == null){
            response.sendRedirect("auth/login.jsp");
            return;
        }

        QuizDAO quizDao = new QuizDAO();
//        MessagesDao messagesDAO = new MessagesDao();
//        FriendsDAO friendsDao = new FriendsDao();

        List<Quiz> popularQuizzes = quizDao.getPopularQuizzes();
        List<Quiz> recentlyAdded = quizDao.getRecentQuizzes();

        request.setAttribute("popularQuizzes", popularQuizzes);
        request.setAttribute("recentQuizzes", recentlyAdded);
        request.getRequestDispatcher("dashboard.jsp").forward(request,response);
    }


}