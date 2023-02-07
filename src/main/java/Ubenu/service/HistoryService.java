package Ubenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ubenu.dao.HistoryRepo;
import Ubenu.model.HistoryItem;

@Service
public class HistoryService {

	@Autowired
	private HistoryRepo repo;
	
	public List<HistoryItem> findAll(){
		return repo.findAll();
	}
	
	public List<HistoryItem> findBetween(String start, String end){
		return repo.findBetween(start, end);
	}
	
}
