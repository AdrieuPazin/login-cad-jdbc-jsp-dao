package application;

import dao.impl.UsersDaoJDBC;
import db.DB;
import entities.Users;

public class MainTest {

	public static void main(String[] args) {

		
		UsersDaoJDBC userJDBC = new UsersDaoJDBC(DB.getConnection());
		
		Users user = userJDBC.findByLogin("adrieu@adrieu.com", "321");
		
		System.out.println(user);
		
	}

}
