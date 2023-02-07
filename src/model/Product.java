package model;

import java.io.Serializable;
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
 * The persistent class for the Products database table.
 *
 */
@Entity
@Table(name = "Products")
@NamedQueries({ @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
		@NamedQuery(name = "Product.countAll", query = "SELECT COUNT(p) FROM Product p") })
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;

	@Column(name = "product_brand")
	private String productBrand;

	@Column(name = "product_des")
	private String productDes;

	@Column(name = "product_img_source")
	private String productImgSource;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_price")
	private double productPrice;

	@Column(name = "product_type")
	private String productType;

	// bi-directional many-to-one association to Orders_detail
	@OneToMany(mappedBy = "product")
	private List<Orders_detail> ordersDetails;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductBrand() {
		return this.productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductDes() {
		return this.productDes;
	}

	public void setProductDes(String productDes) {
		this.productDes = productDes;
	}

	public String getProductImgSource() {
		return this.productImgSource;
	}

	public void setProductImgSource(String productImgSource) {
		this.productImgSource = productImgSource;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public List<Orders_detail> getOrdersDetails() {
		return this.ordersDetails;
	}

	public void setOrdersDetails(List<Orders_detail> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}

	public Orders_detail addOrdersDetail(Orders_detail ordersDetail) {
		getOrdersDetails().add(ordersDetail);
		ordersDetail.setProduct(this);

		return ordersDetail;
	}

	public Orders_detail removeOrdersDetail(Orders_detail ordersDetail) {
		getOrdersDetails().remove(ordersDetail);
		ordersDetail.setProduct(null);

		return ordersDetail;
	}

}