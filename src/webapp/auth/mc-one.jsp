<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Question" %>
<%
    Question q   = (Question) request.getAttribute("question");
    int index    = (Integer) request.getAttribute("index");
    int total    = (Integer) request.getAttribute("total");
    int quizId   = (Integer) request.getAttribute("quizId");
%>

<h3>Question <%= index + 1 %> / <%= total %></h3>

<form action="submit-answer" method="post">
    <p><%= q.getQuestion() %></p>

    <!-- assume Question has List<String> choices -->
    <%
        int optNum = 0;
        for (String choice : q.getChoices()) {
    %>
    <label>
        <input type="radio" name="answer" value="<%= optNum %>" required>
        <%= choice %>
    </label><br>
    <%
            optNum++;
        }
    %>
    <input type="hidden" name="questionId" value="<%= q.getId() %>"/>
    <button type="submit">Submit</button>
</form>

<% if (index + 1 < total) { %>
<form method="get" action="quiz">
    <input type="hidden" name="quizId" value="<%= quizId %>"/>
    <input type="hidden" name="onePerPage" value="true"/>
    <input type="hidden" name="index" value="<%= index + 1 %>"/>
    <button type="submit">Next ▶</button>
</form>
<% } %>
