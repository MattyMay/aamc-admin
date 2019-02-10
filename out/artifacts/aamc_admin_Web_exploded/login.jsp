<%-- 
    Document   : login
    Created on : Dec 8, 2018, 6:45:35 PM
    Author     : mattm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
        <title>login</title>
    </head>
    <body>
        <div>
            <form action="AdminTools" method="post">
                <input type="text" placeholder="username" name="user" class="login"/><br>
                <input type="password" placeholder="password" name="pass" /><br>
                <input type="hidden" name="from" value="${requestScope.from}">
                <input type="submit" name="action" value="login" class="login_submit"/><br>
            </form>
        </div>
        ${requestScope.errorMsg}
    </body>
</html>
