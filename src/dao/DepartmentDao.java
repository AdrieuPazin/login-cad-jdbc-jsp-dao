package dao;

import java.util.List;

import entities.Department;

public interface DepartmentDao {

	void insert(Department dep);
	void update(Department dep);
	void deleteById(int id);
	Department findById(int id);
	List<Department> findAll();
}
