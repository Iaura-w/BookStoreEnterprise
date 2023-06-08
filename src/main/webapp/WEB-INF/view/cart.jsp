<%--
  Created by IntelliJ IDEA.
  User: luke
  Date: 15.05.2019
  Time: 00:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<nav>
    <div>
        <a href="${pageContext.request.contextPath}">Home</a>
    </div>
</nav>
<h2>Cart:</h2>

<div>
    <table>
        <tr>
            <th>Authors</th>
            <th>Name</th>
            <th>Publisher</th>
            <th>Price</th>
            <th>Category</th>
            <th>Delete</th>
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
                    <form:form action="${pageContext.request.contextPath}/cart/delete">
                        <input type="hidden" id="bookId" name="bookId" value="${book.id}"/>
                        <button type="submit">delete</button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>Final price: ${finalPrice}</td>
        </tr>
    </table>
</div>
<div>
    <form:form action="${pageContext.request.contextPath}/orders" method="post">
        <c:choose>
            <c:when test="${finalPrice<=(0.0)}">
                <button disabled type="submit">order</button>
            </c:when>
            <c:otherwise>
                <button type="submit">order</button>
            </c:otherwise>
        </c:choose>
    </form:form>
    <%--    <input type="button" value="Order"--%>
    <%--           onclick="window.location.href='order';return false;"/>--%>
</div>
</body>
</html>
