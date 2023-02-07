<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="row">
	<div class="col-8 mx-auto mt-2 mb-3">
		<form action="" method="get">
			<input type="hidden" name="doSearch" value="doSearch">
			<div class="input-group">
				<select class="form-select" style="flex: 0.12; border-right: 0"
					name="productBrand">
					<option>Category</option>
					<option>Apple</option>
					<option>Samsung</option>
					<option>Sony</option>
					<option>Xiaomi</option>
				</select> <input type="search" class="form-control" name="productName" />
				<button class="btn btn-primary" type="submit">Search</button>
			</div>
		</form>
	</div>
</div>
<div class="col-xl-9 col-12 col-md-12">
	<div class="row">
		<!-- Product card Start-->
		<c:forEach var="product" items="${products}">
			<div class="col-4  mb-4">
				<div class="card text-center">
					<div class="card-body">
						<a href="ProductDetail?productId=${product.getProductId()}"><img
							src="${product.getProductImgSource()}" width="90%" alt=""
							class="img-fluid imageProduct" /></a>
					</div>
					<div class="row  p-1 ps-2">
						<b class="text-center text-secondary">${product. getProductType().toUpperCase()}</b>
					</div>
					<div class="row  p-1 ps-2">
						<b class="text-start text-primary">${product. getProductName()}</b>
					</div>
					<div class="row  p-1 ps-2">
						<b class="text-start text-danger">$${product.
							getProductPrice()}</b>
					</div>
				</div>
			</div>
		</c:forEach>
		<!-- Product card End-->
		<c:choose>
			<c:when test="${not empty products && products.size() > 0}">
				<div class="row mt-3">
					<div class="col">
						<nav aria-label="Page navigation">
							<ul class="pagination justify-content-center">
								<li class="page-item"><a class="page-link"
									href="/PRJ321x_Assignment_3_FX17393/Home?currentPage=${currentPage - 1}"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
										<span class="sr-only">Previous</span>
								</a></li>
								<c:forEach var="i" begin="1" end="${maxPages}">
									<c:choose>
										<c:when test="${i == currentPage}">
											<li class="page-item active"><a
												class="page-link border-end-0"
												href="/PRJ321x_Assignment_3_FX17393/Home?currentPage=${i}">${i}</a>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link border-end-0"
												href="/PRJ321x_Assignment_3_FX17393/Home?currentPage=${i}">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								</li>
								<li class="page-item"><a class="page-link"
									href="/PRJ321x_Assignment_3_FX17393/Home?currentPage=${currentPage + 1}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
										<span class="sr-only">Next</span>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<h4 class="text-info text-center">${errorSearch}</h4>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<div class="col-3">
	<c:set var="cart" value="${sessionScope['cart']}" />
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header text-center fw-bold text-secondary">
					Your Cart</div>
				<c:choose>
					<c:when test="${cart.getItems().size() == 0 || cart == null}">
						<ul class="list-group list-group-flush">
							<li class="list-group-item d-flex justify-content-center">
								<div class="text-info text-center">Your cart is empty!</div>
							</li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="list-group list-group-flush">
							<c:forEach var="item" items="${cart.getItems()}"
								varStatus="status">
								<li class="list-group-item d-flex justify-content-between">
									<div class="col-9  text-primary">${item.getProductName()}
										<div class=" text-secondary">Quantity:
											${item.getAmountProduct()}</div>
									</div>
									<div class="col-3 text-end text-danger align-middle">$${item.getSubTotal()}</div>
								</li>
							</c:forEach>
						</ul>
					</c:otherwise>
				</c:choose>
				<div class="card-footer row">
					<div class="col-8 fw-bold text-primary">Total</div>
					<div class="col-4 text-danger text-end fw-bold">$${cart.getTotalAmount()}</div>
				</div>
			</div>
		</div>
	</div>
</div>
