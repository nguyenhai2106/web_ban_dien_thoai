<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="row">
	<div class="col-3 d-flex mx-auto">
		<a class="btn btn-outline-primary w-100 mt-2 active"
			href="UserMangement">User</a> <a
			class="btn btn-outline-primary w-100 mt-2 ms-2"
			href="ProductManagement">Product</a>

	</div>
</div>
<div class="col-3">
	<form action="UserManagement" method="post">
		<div class=" align-items-center justify-content-around g-0">
			<div class="form-group">
				<label for="userName" class="form-label mt-2 fw-bold">User
					Name</label> <input type="text" name="userName" id="userName"
					class="form-control" placeholder="" aria-describedby="userNameHid"
					value="${account.userName}" />
			</div>
			<div class="form-group">
				<label for="userAddress" class="form-label mt-2 fw-bold">User
					Address</label>
				<textarea name="userAddress" id="userAddress" class="form-control"
					placeholder="" aria-describedby="userAddressHid">${account.userAddress}</textarea>
			</div>
			<div class="form-group">
				<label for="userPhone" class="form-labe mt-2 fw-bold">User
					Phone</label> <input type="text" name="userPhone" id="userPhone"
					class="form-control" placeholder="" aria-describedby="userPhoneHid"
					value="${account.getUserPhone()}" />
			</div>

			<div class="form-group">
				<label for="userMail" class="form-label mt-2 fw-bold">User
					Email</label> <input type="email" name="userMail" id="userMail"
					class="form-control" placeholder="" aria-describedby="userMailHid"
					value="${account.userMail}" />
			</div>

			<div class="form-check-inline pt-2">
				<label class="fw-bold">Role</label> <label class="ml-3 form-label"
					style="padding-left: 20px; padding-right: 20px;"><input
					type="radio" name="accountRole" id="accountRole" value="1"
					${account.getAccountRole() ==1? "checked" : ""} /> Admin</label> <label
					class="ml-3 form-label"><input type="radio" name="accountRole"
					id="accountRole" value="0" ${account.getAccountRole() !=1? "checked" : ""}/> User</label>
			</div>
			<div class="pt-3 d-flex justify-content-around">
				<button class="btn btn-warning" formaction="UserManagement/update">Update</button>
				<button class="btn btn-danger" formaction="UserManagement/delete">Delete</button>
				<button class="btn btn-info" formaction="UserManagement/reset">Reset</button>
			</div>
		</div>
	</form>
</div>
<div class="col-9">
	<table class="table table-stripe">
		<tr class="text-center fw-bold">
			<td>User Email</td>
			<td>User Name</td>
			<td class="w-25">User Address</td>
			<td>User Phone</td>
			<td>Role</td>
			<td>&nbsp;</td>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td class="align-middle">${user.getUserMail()}</td>
				<td class="align-middle">${user.getUserName()}</td>
				<td class="text-center w-25 align-middle">${user.getUserAddress()}</td>
				<td class="text-center align-middle">${user.getUserPhone()}</td>
				<td class="text-center align-middle">${user.getAccountRole() ==1? "Admin" : "User"}</td>
				<td class="text-center align-middle"><a
					href="UserManagement/edit?userMail=${user.getUserMail()}"
					class="pe-2 text-decoration-none"><i class="fa fa-edit"
						aria-hidden="true"></i></a> <a
					href="UserManagement/delete?userMail=${user.getUserMail()}"
					class="text-danger text-decoration-none"><i class="fa fa-trash"
						aria-hidden="true"></i></a></td>
			</tr>
		</c:forEach>
	</table>
</div>

