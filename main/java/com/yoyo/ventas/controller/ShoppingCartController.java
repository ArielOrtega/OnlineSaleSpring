package com.yoyo.ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yoyo.ventas.business.ShoppingCartBusiness;
import com.yoyo.ventas.domain.Product;
import com.yoyo.ventas.domain.ShoppingCart;

@Controller
public class ShoppingCartController {
	@Autowired
	private ShoppingCartBusiness shoppingCartBusiness;
	
	@RequestMapping(value="/store/shoppingCart/add", method=RequestMethod.GET)
	public String addToCartKeep(Model model, @RequestParam("productId") int productId, @RequestParam("quantity") int quantity) {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setQuantity(quantity);
		Product product = new Product();
		product.setProductId(productId);
		shoppingCart.setProduct(product);
		shoppingCartBusiness.saveShoppingCart(shoppingCart);
		return "findProducts";
	}
}
