package Ubenu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Ubenu.service.UserService;

@Controller
@RequestMapping(value="/users/register")
public class RegistrationController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("")
	public String index() {
		return "users/register";
	}
	
	@PostMapping("")
	public void register(@RequestParam String username,@RequestParam String password,@RequestParam String email,
			@RequestParam String firstName,@RequestParam String lastName,
			@RequestParam String dateOfBirth,@RequestParam String address,@RequestParam String phoneNumber, HttpServletResponse response) throws IOException {
		service.save(username, password, email, firstName, lastName, dateOfBirth, address, phoneNumber);
		response.sendRedirect("/Ubenu/users/login");
	}

}
