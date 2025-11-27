package shinhantaste.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import shinhantaste.constant.SQLQuery;
import shinhantaste.dto.CategoryDTO;
import shinhantaste.util.DBUtil;

public class CategoryDAO {

	private CategoryDTO makeArticle(ResultSet rs) throws SQLException {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(rs.getInt("category_id"));
		categoryDTO.setCategoryName(rs.getString("category_name"));
		return categoryDTO;
	}

	public List<CategoryDTO> selectAllCategories() {

		List<CategoryDTO> categoryList = new ArrayList<>();
		Connection conn = null;

		Statement st = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.dbConnect();
			st = conn.createStatement();
			rs = st.executeQuery(SQLQuery.SELECT_CATEGORY);

			while (rs.next()) {
				CategoryDTO category = new CategoryDTO();

				category.setCategoryId((rs.getInt("category_id")));
				category.setCategoryName(rs.getString("category_name"));

				categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return categoryList;
	}
}
