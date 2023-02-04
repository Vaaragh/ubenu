package Ubenu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ubenu.dao.CustomerOrderRepo;
import Ubenu.model.CustomerOrder;

@Service
public class CustomerOrderService {

	@Autowired
	private CustomerOrderRepo repo;
	
	
	public List<CustomerOrder> findAll(){
		return repo.findAll();
	}
	
	public List<CustomerOrder> findForCustomer(String userId){
		return repo.findForCustomer(userId);
	}
	
	public void save(CustomerOrder order, String userId) {
		repo.save(order, userId);
	}
	
}
