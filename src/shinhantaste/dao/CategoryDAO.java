package shinhantaste.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import shinhantaste.dto.CategoryDTO;

public class CategoryDAO {
	
	private CategoryDTO makeArticle(ResultSet rs) throws SQLException {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(rs.getInt("category_id"));
		categoryDTO.setCategoryName(rs.getString("category_name"));
		return categoryDTO;
	}
	
	

}
