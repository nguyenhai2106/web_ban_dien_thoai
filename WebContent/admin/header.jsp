<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="header d-flex justify-content-center align-items-center">
	<div class="logo">
		<img width="130px" alt="logo"
			src="${pageContext.request.contextPath}/images/logo.webp">
	</div>
	<div>
		<h2>Smartphone Shop</h2>
		<p>Smarter, Safer, Smoother</p>
	</div>
</div>

<div class="topnav col-12">
	<a href="Home"
		style="${page.getTitle().equals('Home Page')? 'background-color: #ddd; color: black;':''}"><i
		class="fas fa-home" aria-hidden="true"></i> Home</a> <a href="Cart"
		style="${page.getTitle().equals('Shopping Cart Page')? 'background-color: #ddd; color: black;':''}"><i
		class="fas fa-shopping-cart" aria-hidden="true"></i> Cart</a> <a href="Home"><i
		class="fas fa-info" aria-hidden="true"></i> About Us</a>
	<c:if test="${not empty userLog && empty admin}">
		<a href="Account"
			style="${page.getTitle().equals('Acount Page')? 'background-color: #ddd; color: black;':''}"><i
			class="fas fa-user" aria-hidden="true"></i> Account</a>
	</c:if>
	<c:if test="${not empty admin}">
		<a href="UserManagement"
			style="${page.getTitle().equals('Dashboard Page')? 'background-color: #ddd; color: black;':''}"><i
			class="fas fa-id-card"></i> Dashboard</a>
	</c:if>

	<c:choose>
		<c:when test="${not empty userLog}">
			<a href="Logout" style="float: right">Log Out <i
				class="fas fa-sign-out" aria-hidden="true"></i></a>
			<a href="Account" style="float: right"><i class="fas fa-user"
				aria-hidden="true"></i> Welcome ${userLog}</a>
		</c:when>
		<c:when test="${empty userLog}">
			<a href="Registration"
				style="float: right; ${page.getTitle().equals('Registration Page')? 'background-color: #ddd; color: black;':''}"><i
				class="fas fa-user-plus" aria-hidden="true"></i> Registration</a>
			<a href="Login"
				style="float: right; ${page.getTitle().equals('Login Page')? 'background-color: #ddd; color: black;':''}"><i
				class="fas fa-sign-in" aria-hidden="true"></i> Log In</a>
		</c:when>
	</c:choose>
</div>
