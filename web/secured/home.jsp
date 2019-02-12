<%-- 
    Document   : home
    Created on : Dec 8, 2018, 8:54:39 PM
    Author     : mattm
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/body.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">
        
    </head>
    <body>
        <ul>
            <form action="${pageContext.request.contextPath}/AdminTools" method="post" class="nav">
                <input type="hidden" name="action" value="home">
                <input type="submit" name="submit" value="Home" class="nav active">
            </form>
            <form action="${pageContext.request.contextPath}/AdminTools" method="post" class="nav">
                <input type="hidden" name="action" value="addPayment">
                <input type="submit" name="submit" value="Add Payment" class="nav">
            </form>
            <form action="${pageContext.request.contextPath}/AdminTools" method="post" class="nav">
                <input type="hidden" name="action" value="viewPayments">
                <input type="submit" name="submit" value="View Payments" class="nav">
            </form>
            <form action="${pageContext.request.contextPath}/AdminTools" method="post" class="nav">
                <input type="hidden" name ="action" value="newStudent">
                <input type="submit" name="submit" value="New Student" class="nav">
            </form>
        </ul>
        <h1>Hello ${sessionScope.user}!</h1>
        <c:if test="${not empty errorMsg}">
            Error: ${requestScope.errorMsg}
        </c:if>
    </body>
</html>