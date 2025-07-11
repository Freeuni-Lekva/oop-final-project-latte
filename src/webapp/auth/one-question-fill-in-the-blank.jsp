<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Question" %>
<%
    Question q = (Question) request.getAttribute("question");
    int index = (Integer) request.getAttribute("index");
    int total = (Integer) request.getAttribute("total");
    int quizId = (Integer) request.getAttribute("quizId");
%>

<h3>Question <%= index + 1 %> of <%= total %></h3>

<form action="submit-answer" method="post">
    <p><%= q.getQuestion() %></p>
    <input type="text" name="answer" required />
    <input type="hidden" name="questionId" value="<%= q.getId() %>" />
    <button type="submit">Submit</button>
</form>

<% if (index + 1 < total) { %>
<form method="get" action="quiz">
    <input type="hidden" name="quizId" value="<%= quizId %>" />
    <input type="hidden" name="index" value="<%= index + 1 %>" />
    <input type="hidden" name="onePerPage" value="true" />
    <button type="submit">Next Question</button>
</form>
<% } %>
