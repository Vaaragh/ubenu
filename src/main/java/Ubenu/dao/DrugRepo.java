package Ubenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Ubenu.model.Comment;
import Ubenu.model.Drug;
import Ubenu.model.DrugCategory;
import Ubenu.model.PharmaCompany;
import Ubenu.model.enums.EDrugFormulation;
import Ubenu.model.utilities.IdGen;
import Ubenu.service.CommentService;
import Ubenu.service.DrugCategoryService;
import Ubenu.service.PharmaCompanyService;

@Repository
public class DrugRepo {

	@Autowired
	private JdbcTemplate db;
	
	@Autowired
	private DrugCategoryService categoryServ;
	
	@Autowired
	private PharmaCompanyService companyServ;
	
	@Autowired
	private CommentService commServ;
	
	private class RowMap implements RowMapper<Drug>{

		@Override
		public Drug mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			String id = rs.getString(index++);
			String name = rs.getString(index++);
			String code = rs.getString(index++);
			String description = rs.getString(index++);
			String contra = rs.getString(index++);
			EDrugFormulation form = EDrugFormulation.valueOf(rs.getString(index++));
			float rating = 0;
			String image = rs.getString(index++);
			int inventory = rs.getInt(index++);
			float price = rs.getFloat(index++);
			String companyId = rs.getString(index++);
			String categoryId = rs.getString(index++);
			boolean active = rs.getBoolean(index++);
			
			PharmaCompany company = companyServ.findOne(companyId);
			DrugCategory category = categoryServ.findOne(categoryId);
			

			
			Drug drug = new Drug();
			drug.setSysId(id);
			drug.setDrugName(name);
			drug.setDrugCode(code);
			drug.setDrugDescription(description);
			drug.setContraindications(contra);
			drug.setDrugFormulation(form);
			drug.setRating(rating);
			drug.setImagePath(image);
			drug.setInventory(inventory);
			drug.setPrice(price);
			drug.setPharmaCompany(company);
			drug.setDrugCategory(category);
			drug.setActive(active);
			
			return drug;
		}
	}
	
	public List<Drug> findAll(){
		String sql = "SELECT id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved FROM Drug";
		return db.query(sql, new RowMap());
	}
	
	public List<Drug> findExisting(){
		String sql = "SELECT id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved FROM Drug WHERE approved=1 AND inventory>0";
		return db.query(sql, new RowMap());
	}
	
	public List<Drug> findBoughtDrugs(String userId){
		String sql = "SELECT id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved FROM Drug WHERE id in (SELECT drug_id FROM ShoppingItem WHERE id IN (SELECT item_id FROM CustomerOrderItems WHERE order_id IN (SELECT id FROM CustomerOrder WHERE user_id=?)));";
		return db.query(sql, new RowMap(), userId);

	}
	
	
	
	 public Drug findOne(String sysId) {
			String sql = "SELECT id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved FROM Drug WHERE id=?";
			return db.queryForObject(sql, new RowMap(), sysId);
	 }
	 
	 public void delete(String sysId) {
		 String sql = "DELETE FROM Drug Where id=?";
		 db.update(sql,sysId);
	 }
	 
	 
	 public void update(Drug drug) {
		 String sql = "UPDATE Drug SET title=?, drugCode=?, descript=?, contra=?, form=?, image=?, inventory=?, price=?, company_id=?, category_id=?, approved=? WHERE id=?";
		 db.update(sql, drug.getDrugName(), drug.getDrugCode(), drug.getDrugDescription(), drug.getContraindications(), drug.getDrugFormulation().toString(), drug.getImagePath(), drug.getInventory(), drug.getPrice(), drug.getPharmaCompany().getSysId(), drug.getDrugCategory().getSysId(), drug.isActive(), drug.getSysId());

	 }
	 
	 
	 public void save(Drug drug) {
		 String sql = "INSERT INTO Drug (id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		 db.update(sql, IdGen.newID(), drug.getDrugName(), drug.getDrugCode(), drug.getDrugDescription(), drug.getContraindications(), drug.getDrugFormulation().toString(), drug.getImagePath(), drug.getInventory(), drug.getPrice(), drug.getPharmaCompany().getSysId(), drug.getDrugCategory().getSysId(), drug.isActive());

	 }
	 
	 public void approve(String sysId) {
		 String sql = "UPDATE Drug SET approved=? WHERE id=?;";
		 db.update(sql,true,sysId);
	 }
	 
	 public List<Drug> search(String name, String catId, float min, float max, String sortBy, String ascDesc){
			String sql = "SELECT id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved FROM Drug WHERE title LIKE ? and category_id LIKE ? and price<? and price>=? " + wrapSort(sortBy, ascDesc);
			return db.query(sql, new RowMap(), name, catId, max, min);
	 }
	 
	 public List<Drug> searchCustomer(String name, String catId, float min, float max, String sortBy, String ascDesc){
			String sql = "SELECT id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved FROM Drug WHERE title LIKE ? and category_id LIKE ? and price<? and price>=? and approved=? " + wrapSort(sortBy, ascDesc);
			return db.query(sql, new RowMap(), name, catId, max, min, 1);
	 }
	 
	 
	 private String wrapSort(String sortBy, String ascDesc) {
		if(sortBy=="") {
			return ";";
		} 
		return " ORDER BY " + sortBy + " " + ascDesc + ";";
	 }

	 
	 
	 
	 
	 public List<Drug> findWishlist(String userId){
		 String sql = "SELECT id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved FROM Drug WHERE id in (Select drug_id From wishlist where user_id=?);";
		 return db.query(sql, new RowMap(), userId);
	 }	
	 
	 public void addToWishlist(String userId, String drugId) {
		 String sql = "INSERT INTO WIshlist (user_id, drug_id) VALUES (?,?);";
		 db.update(sql, userId, drugId);
	 }
	 
	 public void removeFromWishlist(String userId, String drugId) {
		 String sql = "DELETE FROM Wishlist WHERE user_id=? AND drug_id=?;";
		 db.update(sql, userId, drugId);
	 }
	
}
