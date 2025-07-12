<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz Result</title>
</head>
<body>
<h2>Quiz Result: <%= ((model.Quiz) request.getAttribute("quiz")).getTitle() %></h2>

<p>Correct Answers: <%= request.getAttribute("correctAnswers") %></p>
<p>Total Questions: <%= request.getAttribute("totalQuestions") %></p>

<a href="<%= request.getContextPath() %>/dashboard.jsp">Back to Dashboard</a>
</body>
</html>
