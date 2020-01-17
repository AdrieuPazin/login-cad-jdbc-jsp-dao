package dao;

import java.util.List;

import entities.Users;

public interface UsersDao {
	
	void insert(Users user);
	void update(Users user);
	void deleteById(int id);
	Users findById(int id);
	boolean findByEmail(String email);
	List<Users> findAll();
	List<Users> findAllLimite(int inicioDaBusca, int qtdeRegistrosResult);
	int countSeller();
	Users findByLogin(String email, String pass);
	void saveUser(Users user);
	

}
