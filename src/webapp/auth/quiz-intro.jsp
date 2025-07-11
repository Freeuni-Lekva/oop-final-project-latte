<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Quiz Introduction</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/auth/quiz-intro.css">
</head>
<body>
<div class="container">
    <div class="meta">
        <span class="badge">Quiz</span>
    </div>

    <h2 class="title"><%= request.getAttribute("title") != null ? request.getAttribute("title") : "" %></h2>
    <p class="description"><%= request.getAttribute("description") != null ? request.getAttribute("description") : "" %></p>

    <form action="<%= request.getContextPath() %>/start-quiz" method="post">
        <input type="hidden" name="quizId" value="<%= request.getAttribute("quizId") %>"/>
        <button type="submit" class="start-btn">Start Quiz</button>
    </form>

    <p style="color:red;">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </p>
</div>
</body>
</html>
