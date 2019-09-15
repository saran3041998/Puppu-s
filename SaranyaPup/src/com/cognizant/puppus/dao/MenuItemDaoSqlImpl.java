package com.cognizant.puppus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.puppus.model.MenuItem;

public class MenuItemDaoSqlImpl implements MenuItemDao {
	Connection connection = ConnectionHandler.getConnection();
	ResultSet rs = null;
	PreparedStatement stmt = null;

	public MenuItemDaoSqlImpl() {
		super();
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		// TODO Auto-generated method stub
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		try {
			stmt = connection.prepareStatement(
					"select me_id, me_name, me_price, me_active, me_quantity from MENUITEM where me_active = 'Yes'");
			try {
				rs = stmt.executeQuery();
				while (rs.next()) {
					MenuItem menuItem = new MenuItem();
					menuItem.setId(rs.getLong(1));
					menuItem.setName(rs.getString(2));
					menuItem.setPrice(rs.getFloat(3));
					String active = rs.getString(4);
					if (active.equals("Yes")) {
						menuItem.setActive(true);
					} else {
						menuItem.setActive(false);
					}
					menuItem.setQuantity(rs.getInt(5));
					menuItemList.add(menuItem);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				rs.close();
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItemList;
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		MenuItem menuItem = new MenuItem();
		try {
			stmt = connection.prepareStatement(
					"select me_id, me_name, me_price, me_active, me_quantity from MENUITEM where me_id=?");
			try {
				stmt.setInt(1, (int) menuItemId);
				rs = stmt.executeQuery();
				if (rs.next()) {
					menuItem.setId(rs.getLong(1));
					menuItem.setName(rs.getString(2));
					menuItem.setPrice(rs.getFloat(3));
					String active = rs.getString(4);
					if (active.equals("Yes")) {
						menuItem.setActive(true);
					} else {
						menuItem.setActive(false);
					}
					menuItem.setQuantity(rs.getInt(5));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				rs.close();
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItem;
	}
}
