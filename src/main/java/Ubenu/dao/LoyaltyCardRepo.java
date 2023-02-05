package Ubenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Ubenu.model.LoyaltyCard;
import Ubenu.model.User;
import Ubenu.service.UserService;

@Repository
public class LoyaltyCardRepo {

	@Autowired
	private JdbcTemplate db;
	
	@Autowired
	private UserService userServ;
	
	private class RowMap implements RowMapper<LoyaltyCard>{

		@Override
		public LoyaltyCard mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int index = 1;
			String userId = rs.getString(index++);
			int points = rs.getInt(index++);
			float discount = rs.getFloat(index++);
			
			User user = userServ.findOne(userId);
			
			LoyaltyCard lc = new LoyaltyCard();
			lc.setUser(user);
			lc.setNumberOfPoints(points);
			lc.setDiscountPerPoint(discount);
			
			return lc;
		}
		
	}
	
	public List<LoyaltyCard> findAllPending(){
		String sql = "SELECT user_id, points, discount FROM LoyaltyCard WHERE approved=0;";
		return db.query(sql, new RowMap());
	}
	
	public List<LoyaltyCard> findAllApproved(){
		String sql = "SELECT user_id, points, discount FROM LoyaltyCard WHERE approved=1;";
		return db.query(sql, new RowMap());
	}
	
	
	public void save(String userId) {
		String sql = "INSERT INTO LoyaltyCard (user_id, points, discount, approved) VALUES (?,?,?,?);";
		db.update(sql, userId, 10, 0.05, 0);
	}
	
	public void update(LoyaltyCard card) {
		String sql = "UPDATE LoyaltyCard SET points=?, discount=? WHERE user_id=?;";
		db.update(sql, card.getNumberOfPoints(), card.getDiscountPerPoint(), card.getUser().getSysId());		
	}
	
	public void approve(String userId) {
		String sql = "UPDATE LoyaltyCard SET approved=1 WHERE user_id=?;";
		db.update(sql, userId);
	}
	
	public void delete(String userId) {
		String sql = "DELETE FROM LoyaltyCard WHERE user_id=?;";
		db.update(sql, userId);
	}
	
	
	
}
