package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.UsersDao;
import db.DB;
import db.DbException;
import entities.Users;

public class UsersDaoJDBC implements UsersDao {

	private Connection conn;

	public UsersDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Users user) {

		PreparedStatement st = null;

		try {

			String sql = "INSERT INTO users (email, pass) VALUES (?, ?)";

			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, user.getEmail());
			st.setString(2, user.getPass());

			int row = st.executeUpdate();
			if (row > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getInt("Id"));
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado");
			}

		} catch (Exception e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Users user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Users findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users findByLogin(String email, String pass) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT * FROM users WHERE users.email = ? AND users.pass = ?";
			
			st = conn.prepareStatement(sql);
			st.setString(1, email);
			st.setString(2, pass);
			
			rs = st.executeQuery();
			
			if (rs.next()) {
				Users user = new Users();
				user.setId(rs.getInt("Id"));
				user.setEmail(rs.getString("email"));
				user.setPass(rs.getString("pass"));
				return user;
			} else {
				return null;
			}
			
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

}
