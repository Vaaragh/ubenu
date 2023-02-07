package Ubenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Ubenu.model.Comment;
import Ubenu.model.Drug;
import Ubenu.model.User;
import Ubenu.model.utilities.IdGen;
import Ubenu.service.DrugService;
import Ubenu.service.UserService;

@Repository
public class CommentRepo {

	@Autowired
	private JdbcTemplate db;
	
	@Autowired
	private UserService userServ;
	
	
	private class RowMap implements RowMapper<Comment>{

		@Override
		public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			String id = rs.getString(index++);
			String txt = rs.getString(index++);
			int rating = rs.getInt(index++);
			LocalDate dateOf = rs.getDate(index++).toLocalDate();
			String userId = rs.getString(index++);
			boolean anon = rs.getBoolean(index++);
			
			User user = userServ.findOne(userId);
			
			Comment comment = new Comment();
			comment.setSysId(id);
			comment.setTextContent(txt);
			comment.setRating(rating);
			comment.setDateOf(dateOf);
			comment.setUser(user);
			comment.setAnon(anon);

			return comment;
		}
	}
	
	public List<Comment> findAll(){
		String sql = "SELECT id, txt, rating, date_of, user_id, anon FROM Comment;";
		return db.query(sql, new RowMap());
	}
	
	public List<Comment> findForDrug(String drugId) {
		String sql = "SELECT id, txt, rating, date_of, user_id, anon FROM Comment WHERE id in (SELECT comm_id FROM RatingTable WHERE drug_id=?) ;";
		return db.query(sql, new RowMap(), drugId);
	}
	
	public void save(Comment c, String drugId) {
		String id = IdGen.newID();
		String sql = "INSERT INTO Comment (id, txt, rating, date_of, user_id, anon) VALUES (?,?,?,?,?,?);";
		db.update(sql, id, c.getTextContent(), c.getRating(), c.getDateOf(), c.getUser().getSysId(), c.isAnon());
		String sql2 = " INSERT INTO RatingTable (comm_id, drug_id) VALUES (?,?);";
		db.update(sql2, id, drugId);
	}
	
	
	
}
