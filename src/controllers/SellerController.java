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
import dao.DepartmentDao;
import dao.SellerDao;
import entities.Department;
import entities.Seller;


@WebServlet("/SellerController")
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SellerController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String acao = request.getParameter("acao");
		
		if(acao!= null && acao.equals("add")) {
			adicionarVendedor(request, response);
		}
		
		if(acao!= null && acao.equals("edit")) {			
			editarVendedor(request, response);			
		}
		if(acao!= null &&  acao.equals("exc")) {			
			excluirVendedor(request, response);			
		}
		
		listarSellers(request, response);
		
		
	}


	private void excluirVendedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		sellerDao.deleteById(Integer.parseInt(request.getParameter("id")));
		listarSellers(request, response);
	}


	private void editarVendedor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(Integer.parseInt(request.getParameter("id")));
		
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		List<Department> listDepartment = depDao.findAll();
		
		request.setAttribute("listDep", listDepartment);
		request.setAttribute("seller", seller);
		
		RequestDispatcher rd = request.getRequestDispatcher("edtSellers.jsp");
		rd.forward(request, response);
	}


	private void adicionarVendedor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		List<Department> listDepartment = depDao.findAll();
		
		request.setAttribute("listDep", listDepartment);
		
		RequestDispatcher rd = request.getRequestDispatcher("edtSellers.jsp");
		rd.forward(request, response);
	}


	private void listarSellers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		List<Seller> lista = sellerDao.findAll();
		
		request.setAttribute("lista", lista);
		
		RequestDispatcher rd = request.getRequestDispatcher("seller.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		
		String acao = request.getParameter("acao");
		
		
		if (acao.equals("edit_proc")) {
			
			
			Integer id = Integer.parseInt(request.getParameter("frmSellerId"));
			String nome = request.getParameter("frmSellerName");
			String email = request.getParameter("frmSellerEmail");
			String birthDate = request.getParameter("frmSellerBirthDate");
			String baseSalary = request.getParameter("frmSellerBaseSalary");
			Integer departmentId = Integer.parseInt(request.getParameter("selectSellerDepartment"));
			
			DepartmentDao depDao = DaoFactory.createDepartmentDao();
			Department dep = depDao.findById(departmentId);
			
			
			Seller seller = new Seller();
			seller.setId(id);
			seller.setName(nome);
			seller.setEmail(email);
			//seller.setBirthDate( new Date());
			seller.setBirthDate(seller.retornaDate(birthDate));
			seller.setBaseSalary(Double.parseDouble(baseSalary));
			seller.setDepartment(dep);
			
			
			SellerDao sellerDao = DaoFactory.createSellerDao();
			seller.setDepartment(dep);
			
			sellerDao.saveSeller(seller);
			
			response.sendRedirect("SellerController");
			
		}
		
		if (acao.equals("add_proc")) {
			
			String nome = request.getParameter("frmSellerName");
			String email = request.getParameter("frmSellerEmail");
			String birthDate = request.getParameter("frmSellerBirthDate");
			String baseSalary = request.getParameter("frmSellerBaseSalary");
			Integer departmentId = Integer.parseInt(request.getParameter("selectSellerDepartment"));
			
			DepartmentDao depDao = DaoFactory.createDepartmentDao();
			Department dep = depDao.findById(departmentId);
			
			
			Seller seller = new Seller();
			seller.setName(nome);
			seller.setEmail(email);
			seller.setBirthDate(seller.retornaDate(birthDate));
			seller.setBaseSalary(Double.parseDouble(baseSalary));
			seller.setDepartment(dep);
			
			
			SellerDao sellerDao = DaoFactory.createSellerDao();
			sellerDao.saveSeller(seller);
			
			response.sendRedirect("SellerController");
			
			
		}
		
		
	}

}
