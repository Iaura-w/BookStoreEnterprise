<%--
  Created by IntelliJ IDEA.
  User: luke
  Date: 15.05.2019
  Time: 00:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>books</title>
</head>
<body>
DELETE BOOK
<form:form action="deleteBook" modelAttribute="book" method="POST">

    <table>
        <tr>
            <th>Nazwa</th>
            <th>Wydawnictwo</th>
            <th>Cena</th>
            <th>Kategoria</th>
        </tr>
        <tr>
        <td>${book.nazwa}</td>
        <td>${book.wydawnictwo}</td>
        <td>${book.cena}</td>
        <td>${book.kategoria.nazwa}</td>

        <td><label></label></td>
        <td><form:hidden path="id"/></td>

        <td><input type="submit" value="Delete"  /></td>
        </tr>

    </table>


</form:form>


<p>
    <a href="${pageContext.request.contextPath}/books/list "> return  </a>
</p>
</body>
</html>
