package Ubenu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ubenu.dao.DrugRepo;
import Ubenu.model.Drug;
import Ubenu.model.DrugCategory;
import Ubenu.model.PharmaCompany;

@Service
public class DrugService {
	
	@Autowired
	private DrugRepo repo;
	
	@Autowired
	private PharmaCompanyService companyServ;
	
	@Autowired
	private DrugCategoryService categServ;
	
	@Autowired
	private CommentService commServ;
	
	
	public List<Drug> findAll(){
		List<Drug> list = repo.findAll();
		for (int i=0;i<list.size();i++) {
			list.get(i).setRating(commServ.findRatingForDrug(list.get(i).getSysId()));
		}
		return list;
	}
	
	public List<Drug> findExisting(){
		List<Drug> list = repo.findExisting();
		for (int i=0;i<list.size();i++) {
			list.get(i).setRating(commServ.findRatingForDrug(list.get(i).getSysId()));
		}
		return list;
	}
	
	public List<Drug> findBoughtDrugs(String userId){
		return repo.findBoughtDrugs(userId);
	}
	
	
	public Drug findOne(String sysId) {
		Drug drug = repo.findOne(sysId);
		drug.setRating(commServ.findRatingForDrug(sysId));
		return drug;
	}
	
	public List<Drug> searchByParams(String name, String categoryId, String priceMin, String priceMax, boolean customer){
		float priceMinV=0;
		float priceMaxV=1000000;
		
		if(name=="") {
			name="%";
		} else {
			name= "%"+name+"%";
		}
		if(categoryId=="") {
			categoryId="%";
		} 
		if(priceMin!="") {
			priceMinV=Float.valueOf(priceMin);
		}
		if(priceMax!="") {
			priceMaxV=Float.valueOf(priceMax);
		}
		
		List<Drug> list;
		if (customer) {
			list = repo.searchCustomer(name,categoryId, priceMinV, priceMaxV);
		} else {
			list = repo.search(name,categoryId, priceMinV, priceMaxV);
		}
		
		
		if (list==null) {
			list = new ArrayList<Drug>();
		}
		for (int i=0;i<list.size();i++) {
			list.get(i).setRating(commServ.findRatingForDrug(list.get(i).getSysId()));
		}
		return list;
	}
	
	
	public void update(Drug drug, String category, String company) {
		DrugCategory cat = categServ.findOne(category);
		PharmaCompany comp = companyServ.findOne(company);
		
		drug.setDrugCategory(cat);
		drug.setPharmaCompany(comp);
		
		repo.update(drug);
		
	}
	
	public void reduce(Drug drug, int amount) {
		drug.setInventory(drug.getInventory()-amount);
		repo.update(drug);
	}
	
	public void approve(String sysId) {
		repo.approve(sysId);
	}
	
	public void delete(String sysId) {
		repo.delete(sysId);
	}
	
	public void save(Drug drug, String category, String company) {
		
		DrugCategory cat = categServ.findOne(category);
		PharmaCompany comp = companyServ.findOne(company);
		
		drug.setDrugCategory(cat);
		drug.setPharmaCompany(comp);
		
		repo.save(drug);
	}
	
		 
	
}
