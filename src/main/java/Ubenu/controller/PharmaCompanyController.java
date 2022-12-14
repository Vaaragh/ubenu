package Ubenu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import Ubenu.controller.utilities.HtmlGen;
import Ubenu.model.PharmaCompany;
import Ubenu.service.PharmaCompanyService;

@Controller
@RequestMapping(value="/companies")
public class PharmaCompanyController implements ServletContextAware{

	public static final String COMPANIES = "pharmaceutical_companies";
	
	@Autowired
	private ServletContext servletContext;
	private String bURL;
	
	@Autowired
	private PharmaCompanyService pharmaCompanyService;
	
	@PostConstruct
	public void init() {
		bURL = servletContext.getContextPath() + "/";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
	}
	
	
	@GetMapping
	@ResponseBody
	public void index(HttpServletResponse response) throws IOException {
		List<PharmaCompany> companies = pharmaCompanyService.findAllActive();
		
		PrintWriter out;
		out = response.getWriter();
		Document doc = HtmlGen.getDocument("static/template.html");

		Element body = doc.select("body").first();
		Element navLinks = HtmlGen.getElement("static/nav_links.html", "ul");
		Element table = HtmlGen.getElement("static/content_table.html", "table");
		
		
		for (PharmaCompany company : companies) {
			Element companyRow = new Element(Tag.valueOf("tr"),"");
			Element nameCell = new Element(Tag.valueOf("td"),"");
			Element nameCellLink = new Element(Tag.valueOf("a"),"").attr("href","companies/details?sysId=" + company.getSysId()).text(company.getCompanyName());
			Element hqCell = new Element(Tag.valueOf("td"),"").text(company.getCountry());

			Element formCell = new Element(Tag.valueOf("td"), "");
			Element form = new Element(Tag.valueOf("form"), "").attr("method", "post").attr("action", "companies/delete");
			Element inputHidden = new Element(Tag.valueOf("input"), "").attr("type", "hidden").attr("name", "sysId").attr("value", company.getSysId());
			Element inputSubmit = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Delete");
			
			HtmlGen.fillElement(form, inputHidden,inputSubmit);
			formCell.appendChild(form);
			
			nameCell.appendChild(nameCellLink);
			HtmlGen.fillElement(companyRow, nameCell,hqCell,formCell);
			
			table.appendChild(companyRow);
			
		}
		
		

		
		// add in order of concatenation
		HtmlGen.fillElement(body,navLinks,table);
		
		out.write(doc.html());
		return;
		
	}
	
	@GetMapping(value="/add")
	@ResponseBody
	public void create(HttpServletResponse response) throws IOException {
		PrintWriter out;
		out = response.getWriter();
		Document doc = HtmlGen.getDocument("static/template.html");
		
		Element body = doc.select("body").first();
		Element navLinks = HtmlGen.getElement("static/nav_links.html", "ul");
		Element form = HtmlGen.getElement("static/pharma_company_form.html", "form");
			
		form.getElementById("submitChoice").attr("value", "Add");
		form.attr("action", "add");
		
		HtmlGen.fillElement(body,navLinks, form);
		out.write(doc.html());
		return;
		
	}
	
	@GetMapping(value="/details")
	@ResponseBody
	public void details(@RequestParam String sysId, HttpServletResponse response) throws IOException {
		PharmaCompany company = pharmaCompanyService.findOne(sysId);
		
		PrintWriter out;
		out = response.getWriter();
		Document doc = HtmlGen.getDocument("static/template.html");
		
		Element body = doc.select("body").first();
		Element navLinks = HtmlGen.getElement("static/nav_links.html", "ul");
		Element form = HtmlGen.getElement("static/pharma_company_form.html", "form");
		Element hiddenId = new Element(Tag.valueOf("input"),"").attr("name","sysId").attr("value", sysId).attr("type", "hidden");
		
		form.appendChild(hiddenId);
		
		form.getElementById("nameField").attr("value", company.getCompanyName());
		form.getElementById("hqField").attr("value", company.getCountry());
		
		form.getElementById("submitChoice").attr("value", "Edit");
		form.attr("action", "edit");
		
		HtmlGen.fillElement(body,navLinks, form);
		out.write(doc.html());
		return;
	}
	
	
	@PostMapping(value="/add")
	public void create(@RequestParam String companyName, @RequestParam String country, HttpServletResponse response) throws IOException {
		PharmaCompany company = pharmaCompanyService.create(companyName, country);
		response.sendRedirect(bURL + "companies");
		
	}
	
	@PostMapping(value="/edit")
	public void edit(@RequestParam String companyName, @RequestParam String country,@RequestParam String sysId, HttpServletResponse response) throws IOException {
		PharmaCompany company = pharmaCompanyService.update(sysId, companyName, country);
		response.sendRedirect(bURL + "companies");

	}
	
	
	@PostMapping(value="/delete")
	public void delete(@RequestParam String sysId, HttpServletResponse response) throws IOException {
		PharmaCompany deleted = pharmaCompanyService.delete(sysId);
		response.sendRedirect(bURL + "companies");
	}

	

}
