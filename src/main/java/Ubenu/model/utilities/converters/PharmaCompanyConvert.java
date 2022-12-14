package Ubenu.model.utilities.converters;

import java.util.StringJoiner;

import Ubenu.model.PharmaCompany;


public class PharmaCompanyConvert {
	
	public static PharmaCompany createObject(String[] parts) {
		
		PharmaCompany company = new PharmaCompany();
		
		company.setSysId(parts[0]);
		company.setCompanyName(parts[1]);
		company.setCountry(parts[2]);
		company.setActive(Boolean.parseBoolean(parts[3]));
		
		return company;
	}
	
	public static String createLine(PharmaCompany company) {
	
		StringJoiner sj = new StringJoiner("|");
		
		sj.add(company.getSysId());
		sj.add(company.getCompanyName());
		sj.add(company.getCountry());
		sj.add(String.valueOf(company.isActive()));
		
		return sj.toString();
	}
	
	

}