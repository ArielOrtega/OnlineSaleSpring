package com.yoyo.ventas.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
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

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
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
	
	@RequestMapping(value="/store/product/details", method=RequestMethod.GET)
	public String details(Model model, @RequestParam("productId") int productId) {
		model.addAttribute("product", productBusiness.findProductById(productId));
		return "productDetails";
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
	public String seeProducts( Model model) throws SQLException {
		model.addAttribute("products", new ArrayList<Product>());

		
		return "productMaintenance";
		
		
	}
	
	@RequestMapping(value="/home/maintenance", method=RequestMethod.POST)
	public String seeProducts( Model model, @RequestParam("productName") String productName) throws SQLException, UnsupportedEncodingException {
		model.addAttribute("products", productBusiness.findAll(productName));
		//Blob blob = productBusiness.findAll("Del Inspiron 15").get(0).getImages()[0];
		//byte byteArray[] = blob.getBytes(1, (int) blob.length());
		//byte[] encodeBase64 = Base64.encode(blob);
		//byte[] encodeBase64 = Base64.getEncoder().encode(blob.getBytes(1, (int) blob.length()));
		//String base64Encoded = new String(encodeBase64, "UTF-8");
		//model.addAttribute("blob", blob );
		
		//model.addAttribute("blob", blob);
		return "productMaintenance";
		
		
	}	
}
