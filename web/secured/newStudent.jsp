<%-- 
    Document   : newStudent
    Created on : Dec 8, 2018, 6:05:42 PM
    Author     : mattm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/body.css">
    </head>
    <body>
        <ul>
            <form action="${pageContext.request.contextPath}/AdminTools" method="post" class="nav">
                <input type="hidden" name="action" value="home">
                <input type="submit" name="submit" value="Home" class="nav">
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
                <input type="submit" name="submit" value="New Student" class="nav active">
            </form>
        </ul>

        <h1> Register a new student. </h1>

        <div>
            <div class="inputs_div">
                <form action="${pageContext.request.contextPath}/AdminTools" method="post" 
                      class='input_form'>
                    Name<br/>
                    <input type="text" placeholder="Enter student's name..." name="name"><br/>
                    Instructor<br/>
                    <select required name="instructor">
                        <option value="" disabled selected hidden>Please choose...</option>
                        <c:forEach var="instructor" items="${requestScope.instructorArr}" varStatus="loopStatus">
                            <option value="${instructor.id}">${instructor.name}</option>
                        </c:forEach>   
                    </select><br/>
                    Instrument<br/>
                    <select required name="instrument">
                        <option value="" disabled selected hidden>Please choose...</option>
                        <c:forEach var="instructor" items="${requestScope.instructorArr}" varStatus="loopStatus">
                            <option value="${instructor.instrumentID}">${instructor.instrument}</option>
                        </c:forEach>   
                    </select><br/>
                    <input type="hidden" name ="action" value="newStudent">
                    <input type="submit" name="submit" value="Submit">
                </form>
            </div>
        </div>

        <c:if test="${not empty errorMsg}">
            Error: ${requestScope.errorMsg}
        </c:if>
    </body>
</html>
