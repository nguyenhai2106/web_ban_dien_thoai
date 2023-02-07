package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the Orders database table.
 *
 */
@Entity
@Table(name = "Orders")
@NamedQueries({ @NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o"),
		@NamedQuery(name = "Order.findByUserMail", query = "SELECT o FROM Order o where o.userMail = :userMail") })

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;

	@Column(name = "order_address")
	private String orderAddress;

	@Column(name = "order_date")
	private Date orderDate = new Date();

	@Column(name = "order_discount_code")
	private String orderDiscountCode;

	@Column(name = "order_status")
	private int orderStatus;

	@Column(name = "user_mail")
	private String userMail;

	// bi-directional many-to-one association to Orders_detail
	@OneToMany(mappedBy = "order")
	private List<Orders_detail> ordersDetails;

	public Order() {
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderAddress() {
		return this.orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderDiscountCode() {
		return this.orderDiscountCode;
	}

	public void setOrderDiscountCode(String orderDiscountCode) {
		this.orderDiscountCode = orderDiscountCode;
	}

	public int getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getUserMail() {
		return this.userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public List<Orders_detail> getOrdersDetails() {
		return this.ordersDetails;
	}

	public void setOrdersDetails(List<Orders_detail> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}

	public Orders_detail addOrdersDetail(Orders_detail ordersDetail) {
		getOrdersDetails().add(ordersDetail);
		ordersDetail.setOrder(this);

		return ordersDetail;
	}

	public Orders_detail removeOrdersDetail(Orders_detail ordersDetail) {
		getOrdersDetails().remove(ordersDetail);
		ordersDetail.setOrder(null);

		return ordersDetail;
	}

}