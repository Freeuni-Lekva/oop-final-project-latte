<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, model.Question" %>
<%
    List<Question> questions = (List<Question>) request.getAttribute("questions");
%>

<h2>Fill in the blanks</h2>
<form action="submit-all-answers" method="post">
    <ol>
        <% for (Question q : questions) { %>
        <li>
            <p><%= q.getQuestion() %></p>
            <input type="text" name="answer_<%= q.getId() %>" required />
            <input type="hidden" name="questionIds" value="<%= q.getId() %>" />
        </li>
        <% } %>
    </ol>
    <button type="submit">Submit All</button>
</form>
