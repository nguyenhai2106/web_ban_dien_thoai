<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="col-4 offset-4">
	<form action="Login" method="post" onsubmit="return validateForm()">
		<div class="card text-start">
			<div class="card-header p-3">
				<b>Login to System</b>
			</div>
			<div class="card-body">
				<div class="form-group">
					<label for="" class="form-label">User Mail</label> <input
						type="email" name="userMail" id="userMail" class="form-control"
						placeholder="" aria-describedby="userMailHid" value="${userMail}"
						onfocus="return clearAlert()" />
				</div>

				<div class="form-group mt-2">
					<label for="" class="form-label">Password</label> <input
						type="password" name="password" id="password" class="form-control"
						placeholder="" aria-describedby="passwordHid"
						onfocus="return clearAlert()" />
				</div>
				<div class="form-check form-check-inline mt-2">
					<label class="form-check-label"> <input
						class="form-check-input" type="checkbox" name="remember" id=""
						value="true" /> Remember me?
					</label>
				</div>
			</div>
			<div class="card-footer text-muted">
				<button class="btn btn-primary">Log In</button>
			</div>
		</div>
	</form>
</div>

<div class="col-4">
	<c:if test="${not empty error}">
		<div class="alert alert-danger alert-dismissible" role="alert"
			id="mesFromServlet">
			<div class="text-center">${error}</div>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>
	<div id="alertMessage" class="text-center"></div>
</div>


<script>
	const validateForm = () => {
		const alertMessage = document.getElementById("alertMessage");
		const username = document.getElementById("userMail").value;
		const password = document.getElementById("password").value;
		if (username == "") {
			const messageDiv = document.createElement("div");
			messageDiv.innerHTML = `
				<div class="alert alert-danger alert-dismissible" role="alert"
				id="mesFromServlet">
				<div>Username must be filled out!</div>
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
				</div>
			`;
			alertMessage.appendChild(messageDiv);
			const deleteAlert = setTimeout(() => {
				const alertMessage = document.getElementById("alertMessage");
				alertMessage.innerHTML = '';
			}, 5000);
			return false;
		} else if (password == "") {
			const messageDiv = document.createElement("div");
			messageDiv.innerHTML = `
				<div class="alert alert-danger alert-dismissible" role="alert"
				id="mesFromServlet">
				<div>Password must be filled out!</div>
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
				</div>
			`;
			alertMessage.appendChild(messageDiv);
			const deleteAlert = setTimeout(() => {
				const alertMessage = document.getElementById("alertMessage");
				alertMessage.innerHTML = '';
			}, 5000);
			return false;
		} 
	}
	const clearAlert = () => {
		const alertMessage = document.getElementById("alertMessage");
		const mesFromServlet = document.getElementById("mesFromServlet");
		alertMessage.innerHTML = '';
		mesFromServlet.remove();
	}
</script>