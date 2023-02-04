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
	
	
	public void save(User user) {
		repo.save(user);
	}
	
	
	public void update(User user) {
		repo.update(user);
	}
	
	public User login(String username, String password) {
		List<User> users = repo.findAll();
		
		for (User user : users) {
			if (user.getUsername()==username && user.getPassword()==password && user.isActive()) {
				return user;
			}
		}
		return null;
	}
	
	
}
