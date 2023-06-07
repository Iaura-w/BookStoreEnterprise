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
<form:form action="saveBook" modelAttribute="book" method="POST">

    <table>
        <tbody>
        <td><form:hidden path="id"/></td>
        <tr>
            <td><label>name:</label></td>
            <td><form:input path="name"/></td>
        </tr>

        <tr>
            <td><label>publisher:</label></td>
            <td><form:input path="publisher"/></td>
        </tr>

        <tr>
            <td><label>price:</label></td>
            <td><form:input path="price"/></td>
        </tr>
        <tr>
            <!-- <td><form:input path="category" /></td> -->
        </tr>

        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save"/></td>
        </tr>

        </tbody>
    </table>


</form:form>


<p>
    <a href="${pageContext.request.contextPath}/books/list "> return </a>
</p>
</body>
</html>
