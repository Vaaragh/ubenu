package Ubenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ubenu.dao.DrugCategoryRepo;
import Ubenu.model.DrugCategory;

@Service
public class DrugCategoryService {

	@Autowired
	private DrugCategoryRepo repo;
	
	public List<DrugCategory> findAll(){
		return repo.findAll();
	}
	
	public void save(DrugCategory drugCategory) {
		repo.save(drugCategory);
	}
	
	public void update(DrugCategory drugCategory) {
		repo.update(drugCategory);
	}
	
	public void delete(String sysId) {
		repo.delete(sysId);
	}
	
	public DrugCategory findOne(String sysId) {
		return repo.findOne(sysId);
	}
	
	public DrugCategory findByName(String name) {
		return repo.findByName(name);
	}
	
}
