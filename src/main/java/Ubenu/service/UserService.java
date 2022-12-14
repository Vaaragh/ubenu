package Ubenu.service;

import java.time.LocalDate;
import java.util.List;

import Ubenu.model.User;


public interface UserService {
	
	User findOne(String sysId);
	List<User> findAll();
	List<User> findAllActive();
	
	User save(User user);
	User update(String sysId, String username, String password, String email, String firstName, String lastName,
			String address, String phoneNumber);
	User delete(String sysId);
	User create(String username, String password, String email, String firstName, String lastName,
			LocalDate dateOfBirth, String address, String phoneNumber);

}
