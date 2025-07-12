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
        // Get quiz object
        model.Quiz quiz = (model.Quiz) request.getAttribute("quiz");
        if (quiz == null) {
    %>
    <h2>Quiz not found.</h2>
    <%
    } else {
        out.print("<h2>" + quiz.getTitle() + "</h2>");
        // Get other attributes safely
        Question currentQuestion = (Question) request.getAttribute("currentQuestion");
        Integer currentIndexObj = (Integer) request.getAttribute("currentIndex");
        Integer totalQuestionsObj = (Integer) request.getAttribute("totalQuestions");

        int currentIndex = currentIndexObj != null ? currentIndexObj : 0;
        int totalQuestions = totalQuestionsObj != null ? totalQuestionsObj : 0;

        if (currentQuestion == null) {
    %>
    <p>Question not found.</p>
    <%
    } else {
    %>

    <form action="<%=request.getContextPath()%>/submit-single-answer" method="post">
        <input type="hidden" name="quizId" value="<%= quiz.getId() %>">
        <input type="hidden" name="questionId" value="<%= currentQuestion.getId() %>">
        <input type="hidden" name="questionIds" value="<%= currentQuestion.getId() %>">
        <input type="hidden" name="index" value="<%= currentIndex %>">

        <div class="question-block">
            <p><b>Question <%= currentIndex + 1 %> of <%= totalQuestions %>:</b> <%= currentQuestion.getQuestion() %></p>

            <% if (currentQuestion.getQuestionType() == QuestionType.multiple_choice) { %>
            <!-- Example static options -->
            <input type="radio" name="answer" value="Option1" id="opt1"> <label for="opt1">Option 1</label><br>
            <input type="radio" name="answer" value="Option2" id="opt2"> <label for="opt2">Option 2</label><br>
            <input type="radio" name="answer" value="Option3" id="opt3"> <label for="opt3">Option 3</label><br>
            <% } else if (currentQuestion.getQuestionType() == QuestionType.picture_response_questions) { %>
            <input type="text" name="answer" placeholder="Describe the picture..." required />
            <% } else { %>
            <input type="text" name="answer" required/>
            <% } %>
        </div>

        <button type="submit">Submit Answer</button>
    </form>

    <div class="navigation" style="margin-top: 20px;">
        <% if (currentIndex > 0) { %>
        <a href="<%= request.getContextPath() %>/take-quiz?id=<%= quiz.getId() %>&mode=single&index=<%= currentIndex - 1 %>">Previous</a>
        <% } %>

        <% if (currentIndex < totalQuestions - 1) { %>
        <a href="<%= request.getContextPath() %>/take-quiz?id=<%= quiz.getId() %>&mode=single&index=<%= currentIndex + 1 %>">Next</a>
        <% } else { %>
        <form action="<%= request.getContextPath() %>/submit-quiz" method="post" style="display:inline;">
            <input type="hidden" name="quizId" value="<%= quiz.getId() %>"/>
            <button type="submit">Finish Quiz</button>
        </form>
        <% } %>
    </div>

    <%
            } // end if currentQuestion != null
        } // end if quiz != null
    %>
</div>
</body>
</html>
