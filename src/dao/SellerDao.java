package dao;

import java.util.List;

import entities.Department;
import entities.Seller;

public interface SellerDao {

	void insert(Seller seller);
	void update(Seller seller);
	void deleteById(int id);
	Seller findById(int id);
	boolean findByEmail(String email);
	List<Seller> findAll();
	List<Seller> findByDepartment(Department dep);
	void saveSeller(Seller seller);
	
	
}
