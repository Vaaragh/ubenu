package Ubenu.service;

import java.util.List;

import Ubenu.model.PharmaCompany;


public interface PharmaCompanyService {
	PharmaCompany findOne(String sysId); 
	List<PharmaCompany> findAll(); 
	List<PharmaCompany> findAllActive(); 

	PharmaCompany save(PharmaCompany company); 
	PharmaCompany update(String sysId, String companyName, String country); 
	PharmaCompany delete(String sysId);
	PharmaCompany create(String companyName, String country); 
}
