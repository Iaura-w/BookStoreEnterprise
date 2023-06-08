<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/styles.css">
</head>
<body>
<nav>
    <div>
        <a href="${pageContext.request.contextPath}/books/list">Books</a>
        <a href="${pageContext.request.contextPath}/authors/list">Authors</a>
        <a href="${pageContext.request.contextPath}/categories/list">Categories</a>
        <sec:authorize access="hasRole('USER')">
            <a href="${pageContext.request.contextPath}/cart">Cart</a>
        </sec:authorize>
        <a href="${pageContext.request.contextPath}/orders">Orders</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</nav>
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
