package application;

import java.util.List;

import dao.DaoFactory;
import dao.UsersDao;
import entities.Users;

public class MainTest {

	public static void main(String[] args) {

		
		UsersDao userDao = DaoFactory.createUsersDao();
		
		Users user = userDao.findByLogin("adrieu@adrieu.com", "123");
		System.out.println("FIND BY LOGIN\n");
		System.out.println(user + "\n----------------------------------------\n");
		
		System.out.println("FIND BY ID\n");
		user = userDao.findById(1);
		System.out.println(user + "\n------------------------------------\n");
		
		Users user2 = new Users();
		user2 = userDao.findById(3);
		user2.setPass("321");
//		userDao.update(user2);
//		userDao.insert(user2);
//		System.out.println("INSERT \n");
//		System.out.println(user2 + "\n");
		
		System.out.println("FIND ALL\n");
		
		List<Users> listUsers = userDao.findAll();
		for (Users users : listUsers) {
			System.out.println(users + " \n");
		}
		
		System.out.println("DELETE BY ID\n");
//		userDao.deleteById(2);

	}

}
