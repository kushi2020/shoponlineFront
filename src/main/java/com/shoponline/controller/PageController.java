package com.shoponline.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.backendshoponline.dao.CategoryDAO;
import com.backendshoponline.dao.ProductDAO;
import com.backendshoponline.dto.Category;
import com.backendshoponline.dto.Product;
import com.shoponline.exception.ProductNotFoundException;



@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	@Autowired
   private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;
	
	
	@RequestMapping(value={"/","/index","/home"})
	public ModelAndView index(@RequestParam(name="logout",required=false)String logout)
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title", "Home");
		
		logger.info("inside PageController index method - INFO");
		logger.debug("inside PageController index method - DEBUG");
		
		mv.addObject("categories", categoryDAO.list() );
		
		if(logout!=null) {
			mv.addObject("message", "You have successfully logged out!");			
		}
		
		
		
		mv.addObject("userClick", true);
		return mv;
		
	}
	
	@RequestMapping(value={"/about"})
	public ModelAndView about()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title", "About");
		mv.addObject("userClickAbout", true);
		return mv;
		
	}

	@RequestMapping(value={"/contact"})
	public ModelAndView contact()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title", "Contact");
		mv.addObject("userClickContact", true);
		return mv;
		
	}
	/*login page*/
	@RequestMapping(value={"/login"})
	public ModelAndView login(@RequestParam(name="error",required=false) String error,@RequestParam(name="logout",required=false) String logout)
	{
		ModelAndView mv=new ModelAndView("login");
		if(error!=null)
		{
			mv.addObject("message", "Invalid UserName And Password");
		}
		
		if(logout!=null)
		{
			mv.addObject("logout", "you successfully logout");
		}
		
		
		mv.addObject("title", "Login");
		
		return mv;
		
	}
	
	
	
	
	@RequestMapping(value={"/show/all/products"})
	public ModelAndView showAllProducts()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title", "All Products");
		mv.addObject("categories", categoryDAO.list() );
		mv.addObject("userClickAllProducts", true);
		return mv;
		
	}
	
	@RequestMapping(value={"/show/category/{id}/products"})
	public ModelAndView showCategoryProducts(@PathVariable("id") int id)
	{	ModelAndView mv=new ModelAndView("page");
	    Category category=null;
	    category=categoryDAO.get(id);
	    
		mv.addObject("title", category.getName());
		mv.addObject("categories", categoryDAO.list() );
		mv.addObject("category", category );
		
		mv.addObject("userClickCategoryProducts", true);
		return mv;
		
	}
	
	@RequestMapping(value="/show/{id}/product") 
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException
	{
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		if(product == null) throw new ProductNotFoundException();
		
		//update the view count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
		mv.addObject("title",product.getName());
		mv.addObject("product",product);
		mv.addObject("userClickShowProduct",true);
		return mv;
		
	}
	
	@RequestMapping(value={"/access-denied"})
	public ModelAndView accessDenied()
	{
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("title", "403-Access Denied");
		mv.addObject("errorTitle", "Ah! Caught You");
		mv.addObject("errorDiscription", "you are not authorized to access this page");
		return mv;
	}
	

	
	
	@RequestMapping(value="/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// Invalidates HTTP Session, then unbinds any objects bound to it.
	    // Removes the authentication from securitycontext 		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		
		return "redirect:/login?logout";
	}


}
