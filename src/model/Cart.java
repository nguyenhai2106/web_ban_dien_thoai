package model;

import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;

public class Cart {
	private List<Item> items;
	private ProductDao dao = new ProductDao();

	public Cart(List<Item> items) {
		super();
		this.items = items;
	}

	public Cart() {
		items = new ArrayList<Item>();
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public int getTotalQuantity() {
		int totalQuantity = 0;
		for (Item item : items) {
			totalQuantity += item.getAmountProduct();
		}
		return totalQuantity;
	}

	public double getTotalAmount() {
		double totalAmount = 0;
		for (Item item : items) {
			totalAmount += item.getAmountProduct() * item.getProductPrice();
		}
		return (double) Math.round(totalAmount * 100) / 100;
	}

	public void addItem(int itemId) {
		for (Item item : items) {
			if (item.getProductId() == itemId) {
				item.setAmountProduct(item.getAmountProduct() + 1);
				return;
			}
		}
		Product product = dao.findById(itemId);
		Item item = new Item(product.getProductId(), product.getProductName(), product.getProductPrice(),
				product.getProductImgSource());
		items.add(item);
	}

	public void updateItem(int[] productIds, int[] quantities) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getProductId() == productIds[i]) {
				if (quantities[i] == 0) {
					removeItem(productIds[i]);
				} else {
					items.get(i).setAmountProduct(quantities[i]);
				}
			}
		}
	}

	public void removeItem(int itemId) {
		for (Item item : items) {
			if (item.getProductId() == itemId) {
				items.remove(item);
				return;
			}
		}
	}

}
