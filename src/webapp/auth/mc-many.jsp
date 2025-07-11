<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, model.Question" %>
<%
    List<Question> qs = (List<Question>) request.getAttribute("questions");
%>

<h2>Multiple‑choice quiz</h2>
<form action="submit-all-answers" method="post">
    <ol>
        <% for (Question q : qs) { %>
        <li>
            <p><%= q.getQuestion() %></p>
            <%
                int i = 0;
                for (String choice : q.getChoices()) {
            %>
            <label>
                <input type="radio" name="answer_<%= q.getId() %>" value="<%= i %>" required>
                <%= choice %>
            </label><br>
            <%
                    i++;
                }
            %>
        </li>
        <% } %>
    </ol>
    <button type="submit">Submit All</button>
</form>
