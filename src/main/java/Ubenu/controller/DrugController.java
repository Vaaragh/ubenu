package Ubenu.controller;

import java.io.IOException;

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

import Ubenu.model.Drug;
import Ubenu.model.enums.EDrugFormulation;
import Ubenu.service.DrugCategoryService;
import Ubenu.service.DrugService;
import Ubenu.service.PharmaCompanyService;
import Ubenu.service.WishlistService;

@Controller
@RequestMapping(value="/drugs")
public class DrugController {

	@Autowired
	private DrugService drugService;
	@Autowired
	private DrugCategoryService categoryService;
	@Autowired
	private PharmaCompanyService companyService;
	@Autowired
	private WishlistService wishServ;


	
	@GetMapping("")
	public String index(Model model, HttpSession session) {
		String role = (String) session.getAttribute("role");
		switch(role) {
		case "admin":
			model.addAttribute("drugs", drugService.findAll());
			break;
		case "customer":
			model.addAttribute("drugs", drugService.findExisting());
			break;
		case "merchant":
			model.addAttribute("drugs", drugService.findAll());
			break;
		}		
		
		return "drugs/index";
	}
	
	@GetMapping("/details")
	public String details(@RequestParam String sysId, Model model) {
		Drug drug = drugService.findOne(sysId);
		model.addAttribute("drugForms", EDrugFormulation.values());
		model.addAttribute("companies", companyService.findAll());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("drug", drug);
		return "drugs/details";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("drugForms", EDrugFormulation.values());
		model.addAttribute("companies", companyService.findAll());
		model.addAttribute("categories", categoryService.findAll());
		return "drugs/add";
	}
	
	
	@PostMapping("/add")
	public void add(@ModelAttribute Drug drug,@RequestParam String drugCategoryId, @RequestParam String pharmaCompanyId, HttpServletResponse response) throws IOException {
		
		drugService.save(drug, drugCategoryId, pharmaCompanyId);
		response.sendRedirect("/Ubenu/drugs");
	}
	
	@PostMapping("/update")
	public void update(@ModelAttribute Drug drug, @RequestParam String drugCategoryId, @RequestParam String pharmaCompanyId, HttpServletResponse response) throws IOException {
		drugService.update(drug, drugCategoryId, pharmaCompanyId);
		response.sendRedirect("/Ubenu/drugs");
	}	
	
	@PostMapping("/delete")
	public void delete(@RequestParam String sysId, HttpServletResponse response) throws IOException  {
		drugService.delete(sysId);
		response.sendRedirect("/Ubenu/drugs");
	}
	
	@PostMapping("/addWish")
	public void addWish(@RequestParam String drugId, @RequestParam String userId, HttpSession session, HttpServletResponse response) throws IOException {
		wishServ.add(userId, drugId);
		session.setAttribute("wishlist", wishServ.findOne(userId));
		response.sendRedirect("/Ubenu/drugs");

	}
	
	@PostMapping("/approve")
	public void approveDrug(@RequestParam String sysApproveId, HttpSession session, HttpServletResponse response) throws IOException {
		drugService.approve(sysApproveId);
		response.sendRedirect("/Ubenu/drugs");
	}
	

	
	
	
}
