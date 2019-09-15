<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Cart</title>
<link rel="stylesheet" type="text/css" href="style/style2.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
</head>
<body>
	<nav>
		<ul>
			<li><a href="ShowMenuItemListCustomer">Puppu's</a></li>
			<li><a class="cart-link" href="ShowMenuItemListCustomer">Menu</a></li>
			<li><a class="cart-link1" href="ShowCart">Cart</a></li>
		</ul>
	</nav>
	<table align="center" width="75%" cellpadding="6">
		<caption>
			Cart
			<c:set var="status" value="${requestScope.removeCartItemStatus }"></c:set>
			<c:if test="${status==true }">
				<br>
				<br>
				<span class="success-message success-message-cart">Item
					removed from Cart Successfully</span>
				<br>
			</c:if>
		</caption>
		<tr>
			<th class="left-align">Name</th>
			<th class="right-align">Price</th>
		</tr>
		<c:set var="menuItemList" value="${requestScope.menuItemList }"></c:set>
		<c:forEach var="menuItem" items="${menuItemList }">
			<tr>
				<td class="left-align">${menuItem.name }</td>
				<td width="10%" class="right-align"><fmt:setLocale
						value="en_IN" /> <fmt:formatNumber value="${menuItem.price}"
						type="currency" /></td>
				<td class="center-align"><a class="black-link"
					href="RemoveCart?menuItemId=${menuItem.id }">Delete</a></td>
			</tr>
		</c:forEach>
		<tr>
			<th class="left-align">Total</th>
			<th class="right-align">${cart.total }</th>
		</tr>
	</table>
</body>
</html>