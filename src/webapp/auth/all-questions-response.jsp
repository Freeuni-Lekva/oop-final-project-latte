<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Question" %>

<html>
<head>
    <title>All Quiz Questions</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/questions.css">
</head>
<body>
<div class="container">
    <h2>All Quiz Questions</h2>

    <form action="<%=request.getContextPath()%>/submit-quiz" method="post">
        <input type="hidden" name="quizId" value="<%=request.getAttribute("quizId")%>"/>

        <%
            List<Question> questions = (List<Question>) request.getAttribute("questions");
            if (questions != null && !questions.isEmpty()) {
                for (Question q : questions) {
        %>
        <div class="question-block">
            <p><b>Question:</b> <%= q.getQuestion() %></p>
            <input type="text" name="answer_<%= q.getId() %>" required/>
        </div>
        <%
            }
        } else {
        %>
        <p>No questions found for this quiz.</p>
        <%
            }
        %>

        <button type="submit">Submit Answers</button>
    </form>
</div>
</body>
</html>
