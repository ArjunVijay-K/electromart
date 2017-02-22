<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table {
    font-family: arial, sans-serif;
   
    
    border-spacing: 15px;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 5px;
    
    
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>
<body>

<h3 style="text-align:center">List of products purchased</h3>

<table width="1000" align="center">
	<tr>

		<th align="left">Cartid</th>
		<th align="left">ProductName</th>
		<th align="left">Username</th>
		<th align="left">UsersId</th>
		
		<th align="left">productId</th>
		<th align="left">quantity</th>
		<th align="left">price</th>
		<th align="left">status</th>
	</tr>
	
	 <c:forEach items="${cartlist}" var="adminCart" varStatus="status"> 
		<tr>
		
			<td align="left">${adminCart.cartid}</td>
			<td align="left">${adminCart.product_Name}</td>
			<td align="left">${adminCart.username}</td>
			<td align="left">${adminCart.usersId}</td>
			
			<td align="left">${adminCart.product_Id}</td>
			<td align="left">${adminCart.quantity}</td>
			<td align="left">${adminCart.price}</td>
			<td align="left">${adminCart.status}</td>
			</tr>
	</c:forEach>	 
	 </table>

</body>
</html>