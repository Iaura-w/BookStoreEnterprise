<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Category</title>
</head>
<body>
DELETE CATEGORY
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
    <a href="${pageContext.request.contextPath}/categories/list "> return </a>
</p>
</body>
</html>
