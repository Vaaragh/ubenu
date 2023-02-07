package Ubenu.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Ubenu.model.LoyaltyCard;
import Ubenu.model.User;
import Ubenu.model.enums.ERole;
import Ubenu.service.LoyaltyCardService;
import Ubenu.service.UserService;
import Ubenu.service.WishlistService;

@Controller
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserService userServ;
	@Autowired
	private WishlistService wishServ;
	@Autowired
	private LoyaltyCardService cardServ;
	
	
	@GetMapping("")
	public String index(Model model, HttpSession session) {
		String role = (String) session.getAttribute("role");

		model.addAttribute("users", userServ.findAll());
		model.addAttribute("pendingCards", cardServ.findPending());

		return "users/index";
	}
	
	@GetMapping("/details")
	public String details(@RequestParam String sysId, Model model) {
		
		
		model.addAttribute("pendingCard", cardServ.checkForPending(sysId));
		model.addAttribute("user", userServ.findOne(sysId));
		model.addAttribute("ERoles", ERole.values());
		return "users/details";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		return "users/register";
	}
	
	@PostMapping("/update")
	public void details(@ModelAttribute User user, @RequestParam String sysId, @RequestParam String dateOfBirthString, HttpSession session, HttpServletResponse response) throws IOException {
		User regUser = (User) session.getAttribute("user");
		if (regUser.getSysId().equals(sysId)) {
			
			user.setDateOfBirth(LocalDate.parse(dateOfBirthString));
			user.setRegistartionDateTime(regUser.getRegistartionDateTime());
			user.setRole(regUser.getRole());
			user.setActive(true);
			
			userServ.update(user);
			
			response.sendRedirect("/Ubenu/users/details?sysId=" + regUser.getSysId());
		
		} else {
			response.sendRedirect("/Ubenu/users/details?sysId=" + regUser.getSysId()) ;
		}
		
	}
	
	@PostMapping("/register")
	public void add(@ModelAttribute User user, @RequestParam String dateOfBirthString, HttpServletResponse response) throws IOException {
		
		user.setDateOfBirth(LocalDate.parse(dateOfBirthString));
		user.setRegistartionDateTime(LocalDateTime.now());
		user.setRole(ERole.CUSTOMER);
		user.setActive(true);
		
		userServ.save(user);
		
		response.sendRedirect("/Ubenu/users/login");
		
	}
	
	@PostMapping("/block")
	public void block(@RequestParam String sysId, HttpServletResponse response) throws IOException {
		boolean old = userServ.findOne(sysId).isActive();
		userServ.block(sysId, old);
		response.sendRedirect("/Ubenu/users");
		
	}
	
	@PostMapping("/removeWish")
	public void removeWish(@RequestParam String sysId, @RequestParam String drugSysId, HttpServletResponse response, HttpSession session) throws IOException {
		wishServ.remove(sysId, drugSysId);
		session.setAttribute("wishlist", wishServ.findOne(sysId));
		response.sendRedirect("/Ubenu/users/details?sysId=" + sysId);
	}
	
	@PostMapping("/requestCard")
	public void requestCard(@RequestParam String userCardId, HttpServletResponse response, HttpSession session) throws IOException {
		cardServ.save(userCardId);
		session.setAttribute("cardRequest", true);
		response.sendRedirect("/Ubenu/users/details?sysId=" + userCardId);

		
	}
	
	@PostMapping("/approveCard")
	public void approveCard(@RequestParam String userApproveId, HttpServletResponse response, HttpSession session) throws IOException {
		cardServ.approve(userApproveId);
		response.sendRedirect("/Ubenu/users");
		
	}
	
	@PostMapping("/deleteCard")
	public void deleteCard(@RequestParam String userApproveId, HttpServletResponse response, HttpSession session) throws IOException {
		cardServ.decline(userApproveId);
		response.sendRedirect("/Ubenu/users");
		
	}
	
	

}
