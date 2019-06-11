package com.yoyo.ventas.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoyo.ventas.data.ShoppingCartData;
import com.yoyo.ventas.domain.ShoppingCart;

@Service
public class ShoppingCartBusiness {
	@Autowired
	private ShoppingCartData shoppingCartData;
	
	public int saveShoppingCart(ShoppingCart shoppingCart) {
		return shoppingCartData.saveShoppingCart(shoppingCart);
	}
}
