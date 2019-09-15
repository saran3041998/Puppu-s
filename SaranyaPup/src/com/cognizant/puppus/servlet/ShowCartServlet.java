package com.cognizant.puppus.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.puppus.dao.CartDao;
import com.cognizant.puppus.dao.CartDaoSqlImpl;
import com.cognizant.puppus.dao.CartEmptyException;
import com.cognizant.puppus.model.Cart;
import com.cognizant.puppus.model.MenuItem;

/**
 * Servlet implementation class ShowCartServlet
 */
@WebServlet("/ShowCart")
public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		CartDaoSqlImpl cartDaoSqlImpl = new CartDaoSqlImpl();
		CartDao cartDao = cartDaoSqlImpl;
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		try {
			Cart cart = cartDao.getAllCartItems(1);
			for (MenuItem menuItem : cart.getMenuItemList()) {
				menuItemList.add(menuItem);
			}
			request.setAttribute("menuItemList", menuItemList);
			request.setAttribute("cart", cart);
			RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
			rd.forward(request, response);
		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block
			RequestDispatcher rd = request.getRequestDispatcher("cart-empty.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
