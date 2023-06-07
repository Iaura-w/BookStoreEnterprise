<%--
  Created by IntelliJ IDEA.
  User: luke
  Date: 15.05.2019
  Time: 00:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<h2>Books:</h2>

<div>
    <table>
        <tr>
            <th>Authors</th>
            <th>Name</th>
            <th>Publisher</th>
            <th>Price</th>
            <th>Category</th>
            <th></th>
            <th>Update</th>
            <th></th>
            <th>Delete</th>
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
                <td>
                    <c:url var="update" value="/books/updateBookForm2">
                        <c:param name="bookId" value="${book.id}"/></c:url>
                </td>
                <td><a href="${update}">update</a></td>

                <td>
                    <c:url var="delete" value="/books/deleteBook">
                        <c:param name="bookId" value="${book.id}"/></c:url>
                </td>
                <td><a href="${delete}">delete</a></td>


                <sec:authorize access="hasRole('USER')">
                    <td>
                        <form:form action="${pageContext.request.contextPath}/cart/add">
                            <input type="hidden" id="bookId" name="bookId" value="${book.id}"/>
                            <button type="submit">add too cart</button>
                        </form:form>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>

    </table>
</div>
<div>
    <input type="button" value="Add Book"
           onclick="window.location.href='formadd2';return false;"/>
</div>
<div>
    <table>
        <tr>
            <td><a href="${pageContext.request.contextPath}/authors/list">Authors</a></td>
            <td><a href="${pageContext.request.contextPath}/categories/list">Categories</a></td>
            <td><a href="${pageContext.request.contextPath}/cart">Cart</a></td>
            <td><a href="${pageContext.request.contextPath}/logout">Logout</a></td>
        </tr>
    </table>

</div>
</body>
</html>
