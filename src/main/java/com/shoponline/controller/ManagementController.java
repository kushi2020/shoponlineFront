package com.shoponline.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.backendshoponline.dao.CategoryDAO;
import com.backendshoponline.dao.ProductDAO;
import com.backendshoponline.dto.Category;
import com.backendshoponline.dto.Product;
import com.shoponline.util.FileUploadUtility;
import com.shoponline.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class); 
	
	
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		Product nproduct = new Product();
	    
	    //set few of the fields
	    nproduct.setSupplierId(1);
	    nproduct.setActive(true);
	    mv.addObject("product", nproduct);
	    if(operation != null){
	    	
	    	
	    	if(operation.equals("product")){
	    		mv.addObject("message","Product Submitted Successfully!"); 
	    	}
	    	else if(operation.equals("category")){
	    		mv.addObject("message","Category Submitted Successfully!"); 
	    	}
	    	
	    }
		return mv;
		
	}
	
	@RequestMapping(value="{id}/product",method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id){
		ModelAndView mv =new ModelAndView("page");
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title", "ManageProducts");
		//fetch product from database
		Product nProduct = productDAO.get(id);
		//set the product fetch from database
		mv.addObject("product", nProduct);
		return mv;
	}
	
	
	
	
	
	
	
	
	//handling product submission
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult results,Model model,HttpServletRequest request)
	{
		
		if(mProduct.getId() == 0){
			//handle image validation for new products
		new ProductValidator().validate(mProduct,results);
		}
		else
		{
			if(!mProduct.getFile().getOriginalFilename().equals("")){
				new ProductValidator().validate(mProduct, results);
			}
		}
		
		
		//check if here are any errors
		if(results.hasErrors()){
			model.addAttribute("userClickManageProducts",true);
			model.addAttribute("title","Manage Products");
			
			return "page";
			
		}
		
	  logger.info(mProduct.toString());
	  if(mProduct.getId() == 0){
		//create new product
		productDAO.add(mProduct);
	  }
	  else
	  {
		  //update the product if id is not 0
		  productDAO.update(mProduct);
	  }
		
		
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		
		 
		
		return "redirect:/manage/products?operation=product";
	}
	
	
	
	
	
	//returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		
		return categoryDAO.list();
	}
	//to handle category submission
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category){
		//add the new category
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=catgory";
		
	}
	
	
	

	@ModelAttribute("category")
	public Category getCategory(){
		return new Category();
		
	}
	
	
	

}
