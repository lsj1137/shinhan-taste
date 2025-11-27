package shinhantaste.service;

import shinhantaste.dao.ArticleDAO;
import shinhantaste.dto.ArticleDTO;

import java.util.List;

public class ArticleService {
	ArticleDAO articleDAO = new ArticleDAO();

	public String insertArticle(ArticleDTO articleDTO) {
		return articleDAO.insertArticle(articleDTO);
	}

	public int updateArticle(ArticleDTO articleDTO) {
		return articleDAO.updateArticle(articleDTO);
	}

	public List<ArticleDTO> selectArticleByJob(String job) {
		return articleDAO.selectArticleByJob(job);
	}
	
	public ArticleDTO selectArticleDetail(int articleId) {
		return articleDAO.selectArticleDetial(articleId);
	}
	
	public String deleteArticle(Integer articleId) {
		return articleDAO.deleteArticle(articleId);
	}

	public boolean checkPassword(ArticleDTO prevArticleDTO, String curPw) {
		return articleDAO.checkPassword(prevArticleDTO, curPw);
	}
}
