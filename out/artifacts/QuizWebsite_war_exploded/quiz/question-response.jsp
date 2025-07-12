<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Question" %>

<html>
<head>
  <title>Quiz Questions</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/answer.css">
</head>
<body>
<h2>Quiz Questions</h2>
<%
  List<Question> questions = (List<Question>) request.getAttribute("questions");
  int quizId = 0;
  Object quizIdObj = request.getAttribute("quizId");
  if (quizIdObj != null) {
    quizId = (Integer) quizIdObj;
  } else if (request.getParameter("quizId") != null) {
    quizId = Integer.parseInt(request.getParameter("quizId"));
  }
%>

<form action="<%=request.getContextPath()%>/submit-quiz" method="post">
  <input type="hidden" name="quizId" value="<%=quizId%>"/>

  <%
    if (questions != null && !questions.isEmpty()) {
      for (Question q : questions) {
  %>
  <div>
    <p><b>Question:</b> <%= q.getQuestion() %></p>
    <input type="text" name="answer_<%= q.getId() %>" required />
  </div>
  <hr/>
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
</body>
</html>
