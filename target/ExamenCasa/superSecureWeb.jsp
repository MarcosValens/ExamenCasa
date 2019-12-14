<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Super Secure Web</title>
</head>
<body>
<h1>Secure Web</h1>
<c:if test="${secret}">
    <h1>${secretWord}</h1>
</c:if>
</body>
</html>
