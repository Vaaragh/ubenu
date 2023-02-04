package Ubenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Ubenu.model.DrugCategory;
import Ubenu.model.utilities.IdGen;

@Repository
public class DrugCategoryRepo {
	
	@Autowired
	private JdbcTemplate db;
	
	private class RowMap implements RowMapper<DrugCategory>{

		@Override
		public DrugCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			String id = rs.getString(index++);
			String name = rs.getString(index++);
			String use = rs.getString(index++);
			String descript = rs.getString(index++);
			DrugCategory drug = new DrugCategory();
			drug.setSysId(id);
			drug.setCategoryName(name);
			drug.setUseCase(use);
			drug.setDescription(descript);
			return drug;
		}	
	}
	public List<DrugCategory> findAll(){
		String sql = "SELECT id, title, useCase, descript FROM DrugCategory";
		return db.query(sql, new RowMap());
	}
	
	public void save(DrugCategory dc) {
		String sql = "insert into DrugCategory (id, title, useCase, descript) VALUES (?, ?, ?, ?);";
		db.update(sql, IdGen.newID(), dc.getCategoryName(), dc.getUseCase(), dc.getDescription());
	}
	
	public void update(DrugCategory drug) {
		String sql = "UPDATE DrugCategory SET title=?, useCase=?, descript=? WHERE id=?;";
		db.update(sql, drug.getCategoryName(), drug.getUseCase(), drug.getDescription(), drug.getSysId());
	}
	
	public void delete(String sysId) {
		String sql = "DELETE FROM DrugCategory WHERE id=?;";
		db.update(sql, sysId);
	}
	
	public DrugCategory findOne(String sysId) {
		String sql = "SELECT id, title, useCase, descript FROM DrugCategory WHERE id=?;";
		return db.queryForObject(sql, new RowMap(), sysId);
	}
	
	public DrugCategory findByName(String name) {
		String sql = "SELECT id, title,useCase,descript FROM DrugCategory WHERE title=?";
		return db.queryForObject(sql, new RowMap(), name);
	}
	
	
}
