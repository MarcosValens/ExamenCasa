<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table>
    <tr>
        <th> User Name</th>
        <th> School</th>
        <th> Name</th>
        <th> Second Name</th>
        <th> Rol</th>
        <th> Picture</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.getUserName()}</td>
            <td>${user.getSchool().getName()}</td>
            <td>${user.getName()}</td>
            <td>${user.getSecondName()}</td>
            <td>${user.getRol()}</td>
            <td><c:if test="${not empty user.getPicture()}"><img src="${user.getPicture()}" alt="userPicture"></c:if></td>
            <td>
                <button><a href="addUser?userName=${user.getUserName()}">Edit</a></button>
            </td>
            <form action="users" method="post">
                <input type="hidden" name="userName" value="${user.getUserName()}">
                <td><input type="submit" value="Delete"></td>
            </form>
        </tr>
    </c:forEach>
</table>
<button><a href="addUser">New User</a></button>
</body>
</html>
