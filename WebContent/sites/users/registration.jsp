<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class=" col-4 offset-4">
	<form action="Registration" method="post">
		${Routes.SITE_REGISTRATION_SHOW}
		<div class="card">
			<div class="card-header p-3">
				<b>Registration</b>
			</div>
			<div class="card-body">
				<input type="hidden" name="doRegistration" value="doRegistration">
				<div class="form-group">
					<label for="userName" class="form-label">User Name</label> <input
						type="text" name="userName" id="userName" class="form-control"
						placeholder="" aria-describedby="userNameHid"
						value="${account.userName}" required="required" />
				</div>
				<div class="form-group">
					<label for="userAddress" class="form-label">User Address</label> <input
						type="text" name="userAddress" id="userAddress"
						class="form-control" placeholder=""
						aria-describedby="userAddressHid" value="${account.userAddress}"
						required="required" />
				</div>
				<div class="form-group">
					<label for="userPhone" class="form-label">User Phone</label> <input
						type="text" name="userPhone" id="userPhone" class="form-control"
						placeholder="" aria-describedby="userPhoneHid"
						value="${account.userPhone}" required="required" />
				</div>

				<div class="form-group">
					<label for="userMail" class="form-label">User Email</label> <input
						type="email" name="userMail" id="userMail" class="form-control"
						placeholder="" aria-describedby="userMailHid"
						value="${account.userMail}" required="required" />
				</div>

				<div class="form-group">
					<label for="password" class="form-label">Password</label> <input
						type="password" name="password" id="password" class="form-control"
						placeholder="" aria-describedby="passwordHid" required="required" />
				</div>
			</div>
			<div class="card-footer text-muted">
				<button class="btn btn-primary" type="submit">Registration</button>
			</div>
		</div>
	</form>
</div>
<div class="col-4">
	<c:if test="${not empty message}">
		<div class="row pt-3">
			<div class="col">
				<div class="text-center text-success fw-bold">${message}.</div>
			</div>
		</div>
	</c:if>
	<c:if test="${not empty error}">
		<div class="alert alert-danger alert-dismissible" role="alert"
			id="mesFromServlet">
			<div class="text-center">${error}</div>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>
</div>