<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Authors</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h2>ADD AUTHOR</h2>
<form:form action="addAuthor" modelAttribute="author" method="POST">

    <table>
        <td><form:hidden path="id"/></td>
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
    <button onclick="location.href='${pageContext.request.contextPath}/authors/list'">Return</button>
</p>

</body>
</html>
