package servlet;

import dao.QuizDAO;
import dao.UserDAO;
import model.Quiz;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user-profile")
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("id");
        if (userIdParam == null) {
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            return;
        }
        int userId = Integer.parseInt(userIdParam);

        // Commented database call and provided hardcoded user
        // User user = UserDAO.getUserById(userId);
        User user = new User("demoUser", userId, "demoPassword", "demoHash", new Timestamp(System.currentTimeMillis()));

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            return;
        }

        // Commented database check and provided static value
        // boolean isFriend = UserDAO.checkIfFriends(getCurrentUserId(request), userId);
        boolean isFriend = false; // Pretend user is not a friend for demo

        // Commented database quiz list and provided static list
        // List<Quiz> allQuizzes = QuizDAO.getAllQuizzes();
        List<Quiz> allQuizzes = new ArrayList<>();
        allQuizzes.add(new Quiz(1, "Sample Quiz", "This is a sample quiz.", userId, false, true, false, false));

        request.setAttribute("profileUser", user);
        request.setAttribute("isFriend", isFriend);
        request.setAttribute("allQuizzes", allQuizzes);
        request.getRequestDispatcher("/auth/user-profile.jsp").forward(request, response);
    }

    private int getCurrentUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User loggedInUser = (User) session.getAttribute("user");
            return loggedInUser.getId();
        }
        return -1;
    }
}
