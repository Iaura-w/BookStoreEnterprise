<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
DELETE BOOK
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
    <a href="${pageContext.request.contextPath}/books/list "> return </a>
</p>
</body>
</html>
