package dao;

import java.util.List;

import entities.Users;

public interface UsersDao {
	
	void insert(Users user);
	void update(Users user);
	void deleteById(int id);
	Users findById(int id);
	List<Users> findAll();
	Users findByLogin(String email, String pass);
	void saveUser(Users user);
	

}
