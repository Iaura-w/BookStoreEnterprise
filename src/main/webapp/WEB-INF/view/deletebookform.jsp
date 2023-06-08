<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Books</title>
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
<h2>DELETE BOOK</h2>
<form:form action="deleteBook" modelAttribute="book" method="POST">

    <div>
        <table>
            <tr>
                <th>Name</th>
                <th>Publisher</th>
                <th>Price</th>
                <th>Category</th>
            </tr>
            <tr>
                <td>${book.name}</td>
                <td>${book.publisher}</td>
                <td>${book.price}</td>
                <td>${book.category.name}</td>

                <td><label></label></td>
                <td><form:hidden path="id"/></td>

                <td><input type="submit" value="Delete"/></td>
            </tr>

        </table>
    </div>

</form:form>


<p>
    <button onclick="location.href='${pageContext.request.contextPath}/books/list'">Return</button>
</p>
</body>
</html>
