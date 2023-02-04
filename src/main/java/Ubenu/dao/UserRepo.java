package Ubenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Ubenu.model.User;
import Ubenu.model.enums.ERole;
import Ubenu.model.utilities.IdGen;

@Repository
public class UserRepo {
	
	@Autowired
	private JdbcTemplate db;
	
	private class RowMap implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {

			int index = 1;
			String sysId = rs.getString(index++);
			String username = rs.getString(index++);
			String password = rs.getString(index++);
			String email = rs.getString(index++);
			String firstName = rs.getString(index++);
			String lastName = rs.getString(index++);
			LocalDate dateOfBirth = rs.getDate(index++).toLocalDate();
			String address = rs.getString(index++);
			String phoneNumber = rs.getString(index++);
			LocalDateTime registartionDateTime = rs.getTimestamp(index++).toLocalDateTime();
			ERole role = ERole.valueOf(rs.getString(index++));
			boolean active = rs.getBoolean(index++);
			
			User user = new User();
			user.setSysId(sysId);
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setDateOfBirth(dateOfBirth);
			user.setAddress(address);
			user.setPhoneNumber(phoneNumber);
			user.setRegistartionDateTime(registartionDateTime);
			user.setRole(role);
			user.setActive(active);
			
			
			return user;
		}
		
	}
	
	public List<User> findAll() {
		String sql = "SELECT id, username, password, email, firstName, lastName, dateOfBirth, address, phoneNumber, registrationDateTime, role, active \r\n"
				+ "FROM UserTable;";
		return db.query(sql, new RowMap());
	}
	
	public User findOne(String sysId) {
		String sql = "SELECT id, username, password, email, firstName, lastName, dateOfBirth, address, phoneNumber, registrationDateTime, role, active FROM UserTable WHERE id=?";
		return db.queryForObject(sql, new RowMap(), sysId);
	}
	
	public void save(User u) {
		String sql = "INSERT INTO UserTable"
				+ " (id, username, password, email, firstName, lastName, dateOfBirth,"
				+ "address, phoneNumber,registrationDateTime, role, active) values "
				+ "(?,?,?,?,?,?,?,?,?,?,?,?)";
		db.update(sql, IdGen.newID(), u.getUsername(), u.getPassword(), u.getEmail(), u.getFirstName(), u.getLastName(), u.getDateOfBirth(), u.getAddress(), u.getPhoneNumber(), u.getRegistartionDateTime(), u.getRole().toString(), u.isActive());
	}
	
	public void update(User u) {
		String sql = "UPDATE UserTable SET"
				+ " id=?, username=?, password=?, email=?, firstName=?, lastName=?, dateOfBirth=?,"
				+ "address=?, phoneNumber=? ,registrationDateTime=?, role=?, active=? WHERE id=? ";
				db.update(sql, u.getUsername(), u.getPassword(), u.getEmail(), u.getFirstName(), u.getLastName(), u.getDateOfBirth(), u.getAddress(), u.getPhoneNumber(), u.getRegistartionDateTime(), u.getRole().toString(), u.isActive(), u.getSysId());
	}
	
	public void delete(String sysId) {
		 String sql = "DELETE FROM UserTable WHERE id=?";
		 db.update(sql,sysId);
	}
	


}
