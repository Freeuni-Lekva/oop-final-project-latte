<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>Registration</h2>
<form action="register" method="post">
    Username: <input type="text" name="username" required /><br>
    Password: <input type="password" name="password" required /><br>
    <input type="submit" value="Register"/>
</form>
<p style="color:red;"><%=request.getAttribute("error") != null ? request.getAttribute("error") : ""%></p>
</body>
</html>