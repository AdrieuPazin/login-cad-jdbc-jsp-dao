package dao;

import dao.impl.DepartmentDaoJDBC;
import dao.impl.SellerDaoJDBC;
import dao.impl.UsersDaoJDBC;
import db.DB;

public class DaoFactory {

	public static UsersDao createUsersDao() {
		return new UsersDaoJDBC(DB.getConnection());

	}

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
}
