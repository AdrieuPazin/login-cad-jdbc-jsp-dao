package dao;

import java.util.List;

import entities.Department;
import entities.Seller;
import entities.Users;

public interface SellerDao {

	void insert(Seller seller);
	void update(Seller seller);
	void deleteById(int id);
	Seller findById(int id);
	List<Seller> findAll();
	List<Seller> findByDepartment(Department dep);
	void saveSeller(Seller seller);
	
	
}
