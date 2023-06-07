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
    <title>List categories</title>
</head>
<body>
        <h2>Categories:</h2>

        <div>
            <table>
                <tr>
                    <th>Nazwa</th>
                </tr>
                <c:forEach var="categories" items="${categories}" >
                    <tr>
                        <td>${categories.nazwa}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div>
            <input type="button" value="Add Category"
                   onclick="window.location.href='formadd';return false;" />
        </div>
</body>
</html>
