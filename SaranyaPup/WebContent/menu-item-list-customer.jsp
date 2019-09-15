<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu Item List Customer</title>
<link rel="stylesheet" type="text/css" href="style/style2.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<nav>
		<ul>
			<li><a href="ShowMenuItemListCustomer">Puppu's</a></li>
			<li><a class="cart-link" href="ShowMenuItemListCustomer">Menu</a></li>
			<li><a class="cart-link1" href="ShowCart">Cart</a></li>
		</ul>
	</nav>
	<table align="center" width="80%" cellpadding="6">
		<caption>
			Products
			<c:set var="status" value="${requestScope.addCartStatus }"></c:set>
			<c:if test="${status==true }">
				<br>
				<br>
				<span class="success-message fs-sm">Item added to Cart
					Successfully</span>
				<br>
				<br>
			</c:if>
		</caption>
		<tr>
			<th class="left-align">Name</th>
			<th class="right-align">Price</th>
			<th class="right-align">Quantity</th>
			<th>Action</th>
		</tr>
		<c:set var="menuItemList" value="${requestScope.menuItemList }"></c:set>
		<c:forEach var="menuItem" items="${menuItemList }">
			<tr>
				<td class="left-align"><c:out value="${menuItem.name }" /></td>
				<td class="right-align"><fmt:setLocale value="en_IN" /> <fmt:formatNumber
						value="${menuItem.price}" type="currency" /></td>
				<td class="right-align"><c:out value="${menuItem.quantity }" /></td>
				<td class="center-align"><a class="black-link"
					href="AddToCart?menuItemId=${menuItem.id }">Add to Cart</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>