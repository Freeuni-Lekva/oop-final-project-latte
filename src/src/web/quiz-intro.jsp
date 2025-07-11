<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Quiz" %>
<%
    Quiz quiz = (Quiz) request.getAttribute("quiz");
    int totalQuestions = (int) request.getAttribute("totalQuestions");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= quiz.getTitle() %></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/quiz.css">
</head>
<body>
<div class="container">
    <h1 class="title"><%= quiz.getTitle() %></h1>

    <p class="description">
        <%= quiz.getDescription() != null ? quiz.getDescription() : "No description provided." %>
    </p>

    <div class="meta">
        <span class="badge"><%= totalQuestions %> <%= totalQuestions == 1 ? "question" : "questions" %></span>
    </div>

    <form action="${pageContext.request.contextPath}/take-quiz" method="get">
        <input type="hidden" name="id" value="<%= quiz.getId() %>"/>
        <button class="start-btn" type="submit">Start Quiz</button>
    </form>
</div>
</body>
</html>
