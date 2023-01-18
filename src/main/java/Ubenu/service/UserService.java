package Ubenu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ubenu.dao.UserRepo;
import Ubenu.model.User;
import Ubenu.model.enums.ERole;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findOne(String sysId) {
		return repo.findOne(sysId);
	}
	
	public void delete(String sysId) {
		repo.delete(sysId);
	}
	
	public void save(String username, String password, String email, String firstName, String lastName,
			String dateOfBirth, String address, String phoneNumber)
	{
		
		repo.save(username, password, email, firstName, lastName, LocalDate.parse(dateOfBirth), address, phoneNumber, LocalDateTime.now(), "CUSTOMER", true);
	}
	
	public void update(String sysId, String username, String password, String email, String firstName, String lastName,
			String dateOfBirth, String address, String phoneNumber, String registartionDateTime, String role) {
		repo.update(sysId, username, password, email, firstName, lastName, LocalDate.parse(dateOfBirth), address, phoneNumber, LocalDateTime.parse(registartionDateTime),ERole.valueOf(role));
	}
	
	public User login(String username, String password) {
		return repo.login(username, password);
	}
	
}
