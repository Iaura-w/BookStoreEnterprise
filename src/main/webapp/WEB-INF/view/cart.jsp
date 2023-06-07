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
    <title>Cart</title>
</head>
<body>
        <h2>Cart:</h2>

        <div>
            <table>
                <tr>
                    <th>Autorzy</th>
                    <th>Nazwa</th>
                    <th>Wydawnictwo</th>
                    <th>Cena</th>
                    <th>Kategoria</th>
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
                        <form:form action="${pageContext.request.contextPath}/cart/delete">
                        <input type="hidden" id="bookId" name="bookId" value="${book.id}"/>
                        <button type="submit">Usuń</button>
                        </form:form>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
        <div>
            <input type="button" value="Zamów"
                   onclick="window.location.href='order';return false;" />
        </div>
</body>
</html>
