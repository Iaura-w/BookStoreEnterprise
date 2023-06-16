<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PayU</title>
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
<h2>Order details:</h2>
<div>
    <table>
        <tr>
            <th>Books</th>
            <th>Price</th>
            <th>Status</th>
        </tr>
        <tr>
            <td>
                <c:forEach var="book" items="${order.books}">${book.name}, </c:forEach>
            </td>
            <td>${order.price}</td>
            <td>${order.status}</td>
        </tr>
    </table>
</div>
<div>
    <p>Pay for order</p>
    <form:form action="${redirectUri}">
        <button type="submit">PayU</button>
    </form:form>
</div>

</body>
</html>