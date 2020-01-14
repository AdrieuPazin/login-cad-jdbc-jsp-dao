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

@WebServlet("/UsersController")
public class UsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UsersController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String acao = request.getParameter("acao");
			if (acao.equals("edit_proc")) {				
				editarUserProc(request, response);					
			}
			
			if (acao.equals("adicionar_proc")) {				
				addUserProc(request, response);				
			}
			
	}


	private void addUserProc(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String email = request.getParameter("frmUserEmail");
		UsersDao userDao = DaoFactory.createUsersDao();
		
		if(userDao.findByEmail(email) == false) {
			String pass = request.getParameter("frmUserPass");			
			
			Users user = new Users();
			user.setEmail(email);
			user.setPass(pass);
			
			userDao.saveUser(user);
			
			response.sendRedirect("UsersController");
			
		}else {
			String invalidEmail = "<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\n" + 
					"  E-mail já existente. Por favor, tente outro e-mail!\n" + 
					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" + 
					"    <span aria-hidden=\"true\">&times;</span>\n" + 
					"  </button>\n" + 
					"</div>";
			request.setAttribute("invalidEmail", invalidEmail);
			RequestDispatcher rq = request.getRequestDispatcher("edtUsers.jsp");
			rq.forward(request, response);
		}
	}


	private void editarUserProc(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Integer id = Integer.parseInt(request.getParameter("frmUserId"));
		String email = request.getParameter("frmUserEmail");

		UsersDao userDao = DaoFactory.createUsersDao();
		Users user = userDao.findById(id)
;		
		if(user.getEmail().equals(email.trim()) && userDao.findByEmail(email)) {
			
			String pass = request.getParameter("frmUserPass");
			
			
			user.setId(id);
			user.setEmail(email);
			user.setPass(pass);
			
			userDao.saveUser(user);
			
			response.sendRedirect("UsersController");
			
		} else {
			String invalidEmail = "<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\n" + 
					"  E-mail diferente do cadastro!\n" + 
					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" + 
					"    <span aria-hidden=\"true\">&times;</span>\n" + 
					"  </button>\n" + 
					"</div>";
			
			request.setAttribute("invalidEmail", invalidEmail);
			RequestDispatcher rd = request.getRequestDispatcher("edtUsers.jsp");
			rd.forward(request, response);
		}
	}

}
