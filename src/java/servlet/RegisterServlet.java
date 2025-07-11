package servlet;
import model.User;
import dao.UserDAO;
import helpers.PasswordHasher;
import helpers.Validator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registration")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (!Validator.isValidUsername(username) ) {
            request.setAttribute("error", "Username must be at least 4 characters long and contain only letters, numbers, or underscores.");
            request.getRequestDispatcher("auth/registration.jsp").forward(request, response);
            return;
        }else  if (!Validator.isValidPassword(password)) {
            request.setAttribute("error", "Password must be at least 6 characters long, contain letters and numbers.");
            request.getRequestDispatcher("auth/registration.jsp").forward(request, response);
            return;
        }


        String hash   = PasswordHasher.generateHash();
        String hashed = PasswordHasher.hashPassword(password, hash);

        UserDAO dao = new UserDAO();
        User newUser = new User(hashed, hash, null, username);

        boolean success = dao.insertUser(newUser);
        if (success) {
            User saved = dao.findByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("user", saved);
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Registration failed. Username might be taken.");
            request.getRequestDispatcher("auth/registration.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("auth/registration.jsp").forward(request, response);
    }
}