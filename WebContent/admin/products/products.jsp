<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="row">
	<div class="col-3 d-flex mx-auto">
		<a class="btn btn-outline-primary w-100 mt-2" href="UserManagement">User</a>
		<a class="btn btn-outline-primary w-100 mt-2 ms-2 active"
			href="ProductManagement">Product</a>

	</div>
</div>
<div class="col-3">
	<form action="Account" method="post">
		<div class=" align-items-center justify-content-around g-0">
			<input type="hidden" name="productId" id="productId"
					class="form-control" placeholder="" aria-describedby="productIdHid"
					value="${product.getProductId()}" />
			<div class="form-group">
				<label for="userName" class="form-label mt-2 fw-bold">Product
					Image URL</label> <input type="text" name="productImgSource" id="productImgSource"
					class="form-control" placeholder="" aria-describedby="productImgSourceHid"
					value="${product.getProductImgSource()}" />
			</div>
			<div class="form-group">
				<label for="userName" class="form-label mt-2 fw-bold">Product
					Brand</label> <input type="text" name="productBrand" id="productBrand"
					class="form-control" placeholder="" aria-describedby="productBrandHid"
					value="${product.getProductBrand().toUpperCase()}" />
			</div>
			<div class="form-group">
				<label for="userAddress" class="form-label mt-2 fw-bold">Product
					Description</label>
				<textarea name="productDes" id="productDes" class="form-control"
					placeholder="" aria-describedby="productDesHid" rows="6">${product.getProductDes()}</textarea>
			</div>
			<div class="form-group">
				<label for="userPhone" class="form-labe mt-2 fw-bold">Product
					Name</label> <input type="text" name="productName" id="productName"
					class="form-control" placeholder="" aria-describedby="productNameHid"
					value="${product.getProductName()}" />
			</div>

			<div class="form-group">
				<label for="userMail" class="form-label mt-2 fw-bold">Product
					Price</label> <input type="text" name="productPrice" id="productPrice"
					class="form-control" placeholder="" aria-describedby="productPriceHid"
					value="${product.getProductPrice()}" />
			</div>

			<div class="form-group">
				<label for="password" class="form-label mt-2 fw-bold">Product
					Type</label> <input type="text" name="productType" id="productType"
					class="form-control" placeholder="" aria-describedby="productTypeHid"
					value="${product.getProductType().toUpperCase()}" />
			</div>
			<div class="pt-3 d-flex justify-content-around">
				<button class="btn btn-primary" formaction="ProductManagement/create">Create</button>
				<button class="btn btn-warning" formaction="ProductManagement/update">Update</button>
				<button class="btn btn-danger" formaction="ProductManagement/delete">Delete</button>
				<button class="btn btn-info" formaction="ProductManagement/reset">Reset</button>
			</div>
		</div>
	</form>
</div>
<div class="col-9 overflow-scroll" style="height: 650px">
	<table class="table table-stripe">
		<tr class="text-center fw-bold">
			<td>Id</td>
			<td>Brand</td>
			<td class="w-25">Descriptions</td>
			<td>Name</td>
			<td>Price</td>
			<td>Type</td>
			<td>&nbsp;</td>
		</tr>
		<c:forEach var="product" items="${products}">
		<tr>
			<td class="text-center align-middle">${product.getProductId()}</td>
			<td class="text-center align-middle">${product.getProductBrand().toUpperCase()}</td>
			<td class="w-25">${product.getProductDes()}</td>
			<td class="text-center align-middle">${product.getProductName()}</td>
			<td class="text-center align-middle">$${product.getProductPrice()}</td>
			<td class="text-center align-middle">${product.getProductType().toUpperCase()}</td>
			<td class="text-center align-middle"><a href="ProductManagement/edit?productId=${product.getProductId()}"
				class="pe-2 text-decoration-none"><i class="fa fa-edit"
					aria-hidden="true"></i></a> <a href="ProductManagement/delete?productId=${product.getProductId()}"
				class="text-danger text-decoration-none"><i class="fa fa-trash"
					aria-hidden="true"></i></a></td>
		</tr>
		</c:forEach>
	</table>
</div>

