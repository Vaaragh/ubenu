package Ubenu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import Ubenu.controller.utilities.HtmlGen;
import Ubenu.model.User;
import Ubenu.service.UserService;

@Controller
@RequestMapping(value="/registration")
public class RegistrationController implements ServletContextAware{

	public static final String USERS = "users";
	
	@Autowired
	private ServletContext servletContext;
	private String bURL;
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void inti() {
		bURL = servletContext.getContextPath() + "/";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		
	}
	
	@GetMapping
	@ResponseBody
	public void index(HttpServletResponse response) throws IOException {
		List<User> users = userService.findAllActive();
		
		PrintWriter out;
		out = response.getWriter();
		Document doc = HtmlGen.getDocument("static/template.html");
		Element body = doc.select("body").first();
		Element navLinks = HtmlGen.getElement("static/nav_links.html", "ul");
		
		Element form = HtmlGen.getElement("static/registration_form.html", "form");
		
		form.getElementById("dob").attr("min",(LocalDate.now().minusYears(18)).toString());
		
		HtmlGen.fillElement(body,navLinks,form);
		
		for(User user: users) {
			body.append(user.toString());
			body.append("</br>");
		}
		
		out.write(doc.html());
		return;
		
	}
	
	@PostMapping(value="/add")
	public void create(@RequestParam String username,@RequestParam String password,@RequestParam String email, @RequestParam String firstName,@RequestParam String lastName, @RequestParam String dateOfBirth, @RequestParam String address, @RequestParam String phoneNumber,HttpServletResponse response) throws IOException {
		User user = userService.create(username, password, email, firstName, lastName, LocalDate.parse(dateOfBirth), address, phoneNumber);
		response.sendRedirect(bURL + "companies");
	}
	

}
