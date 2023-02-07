<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="col-2">
	<a
		class="btn btn-outline-primary w-100 ${empty display || display == 'account' ? 'active': ' '}"
		href="Account?display=account">Profile</a> <a
		class="btn btn-outline-primary w-100 mt-2  ${display == 'hisPurchase' ? 'active': ' '}"
		href="Account?display=hisPurchase">History Purchase</a>
</div>
<div class="col-10">
	<c:choose>
		<c:when test="${empty display || display != 'hisPurchase'}">
			<form action="Account" method="post">
				<div class=" align-items-center justify-content-around g-0">
					<input type="hidden" name="doUpdate" value="doUpdate">
					<div class="form-group">
						<label for="userName" class="form-label mt-2 fw-bold">User
							Name</label> <input type="text" name="userName" id="userName"
							class="form-control" placeholder=""
							aria-describedby="userNameHid" value="${account.userName}" />
					</div>
					<div class="form-group">
						<label for="userAddress" class="form-label mt-2 fw-bold">User
							Address</label> <input type="text" name="userAddress" id="userAddress"
							class="form-control" placeholder=""
							aria-describedby="userAddressHid" value="${account.userAddress}" />
					</div>
					<div class="form-group">
						<label for="userPhone" class="form-labe mt-2 fw-bold">User
							Phone</label> <input type="text" name="userPhone" id="userPhone"
							class="form-control" placeholder=""
							aria-describedby="userPhoneHid" value="${account.userPhone}" />
					</div>

					<div class="form-group">
						<label for="userMail" class="form-label mt-2 fw-bold">User
							Email</label> <input type="email" name="userMail" id="userMail"
							class="form-control" placeholder=""
							aria-describedby="userMailHid" value="${account.userMail}" />
					</div>
					<div class="pt-3">
						<button class="btn btn-primary" type="submit">Update
							Profile</button>
					</div>
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<table class="table table-stripe">
				<tr class="text-center fw-bold">
					<td>Order ID</td>
					<td>Order Status</td>
					<td>Order Date</td>
					<td>Order Discount Code</td>
					<td>Order Address</td>
				</tr>
				<c:forEach var="order" items="${orders}">
					<tr class="text-center">
						<td>${order.getOrderId()}</td>
						<td>${order.getOrderStatus()}</td>
						<td>${order.getOrderDate()}</td>
						<td>${order.getOrderDiscountCode()}</td>
						<td>${order.getOrderAddress()}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>

	</c:choose>
</div>

