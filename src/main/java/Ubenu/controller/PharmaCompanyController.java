package Ubenu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Ubenu.model.PharmaCompany;
import Ubenu.service.PharmaCompanyService;

@Controller
@RequestMapping(value="/companies")
public class PharmaCompanyController{

	@Autowired
	private PharmaCompanyService service;
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("companies", service.findAll());
		return "companies/index";
	}
	
	@GetMapping("/details")
	public String details(@RequestParam String sysId, Model model) {
		model.addAttribute("company", service.findOne(sysId));
		return "companies/details";
	}
	
	@GetMapping("/add")
	public String add() {
		return "companies/add";
	}
	
	@PostMapping("/add")
	public void add(@ModelAttribute PharmaCompany pc, HttpServletResponse response) throws IOException {
		service.save(pc);
		response.sendRedirect("/Ubenu/companies");
	}
	
	@PostMapping("/update")
	public void update(@ModelAttribute PharmaCompany company, HttpServletResponse response) throws IOException {
		service.update(company);
		response.sendRedirect("/Ubenu/companies");
	}
	
	@PostMapping("/delete")
	public void delete(@RequestParam String sysId, HttpServletResponse response) throws IOException {
		service.delete(sysId);
		response.sendRedirect("/Ubenu/companies");
	}
	
	
	

}