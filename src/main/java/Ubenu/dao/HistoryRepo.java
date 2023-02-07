package Ubenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Ubenu.model.Drug;
import Ubenu.model.HistoryItem;

@Repository
public class HistoryRepo {

	@Autowired
	private JdbcTemplate  db;
	
	@Autowired
	private DrugRepo drugRepo;
	
	private class RowMap implements RowMapper<HistoryItem>{

		@Override
		public HistoryItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			String id = rs.getString(index++);
			int amount = rs.getInt(index++);
			float price = rs.getFloat(index++);
			
			Drug drug = drugRepo.findOne(id);
			
			HistoryItem item = new HistoryItem();
			item.setAmount(amount);
			item.setDrug(drug);
			item.setPrice(price);
			
			
			return item;
		}
	}
	public List<HistoryItem> findAll(){
		String sql = "Select d.id, SUM(si.amount) amount, d.price from customerOrder co\r\n"
				+ " JOIN customerorderitems coi on co.id=coi.order_id\r\n"
				+ " JOIN ShoppingItem si on coi.item_id=si.id\r\n"
				+ " JOIN Drug d on d.id=si.drug_id\r\n"
				+ " GROUP BY d.id;";
		return db.query(sql, new RowMap());
	}
	
	public List<HistoryItem> findBetween(String start, String end){
		String sql = "Select d.id, SUM(si.amount) amount, d.price from customerOrder co\r\n"
				+ " JOIN customerorderitems coi on co.id=coi.order_id\r\n"
				+ " JOIN ShoppingItem si on coi.item_id=si.id\r\n"
				+ " JOIN Drug d on d.id=si.drug_id\r\n"
				+ " WHERE co.date_of BETWEEN ? AND ? "
				+ " GROUP BY d.id;";
		return db.query(sql, new RowMap(),start, end);
	}
	

	
}
