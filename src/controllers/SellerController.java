package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.SellerDao;
import entities.Seller;


@WebServlet("/SellerController")
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SellerController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		List<Seller> lista = sellerDao.findAll();
		
		request.setAttribute("lista", lista);
		
		RequestDispatcher rd = request.getRequestDispatcher("seller.jsp");
		rd.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
