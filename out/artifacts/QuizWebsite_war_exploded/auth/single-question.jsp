<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Question" %>
<%@ page import="model.QuestionType" %>

<html>
<head>
    <title>Take Quiz - Single Question</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/questions.css">
</head>
<body>
<div class="container">
    <%
        model.Quiz quiz = (model.Quiz) request.getAttribute("quiz");
        String mode = request.getParameter("mode") != null ? request.getParameter("mode") : "single";

        if (quiz == null) {
    %>
    <h2>Quiz not found.</h2>
    <%
    } else {
        out.print("<h2>" + quiz.getTitle() + "</h2>");
        Question currentQuestion = (Question) request.getAttribute("currentQuestion");
        Integer currentIndexObj = (Integer) request.getAttribute("currentIndex");
        Integer totalQuestionsObj = (Integer) request.getAttribute("totalQuestions");
        Boolean isCorrect = (Boolean) request.getAttribute("isCorrect");

        int currentIndex = currentIndexObj != null ? currentIndexObj : 0;
        int totalQuestions = totalQuestionsObj != null ? totalQuestionsObj : 0;

        if (currentQuestion == null) {
    %>
    <p>Question not found.</p>
    <%
    } else {
    %>

    <form action="<%= request.getContextPath() %>/submit-single-answer" method="post">
        <input type="hidden" name="quizId" value="<%= quiz.getId() %>">
        <input type="hidden" name="questionId" value="<%= currentQuestion.getId() %>">
        <input type="hidden" name="index" value="<%= currentIndex %>">
        <input type="hidden" name="mode" value="<%= mode %>">

        <div class="question-block">
            <p><b>Question <%= currentIndex + 1 %> of <%= totalQuestions %>:</b> <%= currentQuestion.getQuestion() %></p>

            <% if (currentQuestion.getQuestionType() == QuestionType.multiple_choice) { %>
            <input type="radio" name="answer" value="Option1"> Option 1<br>
            <input type="radio" name="answer" value="Option2"> Option 2<br>
            <input type="radio" name="answer" value="Option3"> Option 3<br>
            <% } else if (currentQuestion.getQuestionType() == QuestionType.picture_response_questions) { %>
            <input type="text" name="answer" placeholder="Describe the picture..." required />
            <% } else { %>
            <input type="text" name="answer" required/>
            <% } %>
        </div>

        <button type="submit">Submit Answer</button>
    </form>

    <% if (quiz.isImmediateCorrection() && isCorrect != null) { %>
    <div style="margin-top: 20px; font-weight: bold;">
        <% if (isCorrect) { %>
        ✅ Correct Answer!
        <% } else { %>
        ❌ Incorrect Answer.
        <% } %>
    </div>
    <% } %>

    <div class="navigation" style="margin-top: 20px;">
        <% if (currentIndex > 0) { %>
        <a href="<%= request.getContextPath() %>/take-quiz?id=<%= quiz.getId() %>&mode=<%= mode %>&index=<%= currentIndex - 1 %>">Previous</a>
        <% } %>

        <% if (currentIndex < totalQuestions - 1) { %>
        <a href="<%= request.getContextPath() %>/take-quiz?id=<%= quiz.getId() %>&mode=<%= mode %>&index=<%= currentIndex + 1 %>">Next</a>
        <% } else { %>
        <form action="<%= request.getContextPath() %>/submit-quiz" method="post" style="display:inline;">
            <input type="hidden" name="quizId" value="<%= quiz.getId() %>"/>
            <input type="hidden" name="mode" value="<%= mode %>"/>
            <button type="submit">Finish Quiz</button>
        </form>
        <% } %>
    </div>

    <%
            } // currentQuestion != null
        } // quiz != null
    %>
</div>
</body>
</html>
