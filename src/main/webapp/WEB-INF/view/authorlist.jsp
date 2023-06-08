<%--
  Created by IntelliJ IDEA.
  User: luke
  Date: 15.05.2019
  Time: 00:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors</title>
</head>
<body>
<nav>
    <div>
        <a href="${pageContext.request.contextPath}">Home</a>
    </div>
</nav>
<h2>Authors:</h2>
<div>
    <table>
        <tr>
            <th>Name</th>
            <th>Last name</th>
        </tr>
        <c:forEach var="author" items="${authors}">
            <tr>
                <td>${author.name}</td>
                <td>${author.lastName}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<sec:authorize access="hasRole('ADMIN')">
    <div>
        <input type="button" value="Add Author"
               onclick="window.location.href='formadd';return false;"/>
    </div>
</sec:authorize>
</body>
</html>
