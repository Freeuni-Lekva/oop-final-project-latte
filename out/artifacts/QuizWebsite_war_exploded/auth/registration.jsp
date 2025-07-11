<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>Registration</h2>
<form action="registration" method="post">
    Username: <input type="text" name="username" required /><br>
    Password: <input type="password" name="password" required /><br>
    <input type="submit" value="Register"/>
</form>
<p style="color:red;"><%=request.getAttribute("error") != null ? request.getAttribute("error") : ""%></p>
<p>Already have an account? <a href="<%=request.getContextPath()%>/auth/login.jsp">Login here</a></p>
</body>
</html>