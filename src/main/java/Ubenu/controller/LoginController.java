package Ubenu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Ubenu.model.User;
import Ubenu.service.UserService;

@Controller
@RequestMapping(value="/users/login")
public class LoginController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("")
	public String index() {
		return "users/login";
	}
	
	@PostMapping("")
	public void login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) throws IOException {
		
		boolean found = false;
		List<User> users = service.findAll();
		for (User user : users) {
			System.out.println(user.getUsername() + "---"+ user.getPassword() );
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				found = true;
				break;
			}
		}
		if (found) {
			response.sendRedirect("/Ubenu/companies");
		}else {
		response.sendRedirect("/Ubenu/users/login");
		}
		
	}

}
