package com.cognizant.puppus.dao;

import com.cognizant.puppus.model.Cart;

public interface CartDao {
	public void addCartItem(long userId, long menuItemId);

	public Cart getAllCartItems(long userId) throws CartEmptyException;

	public void removeCartItem(long userId, long menuItemId);

}
