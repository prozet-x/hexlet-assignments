<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete user</title>
</head>
<body>
Вы действительно хотите удалить пользователя ${user.get("firstName")} ${user.get("lastName")} c ID ${user.get("id")}?
<form action='/users/delete?id=${user.get("id")}' method="post">
    <button type="submit" class="btn btn-danger">Удалить</button>
</form>
</body>
</html>
<!-- END -->
