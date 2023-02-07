package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the Orders_detail database table.
 *
 */
@Embeddable
public class Orders_detailPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "order_id", insertable = false, updatable = false)
	private int orderId;

	@Column(name = "product_id", insertable = false, updatable = false)
	private int productId;

	public Orders_detailPK() {
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Orders_detailPK)) {
			return false;
		}
		Orders_detailPK castOther = (Orders_detailPK) other;
		return (this.orderId == castOther.orderId) && (this.productId == castOther.productId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderId;
		hash = hash * prime + this.productId;

		return hash;
	}
}