package Ubenu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Ubenu.service.LoyaltyCardService;

@Controller
@RequestMapping(value="/loyaltyCards")
public class LoyaltyCardController {

	@Autowired
	private LoyaltyCardService cardServ;
	
	
	@GetMapping("")
	public String index(Model model, HttpSession session) {
		model.addAttribute("cards", cardServ.findPending());
		return "loyaltyCards/index";
	}
	
	@PostMapping("/approve")
	public void approve(@RequestParam String userId, HttpSession session, HttpServletResponse response) throws IOException {
		cardServ.approve(userId);
		response.sendRedirect("/Ubenu/loyaltyCards");
	}
	
	@PostMapping("/decline")
	public void decline(@RequestParam String userDId, HttpSession session, HttpServletResponse response) throws IOException {
		cardServ.decline(userDId);
		response.sendRedirect("/Ubenu/loyaltyCards");
	}
	
}
