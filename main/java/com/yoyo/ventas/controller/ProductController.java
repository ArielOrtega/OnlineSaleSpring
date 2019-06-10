package com.yoyo.ventas.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yoyo.ventas.business.CategoryBusiness;
import com.yoyo.ventas.business.ProductBusiness;
import com.yoyo.ventas.domain.Category;
import com.yoyo.ventas.domain.Product;
import com.yoyo.ventas.form.ProductForm;

@Controller
public class ProductController {
	@Autowired
	private ProductBusiness productBusiness;
	@Autowired
	private CategoryBusiness categoryBusiness;
	
	public static String uploadDirectory = System.getProperty("user.dir");
	
	@RequestMapping(value="/store/products", method=RequestMethod.GET)
	public String initiate(Model model) {
		model.addAttribute("products", productBusiness.findTop());
		return "findProducts";
	}
	
	@RequestMapping(value="/home/register", method=RequestMethod.GET)
	public String registerProductForm(Model model) {
		
		model.addAttribute("productForm", new ProductForm());
		model.addAttribute("categories", categoryBusiness.findAll());
		
		return "registerProduct";
	}
	
	//@RequestParam("images") MultipartFile[] images
	@RequestMapping(value="/home/register", method=RequestMethod.POST)
	public String registerProduct(@Valid ProductForm productForm,BindingResult br, Model model) throws SerialException, SQLException, IOException {
		//StringBuilder fileNames = new StringBuilder();

		if(br.hasErrors()) {
			model.addAttribute("productForm", new ProductForm());
			model.addAttribute("categories", categoryBusiness.findAll());
			return "registerProduct";
			
		}
		
		Category category = new Category();
		category.setCategoryId(productForm.getCategoryId());
		
		MultipartFile[] files = productForm.getImages();
		Blob[] images= new Blob[files.length];
		int i= files.length - 1;
		for (MultipartFile img : files) {
			Blob blob = new SerialBlob(img.getBytes());
			images[i]= blob;
			if(i==0)
				break;
			i--;
		}
		
		Product product = new Product(productForm.getProductName(),
				productForm.getDescription(), productForm.getPrice(), productForm.getStockUnits(), 
				images, category);
		
		productBusiness.registerProduct(product);
		return "registerProductExit";
	}

	@RequestMapping(value="/home/maintenance", method=RequestMethod.GET)
	public String seeProducts( Model model) {
		model.addAttribute("products", new ArrayList<Product>());
		//Blob blob = productBusiness.findAll(productName).get(3).getImages()[0];
		//model.addAttribute("blob", blob);
		return "productMaintenance";
		
		
	}
	
	@RequestMapping(value="/home/maintenance", method=RequestMethod.POST)
	public String seeProducts( Model model, @RequestParam("productName") String productName) {
		model.addAttribute("products", productBusiness.findAll(productName));
		//model.Blob blob = productBusiness.findAll(productName).get(3).getImages()[0];
		//model.addAttribute("blob", blob);
		return "productMaintenance";
		
		
	}	
}
