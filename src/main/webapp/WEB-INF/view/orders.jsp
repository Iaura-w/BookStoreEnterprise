<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
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
                <th colspan="2">Change status</th>
            </sec:authorize>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${dateFormat.format(order.dateTime)}</td>
                <td>
                    <c:forEach var="orderItem" items="${order.orderItems}">${orderItem.book.name} x${orderItem.quantity}
                        <br></c:forEach>
                </td>
                <td>${order.price}</td>
                <td>${order.status}</td>
                <sec:authorize access="hasRole('ADMIN')">
                    <td>${order.user.username}</td>
                    <td>
                        <form:form action="${pageContext.request.contextPath}/orders/complete">
                            <input type="hidden" id="orderId" name="orderId" value="${order.id}"/>
                            <c:choose>
                                <c:when test='${order.status.equals("COMPLETED")}'>
                                    <button disabled type="submit">COMPLETE</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit">COMPLETE</button>
                                </c:otherwise>
                            </c:choose>
                        </form:form>
                    </td>
                    <td>
                        <form:form action="${pageContext.request.contextPath}/orders/cancel">
                            <input type="hidden" id="orderId" name="orderId" value="${order.id}"/>
                            <c:choose>
                                <c:when test='${order.status.equals("CANCELED")}'>
                                    <button disabled type="submit">CANCEL</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit">CANCEL</button>
                                </c:otherwise>
                            </c:choose>
                        </form:form>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>
</div>
<p>
    <button onclick="location.href='${pageContext.request.contextPath}/books/list'">Return</button>
</p>
</body>
</html>
