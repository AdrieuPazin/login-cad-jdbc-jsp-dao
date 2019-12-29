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
import dao.UsersDao;
import entities.Users;
/**
 * Servlet implementation class Users
 */
@WebServlet("/UsersController")
public class UsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String acao = request.getParameter("acao");
		
		if (acao != null && acao.equals("add")) {
			adicionarUsuario(request, response);
		}
		
		if (acao != null && acao.equals("edit")) {
			editarUsuario(request, response);
		}
		
		if (acao != null && acao.equals("exc")) {
			
			excluirUsuario(request, response);			
			
		}
		
		
		listarUsuarios(request, response);
		
		
	
	}

	private void excluirUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UsersDao userDao = DaoFactory.createUsersDao();
		userDao.deleteById(Integer.parseInt(request.getParameter("id")));
		
		listarUsuarios(request, response);
	}

	private void editarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UsersDao userDao = DaoFactory.createUsersDao();
		Users user = userDao.findById(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("user", user);
		
		RequestDispatcher rd = request.getRequestDispatcher("edtUsers.jsp");
		rd.forward(request, response);
	}

	private void adicionarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("edtUsers.jsp");
		rd.forward(request, response);
	}

	private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsersDao userDao = DaoFactory.createUsersDao();
		List<entities.Users> lista = userDao.findAll();
		
		request.setAttribute("lista", lista);
		
		RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		String acao = request.getParameter("acao");
			if (acao.equals("edit_proc")) {
				
				Integer id = Integer.parseInt(request.getParameter("frmUserId"));
				String email = request.getParameter("frmUserEmail");
				String pass = request.getParameter("frmUserPass");
				
				
				
				Users user = new Users();
				user.setId(id);
				user.setEmail(email);
				user.setPass(pass);
				
				UsersDao userDao = DaoFactory.createUsersDao();
				userDao.saveUser(user);
				
				response.sendRedirect("UsersController");
				
			}
			
			if (acao.equals("adicionar_proc")) {
				
				String email = request.getParameter("frmUserEmail");
				String pass = request.getParameter("frmUserPass");			
				
				Users user = new Users();
				user.setEmail(email);
				user.setPass(pass);
				
				UsersDao userDao = DaoFactory.createUsersDao();
				userDao.saveUser(user);
				
				response.sendRedirect("UsersController");
				
				
			}
			
	}

}
