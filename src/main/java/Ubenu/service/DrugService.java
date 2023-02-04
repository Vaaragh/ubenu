package Ubenu.service;

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
	
	public List<Drug> findAll(){
		return repo.findAll();
	}
	
	public Drug findOne(String sysId) {
		return repo.findOne(sysId);
	}
	
	
	public void update(Drug drug, String category, String company) {
		DrugCategory cat = categServ.findOne(category);
		PharmaCompany comp = companyServ.findOne(company);
		
		drug.setDrugCategory(cat);
		drug.setPharmaCompany(comp);
		
		repo.update(drug);
		
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
