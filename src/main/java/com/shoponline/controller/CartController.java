package com.shoponline.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shoponline.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	private final static Logger logger = LoggerFactory.getLogger(CartController.class);
    @RequestMapping(value="/show",method=RequestMethod.GET)
	public ModelAndView showCart(@RequestParam(name="result",required=false) String result)
	{	ModelAndView mv=new ModelAndView("page");
	    if(result != null)
	    {
	    	switch(result)
	    	{
	    	case "unavailable":
	    		mv.addObject("message","Product is unavailable!");
	    		break;
	    	case "maximum":
	    		mv.addObject("message","Product is maximum!");
	    		break;
	    	case "added":
	    		mv.addObject("message","CatLine Added successfully!");
	    		break;
	    	case "updated":
	    		mv.addObject("message","CatLine Updated successfully!");
	    		break;
	    	case "deleted":
	    		mv.addObject("message","CatLine Deleted successfully!");
	    		break;
	    	case "error":
	    		mv.addObject("message", "Something went wrong");
	    		break;
	    	}
	    	
	    }
	
	
		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart", true);
		mv.addObject("cartLines", cartService.getCartLines());
		return mv;
	}
	@RequestMapping("/{cartLineId}/update")
	public String updateCart(@PathVariable int cartLineId,@RequestParam int count){
		String response=cartService.updateCartLine(cartLineId,count);
		return "redirect:/cart/show?"+response;
	}
    
	@RequestMapping("/{cartLineId}/remove")
	public String updateCart(@PathVariable int cartLineId){
		String response=cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?"+response;
	} 
	
    @RequestMapping("/add/{cartLineId}/product")
    public String addCart(@PathVariable int cartLineId){
		String response=cartService.addCartLine(cartLineId);
		return "redirect:/cart/show?"+response;
	} 
	
}
