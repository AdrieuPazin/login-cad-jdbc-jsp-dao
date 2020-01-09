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

import dao.DepartmentDao;
import db.DB;
import db.DbException;
import entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn = null;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department dep) {
		
		PreparedStatement st = null;
		
		try {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO department (Name) VALUES (?)";
			
			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, dep.getName());
			
			int row = st.executeUpdate();
			
			if (row > 0 ) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					dep.setId(rs.getInt(1));
				}
				conn.commit();
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inexperado!");
			}
			
		} catch (SQLException e) {
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
	public void update(Department dep) {

		
		PreparedStatement st = null;
		
		try {
			
			conn.setAutoCommit(false);
			
			String sql = "UPDATE department SET Name = ? WHERE Id = ?";
			
			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, dep.getName());
			st.setInt(2, dep.getId());
			
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
			
			String sql = "DELETE FROM department WHERE Id = ?";
			
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			
			int row = st.executeUpdate();
			
			if (row == 0) {
				throw new DbException("Erro ao excluir registro!");
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
		} DB.closeStatement(st);
		
		
	}

	@Override
	public Department findById(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM Department WHERE Id=?";

			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {

				Department dep = inicializarDepartment(rs);
				
				return dep;
				
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT * FROM Department ORDER BY Name";

			st = conn.prepareStatement(sql);
			rs = st.executeQuery();

			List<Department> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

				Department dep = map.get(rs.getInt("Id"));
				if (dep == null) {
					dep = inicializarDepartment(rs);
					map.put(rs.getInt("Id"), dep);
				}

				dep = inicializarDepartment(rs);
				list.add(dep);

			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	private Department inicializarDepartment(ResultSet rs) throws SQLException {

		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

	@Override
	public void salvarDepartment(Department dep) {

		if (dep.getId() != null) {
			update(dep);
		} else {
			insert(dep);
		}
		
	}

}
