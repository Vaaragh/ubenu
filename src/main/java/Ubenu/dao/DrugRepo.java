package Ubenu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import Ubenu.model.Drug;
import Ubenu.model.DrugCategory;
import Ubenu.model.PharmaCompany;
import Ubenu.model.enums.EDrugFormulation;
import Ubenu.model.utilities.IdGen;

@Repository
public class DrugRepo {

	@Autowired
	private JdbcTemplate db;
	
	@Autowired
	private DrugCategoryRepo categoryRepo;
	
	@Autowired
	private PharmaCompanyRepo companyRepo;
	
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
			
			PharmaCompany company = companyRepo.findOne(companyId);
			DrugCategory category = categoryRepo.findOne(categoryId);
			
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
	
	public List<Drug> findWishlist(String userId){
		String sql = "SELECT id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved FROM Drug WHERE id in (Select drug_id From wishlist where user_id=?);";
		
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
		 String sql = "INSERT INTO Drug (id, title, drugCode, descript, contra, form, image, inventory, price, company_id, category_id, approved) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 db.update(sql, IdGen.newID(), drug.getDrugName(), drug.getDrugCode(), drug.getDrugDescription(), drug.getContraindications(), drug.getDrugFormulation().toString(), drug.getImagePath(), drug.getInventory(), drug.getPrice(), drug.getPharmaCompany().getSysId(), drug.getDrugCategory().getSysId(), drug.isActive());

	 }
	
	
}
