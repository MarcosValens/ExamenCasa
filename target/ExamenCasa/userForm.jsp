<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>User Form</title>
</head>
<body>
<form action="addUser" method="post" enctype="multipart/form-data">
    <input type="hidden" name="userName" value="${user.getUserName()}">
    <label for="userName">User Name:
        <br>
        <input type="text" id="userName" name="userNameInput" value="${user.getUserName()}">
    </label>
    <br>
    <label for="pwd">Password:
        <br>
        <input type="text" id="pwd" name="pwd">
    </label>
    <br>
    <label for="school">School:
        <br>
        <select name="school" id="school">
            <c:forEach var="school" items="${schools}">
                <option value="${school.getId()}"
                        <c:if test="${school.getName()==user.getSchool().getName()}">selected</c:if>>${school.getName()}
                </option>
            </c:forEach>
        </select>
    </label>
    <br>
    <label for="name">Name:
        <br>
        <input type="text" id="name" name="name" value="${user.getName()}">
    </label>
    <br>
    <label for="secondName">Second Name:
        <br>
        <input type="text" id="secondName" name="secondName" value="${user.getSecondName()}">
    </label>
    <br>
    <label for="rol">Rol:
        <br>
        <input type="text" id="rol" name="rol" value="${user.getRol()}">
    </label>
    <br>
    <c:if test="${picture}">
        File:
        <input type="file" name="file">
        <br>
    </c:if>
    <input type="submit" value="Continue">
</form>
</body>
</html>
