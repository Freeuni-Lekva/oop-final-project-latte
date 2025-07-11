package servlet;
import model.User;
import dao.UserDAO;
import helpers.PasswordHasher;
import helpers.Validator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (!Validator.isValidUsername(username) || !Validator.isValidPassword(password)) {
            request.setAttribute("error", "Invalid input");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByUsername(username);

        if (user == null) {
            request.setAttribute("error", "User not found");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String hashedInput = PasswordHasher.hashPassword(password, user.getHash());

        if (hashedInput.equals(user.getHashedPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Incorrect password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}

