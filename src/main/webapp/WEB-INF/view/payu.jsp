<%@ page import="java.security.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PayU</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/styles.css">
    <script>
        function generate_signature(form, secondKey, posId) {
            var sortedValues = sortValuesByItsName(form);

            var content = "";
            for (var i = 0; i < sortedValues.length; i++) {
                var field = sortedValues[i];
                if (field.name !== "OpenPayu-Signature") {
                    content += field.name + "=" + encodeURIComponent(field.value) + "&";
                }
            }
            <c:forEach var="value" items="${sortedValues}">
            content += "${value.name}=" + encodeURIComponent("${value.value}") + "&";
            </c:forEach>

            content += secondKey;

            var algorithm = "SHA-256";
            var result = "sender=" + posId+";";
            result += "algorithm=" + algorithm + ";";
            result += "signature=" + calculateHash(content, algorithm);

            return result;
        }

        function sortValuesByItsName(form) {
            var values = [];
            var formElements = form.elements;
            for (var i = 0; i < formElements.length; i++) {
                var element = formElements[i];
                if (element.type === "hidden" && element.name !== "OpenPayu-Signature") {
                    values.push({
                        name: element.name,
                        value: element.value
                    });
                }
            }
            values.sort(function (a, b) {
                return a.name.localeCompare(b.name);
            });
            return values;
        }

        function calculateHash(content, algorithm) {
            var hash = CryptoJS.algo.SHA256.create();
            hash.update(content);
            var result = hash.finalize();
            return result.toString(CryptoJS.enc.Hex);
        }
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js"></script>
</head>
<body>
<%!
    public String generateSignature(Map<String, String> form, String secondKey, String algorithm, String posId) throws Exception {
        List<String> sortedValues = new ArrayList<>(form.values());
        Collections.sort(sortedValues);

        StringBuilder content = new StringBuilder();

        for (String value : sortedValues) {
            content.append(URLEncoder.encode(value, "UTF-8")).append("&");
        }

        content.append(secondKey);

        String signature;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = md.digest(content.toString().getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            signature = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Failed to generate signature: " + e.getMessage());
        }

        String result = "signature=" + signature + ";";
        result += "algorithm=" + algorithm + ";";
        result += "sender=" + posId;

        return result;
    }
%>
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
<form method="POST" action="https://secure.snd.payu.com/api/v2_1/orders" onsubmit="this['OpenPayu-Signature'].value = generate_signature(this, 'b6ca15b0d1020e8094d9b5f8d163db54', '300746');">
    <input type="hidden" name="customerIp" value="127.0.0.1">
    <input type="hidden" name="merchantPosId" value="300746">
    <input type="hidden" name="description" value="ksiazki">
    <input type="hidden" name="totalAmount" value="${order.price*100}">
    <input type="hidden" name="currencyCode" value="PLN">
    <c:forEach var="book" items="${order.books}" varStatus="count">
        <input type="hidden" name="products[${count.count-1}].name" value="${book.name}">
        <input type="hidden" name="products[${count.count-1}].unitPrice" value="${book.price}">
        <input type="hidden" name="products[${count.count-1}].quantity" value="1">
    </c:forEach>
    <input type="hidden" name="notifyUrl" value="http://shop.url/notify">
    <input type="hidden" name="continueUrl" value="${pageContext.request.contextPath}/orders">
    <input type="hidden" name="OpenPayu-Signature">
    <button type="submit" formtarget="_blank">Płacę z PayU</button>
</form>

<h2>Order details:</h2>
<div>
    <table>
        <tr>
            <th>Books</th>
            <th>Price</th>
            <th>Status</th>
        </tr>
        <tr>
            <td>
                <c:forEach var="book" items="${order.books}">${book.name}, </c:forEach>
            </td>
            <td>${order.price}</td>
            <td>${order.status}</td>
        </tr>
    </table>
</div>

</body>
</html>