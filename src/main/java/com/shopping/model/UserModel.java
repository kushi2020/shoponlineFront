package com.shopping.model;

import java.io.Serializable;

import com.backendshoponline.dto.Cart;

public class UserModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String role;
	private String fullName;
	private String email;
	private Cart cart;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", role=" + role + ", fullName=" + fullName + ", email=" + email + ", cart="
				+ cart + "]";
	}
	
	
	
	

}
