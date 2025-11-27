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
		articleDTO.setRestaurant(rs.getString("restaurant"));
		articleDTO.setRating(rs.getInt("rating"));
		articleDTO.setReview(rs.getString("review"));
		articleDTO.setDistance(rs.getInt("distance"));
		articleDTO.setPassword(rs.getString("password"));
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

	public String insertArticle(ArticleDTO articleDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";

		try {
			conn = DBUtil.dbConnect();
			pstmt = conn.prepareStatement(SQLQuery.INSERT_ARTICLE);
			pstmt.setLong(1, articleDTO.getCategoryId());
			pstmt.setString(2, articleDTO.getTitle());
			pstmt.setString(3, articleDTO.getRestaurant());
			pstmt.setInt(4, articleDTO.getRating());
			pstmt.setString(5, articleDTO.getReview());
//			if (articleDTO.getDistance() != null)
			pstmt.setInt(6, articleDTO.getDistance());
			pstmt.setString(7, articleDTO.getPassword());
			int done = pstmt.executeUpdate();
			if (done > 0) {
				result = "글 등록을 완료했습니다.";
			}
		} catch (SQLException e) {
			result = "글 등록에 실패했습니다.";
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pstmt, rs);
		}

		return result;
	}

	public void updateArticle(ArticleDTO articleDTO) {
		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DBUtil.dbConnect();
			st = conn.prepareStatement(SQLQuery.UPDATE_ARTICLE);
			st.setInt(1, articleDTO.getArticleId());
			st.setString(2, articleDTO.getTitle());
			st.setString(3, articleDTO.getRestaurant());
			st.setInt(4, articleDTO.getRating());
			st.setString(5, articleDTO.getReview());
			st.setInt(6, articleDTO.getDistance());
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, null);
		}
	}

	public List<ArticleDTO> selectArticleByJob(String job) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ArticleDTO> articleList = new ArrayList<>();

		try {
			conn = DBUtil.dbConnect();
			String sql = "";

			if (job.equals("1")) {
				// 최신순
				sql = String.format(SQLQuery.SELECT_ARTICLE_BY_JOB, "created_at");
				pstmt = conn.prepareStatement(sql);

			} else if (job.equals("2")) {
				// 별점순
				sql = String.format(SQLQuery.SELECT_ARTICLE_BY_JOB, "rating");
				pstmt = conn.prepareStatement(sql);
			} else {
				pstmt = conn.prepareStatement(SQLQuery.SELECT_ARTICLE_ONLY_JOB);
				pstmt.setInt(1, Integer.parseInt(job) - 2);
			}
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
	
	public String insertArticle(ArticleDTO articleDTO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String result = "";
=======
>>>>>>> 36757b60b35fca55617df346a5cee7646365ce80

	public String deleteArticle(Integer articleId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";

		try {
			conn = DBUtil.dbConnect();
			pstmt = conn.prepareStatement(SQLQuery.DELETE_ARTICLE);
			pstmt.setLong(1, articleId);
			int done = pstmt.executeUpdate();
			if (done > 0) {
				result = "글 삭제를 완료했습니다.";
			}
		} catch (SQLException e) {
			result = "글 삭제에 실패했습니다.";
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pstmt, rs);
		}
		return result;
	}
<<<<<<< HEAD
	
	
	
>>>>>>> develop
=======

	public ArticleDTO selectArticleDetial(int articleId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleDTO articleDTO = new ArticleDTO();

		try {
			conn = DBUtil.dbConnect();
			pstmt = conn.prepareStatement(SQLQuery.SELECT_ARTICLE);
			pstmt.setInt(1, articleId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleDTO = makeArticle(rs);
			}else {
				// 잘못된 articleId인 경우 null 반환
				return null;
			}
		} catch (SQLException e) {

		} finally {
			DBUtil.dbDisconnect(conn, pstmt, rs);
		}
		return articleDTO;
	}
>>>>>>> 36757b60b35fca55617df346a5cee7646365ce80
}
