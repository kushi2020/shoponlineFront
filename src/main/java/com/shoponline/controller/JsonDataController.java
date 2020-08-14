package com.shoponline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.backendshoponline.dao.ProductDAO;
import com.backendshoponline.dto.Product;

@Controller
@RequestMapping("json/data")
public class JsonDataController {
    @Autowired
	private ProductDAO productDAO;
    
    @RequestMapping("/all/products")
    @ResponseBody
    public List<Product> getAllProducts(){
    	
    	return productDAO.listActiveProducts();
    }
    
    @RequestMapping("admin/all/products")
    @ResponseBody
    public List<Product> getAllProductsForAdmin(){
    	
    	return productDAO.list();
    }
    @RequestMapping(value = "/product/{id}/activation", method=RequestMethod.POST)
    @ResponseBody
    public String handleProductActivation(@PathVariable int id){
    	Product product = productDAO.get(id);
    	boolean isActive= product.isActive();
    	System.out.println("isActive----------------"+ isActive);
    	//activating and deactivating based on the value of active field
    	product.setActive(!product.isActive());
    	//updating the product
    	productDAO.update(product);
    	System.out.println("isActive After---------------------"+ isActive);
    	
    	return (isActive)?"You have succesfully deactivated the product with id " + product.getId():"You have succesfully activated the product with id " + product.getId();
    }
    
    
	
    @RequestMapping("/category/{id}/products")
    @ResponseBody
    public List<Product> getProductByCategory(@PathVariable int id){
    		return productDAO.listActiveProductByCategory(id);
    	
    }
    
}
