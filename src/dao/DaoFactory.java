package dao;

import dao.impl.UsersDaoJDBC;
import db.DB;

public class DaoFactory {

	public static UsersDao createUsersDao() {
		return new UsersDaoJDBC(DB.getConnection());

	}

}
