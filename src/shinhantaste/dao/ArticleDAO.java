package shinhantaste.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shinhantaste.constant.SQLQuery;
import shinhantaste.dto.ArticleDTO;
import shinhantaste.util.DBUtil;

public class ArticleDAO {
	
	private ArticleDTO makeArticle(ResultSet rs) throws SQLException {
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setArticleId(rs.getInt("article_id"));
		articleDTO.setCategoryId(rs.getInt("category_id"));
		articleDTO.setTitle(rs.getString("title"));
		articleDTO.setTitle(rs.getString("restaurant"));
		articleDTO.setArticleId(rs.getInt("rating"));
		articleDTO.setTitle(rs.getString("review"));
		articleDTO.setArticleId(rs.getInt("distance"));
		articleDTO.setArticleId(rs.getInt("password"));
		articleDTO.setCreatedAt(rs.getDate("created_at"));
		return articleDTO;
	}
	
	public List<ArticleDTO> selectAllArticle() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

		List<ArticleDTO> articleList = new ArrayList<>();

        try {
            conn = DBUtil.dbConnect();
            pstmt = conn.prepareStatement(SQLQuery.SELECT_ARTICLE);
            rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleDTO newStock = makeArticle(rs);
				articleList.add(newStock);
			}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pstmt, rs);
        }
		return articleList;
		
	}
	
	
	
}
