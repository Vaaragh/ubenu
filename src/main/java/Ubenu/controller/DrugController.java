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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Ubenu.model.Comment;
import Ubenu.model.Drug;
import Ubenu.model.ShoppingItem;
import Ubenu.model.User;
import Ubenu.model.enums.EDrugFormulation;
import Ubenu.service.CommentService;
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
	@Autowired
	private CommentService commServ;

	
	@GetMapping("")
	public String index(Model model, HttpSession session) {
		String role = (String) session.getAttribute("role");
		model.addAttribute("categories", categoryService.findAll());
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
	
	@GetMapping("index/search")
	public String indexSearch(Model model, HttpSession session, 
								@RequestParam String searchName, 
								@RequestParam String searchCategory,
								@RequestParam String searchPriceMin, 
								@RequestParam String searchPriceMax,
								@RequestParam String sortBy,
								@RequestParam String ascDesc) {
		boolean customer = ((String)session.getAttribute("role")).equals("customer");
		List<Drug> list = drugService.searchByParams(searchName, searchCategory, searchPriceMin, searchPriceMax, customer, sortBy, ascDesc);
		
		model.addAttribute("drugs", list);
		
		model.addAttribute("categories", categoryService.findAll());
		return "drugs/index";
	}
	
	
	
	
	
	
	@GetMapping("/details")
	public String details(@RequestParam String sysId, Model model, HttpSession session) {
		Drug drug = drugService.findOne(sysId);
		List<Comment> commList = commServ.findForDrug(sysId);
		
		boolean allowed = false;
		List<Drug> bought = (List<Drug>) session.getAttribute("boughtDrugs");
		if (bought!=null) {
			for (int i=0; i<bought.size();i++) {
				if (bought.get(i).getSysId().equals(sysId)) {
					allowed = true;
					break;
				}
			}
		}
		model.addAttribute("allowed", allowed);
		model.addAttribute("drugForms", EDrugFormulation.values());
		model.addAttribute("companies", companyService.findAll());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("drug", drug);
		if (commList.isEmpty()) {
			model.addAttribute("comments", new ArrayList<Comment>());
		} else {
			model.addAttribute("comments", commList);			
		}
		
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
	
	@PostMapping("/addToCart")
	public void addToCart(@RequestParam String drugCartId, HttpServletResponse response, HttpSession session) throws IOException {
		ArrayList<ShoppingItem> list = (ArrayList<ShoppingItem>) session.getAttribute("shoppingCart");
		
		if(list.isEmpty()) {
			ShoppingItem newItem = new ShoppingItem();
			newItem.setAmount(1);
			newItem.setDrug(drugService.findOne(drugCartId));
			list.add(newItem);
			session.setAttribute("shoppingCart", list);
		} else {
			int index = -1;
			for (int i=0; i<list.size();i++) {
				if (list.get(i).getDrug().getSysId().equals(drugCartId)) {
					index = i;
				}
			}
			if (index == -1) {
				ShoppingItem newItem = new ShoppingItem();
				newItem.setAmount(1);
				newItem.setDrug(drugService.findOne(drugCartId));
				list.add(newItem);
				session.setAttribute("shoppingCart", list);
			} else {
				ShoppingItem item = list.get(index);
				item.setAmount(item.getAmount()+1);
			}			
		}
		response.sendRedirect("/Ubenu/drugs");
	}
	
	@PostMapping("/addComment")
	public void comment(@RequestParam String drugCommentId, @RequestParam String anonComment, @RequestParam String ratingComment, @RequestParam String textComment, HttpSession session, HttpServletResponse response) throws IOException {
		String userId =((User) session.getAttribute("user")).getSysId();
		Comment comment = new Comment();
		comment.setAnon(Boolean.parseBoolean(anonComment));
		comment.setRating(Integer.parseInt(ratingComment));
		comment.setTextContent(textComment);
		commServ.save(comment, userId, drugCommentId);
		
		response.sendRedirect("/Ubenu/drugs");
	}
	

	

	
	
	
}
