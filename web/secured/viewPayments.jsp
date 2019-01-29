<%-- 
    Document   : viewPayments
    Created on : Dec 8, 2018, 6:05:56 PM
    Author     : mattm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" 
              href="${pageContext.request.contextPath}/css/navbar.css">
        <link rel="stylesheet" type="text/css" 
              href="${pageContext.request.contextPath}/css/body.css">
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
                <input type="submit" name="submit" value="Add Payment" class="nav">
            </form>               
            <form action="${pageContext.request.contextPath}/AdminTools" method="post" class="nav">
                <input type="hidden" name="action" value="viewPayments">
                <input type="submit" name="submit" value="View Payments" class="nav active">
            </form>
            <form action="${pageContext.request.contextPath}/AdminTools" method="post" class="nav">
                <input type="hidden" name ="action" value="newStudent">
                <input type="submit" name="submit" value="New Student" class="nav">
            </form>
        </ul>
        <h1>Set filters to find payment history.</h1>

        <div>

            <div class="inputs_div">
                <form action="${pageContext.request.contextPath}/AdminTools" 
                      method ="post" class='input_form'>
                    Filter by student...<br>
                    <select required name="student">
                        <option value='%' selected hidden>Please choose...</option>
                        <c:forEach var="student" items="${requestScope.studentArr}" 
                                   varStatus="loopStatus">
                            <option value="${student.id}">${student.name}</option>
                        </c:forEach>   
                    </select><input type="checkbox" name="filter" value="student">
                    <br>Filter by instructor...<br>
                    <select required name="instructor">
                        <option value="%"  selected hidden>Please choose...</option>
                        <c:forEach var="instructor" items="${requestScope.instructorArr}" 
                                   varStatus="loopStatus">
                            <option value="${instructor.id}">${instructor.name}</option>
                        </c:forEach>   
                    </select><input type="checkbox" name="filter" value="instructor">
                    <br>Filter by month...<br>
                    <select name="month">
                        <option <c:if test="${pageScope.month == 'January'}">selected</c:if> value="1"> 
                                January</option>
                            <option <c:if test="${pageScope.month == 'February'}">selected</c:if> value="2"> 
                                February</option>
                            <option <c:if test="${pageScope.month == 'March'}">selected</c:if> value="3"> 
                                arch</option>
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
                        </select><input type="checkbox" name="filter" value="month">
                        <br>Filter by year...<br>
                        <select name="year">
                            <option value=${pageScope.year - 2}>${pageScope.year - 2}</option>
                        <option value=${pageScope.year - 1}>${pageScope.year - 1}</option>
                        <option selected value=${pageScope.year}>${pageScope.year}</option>
                        <option value=${pageScope.year + 1}>${pageScope.year + 1}</option>
                        <option value=${pageScope.year + 2}>${pageScope.year + 2}</option>
                    </select><input type="checkbox" name="filter" value="year">
                    <br>
                    <input type='hidden' name='action' value='viewPayments'>
                    <input type="submit" name="submit" value="Apply Filters">
                </form>
            </div>

            <div class="outputs_div">
                <table id="payments">
                        <tr><th>Student</th><th>Instructor</th><th>Year</th><th>Month</th><th>Lessons</th></tr>
                    <c:forEach var="payment" items="${requestScope.paymentArr}" 
                               varStatus="loopStatus">
                        <tr><td>${payment.studentName}</td>
                            <td>${payment.instructorName}</td> 
                            <td>${payment.year}</td>
                            <td>${payment.monthStr}</td>
                            <td>${payment.number_lessons}</td></tr>
                        </c:forEach> 
                </table>
            </div>
        </div>
        <c:if test="${not empty errorMsg}">
            Error: ${requestScope.errorMsg}
        </c:if>
    </body>
</html>
