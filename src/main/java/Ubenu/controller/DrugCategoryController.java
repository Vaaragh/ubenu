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

import Ubenu.model.DrugCategory;
import Ubenu.service.DrugCategoryService;

@Controller
@RequestMapping(value="/categories")
public class DrugCategoryController {

	@Autowired
	private DrugCategoryService service;
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("categories", service.findAll());
		return "categories/index";
	}
	
	@GetMapping("/details")
	public String details(@RequestParam String sysId, Model model) {
		model.addAttribute("category", service.findOne(sysId));
		return "categories/details";
	}
	
	@GetMapping("/add")
	public String add() {
		return "categories/add";
	}
	
	@PostMapping("/add")
	public void add(@RequestParam String categoryName, @RequestParam String useCase, @RequestParam String description, HttpServletResponse response) throws IOException {
		service.save(categoryName, useCase, description);
		response.sendRedirect("/Ubenu/categories");
	}
	
	@PostMapping("/update")
	public void update(@ModelAttribute DrugCategory category, HttpServletResponse response) throws IOException {
		service.update(category);
		response.sendRedirect("/Ubenu/categories");
	}
	
	@PostMapping("/delete")
	public void delete(@RequestParam String sysId, HttpServletResponse response) throws IOException {
		service.delete(sysId);
		response.sendRedirect("/Ubenu/categories");

	}
	
}
