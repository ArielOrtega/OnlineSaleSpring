package com.yoyo.ventas.domain;

import java.util.List;

public class ShoppingCart {
	private int cadId;
	private String cartGuId;
	private String date_created;
	private List<Product> products;
	
	public ShoppingCart() {
		
	}

	public int getCadId() {
		return cadId;
	}

	public void setCadId(int cadId) {
		this.cadId = cadId;
	}

	public String getCartGuId() {
		return cartGuId;
	}

	public void setCartGuId(String cartGuId) {
		this.cartGuId = cartGuId;
	}

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
