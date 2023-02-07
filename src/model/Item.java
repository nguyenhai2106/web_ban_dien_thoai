package model;

public class Item {
	private int productId;
	private String productName;
	private double productPrice;
	private double subTotal;
	private int amountProduct = 1;
	private String productImgSource;

	public Item(int productId, String productName, double productPrice, String productImgSource) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productImgSource = productImgSource;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getAmountProduct() {
		return amountProduct;
	}

	public void setAmountProduct(int amountProduct) {
		this.amountProduct = amountProduct;
	}

	public String getProductImgSource() {
		return productImgSource;
	}

	public void setProductImgSource(String productImgSource) {
		this.productImgSource = productImgSource;
	}

	public double getSubTotal() {
		return (double) Math.round(productPrice * amountProduct * 100) / 100;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

}
