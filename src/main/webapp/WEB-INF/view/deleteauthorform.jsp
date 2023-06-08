<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Authors</title>
</head>
<body>
DELETE AUTHOR
<form:form action="deleteAuthor" modelAttribute="author" method="POST">

    <div>
        <table>
            <tr>
                <th>Name</th>
                <th>Last Name</th>
            </tr>
            <tr>
                <td>${author.name}</td>
                <td>${author.lastName}</td>

                <td><label></label></td>
                <td><form:hidden path="id"/></td>

                <td><input type="submit" value="Delete"/></td>
            </tr>

        </table>
    </div>

</form:form>


<p>
    <a href="${pageContext.request.contextPath}/authors/list "> return </a>
</p>
</body>
</html>
