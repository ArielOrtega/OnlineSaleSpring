package com.yoyo.ventas.domain;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Product {
	private int productId;
	private String productName;
	private String description;
	private float price;
	private float stockUnits;
	private List<Image> images;
	private Category category;
	
	public Product() {
		images = new ArrayList<>();
		category = new Category();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getStockUnits() {
		return stockUnits;
	}

	public void setStockUnits(float stockUnits) {
		this.stockUnits = stockUnits;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
