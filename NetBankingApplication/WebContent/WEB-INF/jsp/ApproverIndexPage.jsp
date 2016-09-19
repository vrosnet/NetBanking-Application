
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
    <c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'approver'}">
    <c:redirect url="userHomePage" />
</c:if>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
.well {
    background-color: #8ddfe1;
}

.pos {
    position: absolute;
    right: 100px;
    top: 108px;
    height: 70px;
    width: 50px;
}
</style>
</head>
<body>
    <div class="well">
        <font size="20"><marquee behavior="alternate">I2I NETBANKING</marquee></font>
    </div>
    <h3> Welcome  ${sessionScope['name']} </h3><br />
    <center>
        <h1>WELCOME I2I NETBANKING APPLICATION</h1>
        If U Want Branch Operation Open Click On <br /> <br /> <a
            href=BranchIndex>OPEN</a> <br /> <br /> If U Want Customer
        Operation Open Click On <br /> <br /> <a href=GetCustomer>OPEN</a> <br />
        <br /> If U Want Transaction Operation Open Click On <br /> <br />
        <a href=TransactionIndex>OPEN</a>
    </center>
    <br />
    <div class="pos">
        <a class="btn btn-danger" href="logoutController"> LOGOUT</a>
    </div>
</body>
</html> 