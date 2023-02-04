package Ubenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ubenu.dao.CommentRepo;
import Ubenu.model.Comment;
import Ubenu.model.Drug;
import Ubenu.model.User;

@Service 
public class CommentService {

	@Autowired
	private CommentRepo repo;
	
	@Autowired
	private DrugService drugServ;
	
	@Autowired
	private UserService userServ;
	
	public List<Comment> findAll(){
		return repo.findAll();
	}
	
	public List<Comment> findForDrug(String drugId){
		return repo.findForDrug(drugId);
	}
	
	public void save(Comment comment, String userId, String drugId) {
		User user = userServ.findOne(userId);
		Drug drug = drugServ.findOne(drugId);
		
		comment.setUser(user);
		comment.setDrug(drug);
		
		repo.save(comment);
	}
	
}
