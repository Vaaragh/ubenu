package Ubenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ubenu.dao.PharmaCompanyRepo;
import Ubenu.model.PharmaCompany;

@Service
public class PharmaCompanyService {
	
	@Autowired
	private PharmaCompanyRepo repo;
	
	public List<PharmaCompany> findAll(){
		return repo.findAll();
	}
	
	public void save(String name, String country) {
		repo.save(name, country);
	}
	
	public void update(PharmaCompany pharmaCompany) {
		repo.update(pharmaCompany);
	}
	
	public void delete(String sysId) {
		repo.delete(sysId);
	}	
	
	public PharmaCompany findOne(String sysId){
		return repo.findOne(sysId);
	}
	
}
