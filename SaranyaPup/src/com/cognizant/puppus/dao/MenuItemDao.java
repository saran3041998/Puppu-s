package com.cognizant.puppus.dao;

import java.util.List;

import com.cognizant.puppus.model.MenuItem;

public interface MenuItemDao {

	public List<MenuItem> getMenuItemListCustomer();

	public MenuItem getMenuItem(long menuItemId);

}
