<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
<table>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.get("id")}</td>
            <td><a href='/users/show?id=${user.get("id")}'>${user.get("firstName")} ${user.get("lastName")}</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
<!-- END -->
