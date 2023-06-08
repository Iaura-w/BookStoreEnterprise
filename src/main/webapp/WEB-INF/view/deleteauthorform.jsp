<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Authors</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/styles.css">
</head>
<body>
<nav>
    <div>
        <a href="${pageContext.request.contextPath}/books/list">Books</a>
        <a href="${pageContext.request.contextPath}/authors/list">Authors</a>
        <a href="${pageContext.request.contextPath}/categories/list">Categories</a>
        <sec:authorize access="hasRole('USER')">
            <a href="${pageContext.request.contextPath}/cart">Cart</a>
        </sec:authorize>
        <a href="${pageContext.request.contextPath}/orders">Orders</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</nav>
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
