<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Logout</title>
</head>
<body>
<nav>
    <div>
        <a href="${pageContext.request.contextPath}">Home</a>
    </div>
</nav>
<h3>Logout</h3>
<form:form method="post">
    <button type="submit">Logout</button>
</form:form>
</body>
</html>