<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Categories</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h2>Categories:</h2>

<div>
    <table>
        <tr>
            <th>Name</th>
            <sec:authorize access="hasRole('ADMIN')">
                <th></th>
                <th>Update</th>
            </sec:authorize>
        </tr>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td>${category.name}</td>
                <sec:authorize access="hasRole('ADMIN')">
                    <td>
                        <c:url var="update" value="/categories/updateCategoryForm">
                            <c:param name="categoryId" value="${category.id}"/></c:url>
                    </td>
                    <td>
                        <button onclick="location.href='${update}'">UPDATE</button>
                    </td>
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
