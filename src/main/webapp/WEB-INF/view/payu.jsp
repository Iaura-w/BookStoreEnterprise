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
<jsp:include page="navbar.jsp"/>

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
                <c:forEach var="orderItem" items="${order.orderItems}">${orderItem.book.name} x${orderItem.quantity}
                    <br></c:forEach>
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