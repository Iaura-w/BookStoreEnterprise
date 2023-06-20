<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order information</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>

<h2>Order information:</h2>
<div>
    <p>${message}</p>
    <p>
        <button onclick="location.href='${pageContext.request.contextPath}/orders'">orders</button>
    </p>
</div>

</body>
</html>