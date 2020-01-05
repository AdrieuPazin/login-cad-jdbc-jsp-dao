package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.UsersDao;
import entities.Users;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Login() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

				
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		doGet(request, response);
		
		String email = request.getParameter("frmLoginEmail");
		String pass = request.getParameter("frmLoginPass");
		
		
		UsersDao userDao = DaoFactory.createUsersDao();
		Users user = userDao.findByLogin(email, pass);
		if (user != null && user.getEmail().equals(email.trim()) && user.getPass().equals(pass)) {
		
			HttpSession session = request.getSession();
			session.setAttribute("email", user.getEmail());
			session.setAttribute("pass", user.getPass());
			session.setAttribute("id", user.getId());
			
			String mensagem = "<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n" + 
					"  Logado com sucesso\n" + 
					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" + 
					"    <span aria-hidden=\"true\">&times;</span>\n" + 
					"  </button>\n" + 
					"</div>";
			request.setAttribute("mensagem", mensagem);
			RequestDispatcher rq = request.getRequestDispatcher("home.jsp");
			rq.forward(request, response);
			
			//response.sendRedirect("home.jsp");
		} else {			
			String mensagem =  "<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n" + 
					"  Login não encontrado!\n" + 
					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" + 
					"    <span aria-hidden=\"true\">&times;</span>\n" + 
					"  </button>\n" + 
					"</div>";
			request.setAttribute("mensagem", mensagem);
			RequestDispatcher rq = request.getRequestDispatcher("login.jsp");
			rq.forward(request, response);
		}
	}

}
