package Ubenu.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Ubenu.model.ShoppingItem;
import Ubenu.model.User;
import Ubenu.service.CustomerOrderService;
import Ubenu.service.LoyaltyCardService;
import Ubenu.service.UserService;
import Ubenu.service.WishlistService;

@Controller
@RequestMapping(value="users/login")
public class LoginController {

	@Autowired
	private UserService userServ;
	
	@Autowired
	private WishlistService wishServ;
	
	@Autowired
	private CustomerOrderService custServ;
	
	@Autowired
	private LoyaltyCardService cardServ;
	
	
	@GetMapping("")
	public String index(HttpServletResponse response, HttpSession session) {
		return "/users/login";
	}
	
	@PostMapping("")
	public void login(HttpServletResponse response, HttpSession session, @RequestParam String username, @RequestParam String password) throws IOException {
		User user = userServ.login(username, password);
		if (user==null) {
			response.sendRedirect("/Ubenu/users/login");
		}else {
			switch (user.getRole().toString()) {
			case "CUSTOMER":
				session.setAttribute("user", user);
				session.setAttribute("loyalty", cardServ.findUserCard(user));
				session.setAttribute("wishlist", wishServ.findOne(user.getSysId()));
				session.setAttribute("history", custServ.findForCustomer(user.getSysId()));
				session.setAttribute("shoppingCart", new ArrayList<ShoppingItem>());
				session.setAttribute("role", "customer");
				session.setAttribute("cardRequest", cardServ.checkForRequest(user.getSysId()));
				response.sendRedirect("/Ubenu/users/details?sysId="+user.getSysId());
				break;
			case "ADMIN":
				session.setAttribute("user", user);
				session.setAttribute("role", "admin");
				response.sendRedirect("/Ubenu/users");
				break;
			case "MERCHANT":
				session.setAttribute("user", user);
				session.setAttribute("role", "merchant");
				response.sendRedirect("/Ubenu/drugs");
			}			
		}
	}
	
}
