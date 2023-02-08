package Ubenu.model;

import java.time.LocalDate;
import java.util.List;

public class CustomerOrder {

	private String sysId;
	private List<ShoppingItem> items;
	private LocalDate date;
	private float total;
	
	public CustomerOrder() {
		
	}
	
	
	
	

	public float getTotal() {
		return total;
	}





	public void setTotal(float total) {
		this.total = total;
	}





	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public List<ShoppingItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingItem> items) {
		this.items = items;
	}
	
	
	
}
