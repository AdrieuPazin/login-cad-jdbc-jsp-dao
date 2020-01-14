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

import dao.SellerDao;
import db.DB;
import db.DbException;
import entities.Department;
import entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {

		PreparedStatement st = null;

		try {
			
			conn.setAutoCommit(false);

			String sql = "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)";
			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, seller.getName());
			st.setString(2, seller.getEmail());
			st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			st.setDouble(4, seller.getBaseSalary());
			st.setInt(5, seller.getDepartment().getId());

			int row = st.executeUpdate();

			if (row > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {

					seller.setId(rs.getInt(1));

				}
				conn.commit();
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado. Nehuma linha alterada");
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
	public void update(Seller seller) {
		PreparedStatement st = null;

		try {
			
			conn.setAutoCommit(false);

			String sql = "UPDATE seller " + 
					"SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " + 
					"WHERE Id = ?";
			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, seller.getName());
			st.setString(2, seller.getEmail());
			st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			st.setDouble(4, seller.getBaseSalary());
			st.setInt(5, seller.getDepartment().getId());
			st.setInt(6, seller.getId());

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
			
			String sql = "DELETE FROM seller " + 
					"WHERE Id = ?";
			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id);
			
			int row = st.executeUpdate();
			
			if (row == 0 ) {
				throw new DbException("Registro inexistente");
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
	public Seller findById(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"WHERE seller.Id = ?";
			
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			//testando se veio algum resultado, se true pega os dados do result e instancia os objetos
			if (rs.next()) {
				Department dep = inicializacaoDepartment(rs);
				
				Seller obj = inicializacaoSeller(rs, dep);
				return obj;
				
			}
			
			return null;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	private Seller inicializacaoSeller(ResultSet rs, Department dep) throws SQLException {

		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		
		return obj;
		
	}

	private Department inicializacaoDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"ORDER BY Name";
			
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			
			//testando se veio algum resultado, se true pega os dados do result e instancia os objetos
			List<Seller> list  = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			while (rs.next()) {
			    //verifico se já existe algum departamento com o ID, se for nullo aí sim instancio um novo departamento
				//Se já existir um departamento, não é instanciado um novo
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if (dep == null) {
					dep = inicializacaoDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = inicializacaoSeller(rs, dep);
				list.add(obj);
				
				
			}
			return list;
			

			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Seller> findByDepartment(Department dep) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"WHERE DepartmentId = ? " + 
					"ORDER BY Name";
			
			st = conn.prepareStatement(sql);
			st.setInt(1, dep.getId());
			rs = st.executeQuery();
			
			//testando se veio algum resultado, se true pega os dados do result e instancia os objetos
			List<Seller> list  = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			while (rs.next()) {
			    //verifico se já existe algum departamento com o ID, se for nullo aí sim instancio um novo departamento
				//Se já existir um departamento, não é instanciado um novo
				Department department = map.get(rs.getInt("DepartmentId"));
				
				if (department == null) {
					department = inicializacaoDepartment(rs);
					map.put(rs.getInt("DepartmentId"), department);
				}
				
				Seller obj = inicializacaoSeller(rs, department);
				list.add(obj);
				
				
			}
			return list;
			

			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void saveSeller(Seller seller) {
		if(seller.getId() != null && seller.getId() > 0 && findByEmail(seller.getEmail())) {
			update(seller);
		} else if (findByEmail(seller.getEmail()) == false) {
			insert(seller);
		}
		
	}

	@Override
	public boolean findByEmail(String email) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT * FROM seller " + 
					"WHERE seller.Email = ?";
			
			st = conn.prepareStatement(sql);
			st.setString(1, email);
			rs = st.executeQuery();
			
			//testando se veio algum resultado com o mesmo e-mail a ser inserido e/ou atualizado
			if (rs.next()) {
				return true;	
			}
			
			return false;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
