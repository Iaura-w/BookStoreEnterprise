<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h2>Books:</h2>

<div>
    <table>
        <tr>
            <th>Authors</th>
            <th>Name</th>
            <th>Publisher</th>
            <th>Price</th>
            <th>Category</th>
            <sec:authorize access="hasRole('ADMIN')">
                <th></th>
                <th>Update</th>
                <th></th>
                <th>Delete</th>
            </sec:authorize>
            <sec:authorize access="hasRole('USER')">
                <th>Cart</th>
            </sec:authorize>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>

                <td>
                    <c:forEach var="author" items="${book.authors}">${author.name} ${author.lastName} <br/>
                    </c:forEach>
                </td>
                <td>${book.name}</td>
                <td>${book.publisher}</td>
                <td>${book.price}</td>
                <td>${book.category.name}</td>
                <sec:authorize access="hasRole('ADMIN')">
                    <td>
                        <c:url var="update" value="/books/updateBookForm">
                            <c:param name="bookId" value="${book.id}"/></c:url>
                    </td>
                    <td>
                        <button onclick="location.href='${update}'">UPDATE</button>
                    </td>

                    <td>
                        <c:url var="delete" value="/books/deleteBook">
                            <c:param name="bookId" value="${book.id}"/></c:url>
                    </td>
                    <td>
                        <button onclick="location.href='${delete}'">DELETE</button>
                    </td>
                </sec:authorize>

                <sec:authorize access="hasRole('USER')">
                    <td>
                        <form:form action="${pageContext.request.contextPath}/cart/add">
                            <input type="hidden" id="bookId" name="bookId" value="${book.id}"/>
                            <button type="submit">add to cart</button>
                        </form:form>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>

    </table>
</div>
<sec:authorize access="hasRole('ADMIN')">
    <div>
        <input type="button" value="Add Book"
               onclick="window.location.href='formadd';return false;"/>
    </div>
</sec:authorize>

</body>
</html>
