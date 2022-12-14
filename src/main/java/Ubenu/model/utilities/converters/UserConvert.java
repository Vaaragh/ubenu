package Ubenu.model.utilities.converters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.StringJoiner;

import Ubenu.model.User;
import Ubenu.model.enums.ERole;



public class UserConvert {
	
	public static User createObject(String[] parts) {
		
		User user = new User();
		
		user.setSysId(parts[0]);
		user.setUsername(parts[1]);
		user.setPassword(parts[2]);
		user.setEmail(parts[3]);
		user.setFirstName(parts[4]);
		user.setLastName(parts[5]);
		user.setDateOfBirth(LocalDate.parse(parts[6]));
		user.setAddress(parts[7]);
		user.setPhoneNumber(parts[8]);
		user.setRegistartionDateTime(LocalDateTime.parse(parts[9]));
		user.setRole(ERole.valueOf(parts[10]));
		user.setActive(Boolean.parseBoolean(parts[11]));
		
		return user;		
	}
	
	public static String createLine(User user) {
		
		StringJoiner sj = new StringJoiner("|");
		
		sj.add(user.getSysId());
		sj.add(user.getUsername());
		sj.add(user.getPassword());
		sj.add(user.getEmail());
		sj.add(user.getFirstName());
		sj.add(user.getLastName());
		sj.add(user.getDateOfBirth().toString());
		sj.add(user.getAddress());
		sj.add(user.getPhoneNumber());
		sj.add(user.getRegistartionDateTime().toString());
		sj.add(user.getRole().toString());
		sj.add(String.valueOf(user.isActive()));
		
		
		
		return sj.toString();
	}

}
