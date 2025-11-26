package shinhantaste.service;

import java.util.List;

import shinhantaste.dao.ArticleDAO;
import shinhantaste.dto.ArticleDTO;

public class ArticleService {

	ArticleDAO articleDAO = new ArticleDAO();

	public void updateArticle(ArticleDTO articleDTO) {
		articleDAO.updateArticle(articleDTO);
	}

	public List<ArticleDTO> selectArticleByJob(String job) {
		return articleDAO.selectArticleByJob(job);
	}
}
