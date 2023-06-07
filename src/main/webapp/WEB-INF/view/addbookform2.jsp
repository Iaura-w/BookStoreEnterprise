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
ADD BOOK
<form:form action="saveBook2" modelAttribute="bookDTO" method="POST">

    <td><form:hidden path="id"/></td>
    <table>
        <tbody>
        <tr>
            <td><label>nazwa:</label></td>
            <td><form:input path="nazwa" /></td>
        </tr>

        <tr>
            <td><label>wydawnictwo:</label></td>
            <td><form:input path="wydawnictwo" /></td>
        </tr>

        <tr>
            <td><label>cena:</label></td>
            <td><form:input path="cena" /></td>
        </tr>
        <tr>

            <td>
                <form:select path="kategoriaid">
                    <form:options items="${categories}" itemValue="id" itemLabel="nazwa"/>
                </form:select>

            </td>

        </tr>

        <tr>

            <td>
                <form:select path="authorsIds">
                    <form:options items="${authors}" itemValue="id" itemLabel="nazwisko"/>
                </form:select>

            </td>

        </tr>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save"  /></td>
        </tr>


        </tbody>
    </table>


</form:form>


<p>
    <a href="${pageContext.request.contextPath}/books/list "> return  </a>
</p>
</body>
</html>
