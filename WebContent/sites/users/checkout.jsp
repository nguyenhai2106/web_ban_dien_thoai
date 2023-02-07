<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="col-11 mx-auto">
	<c:set var="cart" value="${sessionScope['cart']}" />
	<c:if test="${cart.getItems().size() > 0}">
		<div class="row">
			<div class="col boder">
				<table class="table table-stripe">
					<tr class="text-center fw-bold">
						<td>Product</td>
						<td>Price</td>
						<td>Quantity</td>
						<td>Total</td>
					</tr>
					<c:forEach var="item" items="${cart.getItems()}" varStatus="status">
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
							<td class="text-center align-middle">${item.getAmountProduct()}</td>
							<td class="text-center align-middle">$${item.getProductPrice()*item.getAmountProduct()}</td>
						</tr>
					</c:forEach>
					<tr class="text-center fw-bold">
						<td>&nbsp;</td>
						<td class="fw-bold">Total</td>
						<td class="text-primary">${cart.getTotalQuantity()}</td>
						<td class="text-danger">$${cart.getTotalAmount()}</td>
					</tr>
				</table>
			</div>
		</div>
	</c:if>
	<div class="row">
		<div class="mx-auto col-8">
			<form action="Checkout" method="post">
				<div class="card text-start">
					<div class="card-header p-3 rounded-0">
						<b>Billing address</b>
					</div>
					<div class="card-body">
						<div class="form-group">
							<label for="" class="form-label"><b>Discount Code</b></label> <input
								type="text" name="orderDiscountCode" id="orderDiscountCode"
								class="form-control" placeholder="Discount Code"
								aria-describedby="orderDiscountCodeHid" />
						</div>

						<div class="form-group mt-2">
							<label for="" class="form-label"><b>Email</b></label> <input
								type="text" name="userMail" id="userMail" class="form-control"
								placeholder="Email" aria-describedby="userMailHid"
								value="${sessionScope['userLog']}" />
						</div>

						<div class="mb-3 mt-2">
							<label for="exampleFormControlTextarea1" class="form-label"><b>Address</b></label>
							<textarea class="form-control" id="userAddress"
								name="userAddress" rows="3" placeholder="Address" required="required"></textarea>
						</div>

					</div>
					<div class="card-footer text-muted">
						<button class="btn btn-danger" type="submit">Place Order</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>