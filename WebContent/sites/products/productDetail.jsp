<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="col-9">
	<div class="card mb-3">
		<div class="card-header text-primary fw-bold">${product.getProductName()}</div>
		<div class="row">
			<div class="col-md-4 py-3">
				<img src="${product.getProductImgSource()}" class="img-fluid"
					alt="Product Image">
			</div>
			<div class="col-md-8 d-flex flex-column">
				<div class="card-body">
					<h5 class="card-title text-danger">$${product.getProductPrice()}</h5>
					<p class="card-text">${product.getProductDes()}</p>
				</div>
				<div class="p-3">
					<a class="btn btn-danger" href="/PRJ321x_Assignment_3_FX17393/AddToCart?productId=${product.getProductId()}">Add To Cart</a>
				</div>
			</div>
		</div>
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