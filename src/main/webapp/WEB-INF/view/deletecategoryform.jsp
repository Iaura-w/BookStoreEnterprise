<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Category</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>

<h2>DELETE CATEGORY</h2>
<form:form action="deleteCategory" modelAttribute="category" method="POST">

    <div>
        <table>
            <tr>
                <th>Name</th>
            </tr>
            <tr>
                <td>${category.name}</td>

                <td><label></label></td>
                <td><form:hidden path="id"/></td>

                <td><input type="submit" value="Delete"/></td>
            </tr>

        </table>
    </div>

</form:form>


<p>
    <button onclick="location.href='${pageContext.request.contextPath}/categories/list'">Return</button>
</p>
</body>
</html>
