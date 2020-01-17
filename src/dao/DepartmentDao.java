package dao;

import java.util.List;

import entities.Department;
import entities.Users;

public interface DepartmentDao {

	void insert(Department dep);
	void update(Department dep);
	void deleteById(int id);
	Department findById(int id);
	List<Department> findAll();
	List<Department> findAllLimite(int inicioDaBusca, int qtdeRegistrosResult);
	int countSeller();
	void salvarDepartment(Department dep);
}
