package com.niit.shoppingcartback.dao;

import java.util.List;

import com.niit.shoppingcartback.model.Cart;

public interface CartDAO {
	
public List<Cart> list(String emailid);
	
	public List<Cart> list();
	
	public List<Cart> getlist(String usersId);
	
	public Cart get(String id);
	
	public void saveOrUpdate(Cart cart);
	
	public void delete(String cartId);
		
	public Long getTotalAmount(String id);
	
	public Long getCount(String username);
	
	public List<Cart> search(String keyWord);
	
	public boolean itemAlreadyExist(String usersId, String product_Id, boolean b);
	
	public Cart getByUserandProduct(String usersId, String product_Id);
	
	public void UpdateSatus(String username);
	
}
