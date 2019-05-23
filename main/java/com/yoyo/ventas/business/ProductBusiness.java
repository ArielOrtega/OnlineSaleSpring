package com.yoyo.ventas.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoyo.ventas.data.ProductData;
import com.yoyo.ventas.domain.Product;

@Service
public class ProductBusiness {
	@Autowired
	private ProductData productData;
	
	public List<Product> findTop(){
		return productData.findTop();
	}
}
