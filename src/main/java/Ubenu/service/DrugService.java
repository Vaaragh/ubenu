package Ubenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ubenu.dao.DrugRepo;
import Ubenu.model.Drug;

@Service
public class DrugService {
	
	@Autowired
	private DrugRepo repo;
	
	public List<Drug> findAll(){
		return repo.findAll();
	}
	
	public Drug findOne(String sysId) {
		return repo.findOne(sysId);
	}
	
	public void update(String sysId, String drugName, String drugCode, String drugDescription, String contraindications,
			String drugFormulation, float rating, String imagePath, int inventory, float price,
			String pharmaCompany, String drugCategory, boolean active) {
		repo.update(sysId, drugName, drugCode, drugDescription, contraindications, drugFormulation, rating, imagePath, inventory, price, pharmaCompany, drugCategory, active);
	}
	
	public void delete(String sysId) {
		repo.delete(sysId);
	}
	
	public void save(String drugName, String drugCode, String drugDescription, String contraindications,
			String drugFormulation, float rating, String imagePath, int inventory, float price,
			String pharmaCompany, String drugCategory, boolean active) {
		repo.save(drugName, drugCode, drugDescription, contraindications, drugFormulation, rating, imagePath, inventory, price, pharmaCompany, drugCategory, active);
	}
		 
	
}
