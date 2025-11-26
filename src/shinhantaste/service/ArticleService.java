package shinhantaste.service;

import shinhantaste.dao.ArticleDAO;
import shinhantaste.dto.ArticleDTO;

public class ArticleService {
	ArticleDAO articleDAO = new ArticleDAO();

	public String insertArticle(ArticleDTO articleDTO) {
		return articleDAO.insertArticle(articleDTO);
	}
}
