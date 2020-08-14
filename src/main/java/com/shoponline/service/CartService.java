package com.shoponline.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendshoponline.dao.CartLineDAO;
import com.backendshoponline.dao.ProductDAO;
import com.backendshoponline.dto.Cart;
import com.backendshoponline.dto.CartLine;
import com.backendshoponline.dto.Product;
import com.shopping.model.UserModel;

@Service("cartService")
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;
	@Autowired
	private ProductDAO productDAO;
	
	
	
	@Autowired
	private HttpSession session;
	
	public Cart getCart()
	{
		return   ( (UserModel)session.getAttribute("userModel")).getCart();
	}

	public List<CartLine> getCartLines()
	{
		return cartLineDAO.list(this.getCart().getId());
	}

	public String updateCartLine(int cartLineId, int count) {
		//fetch the cart line
		CartLine cartLine = cartLineDAO.get(cartLineId);
		
		if(cartLine == null){
			return "result=error";
		}
		else
		{
			Product product=cartLine.getProduct();
			
			double oldTotal = cartLine.getTotal();
			if(product.getQuantity() <= count)
			{
				return "result=unavailable";
			}
		    cartLine.setProductCount(count);
		    cartLine.setBuyingPrice(product.getUnitPrice());
		    cartLine.setTotal(product.getUnitPrice() * count);
		    cartLineDAO.update(cartLine);
		    Cart cart =this.getCart();
		    cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
		    cartLineDAO.updateCart(cart);
			return "result=updated";
		}
		
		
		
	
	}

	public String deleteCartLine(int cartLineId) {
		CartLine cartLine= cartLineDAO.get(cartLineId);
		if(cartLine != null)
		{
			Cart cart=this.getCart();
			cart.setGrandTotal(cart.getGrandTotal()-cartLine.getTotal());
			cart.setCartLines(cart.getCartLines()-1);
			cartLineDAO.updateCart(cart);
			cartLineDAO.remove(cartLine);
			if(cart.getGrandTotal()==0.0){
				cart.setCartLines(0);
			}
			return "result=deleted";
			
			
		}
		else{
			return "result=error";
		}
		
	}

	public String addCartLine(int productId) {
		String response=null;
		Cart cart=this.getCart();
		CartLine cartLine=cartLineDAO.getByCartAndProduct(cart.getId(), productId);

		
		if(cartLine == null)
		{
			
			CartLine cartline=new CartLine();
			cartline.setCartId(cart.getId());
			Product product=productDAO.get(productId);
			cartline.setProduct(product);
			cartline.setBuyingPrice(product.getUnitPrice());
			cartline.setAvailable(true);
			cartline.setProductCount(1);
			cartline.setTotal(product.getUnitPrice());
			cartLineDAO.add(cartline);
			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal()+cartline.getTotal());
			cartLineDAO.updateCart(cart);
			response ="result=added";
			return response;
		}
		if(cartLine.getProductCount()<3)
		{
			response = this.updateCartLine(cartLine.getId(), cartLine.getProductCount()+1);
		}
		else{
			response = "result=maximum";
			return response;
		}
		
		return response;
		
		
		
		
		
	}
	
	
	
}
