package com.shoponline.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNohandlerFoundException()
	{
		ModelAndView mv =new ModelAndView("error");
		mv.addObject("errorTitle","The page is not constructed!");
		mv.addObject("errorDescription","The page you are looking for is not available now!");
		mv.addObject("title","404 Error page");
		return mv;
	}
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView ProductNotFoundException()
	{
		ModelAndView mv =new ModelAndView("error");
		mv.addObject("errorTitle","product is not available!");
		mv.addObject("errorDescription","The Product you are looking for is not available right now!");
		mv.addObject("title","Product Unavailable ");
		return mv;
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex)
	{
		ModelAndView mv =new ModelAndView("error");
		mv.addObject("errorTitle","Contact Your Administrator!!");
		mv.addObject("errorDescription","Some Error Generated");
		mv.addObject("title","Error");
		return mv;
	}
	
	

	
	
	

}
