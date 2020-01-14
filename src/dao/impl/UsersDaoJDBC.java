package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

			conn.setAutoCommit(false);	
			
			String sql = "INSERT INTO users (email, pass) VALUES (?, ?)";

			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, user.getEmail());
			st.setString(2, user.getPass());

			int row = st.executeUpdate();
			if (row > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getInt(1));
				}
				conn.commit();
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado");
			}

		} catch (Exception e) {
			try {
				conn.rollback();
				throw new DbException("Erro ao inserir registro! Voltado alterações.  " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro ao voltar alterações! "+ e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Users user) {
		PreparedStatement st = null;

		try {

			conn.setAutoCommit(false);
			
			String sql = "UPDATE users SET email = ?, pass = ? WHERE Id = ?";

			st = conn.prepareStatement(sql);
			st.setString(1, user.getEmail());
			st.setString(2, user.getPass());
			st.setInt(3, user.getId());

			int row = st.executeUpdate();

			if (row > 0) {
				conn.commit();
			}
			
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Erro ao alterar registro! Voltado alterações.  " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro ao voltar alterações! "+ e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(int id) {
		PreparedStatement st = null;

		try {
			
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM users WHERE Id = ?";

			st = conn.prepareStatement(sql);
			st.setInt(1, id);

			int row = st.executeUpdate();

			if (row == 0) {
				throw new DbException("Erro ao deletar usuário");
			} else {
				conn.commit();
			}
		
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Erro ao deletar registro! Voltado alterações.  " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro ao voltar alterações! "+ e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Users findById(int id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM users WHERE Id = ?";

			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {

				return inicializaUsers(rs);

			}

			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	private Users inicializaUsers(ResultSet rs) throws SQLException {

		Users users = new Users();
		users.setId(rs.getInt("Id"));
		users.setEmail(rs.getString("email"));
		users.setPass(rs.getString("pass"));

		return users;
	}

	@Override
	public List<Users> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM users ORDER BY email";

			st = conn.prepareStatement(sql);
			rs = st.executeQuery();

			List<Users> listUsers = new ArrayList<>();
			Map<Integer, Users> map = new HashMap<>();
			while (rs.next()) {

				Users user = map.get(rs.getInt("Id"));

				if (user == null) {
					user = inicializaUsers(rs);
					map.put(rs.getInt("Id"), user);
					listUsers.add(user);
				} else {
					user = inicializaUsers(rs);
					listUsers.add(user);
				}

			}
			return listUsers;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

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
	
	@Override
	public void saveUser(Users user) {
		
		if(user.getId() != null && user.getId() > 0 && findByEmail(user.getEmail())) {
			update(user);
		} else if (findByEmail(user.getEmail()) == false) {
			insert(user);
		}
		
	}

	@Override
	public boolean findByEmail(String email) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM users WHERE email = ?";

			st = conn.prepareStatement(sql);
			st.setString(1, email);
			rs = st.executeQuery();

			if (rs.next()) {

				inicializaUsers(rs);
				return true;
			}

			return false ;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
