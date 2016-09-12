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
</style>
</head>
<body">
	<div class="well">
		<font size="20"><marquee behavior="alternate">NET
				BANKING</marquee></font>
	</div>
	<h1>VIEW CUSTOMER</h1>
	<center>
		<c:if test="${customer != null}">
			<h2>Fetching Data From A Customer Management System</h2>
			<table class=" table table-condensed">
				<tr>
					<th align="center" height="30" width="100">CUSTOMER ID</th>
					<th align="center" height="30" width="100">NAME</th>
					<th align="center" height="30" width="100">AGE</th>
					<th align="center" height="30" width="100">DOB</th>
					<th align="center" height="30" width="100">GENDER</th>
					<th align="center" height="30" width="100">MOB.NUMBER</th>
					<th align="center" height="30" width="100">EMAIL</th>
					<th align="center" height="30" width="100">ACCOUNT NUMBER</th>
					<th align="center" height="30" width="100">ADDRESS ID</th>
					<th colspan="2" align="center" height="30" width="100">ADDRESS</th>
				</tr>
				<c:set value="${customer}" var="customer" />
				<tr>
					<td><c:out value="${customer.getCustomerId()}" /></td>
					<td><c:out value="${customer.getName()}" /></td>
					<td><c:out value="${customer.getAge()}" /></td>
					<td><c:out value="${customer.getDob()}" /></td>
					<td><c:out value="${customer.getGender()}" /></td>
					<td><c:out value="${customer.getMobileNumber()}" /></td>
					<td><c:out value="${customer.getEmail()}" /></td>
					<td><c:out value="${customer.getAccountNumber()}" /></td>
					<td><c:out value="${customer.getStatus()}" /></td>
					<c:choose>
						<c:when test="${null == customer.getAddress()}">
							<td><c:out value="${'No Address Allocated'}" /></td>
						</c:when>
						<c:otherwise>
							<td><c:set value="${customer.getAddress()}" var="address" />
								<c:out value="${address.addressId}" /></td>
						</c:otherwise>
					</c:choose>
					<td><a
						href="viewCustomerAddress?addressId=<c:out value="${address.addressId}"/>"
						style="color: blue">VIEW</a></td>
				</tr>
			</table>
		</c:if>
		<c:if test="${customers != null}">
			<h2>Fetching Data From A Customer Management System</h2>
			<table class=" table table-bordered">
				<tr>
					<th align="center" height="30" width="100">S.NO</th>
					<th align="center" height="30" width="100">CUSTOMER ID</th>
					<th align="center" height="30" width="100">NAME</th>
					<th align="center" height="30" width="100">AGE</th>
					<th align="center" height="30" width="100">DOB</th>
					<th align="center" height="30" width="100">GENDER</th>
					<th align="center" height="30" width="100">MOB.NUMBER</th>
					<th align="center" height="30" width="100">EMAIL</th>
					<th align="center" height="30" width="100">ACCOUNT NUMBER</th>
					<th align="center" height="30" width="100">STATUS</th>
					<th align="center" height="30" width="100">ADDRESS ID</th>
					<th colspan="2" align="center" height="30" width="100">ADDRESS</th>
				</tr>
				<%
					int sno = 1;
				%>
				<c:forEach items="${customers}" var="customers">
					<tr>
						<td><c:out value="<%=sno%>" /></td>
						<td><c:out value="${customers.getCustomerId()}" /></td>
						<td><c:out value="${customers.getName()}" /></td>
						<td><c:out value="${customers.getAge()}" /></td>
						<td><c:out value="${customers.getDob()}" /></td>
						<td><c:out value="${customers.getGender()}" /></td>
						<td><c:out value="${customers.getMobileNumber()}" /></td>
						<td><c:out value="${customers.getEmail()}" /></td>
						<td><c:out value="${customers.getAccountNumber()}" /></td>
						<td><c:out value="${customers.getStatus()}" /></td>
						<c:choose>
							<c:when test="${null == customers.getAddress()}">
								<td><c:out value="${'No Address Allocated'}" /></td>
							</c:when>
							<c:otherwise>
								<td><c:set value="${customers.getAddress()}" var="address" />
									<c:out value="${address.addressId}" /></td>
							</c:otherwise>
						</c:choose>
						<td><a
							href="viewCustomerAddress?addressId=<c:out value="${address.addressId}"/>"
							style="color: blue">VIEW</a></td>
					</tr>
					<%
						sno++;
					%>
				</c:forEach>
			</table>
		</c:if>
		<h3>Insert Id</h3>
		<form action="getCustomer" method="get">
			<table>
				<tr>
					<td><input type="text" name="customerId"
						placeholder="CUSTOMER ID" required></td>
					<td><input type="submit" name="view" value="VIEW"></td>
				</tr>
			</table>
		</form>
		<b>Go to Customer main page </b><a href="CustomerIndex"
			style="font-sise: 18px"> click here</a> <br />
		<a href="logoutController" style="width: 300px;"> LOGOUT</a>
	</center>
	<c:if test="${message != null}">
		<script type="text/javascript">
			alert('CLICK OK THE PAGE WILL BE REFRESHED...'
					+ "<c:out value='${message}'/>");
			windows.location.reload();
		</script>
	</c:if>
</body>
</html>