package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.UsersDao;
import entities.Users;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

				
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		doGet(request, response);
		
		String email = request.getParameter("frmLoginEmail");
		String pass = request.getParameter("frmLoginPass");
		
		
		UsersDao userDao = DaoFactory.createUsersDao();
		Users user = userDao.findByLogin(email, pass);
		if (user != null && user.getEmail().equals(email) && user.getPass().equals(pass)) {
		
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			
			response.sendRedirect("home.jsp");
		} else {
			out.println("Login inválido!");
		}
	}

}
