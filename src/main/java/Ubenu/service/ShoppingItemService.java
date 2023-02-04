package Ubenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ubenu.dao.ShoppingItemRepo;
import Ubenu.model.Drug;
import Ubenu.model.ShoppingItem;

@Service
public class ShoppingItemService {

	@Autowired
	private ShoppingItemRepo repo;
	
	@Autowired
	private DrugService drugServ;
	
	public List<ShoppingItem> findAll(){
		return repo.findAll();
	}
	
	public ShoppingItem findOne(String sysId) {
		return repo.findOne(sysId);
	}
	
	public void save(ShoppingItem ite, String drugId) {
		Drug drug = drugServ.findOne(drugId);
		ite.setDrug(drug);
		repo.save(ite);		
	}
	
	public void update(ShoppingItem ite, String drugId) {
		Drug drug = drugServ.findOne(drugId);
		ite.setDrug(drug);
		repo.update(ite);		
	}
	
	public void delete(String sysId) {
		repo.delete(sysId);
	}
	
}
