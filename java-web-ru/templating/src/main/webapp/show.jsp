<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
<table>
        <tr>
            <td>${user.get("id")}</td>
            <td>${user.get("firstName")} ${user.get("lastName")}</td>
            <td>${user.get("email")}</td>
            <td>
                <a href='/users/delete?id=${user.get("id")}'>Удалить</a>
            </td>
        </tr>
</table>
</body>
</html>
<!-- END -->
