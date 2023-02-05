package Ubenu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Ubenu.model.CustomerOrder;
import Ubenu.model.ShoppingItem;
import Ubenu.model.User;
import Ubenu.model.utilities.IdGen;
import Ubenu.service.CustomerOrderService;
import Ubenu.service.LoyaltyCardService;
import Ubenu.service.ShoppingItemService;
import Ubenu.service.UserService;

@Controller
@RequestMapping(value="/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private ShoppingItemService itemServ;
	@Autowired
	private UserService userServ;
	@Autowired
	private CustomerOrderService orderServ;
	@Autowired
	private LoyaltyCardService cardServ;
	
	@GetMapping("")
	public String index(HttpSession session, Model model) {
		
		List<ShoppingItem> list = (List<ShoppingItem>) session.getAttribute("shoppingCart");
		int total = 0;
		if (!list.isEmpty()) {
			for (int i=0;i<list.size();i++) {
				total += list.get(i).getAmount()*list.get(i).getDrug().getPrice();
			}
			
		}
		model.addAttribute("total", total);
		
		
		return "shoppingCart/index";
	}
	
	@PostMapping("/removeFromCart")
	public void remove(@RequestParam String removalId, HttpSession session, HttpServletResponse response) throws IOException {
		ArrayList<ShoppingItem> list = (ArrayList<ShoppingItem>) session.getAttribute("shoppingCart");
		int index = -1;
		for (int i=0;i<list.size();i++) {
			if (list.get(i).getDrug().getSysId().equals(removalId)) {
				index = i;
			}
		}
		
		list.remove(index);
		session.setAttribute("shoppingCart",list);
		response.sendRedirect("/Ubenu/shoppingCart");
		
	}
	
	@PostMapping("/reduce")
	public void reduce(@RequestParam String reduceId, HttpSession session, HttpServletResponse response) throws IOException {
		ArrayList<ShoppingItem> list = (ArrayList<ShoppingItem>) session.getAttribute("shoppingCart");
		int index = -1;
		for (int i=0;i<list.size();i++) {
			if (list.get(i).getDrug().getSysId().equals(reduceId)) {
				index = i;
			}
		}
		
		list.get(index).setAmount(list.get(index).getAmount()-1);
		response.sendRedirect("/Ubenu/shoppingCart");	
	}
	
	@PostMapping("/increase")
	public void increase(@RequestParam String increaseId, HttpSession session, HttpServletResponse response) throws IOException {
		ArrayList<ShoppingItem> list = (ArrayList<ShoppingItem>) session.getAttribute("shoppingCart");
		int index = -1;
		for (int i=0;i<list.size();i++) {
			if (list.get(i).getDrug().getSysId().equals(increaseId)) {
				index = i;
			}
		}
		
		list.get(index).setAmount(list.get(index).getAmount()+1);
		response.sendRedirect("/Ubenu/shoppingCart");	
	}
	
	@PostMapping("/checkout")
	public void checkout(HttpSession session, HttpServletResponse response) throws IOException {
		String userId =((User) session.getAttribute("user")).getSysId();
		List<ShoppingItem> listItems = (List<ShoppingItem>) session.getAttribute("shoppingCart");
		String orderId = IdGen.newID();
		CustomerOrder order = new CustomerOrder();
		
		order.setItems(listItems);
		orderServ.save(order, userId, orderId);
		session.setAttribute("shoppingCart", new ArrayList<ShoppingItem>());
		response.sendRedirect("/Ubenu/shoppingCart");		
	}
}
