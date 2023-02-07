package Ubenu.model;

public class HistoryItem {

	private Drug drug;
	private int amount;
	private float price;
	
	
	
	
	public HistoryItem() {
	}
	
	
	public Drug getDrug() {
		return drug;
	}
	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
}
