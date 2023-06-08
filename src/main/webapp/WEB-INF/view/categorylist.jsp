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
            <sec:authorize access="hasRole('ADMIN')">
                <th></th>
                <th>Delete</th>
            </sec:authorize>
        </tr>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td>${category.name}</td>
                <sec:authorize access="hasRole('ADMIN')">
                    <td>
                        <c:url var="delete" value="/categories/deleteCategory">
                            <c:param name="categoryId" value="${category.id}"/></c:url>
                    </td>
                    <td><a href="${delete}">delete</a></td>
                </sec:authorize>
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
