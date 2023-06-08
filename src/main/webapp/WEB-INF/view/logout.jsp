<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Logout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/styles.css">
</head>
<body>
<nav>
    <div>
        <a href="${pageContext.request.contextPath}/books/list">Books</a>
        <a href="${pageContext.request.contextPath}/authors/list">Authors</a>
        <a href="${pageContext.request.contextPath}/categories/list">Categories</a>
        <sec:authorize access="hasRole('USER')">
            <a href="${pageContext.request.contextPath}/cart">Cart</a>
        </sec:authorize>
        <a href="${pageContext.request.contextPath}/orders">Orders</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</nav>
<p>Do you want to log out?</p>
<form:form method="post">
    <button type="submit">Logout</button>
</form:form>
</body>
</html>