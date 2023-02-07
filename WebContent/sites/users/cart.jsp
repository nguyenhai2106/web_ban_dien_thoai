<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="col">
	<c:set var="cart" value="${sessionScope['cart']}" />
	<c:if test="${cart.getItems().size() == 0 || cart == null}">
		<h4 class="text-info text-center">Your cart is empty!</h4>
	</c:if>
	<c:if test="${cart.getItems().size() > 0}">
		<form action="UpdateCart" method="get">
			<div class="row">
				<div class="col boder">
					<table class="table table-stripe">
						<tr class="text-center fw-bold">
							<td>Product</td>
							<td>Price</td>
							<td>Quantity</td>
							<td>Total</td>
							<td>&nbsp;</td>
						</tr>
						<c:forEach var="item" items="${cart.getItems()}"
							varStatus="status">
							<tr>
								<td>
									<div class="row">
										<div class="col-4">
											<img class="img-fluid  d-block" alt="Product Image"
												src="${item.getProductImgSource()}" width="80px" />
										</div>
										<div class="col-8 d-flex align-items-center">${item.getProductName()}</div>
									</div>
								</td>
								<td class="text-center align-middle">$${item.getProductPrice()}</td>
								<td class="text-center align-middle">
									<div class="d-flex justify-content-center">
										<input type="hidden" name="productId"
											value="${item.getProductId()}"> <input
											class="form-control" type="number"
											style="text-align: center; width: 100px;" min="0"
											name="quantity${status.index + 1 }"
											value="${item.getAmountProduct()}" />
									</div>
								</td>
								<td class="text-center align-middle">$${item.getSubTotal()}</td>
								<td class="text-center align-middle"><button type="submit"
										class="pe-2 border-0 text-primary bg-transparent">
										<i class="fa fa-edit" aria-hidden="true"></i> Update
									</button> <a
									href="/PRJ321x_Assignment_3_FX17393/RemoveCartItem?itemId=${item.getProductId()}"
									class="text-danger text-decoration-none"><i
										class="fa fa-trash" aria-hidden="true"></i> Delete</a></td>
							</tr>
						</c:forEach>
						<tr class="text-center fw-bold">
							<td class="fw-bold">Total</td>
							<td>&nbsp;</td>
							<td class="text-primary">${cart.getTotalQuantity()}</td>
							<td class="text-danger">$${cart.getTotalAmount()}</td>
							<td>&nbsp;</td>
						</tr>
						<tr class="text-center fw-bold">
							<td><a type="button" class="btn btn-primary"
								href="Home">Continue
									Shopping</a></td>
							<td><button type="submit" class="btn btn-success">Update</button></td>
							<td><a type="button"
								href="Cart?clearCart=doClear"
								class="btn btn-warning">Clear Cart</a></td>
							<td><c:choose>
									<c:when test="${not empty userLog}">
										<a type="button" href="Checkout"
											class="btn btn-danger">Checkout</a>
									</c:when>
									<c:otherwise><a type="button" href="Login"
											class="btn btn-info">Login To Checkout</a></c:otherwise>
								</c:choose></td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</c:if>
</div>