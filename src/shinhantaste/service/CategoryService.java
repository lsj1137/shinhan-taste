package shinhantaste.service;

import java.util.List;

import shinhantaste.dao.CategoryDAO;
import shinhantaste.dto.CategoryDTO;

public class CategoryService {
	CategoryDAO category = new CategoryDAO();

	public List<CategoryDTO> selectAll() {
		return category.selectAllCategories();
	}
}
