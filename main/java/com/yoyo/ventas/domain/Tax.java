package com.yoyo.ventas.domain;

public class Tax {
	private int vat;
	private int salesTax;
	private int shippingTax;
	private int discountRate;
	
	
	public Tax() {
		
	}


	public int getVat() {
		return vat;
	}


	public void setVat(int vat) {
		this.vat = vat;
	}


	public int getSalesTax() {
		return salesTax;
	}


	public void setSalesTax(int salesTax) {
		this.salesTax = salesTax;
	}


	public int getShippingTax() {
		return shippingTax;
	}


	public void setShippingTax(int shippingTax) {
		this.shippingTax = shippingTax;
	}


	public int getDiscountRate() {
		return discountRate;
	}


	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	
	
	
}
