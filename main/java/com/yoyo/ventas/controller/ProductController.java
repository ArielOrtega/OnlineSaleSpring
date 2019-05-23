package com.yoyo.ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yoyo.ventas.business.ProductBusiness;

@Controller
public class ProductController {
	@Autowired
	private ProductBusiness productBusiness;
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public String initiate(Model model) {
		model.addAttribute("products", productBusiness.findTop());
		return "findProducts";
	}
}
