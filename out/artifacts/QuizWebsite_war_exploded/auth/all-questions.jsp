<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
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
    List<Question> questions = (List<Question>) request.getAttribute("questions");
    Map<Integer, Boolean> correctnessMap = (Map<Integer, Boolean>) request.getAttribute("correctnessMap");
    boolean isImmediateCorrection = request.getAttribute("isImmediateCorrection") != null
            && (Boolean) request.getAttribute("isImmediateCorrection");

    if (quiz == null) {
  %>
  <h2>Quiz not found.</h2>
  <%
  } else {
  %>
  <h2><%= quiz.getTitle() %></h2>

  <form action="<%= request.getContextPath() %>/submit-quiz" method="post">
    <input type="hidden" name="quizId" value="<%= quiz.getId() %>">

    <%
      if (questions != null && !questions.isEmpty()) {
        for (Question q : questions) {
          Boolean isCorrect = correctnessMap != null ? correctnessMap.get(q.getId()) : null;
    %>
    <div class="question-block">
      <p><b>Question:</b> <%= q.getQuestion() %></p>
      <input type="hidden" name="questionIds" value="<%= q.getId() %>">

      <% if (q.getQuestionType() == QuestionType.multiple_choice) { %>
      <!-- Replace with dynamic options as needed -->
      <input type="radio" name="answer_<%= q.getId() %>" value="Option1"> Option 1<br>
      <input type="radio" name="answer_<%= q.getId() %>" value="Option2"> Option 2<br>
      <input type="radio" name="answer_<%= q.getId() %>" value="Option3"> Option 3<br>

      <% } else if (q.getQuestionType() == QuestionType.picture_response_questions) { %>
      <input type="text" name="answer_<%= q.getId() %>" placeholder="Describe the picture..." required />

      <% } else { %>
      <input type="text" name="answer_<%= q.getId() %>" required />

      <% } %>

      <% if (isImmediateCorrection && isCorrect != null) { %>
      <div style="margin-top: 8px; font-weight: bold;">
        <% if (isCorrect) { %>
        ✅ Correct Answer!
        <% } else { %>
        ❌ Incorrect Answer.
        <% } %>
      </div>
      <% } %>
    </div>
    <hr/>
    <%  }
    } else { %>
    <p>No questions available.</p>
    <% } %>

    <button type="submit">Submit Quiz</button>
  </form>
  <%
    }
  %>
</div>
</body>
</html>
