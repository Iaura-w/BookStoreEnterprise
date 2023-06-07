<%--
  Created by IntelliJ IDEA.
  User: luke
  Date: 15.05.2019
  Time: 00:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List books</title>
</head>
<body>
        <h2>Books:</h2>

        <div>
            <table>
                <tr>
                    <th>Autorzy</th>
                    <th>Nazwa</th>
                    <th>Wydawnictwo</th>
                    <th>Cena</th>
                    <th>Kategoria</th>
                    <sec:authorize access="hasRole('USER')">
                    <th>Do koszyka</th>
                    </sec:authorize>
                </tr>
                <c:forEach var="book" items="${books}" >
                    <tr>

                        <td>
                        <c:forEach var="autor" items="${book.autorzy}">${autor.imie} ${autor.nazwisko} <br />
                        </c:forEach>
                        </td>
                        <td>${book.nazwa}</td>
                        <td>${book.wydawnictwo}</td>
                        <td>${book.cena}</td>
                        <td>${book.kategoria.nazwa}</td>
                        <td>
                        <c:url var="update" value="/books/updateBookForm2">
                        <c:param name="bookId" value="${book.id}"/></c:url>
                        </td>
                        <td><a href="${update}" >update</a> </td>

                        <td>
                        <c:url var="delete" value="/books/deleteBook">
                        <c:param name="bookId" value="${book.id}"/></c:url>
                        </td>
                        <td><a href="${delete}" >delete</a> </td>


                        <sec:authorize access="hasRole('USER')">
                        <td>
                        <form:form action="${pageContext.request.contextPath}/cart/add">
                        <input type="hidden" id="bookId" name="bookId" value="${book.id}"/>
                        <button type="submit" >Do koszyka</button>
                        </form:form>
                        </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>

            </table>
        </div>
        <div>
            <input type="button" value="Add Book"
                   onclick="window.location.href='formadd2';return false;" />
        </div>
</body>
</html>
