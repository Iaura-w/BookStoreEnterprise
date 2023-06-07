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
    <title>List authors</title>
</head>
<body>
        <h2>Authors:</h2>

        <div>
            <table>
                <tr>
                    <th>Imie</th>
                    <th>Nazwisko</th>
                </tr>
                <c:forEach var="authors" items="${authors}" >
                    <tr>
                        <td>${authors.imie}</td>
                        <td>${authors.nazwisko}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div>
            <input type="button" value="Add Author"
                   onclick="window.location.href='formadd';return false;" />
        </div>
</body>
</html>
