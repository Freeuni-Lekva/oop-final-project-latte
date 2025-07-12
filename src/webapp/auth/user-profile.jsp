<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%@ page import="model.Quiz" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Timestamp" %>

<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<div class="container">
    <%
        User profileUser = (User) request.getAttribute("profileUser");
        if (profileUser == null) {
            profileUser = new User("demoUser", 1, "demoPassword", "demoHash", new Timestamp(System.currentTimeMillis()));
        }

        String friendshipStatus = (String) request.getAttribute("friendshipStatus");
        if (friendshipStatus == null) {
            friendshipStatus = "none"; // Possible values: none, waiting, accepted, rejected
        }

        List<Quiz> allQuizzes = (List<Quiz>) request.getAttribute("allQuizzes");
        if (allQuizzes == null) {
            allQuizzes = new ArrayList<>();
            allQuizzes.add(new Quiz(1, "Sample Quiz 1", "Demo Description 1", profileUser.getId(), false, true, false, false));
            allQuizzes.add(new Quiz(2, "Sample Quiz 2", "Demo Description 2", profileUser.getId(), true, false, true, false));
        }
    %>

    <h2><%= profileUser.getUsername() %>'s Profile</h2>
    <p>Joined: <%= profileUser.getTimestamp() %></p>

    <% if ("none".equals(friendshipStatus)) { %>
    <form action="<%= request.getContextPath() %>/send-friend-request" method="post">
        <input type="hidden" name="friendId" value="<%= profileUser.getId() %>">
        <button type="submit">Add Friend</button>
    </form>
    <% } else if ("waiting".equals(friendshipStatus)) { %>
    <p>Friend request sent. Waiting for response.</p>
    <% } else if ("accepted".equals(friendshipStatus)) { %>
    <p>You are already friends.</p>
    <% } %>

    <h3>Send Message</h3>
    <form action="<%= request.getContextPath() %>/send-message" method="post">
        <input type="hidden" name="receiverId" value="<%= profileUser.getId() %>">
        <textarea name="message" placeholder="Write your message here..."></textarea>
        <button type="submit">Send Text Message</button>
    </form>

    <h3>Send Challenge</h3>
    <form action="<%= request.getContextPath() %>/send-challenge" method="post">
        <input type="hidden" name="receiverId" value="<%= profileUser.getId() %>">
        <select name="quizId" required>
            <% for (Quiz quiz : allQuizzes) { %>
            <option value="<%= quiz.getId() %>"><%= quiz.getTitle() %></option>
            <% } %>
        </select>
        <button type="submit">Send Challenge</button>
    </form>

    <h3>Send Notification</h3>
    <form action="<%= request.getContextPath() %>/send-notification" method="post">
        <input type="hidden" name="receiverId" value="<%= profileUser.getId() %>">
        <input type="text" name="notificationText" placeholder="Notification text" required />
        <button type="submit">Send Notification</button>
    </form>
</div>
</body>
</html>
