<%--
  Created by IntelliJ IDEA.
  User: luke
  Date: 15.05.2019
  Time: 00:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
<nav>
    <div>
        <a href="${pageContext.request.contextPath}">Home</a>
    </div>
</nav>
<h2>Categories:</h2>

<div>
    <table>
        <tr>
            <th>Name</th>
        </tr>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td>${category.name}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<sec:authorize access="hasRole('ADMIN')">
    <div>
        <input type="button" value="Add Category"
               onclick="window.location.href='formadd';return false;"/>
    </div>
</sec:authorize>
</body>
</html>
