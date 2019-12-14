<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${errorValidation}">
    <h1>ERROR VALIDATION!</h1>
</c:if>
<form action="loginForm" method="post">
    <label for="userName">User Name
        <br>
        <input type="text" id="userName" name="userName">
    </label>
    <br>
    <label for="pwd">Password
        <br>
        <input type="text" id="pwd" name="pwd">
    </label>
    <br>
    <label for="school">School:
        <br>
        <select name="schoolId" id="school">
            <c:forEach var="school" items="${schools}">
                <option value="${school.getId()}">${school.getName()}</option>
            </c:forEach>
        </select>
    </label>
    <br>
    <input type="submit" value="Login">
</form>
</body>
</html>
