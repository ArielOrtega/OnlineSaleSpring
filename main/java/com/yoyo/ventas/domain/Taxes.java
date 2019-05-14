package com.yoyo.ventas.domain;

public class Taxes {
	private int vat;
	private int sales_tax;
	private int shipping_tax;
	private int discount_rate;
	
	
	public Taxes() {
		
	}


	public int getVat() {
		return vat;
	}


	public void setVat(int vat) {
		this.vat = vat;
	}


	public int getSales_tax() {
		return sales_tax;
	}


	public void setSales_tax(int sales_tax) {
		this.sales_tax = sales_tax;
	}


	public int getShipping_tax() {
		return shipping_tax;
	}


	public void setShipping_tax(int shipping_tax) {
		this.shipping_tax = shipping_tax;
	}


	public int getDiscount_rate() {
		return discount_rate;
	}


	public void setDiscount_rate(int discount_rate) {
		this.discount_rate = discount_rate;
	}
	
}
