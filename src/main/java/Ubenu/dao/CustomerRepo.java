package Ubenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Ubenu.model.Customer;
import Ubenu.model.User;

@Repository
public class CustomerRepo {

	@Autowired
	private JdbcTemplate db;
	
	@Autowired
	private UserRepo userRepo;
	
	private class RowMap implements RowMapper<Customer>{

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int index = 1;
			User user = userRepo.findOne(rs.getString(index++));
			
			Customer customer = new Customer(user);
			
			return customer;
		}	
	}
	
	public List<Customer> findAll(){
		String sql = "SELECT userId, wishlistId, shoppingId, loyaltyId FROM Customers";
		return db.query(sql, new RowMap());
	}
	
	public Customer findOne(String sysId) {
		String sql = "SELECT userId, wishlistId, shoppingId, loyaltyId FROM Customers WHERE userId=?";
		return db.queryForObject(sql, new RowMap(), sysId);
	}
	
	public void delete(String sysId) {
		String sql = "DELETE FROM Customers WHERE userId=?";
		db.update(sql, sysId);
	}
	
	public void save(String userId) {
		String sql = "INSERT INTO Customers";
	}
	
	
}
