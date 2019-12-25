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
		PreparedStatement st = null;

		try {

			String sql = "UPDATE users SET email = ?, pass = ? WHERE Id = ?";

			st = conn.prepareStatement(sql);
			st.setString(1, user.getEmail());
			st.setString(2, user.getPass());
			st.setInt(3, user.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(int id) {
		PreparedStatement st = null;

		try {

			String sql = "DELETE FROM users WHERE Id = ?";

			st = conn.prepareStatement(sql);
			st.setInt(1, id);

			int row = st.executeUpdate();

			if (row == 0) {
				throw new DbException("Erro ao deletar usu�rio");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
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

			String sql = "SELECT * FROM users";

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

}