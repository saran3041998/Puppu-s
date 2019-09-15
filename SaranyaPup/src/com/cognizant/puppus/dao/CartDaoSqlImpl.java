package com.cognizant.puppus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.puppus.model.Cart;
import com.cognizant.puppus.model.MenuItem;

public class CartDaoSqlImpl implements CartDao {
	Connection connection = ConnectionHandler.getConnection();
	ResultSet rs = null;
	PreparedStatement stmt = null;

	@Override
	public void addCartItem(long userId, long menuItemId) {
		// TODO Auto-generated method stub
		try {
			stmt = connection.prepareStatement("INSERT INTO `puppus`.`cart` (`ct_us_id`, `ct_pr_id`) VALUES (?, ?)");
			try {
				stmt.setInt(1, (int) userId);
				stmt.setInt(2, (int) menuItemId);
				stmt.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Cart getAllCartItems(long userId) throws CartEmptyException {
		// TODO Auto-generated method stub
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		Cart cart = null;
		try {
			stmt = connection.prepareStatement(
					"select menu.me_id, menu.me_name, menu.me_price, menu.me_active, me_quantity from menuitem menu inner join cart Cart on Cart.ct_us_id = ? and Cart.ct_pr_id = menu.me_id");
			stmt.setInt(1, (int) userId);
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
				if (menuItemList.isEmpty()) {
					throw new CartEmptyException();
				} else {
					PreparedStatement stmt1 = connection.prepareStatement(
							"select sum(menu.me_price) from menuitem menu inner join cart Cart on Cart.ct_us_id = ? and Cart.ct_pr_id = menu.me_id");
					stmt1.setInt(1, (int) userId);
					ResultSet rs1 = stmt1.executeQuery();
					while (rs1.next()) {
						cart = new Cart(menuItemList, rs1.getDouble(1));
					}
					rs1.close();
					stmt1.close();
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
		return cart;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		// TODO Auto-generated method stub
		try {
			stmt = connection.prepareStatement(
					"delete Cart from cart Cart inner join menuitem menu on menu.me_id=Cart.ct_pr_id where Cart.ct_us_id=? and Cart.ct_pr_id=?");
			try {
				stmt.setInt(1, (int) userId);
				stmt.setInt(2, (int) menuItemId);
				stmt.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
