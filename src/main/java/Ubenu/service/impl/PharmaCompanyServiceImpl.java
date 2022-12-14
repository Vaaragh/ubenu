package Ubenu.service.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import Ubenu.model.PharmaCompany;
import Ubenu.model.utilities.IdGen;
import Ubenu.model.utilities.converters.PharmaCompanyConvert;
import Ubenu.service.PharmaCompanyService;



@Service
@Qualifier("pharmaCompanyFiles")
public class PharmaCompanyServiceImpl implements PharmaCompanyService {

	@Value("${companies.pathToFile}")
	private String pathToFile;
	
	private Map<String, PharmaCompany> readFromFile(){
		Map<String, PharmaCompany> companies = new HashMap<>();
		
		Path path = Paths.get(pathToFile);
		try {
			List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
			
			for(String line: lines) {
//				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				String[] parts = line.split("\\|");

				PharmaCompany company = PharmaCompanyConvert.createObject(parts);
				companies.put(company.getSysId(), company);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companies;
		
	}
	
	private Map<String, PharmaCompany> saveToFile(Map<String, PharmaCompany> companies){
		
		Map<String, PharmaCompany> ret = new HashMap<>();
		
		try {
			Path path = Paths.get(pathToFile);
			List<String> lines = new ArrayList<>();
			for (PharmaCompany company : companies.values()) {
				lines.add(PharmaCompanyConvert.createLine(company));
				ret.put(company.getSysId(), company);
			}
			Files.write(path, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
		
	}
	
	public PharmaCompany create(String companyName, String country) {
		PharmaCompany company = new PharmaCompany();
		company.setCompanyName(companyName);
		company.setCountry(country);
		company.setActive(true);
		company.setSysId(IdGen.newID());
		
		save(company);
		return company;
	}
	
	@Override
	public PharmaCompany update(String sysId, String companyName, String country) {
		Map<String, PharmaCompany> companies = readFromFile();
		
		if (!companies.containsKey(sysId)) {
			throw new IllegalArgumentException("Non existing company");
		}
		PharmaCompany company = companies.get(sysId);
		if (company != null) {
			company.setCompanyName(companyName);
			company.setCountry(country);
		}
		saveToFile(companies);
		
		return company;
		
	}
	
	
	
	
	@Override
	public PharmaCompany findOne(String sysId) {
		Map<String, PharmaCompany> companies = readFromFile();
		return companies.get(sysId);
	}

	@Override
	public List<PharmaCompany> findAll() {
		Map<String, PharmaCompany> companies = readFromFile();
		return new ArrayList<>(companies.values());
	}
	
	@Override
	public List<PharmaCompany> findAllActive() {
		List<PharmaCompany> actives =  findAll();
		actives.removeIf(c -> c.isActive() == false);
		return actives;
	}

	@Override
	public PharmaCompany save(PharmaCompany company) {
		Map<String, PharmaCompany> companies = readFromFile();
		
		if (company.getSysId() == null) {
			company.setSysId(IdGen.newID());
		}
		companies.put(company.getSysId(), company);
		saveToFile(companies);
		return company;
	}

	@Override
	public PharmaCompany delete(String sysId) {
		Map<String, PharmaCompany> companies = readFromFile();
		
		if (!companies.containsKey(sysId)) {
			throw new IllegalArgumentException("Non existing company");
		}
		PharmaCompany company = companies.get(sysId);
		if (company != null) {
			company.setActive(false);
		}
		saveToFile(companies);
		
		return company;
	}


}
