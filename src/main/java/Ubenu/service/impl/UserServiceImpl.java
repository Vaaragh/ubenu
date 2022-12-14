package Ubenu.service.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import Ubenu.model.User;
import Ubenu.model.enums.ERole;
import Ubenu.model.utilities.IdGen;
import Ubenu.model.utilities.converters.UserConvert;
import Ubenu.service.UserService;



@Service
@Qualifier("userFiles")
public class UserServiceImpl implements UserService {
	
	@Value("${users.pathToFile}")
	private String pathToFile;
	
	private Map<String, User> readFromFile(){
		Map<String, User> users = new HashMap<>();
		
		try {
			
			Path path = Paths.get(pathToFile);
			
			List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
			
			for (String line: lines) {
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				String[] parts = line.split("\\|");
				
				User user = UserConvert.createObject(parts);
				users.put(user.getSysId(), user);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	private Map<String, User> saveToFile(Map<String, User> users){
		Map<String, User> ret = new HashMap<>();
		
		Path path = Paths.get(pathToFile);
		List<String> lines = new ArrayList<>();
		for (User user: users.values()) {
			lines.add(UserConvert.createLine(user));
			ret.put(user.getSysId(), user);
		}
		try {
			Files.write(path, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	
	
	
	

	@Override
	public User findOne(String sysId) {
		Map<String, User> users = readFromFile();
		return users.get(sysId);
	}

	@Override
	public List<User> findAll() {
		Map<String, User> users = readFromFile();
		return new ArrayList<>(users.values());
	}

	@Override
	public List<User> findAllActive() {
		List<User> actives = findAll();
		actives.removeIf(u -> u.isActive() == false);
		return actives;
	}

	@Override
	public User save(User user) {
		Map<String, User> users = readFromFile();
		
		if(user.getSysId() == null) {
			user.setSysId(IdGen.newID());
		}
		users.put(user.getSysId(), user);
		saveToFile(users);
		
		return user;
	}

	@Override
	public User update(String sysId, String username, String password, String email, String firstName, String lastName,
			String address, String phoneNumber) {
		Map<String, User> users = readFromFile();

		if (!users.containsKey(sysId)) {
			throw new IllegalArgumentException("User does not exist");
		}
		User user = users.get(sysId);
		if (user != null) {
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setAddress(address);
			user.setPhoneNumber(phoneNumber);
		}
		saveToFile(users);
		
		return user;
	}

	@Override
	public User delete(String sysId) {
		Map<String, User> users = readFromFile();

		if (!users.containsKey(sysId)) {
			throw new IllegalArgumentException("User does not exist");
		}
		User user = users.get(sysId);
		if (user != null) {
			user.setActive(false);
		}
		saveToFile(users);
		return null;
	}

	@Override
	public User create(String username, String password, String email, String firstName, String lastName,
			LocalDate dateOfBirth, String address, String phoneNumber) {
		
		User user = new User();
		user.setSysId(IdGen.newID());
		user.setActive(true);
		user.setRegistartionDateTime(LocalDateTime.now());
		user.setRole(ERole.CUSTOMER);
		user.setDateOfBirth(dateOfBirth);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAddress(address);
		user.setPhoneNumber(phoneNumber);
		
		save(user);

		return user;
	}

}
