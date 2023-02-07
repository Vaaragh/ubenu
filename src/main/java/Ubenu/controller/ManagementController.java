package Ubenu.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Ubenu.model.HistoryItem;
import Ubenu.service.HistoryService;

@Controller
@RequestMapping(value="/management")
public class ManagementController {
	
	@Autowired
private HistoryService histServ;
	
	@GetMapping("")
	public String index(HttpSession session, Model model) {
		List<HistoryItem> list = histServ.findAll();
		float total = 0;
		for (int i=0; i<list.size();i++) {
			total += list.get(i).getAmount()*list.get(i).getPrice();
		}
		model.addAttribute("totalPrice", total); 
		model.addAttribute("history", list);
		model.addAttribute("today", LocalDate.now());
		
		return "management/index";
	}
	
	@GetMapping("index/search")
	public String indexSearch(HttpSession session, Model model, @RequestParam String searchDateStart, @RequestParam String searchDateEnd) {
		List<HistoryItem> list = histServ.findBetween(searchDateStart, searchDateEnd);

		float total = 0;
		for (int i=0; i<list.size();i++) {
			total += list.get(i).getAmount()*list.get(i).getPrice();
		}
		model.addAttribute("totalPrice", total); 
		model.addAttribute("history", list);
		model.addAttribute("today", LocalDate.now());
		
		
		return "management/index";
	}

}
