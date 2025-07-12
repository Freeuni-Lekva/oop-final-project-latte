@WebServlet("/submit-single-answer")
public class SubmitSingleAnswerServlet extends HttpServlet {
    private final AnswerDAO answerDAO = new AnswerDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String userAnswer = request.getParameter("answer");

        boolean isCorrect = answerDAO.isAnswerCorrect(questionId, userAnswer);

        request.setAttribute("isCorrect", isCorrect);

        request.getRequestDispatcher("/auth/take-quiz?id=" + request.getParameter("quizId") + "&mode=single&index=" + request.getParameter("index"))
                .forward(request, response);
    }
}
