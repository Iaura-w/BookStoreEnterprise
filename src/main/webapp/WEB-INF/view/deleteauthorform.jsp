<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Authors</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>

<h2>DELETE AUTHOR</h2>
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
    <button onclick="location.href='${pageContext.request.contextPath}/authors/list'">Return</button>
</p>
</body>
</html>
