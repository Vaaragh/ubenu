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
		String sql = "SELECT sysId, username, password, email, firstName, lastName, dateOfBirth, address, phoneNumber, registrationDateTime, role, active \r\n"
				+ "FROM UserTable;";
		return db.query(sql, new RowMap());
	}
	
	public User findOne(String sysId) {
		String sql = "SELECT sysId, username, password, email, firstName, lastName,"
				+ " dateOfBirth, address, phoneNumber, registrationDateTime, role, active"
				+ "FROM UserTable WHERE sysId=?";
		return db.queryForObject(sql, new RowMap(), sysId);
	}
	
	public void save(String username, String password, String email, String firstName, String lastName,
			LocalDate dateOfBirth, String address, String phoneNumber, LocalDateTime registartionDateTime, String role,
			boolean active) {
		String sql = "INSERT INTO UserTable"
				+ " (sysId, username, password, email, firstName, lastName, dateOfBirth,"
				+ "address, phoneNumber,registrationDateTime, role, active) values "
				+ "(?,?,?,?,?,?,?,?,?,?,?,?)";
		db.update(sql, IdGen.newID(), username, password, email, firstName, lastName, dateOfBirth, address, phoneNumber, registartionDateTime, role, active);
	}
	
	public void update(String sysId, String username, String password, String email, String firstName, String lastName,
			LocalDate dateOfBirth, String address, String phoneNumber, LocalDateTime registartionDateTime, ERole role) {
		String sql = "UPDATE UserTable SET"
				+ " sysId=?, username=?, password=?, email=?, firstName=?, lastName=?, dateOfBirth=?,"
				+ "address=?, phoneNumber=? ,registrationDateTime=?, role=? WHERE sysId=? ";
				db.update(sql, username, password, email, firstName, lastName, dateOfBirth, address, phoneNumber, registartionDateTime, role, sysId);
	}
	
	public void delete(String sysId) {
		 String sql = "DELETE FROM UserTable WHERE sysId=?";
		 db.update(sql,sysId);
	}
	
	public User login(String username, String password) {
		String sql = "SELECT sysId, username, password, email, firstName, lastName,"
				+ " dateOfBirth, address, phoneNumber, registrationDateTime, role, active "
				+ "FROM UserTable WHERE username=? and password=?";
		User user =  db.queryForObject(sql, new RowMap(), username, password);
		return user;

	}
	

}
