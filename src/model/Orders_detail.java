package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Orders_detail.findAll", query = "SELECT o FROM Orders_detail o")
public class Orders_detail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Orders_detailPK id;

	@Column(name = "amount_product")
	private int amountProduct;

	@Column(name = "price_product")
	private double priceProduct;

	// bi-directional many-to-one association to Order
	@ManyToOne(optional = false)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id", insertable = false, updatable = false)
	private Order order;

	// bi-directional many-to-one association to Product
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
	private Product product;

	public Orders_detail() {
	}

	public Orders_detailPK getId() {
		return this.id;
	}

	public void setId(Orders_detailPK id) {
		this.id = id;
	}

	public int getAmountProduct() {
		return this.amountProduct;
	}

	public void setAmountProduct(int amountProduct) {
		this.amountProduct = amountProduct;
	}

	public double getPriceProduct() {
		return this.priceProduct;
	}

	public void setPriceProduct(double priceProduct) {
		this.priceProduct = priceProduct;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}