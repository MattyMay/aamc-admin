<%-- 
    Document   : addPayment
    Created on : Dec 8, 2018, 6:05:17 PM
    Author     : mattm
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/body.css">
    </head>
    <jsp:useBean id="now" class="java.util.Date" />
    <fmt:formatDate var="year" value="${now}" pattern="yyyy" />
    <fmt:formatDate var="month" value="${now}" pattern="MMMM" />
    <body>

        <ul>
            <form action="${pageContext.request.contextPath}/AdminTools" method="post" class="nav">
                <input type="hidden" name="action" value="home">
                <input type="submit" name="submit" value="Home" class="nav">
            </form>             
            <form action="${pageContext.request.contextPath}/AdminTools" method="post" class="nav">
                <input type="hidden" name="action" value="addPayment">
                <input type="submit" name="submit" value="Add Payment" class="nav active">
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
        <h1>Log payments.</h1>
        <div>
            <div class="inputs_div">
                <form action="${pageContext.request.contextPath}/AdminTools" 
                      method="post" class='input_form'>
                    Student<br/>
                    <select required name="student">
                        <option value="%" selected hidden>Please choose...</option>
                        <c:forEach var="student" items="${requestScope.studentArr}" varStatus="loopStatus">
                            <option value="${student.id}">${student.name}</option>
                        </c:forEach>   
                    </select>
                    Instructor<br/>
                    <select required name="instructor">
                        <option value="" disabled selected hidden>Please choose...</option>
                        <c:forEach var="instructor" items="${requestScope.instructorArr}" varStatus="loopStatus">
                            <option value="${instructor.id}">${instructor.name}</option>
                        </c:forEach>   
                    </select>
                    <br/>Number of lessons<br/>
                    <select name="number_lessons">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4" selected>4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                    </select>
                    <br/>Month<br/>
                    <select name="month">
                        <option <c:if test="${pageScope.month == 'January'}">selected</c:if> value="1"> 
                                January</option>
                            <option <c:if test="${pageScope.month == 'February'}">selected</c:if> value="2"> 
                                February</option>
                            <option <c:if test="${pageScope.month == 'March'}">selected</c:if> value="3"> 
                                March</option>
                            <option <c:if test="${pageScope.month == 'April'}">selected</c:if> value="4"> 
                                April</option>
                            <option <c:if test="${pageScope.month == 'May'}">selected</c:if> value="5"> 
                                May</option>
                            <option <c:if test="${pageScope.month == 'June'}">selected</c:if> value="6"> 
                                June</option>
                            <option <c:if test="${pageScope.month == 'July'}">selected</c:if> value="7"> 
                                July</option>
                            <option <c:if test="${pageScope.month == 'August'}">selected</c:if> value="8"> 
                                August</option>
                            <option <c:if test="${pageScope.month == 'September'}">selected</c:if> value="9"> 
                                September</option>
                            <option <c:if test="${pageScope.month == 'October'}">selected</c:if> value="10"> 
                                October</option>
                            <option <c:if test="${pageScope.month == 'November'}">selected</c:if> value="11"> 
                                November</option>
                            <option <c:if test="${pageScope.month == 'December'}">selected</c:if> value="12"> 
                                December</option>
                        </select>
                        <br/>
                        Year<br/>
                        <select name="year">
                            <option value=${pageScope.year - 2}>
                            ${pageScope.year - 2}</option>
                        <option value=${pageScope.year - 1}>
                            ${pageScope.year - 1}</option>
                        <option selected value=${pageScope.year}>
                            ${pageScope.year}</option>
                        <option value=${pageScope.year + 1}>
                            ${pageScope.year + 1}</option>
                        <option value=${pageScope.year + 2}>
                            ${pageScope.year + 2}</option>
                    </select><br/>
                    <input type="hidden" name ="action" value="addPayment">
                    <input type="submit" name="submit" value="Submit">
                </form>
            </div>
            <div class='outputs_div'>


            </div>
        </div>

        <c:if test="${not empty errorMsg}">
            Error: ${requestScope.errorMsg}
        </c:if>
    </body>
</html>
