package Ubenu.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ubenu.dao.CommentRepo;
import Ubenu.model.Comment;
import Ubenu.model.User;

@Service 
public class CommentService {

	@Autowired
	private CommentRepo repo;
	
	@Autowired
	private UserService userServ;
	
	public List<Comment> findAll(){
		return repo.findAll();
	}
	
	public List<Comment> findForDrug(String drugId){
		return repo.findForDrug(drugId);
	}
	
	public float findRatingForDrug(String drugId) {
		List<Comment> list = findForDrug(drugId);
		float ret = 0;
		if (!list.isEmpty()) {
			float total = 0;
			for (int i=0; i<list.size();i++) {
				total += list.get(i).getRating();
			}
			ret = total/list.size();
		}
		return ret;
	}
	
	
	public void save(Comment comment, String userId, String drugId) {
		User user = userServ.findOne(userId);
		
		comment.setUser(user);
		comment.setDateOf(LocalDate.now());
		
		repo.save(comment, drugId);
	}
	
}
