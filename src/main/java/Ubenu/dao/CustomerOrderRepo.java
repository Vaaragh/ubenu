package Ubenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Ubenu.model.CustomerOrder;
import Ubenu.model.ShoppingItem;
import Ubenu.model.utilities.IdGen;
import Ubenu.service.ShoppingItemService;

@Repository
public class CustomerOrderRepo {

	@Autowired
	private JdbcTemplate db;
	
	@Autowired
	private ShoppingItemService siServ;
	
	private class RowMap implements RowMapper<CustomerOrder>{

		@Override
		public CustomerOrder mapRow(ResultSet rs, int rowNum) throws SQLException {

			int i = 1;
			String id = rs.getString(i++);
			LocalDate date = rs.getDate(i++).toLocalDate();
			
			List<ShoppingItem> items = siServ.findForOrder(id);
			float total = 0;
			for (int j=0;j<items.size();j++) {
				total += items.get(j).getAmount()*items.get(j).getDrug().getPrice();
				
			}
			
			
			
			CustomerOrder order = new CustomerOrder();
			order.setTotal(total);
			order.setSysId(id);
			order.setDate(date);
			order.setItems(items);
			return order;
		}
		
	}
	
	public List<CustomerOrder> findAll(){
		String sql = "SELECT id, date_of FROM CustomerOrder;";
		return db.query(sql, new RowMap());
	}
	
	public List<CustomerOrder> findForCustomer(String userId){
		String sql = "SELECT id, date_of FROM CustomerOrder WHERE user_id=? ORDER BY date_of desc;";
		return db.query(sql, new RowMap(), userId);
	}
	
	public CustomerOrder findOne(String sysId) {
		String sql = "SELECT id, date_of FROM CustomerOrder WHERE id=?;";
		return db.queryForObject(sql, new RowMap(), sysId);
	}
	
	public void save(CustomerOrder o, String userId, String orderId) {
		String sql2 = "INSERT INTO CustomerOrder (id, date_of, user_id) VALUES (?,?,?); ";
		db.update(sql2, orderId, LocalDate.now(), userId);
		
		for (ShoppingItem item : o.getItems()) {
			String itemId = IdGen.newID();
			siServ.save(item, item.getDrug().getSysId(), itemId);
			String sql = "INSERT INTO CustomerOrderItems (order_id, item_id) VALUES (?,?);";
			db.update(sql, orderId, itemId);
		}
	}
	
	
	
}
