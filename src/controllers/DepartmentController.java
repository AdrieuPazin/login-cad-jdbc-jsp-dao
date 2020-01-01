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

@WebServlet("/DepartmentController")
public class DepartmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DepartmentController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String acao = request.getParameter("acao");

		if (acao != null && acao.equals("exc")) {
			excluirDepartment(request, response);
		}

		if (acao != null && acao.equals("edit")) {
			editarDepartment(request, response);
		}

		if (acao != null && acao.equals("add")) {
			adicionarDepartment(request, response);
		}

		listarDepartment(request, response);

	}

	private void adicionarDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("edtDepartment.jsp");
		rd.forward(request, response);
	}

	private void listarDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		List<Department> list = depDao.findAll();

		request.setAttribute("listDep", list);

		RequestDispatcher rq = request.getRequestDispatcher("department.jsp");
		rq.forward(request, response);
	}

	private void editarDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		Department dep = depDao.findById(id);

		request.setAttribute("dep", dep);

		adicionarDepartment(request, response);
	}

	private void excluirDepartment(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));

		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		Department dep = depDao.findById(id);

		SellerDao sellerdao = DaoFactory.createSellerDao();
		List<Seller> list = sellerdao.findByDepartment(dep);
		if (list.isEmpty()) {
			depDao.deleteById(id);
			listarDepartment(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);

		String acao = request.getParameter("acao");

		if (acao.equals("edit_proc")) {

			int id = Integer.parseInt(request.getParameter("frmDepartmentId"));
			String nome = request.getParameter("frmDepartmentName");

			DepartmentDao depDao = DaoFactory.createDepartmentDao();
			Department dep = new Department();

			dep.setId(id);
			dep.setName(nome);

			depDao.salvarDepartment(dep);

			response.sendRedirect("DepartmentController");

		}

		if (acao.equals("add_proc")) {

			String nome = request.getParameter("frmDepartmentName");

			DepartmentDao depDao = DaoFactory.createDepartmentDao();
			Department dep = new Department();

			dep.setName(nome);

			depDao.salvarDepartment(dep);

			response.sendRedirect("DepartmentController");

		}

	}

}
