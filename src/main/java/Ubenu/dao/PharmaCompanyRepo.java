package Ubenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Ubenu.model.PharmaCompany;
import Ubenu.model.utilities.IdGen;

@Repository
public class PharmaCompanyRepo {
	
	@Autowired
	private JdbcTemplate db;
	
	private class RowMap implements RowMapper<PharmaCompany>{

		@Override
		public PharmaCompany mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			String id = rs.getString(index++);
			String name = rs.getString(index++);
			String country = rs.getString(index++);
			PharmaCompany company = new PharmaCompany();
			company.setSysId(id);
			company.setCompanyName(name);
			company.setCountry(country);
			return company;
		}		
	}
	
	public List<PharmaCompany> findAll(){
		String sql = "SELECT id, title, country FROM PharmaCompany";
		return db.query(sql, new RowMap());
	}
	
	public void save(PharmaCompany pc) {
		String sql = "INSERT INTO PharmaCompany (id, title, country) values (?, ?, ?)";
		db.update(sql,IdGen.newID() ,pc.getCompanyName(), pc.getCountry());
	}
	
	public void update(PharmaCompany pharmaCompany) {
		String sql = "UPDATE PharmaCompany SET title = ?, country = ? WHERE id = ?";
		db.update(sql, pharmaCompany.getCompanyName(), pharmaCompany.getCountry(), pharmaCompany.getSysId());
	}
	
	public void delete(String sysId) {
		String sql = "DELETE FROM PharmaCompany WHERE id = ?";
		db.update(sql, sysId);
	}
	
	public PharmaCompany findOne(String sysId) {
		String sql = "SELECT id, title, country FROM PharmaCompany WHERE id = ?";
		return db.queryForObject(sql, new RowMap(), sysId);

	}
	
}
