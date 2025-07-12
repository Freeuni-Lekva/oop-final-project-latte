<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<h2>Welcome to Your Dashboard, ${sessionScope.user.username}</h2>

<h3>Popular Quizzes</h3>
<%
    List<Quiz> popularQuizzes = (List<Quiz>) request.getAttribute("allQuizzes");
    if (popularQuizzes != null && !popularQuizzes.isEmpty()) {
%>
<ul>
    <% for (Quiz quiz : popularQuizzes) { %>
    <li>
        <strong><%= quiz.getTitle() %></strong><br/>
        <%= quiz.getDescription() %>
    </li>
    <% } %>
</ul>
<% } else { %>
<p>No popular quizzes found.</p>
<% } %>


<h3>Recently Added Quizzes</h3>
<%
    List<Quiz> recentQuizzes = (List<Quiz>) request.getAttribute("recentQuizzes");
    if (recentQuizzes != null && !recentQuizzes.isEmpty()) {
%>
<ul>
    <% for (Quiz quiz : recentQuizzes) { %>
    <li>
        <strong><%= quiz.getTitle() %></strong><br/>
        <%= quiz.getDescription() %>
    </li>
    <% } %>
</ul>
<% } else { %>
<p>No recently added quizzes found.</p>
<% } %>

</body>
</html>