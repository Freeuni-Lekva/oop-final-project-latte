
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="<%= request.getContextPath() %>/login" method="post">
    Username: <input type="text" name="username" required /><br>
    Password: <input type="password" name="password" required /><br>
    <input type="submit" value="Login"/>
</form>
    <p style="color:red;"><%=request.getAttribute("error") != null ? request.getAttribute("error") : ""%></p>
    <p>Don't have an account? <a href="<%=request.getContextPath()%>/auth/registration.jsp">Register here</a></p>
</body>
</html>
