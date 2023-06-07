<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Register</title>
</head>
<body>
<nav>
    <div>
        <a href="${pageContext.request.contextPath}">Home</a>
    </div>
</nav>
<h3>Register</h3>
<c:if test="${not empty validator}"><p><c:out value="${validator}"/></p></c:if>
<form:form method="post" modelAttribute="user">
    <table>
        <tbody>
        <tr>
            <td><label for="username">Username:</label></td>
            <td><form:input path="username" id="username" class="form-control"/></td>
        </tr>
        <tr>
            <td><label for="password">Password:</label></td>
            <td><form:password path="password" id="password" class="form-control"/></td>
        </tr>
        <tr>
            <td>
                <button type="submit">Register</button>
            </td>
        </tr>
        </tbody>
    </table>
</form:form>
</body>
</html>