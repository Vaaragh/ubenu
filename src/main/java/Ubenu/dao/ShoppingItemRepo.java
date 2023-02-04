package Ubenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Ubenu.model.Drug;
import Ubenu.model.ShoppingItem;
import Ubenu.model.utilities.IdGen;
import Ubenu.service.DrugService;

@Repository
public class ShoppingItemRepo {

	@Autowired
	private JdbcTemplate db;
	
	@Autowired
	private DrugService drugServ;
	
	private class RowMap implements RowMapper<ShoppingItem>{

		@Override
		public ShoppingItem mapRow(ResultSet rs, int rowNum) throws SQLException {

			int index = 1;
			String id = rs.getString(index++);
			String drugId = rs.getString(index++);
			int amount = rs.getInt(index++);
			
			Drug drug = drugServ.findOne(drugId);
			
			ShoppingItem spItem = new ShoppingItem();
			spItem.setSysId(id);
			spItem.setDrug(drug);
			spItem.setAmount(amount);
			

			return spItem;
		}		
	}
	
	public List<ShoppingItem> findAll(){
		String sql = "SELECT id, drug_id, amount FROM ShoppingItem;";
		return db.query(sql,new RowMap());
	}
	
	public ShoppingItem findOne(String sysId) {
		String sql = "SELECT id, drug_id, amount FROM ShoppingItem WHERE id=?;";
		return db.queryForObject(sql,new RowMap(), sysId);
	}
	
	public List<ShoppingItem> findForOrder(String sysId){
		String sql = "SELECT s.id, s.drug_id, s.amount FROM ShoppingItem s WHERE s.id IN (SELECT c.item_id FROM CustomerOrderItems c WHERE c.order_id=?);";
		return db.query(sql, new RowMap(), sysId);
		
	}
	
	public void delete(String sysId) {
		String sql = "DELETE FROM ShoppingItem WHERE id=?";
		db.update(sql, sysId);
	}
	
	public void save(ShoppingItem s) {
		String sql = "INSERT INTO ShoppingItem (id, drug_id, amount) VALUES (?,?,?);";
		db.update(sql,IdGen.newID(), s.getDrug().getSysId(), s.getAmount());
	}
	
	public void update(ShoppingItem s) {
		String sql = " UPDATE ShoppingItem SET amount=? WHERE id=?;";
		db.update(sql,s.getAmount(), s.getSysId());
	}
	
	
	
	
	
}
