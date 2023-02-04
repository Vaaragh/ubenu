package Ubenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Ubenu.model.Drug;
import Ubenu.model.Wishlist;

@Repository
public class WishlistRepo {

	@Autowired
	private JdbcTemplate db;
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired 
	private DrugRepo drugRepo;
	
	private class RowMap implements RowMapper<Wishlist>{

		@Override
		public Wishlist mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			
			List<Drug> list = new ArrayList<Drug>();
			
			
			while (rs.next()) {
				list.add(drugRepo.findOne(rs.getString(1)));
			}
			
			Wishlist wishlist = new Wishlist(list);
			
			return wishlist;
		}
		
	}
	
	public Wishlist findOne(String userId) {
		String sql = "SELECT drug_id FROM Wishlist WHERE user_id=?";
		return db.queryForObject(sql, new RowMap(), userId);
	}
	
	
	
}
