<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/styles.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h2>ADD BOOK</h2>
<form:form action="saveBook" modelAttribute="bookDTO" method="POST">

    <td><form:hidden path="id"/></td>
    <table>
        <tbody>
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
            <td>
                <form:select path="categoryid">
                    <form:options items="${categories}" itemValue="id" itemLabel="name"/>
                </form:select>
            </td>
        </tr>

        <tr>
            <td>
                <form:select path="authorsIds">
                    <form:options items="${authors}" itemValue="id" itemLabel="lastName"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save"/></td>
        </tr>
        </tbody>
    </table>


</form:form>


<p>
    <button onclick="location.href='${pageContext.request.contextPath}/books/list'">Return</button>
</p>
</body>
</html>
