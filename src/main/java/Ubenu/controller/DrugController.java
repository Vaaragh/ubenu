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

import Ubenu.model.Drug;
import Ubenu.model.enums.EDrugFormulation;
import Ubenu.service.DrugCategoryService;
import Ubenu.service.DrugService;
import Ubenu.service.PharmaCompanyService;

@Controller
@RequestMapping(value="/drugs")
public class DrugController {

	@Autowired
	private DrugService drugService;
	@Autowired
	private DrugCategoryService categoryService;
	@Autowired
	private PharmaCompanyService companyService;
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("drugs", drugService.findAll());
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
	
	
	
}
