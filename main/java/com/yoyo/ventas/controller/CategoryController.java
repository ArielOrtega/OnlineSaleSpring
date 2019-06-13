package com.yoyo.ventas.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
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
import com.yoyo.ventas.domain.Category;
import com.yoyo.ventas.domain.Product;

@Controller
public class CategoryController {
	@Autowired
	private CategoryBusiness categoryBusiness;	
	
	@RequestMapping(value="/home/addCategory", method=RequestMethod.GET)
	public String registerProductForm(Model model, HttpServletRequest request) {
		
		model.addAttribute("categories", categoryBusiness.findAll());
		
		return "registerCategory";
	}
	
	//@RequestParam("images") MultipartFile[] images
	@RequestMapping(value="/home/addCategory", method=RequestMethod.POST)
	public String registerProduct(Model model, @RequestParam("categoryName") String categoryName) {
		//StringBuilder fileNames = new StringBuilder();
		
		if(categoryName.isEmpty()) {
			model.addAttribute("err","Must enter a name");
			return "registerCategory";
			
		} else {
			model.addAttribute("err","");
		Category ctgry = new Category();
		ctgry.setCategoryName(categoryName);
		categoryBusiness.addCategory(ctgry);
		return "redirect:/home/maintenanceCategory";
		}
	}	
	
	@RequestMapping(value="/home/maintenanceCategory", method=RequestMethod.GET)
	public String seeProducts( Model model) throws SQLException {
		model.addAttribute("categories", new ArrayList<Category>());
		return "redirect:/home/maintenanceCategory";
		
		
	}
	
	@RequestMapping(value="/home/maintenanceCategory", method=RequestMethod.POST)
	public String seeProducts( Model model, @RequestParam("categoryName") String categoryName)  {
		model.addAttribute("categories", categoryBusiness.findByName(categoryName));
		return "categoryMaintenance";
				
	}	

}
