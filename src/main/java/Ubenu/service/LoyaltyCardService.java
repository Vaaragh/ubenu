package Ubenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ubenu.dao.LoyaltyCardRepo;
import Ubenu.model.LoyaltyCard;
import Ubenu.model.User;

@Service
public class LoyaltyCardService {

	@Autowired
	private LoyaltyCardRepo repo;
	
	public List<LoyaltyCard> findPending(){
		return repo.findAllPending();
	}
	
	public List<LoyaltyCard> findApproved(){
		return repo.findAllApproved();
	}
	
	public LoyaltyCard findUserCard(User user) {
		List<LoyaltyCard> list = findApproved();
		for (LoyaltyCard card : list) {
			System.out.println(card.getNumberOfPoints());
			if (card.getUser().getSysId().equals(user.getSysId())) {
				return card;
			}
		}
		return null;
	}
	
	public boolean checkForRequest(String userId) {
		List<LoyaltyCard> list = findApproved();
		list.addAll(findPending());
		for (LoyaltyCard card : list) {
			if (card.getUser().getSysId().equals(userId)) {
				return true;
			}
		}
		return false;
	}
	
	public void save(String userId) {
		repo.save(userId);
	}
	
	public void update(LoyaltyCard card) {
		repo.update(card);
	}
	
	public void approve(String userId) {
		repo.approve(userId);
	}
	
	
}
