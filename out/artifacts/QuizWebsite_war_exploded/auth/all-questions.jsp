<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Question" %>
<%@ page import="model.QuestionType" %>

<html>
<head>
  <title>Take Quiz - All Questions</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/questions.css">
</head>
<body>
<div class="container">
  <%
    model.Quiz quiz = (model.Quiz) request.getAttribute("quiz");
  %>
  <h2><%= quiz != null ? quiz.getTitle() : "Quiz" %></h2>

  <form action="<%= request.getContextPath() %>/submit-quiz" method="post">
    <input type="hidden" name="quizId" value="<%= quiz != null ? quiz.getId() : "" %>">

    <%
      List<Question> questions = (List<Question>) request.getAttribute("questions");
      if (questions != null && !questions.isEmpty()) {
        for (Question q : questions) {
    %>
    <div class="question-block">
      <p><b>Question:</b> <%= q.getQuestion() %></p>
      <input type="hidden" name="questionIds" value="<%= q.getId() %>">

      <% if (q.getQuestionType() == QuestionType.multiple_choice) { %>
      <!-- Example static options; replace with dynamic options from DB if available -->
      <input type="radio" name="answer_<%= q.getId() %>" value="Option1"> Option 1<br>
      <input type="radio" name="answer_<%= q.getId() %>" value="Option2"> Option 2<br>
      <input type="radio" name="answer_<%= q.getId() %>" value="Option3"> Option 3<br>

      <% } else if (q.getQuestionType() == QuestionType.picture_response_questions) { %>
      <input type="text" name="answer_<%= q.getId() %>" placeholder="Describe the picture..." required />

      <% } else { %>
      <input type="text" name="answer_<%= q.getId() %>" required />

      <% } %>
    </div>
    <hr/>
    <%      }
    } else { %>
    <p>No questions available.</p>
    <% } %>

    <button type="submit">Submit Quiz</button>
  </form>
</div>
</body>
</html>
