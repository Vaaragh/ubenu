package Ubenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ubenu.dao.DrugRepo;
import Ubenu.model.Drug;

@Service
public class WishlistService {
	
	@Autowired
	private DrugRepo repo;
	
	public List<Drug> findOne(String userId){
		return repo.findWishlist(userId);
	}
	
	public void add(String userId, String drugId) {
		repo.addToWishlist(userId, drugId);
	}
	
	public void remove(String userId, String drugId) {
		repo.removeFromWishlist(userId, drugId);
	}
	
	

}
