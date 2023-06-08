<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
ADD CATEGORY
<form:form action="addCategory" modelAttribute="category" method="POST">

    <table>
        <tbody>
        <tr>
            <td><label>name:</label></td>
            <td><form:input path="name"/></td>
        </tr>

        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save"/></td>
        </tr>


        </tbody>
    </table>


</form:form>


<p>
    <a href="${pageContext.request.contextPath}/categories/list "> return </a>
</p>
</body>
</html>
