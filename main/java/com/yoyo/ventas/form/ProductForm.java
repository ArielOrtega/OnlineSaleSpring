package com.yoyo.ventas.form;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
	@NotNull
	private String productName;
	private String description;
	@NotNull
	private float price;
	@NotNull
	private int stockUnits;
	@NotNull
	private int categoryId;
	@NotNull
	private MultipartFile[] images;
	
	public ProductForm() {
		
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

	public int getStockUnits() {
		return stockUnits;
	}

	public void setStockUnits(int stockUnits) {
		this.stockUnits = stockUnits;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public MultipartFile[] getImages() {
		return images;
	}

	public void setImages(MultipartFile[] images) {
		this.images = images;
	}
	
	

}
