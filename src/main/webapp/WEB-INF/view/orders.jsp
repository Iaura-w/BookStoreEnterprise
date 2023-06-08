<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<nav>
    <div>
        <a href="${pageContext.request.contextPath}">Home</a>
    </div>
</nav>
<h2>Orders:</h2>
<div>
    <table>
        <tr>
            <th>Date</th>
            <th>Books</th>
            <th>Price</th>
            <th>Status</th>
            <sec:authorize access="hasRole('ADMIN')">
                <th>User</th>
                <th>Change status</th>
            </sec:authorize>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${dateFormat.format(order.dateTime)}</td>
                <td>
                    <c:forEach var="book" items="${order.books}">${book.name}  </c:forEach>
                </td>
                <td>${order.price}</td>
                <td>${order.status}</td>
                <sec:authorize access="hasRole('ADMIN')">
                    <td>${order.user.username}</td>
                    <td>
                        <form:form action="${pageContext.request.contextPath}/orders/complete">
                            <input type="hidden" id="orderId" name="orderId" value="${order.id}"/>
                            <button type="submit">COMPLETE</button>
                        </form:form>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>
</div>
<p>
    <a href="${pageContext.request.contextPath}/books/list "> return </a>
</p>
</body>
</html>
