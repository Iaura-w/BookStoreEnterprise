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
            <sec:authorize access="hasRole('ADMIN')">
                <th></th>
                <th>Delete</th>
            </sec:authorize>
        </tr>
        <c:forEach var="author" items="${authors}">
            <tr>
                <td>${author.name}</td>
                <td>${author.lastName}</td>
                <sec:authorize access="hasRole('ADMIN')">
                    <td>
                        <c:url var="delete" value="/authors/deleteAuthor">
                            <c:param name="authorId" value="${author.id}"/></c:url>
                    </td>
                    <td><a href="${delete}">delete</a></td>
                </sec:authorize>
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
