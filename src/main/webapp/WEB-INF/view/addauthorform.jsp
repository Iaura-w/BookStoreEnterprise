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
    <title>Authors</title>
</head>
<body>
ADD AUTHOR
<form:form action="addAuthor" modelAttribute="author" method="POST">

    <table>
        <tbody>
        <tr>
            <td><label>name:</label></td>
            <td><form:input path="name"/></td>
        </tr>

        <tr>
            <td><label>last name:</label></td>
            <td><form:input path="lastName"/></td>
        </tr>

        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save"/></td>
        </tr>

        </tbody>
    </table>


</form:form>


<p>
    <a href="${pageContext.request.contextPath}/authors/list "> return </a>
</p>

</body>
</html>
