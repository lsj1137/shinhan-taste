package shinhantaste.service;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> develop
import shinhantaste.dao.ArticleDAO;
import shinhantaste.dto.ArticleDTO;

public class ArticleService {
	ArticleDAO articleDAO = new ArticleDAO();

	public String insertArticle(ArticleDTO articleDTO) {
		return articleDAO.insertArticle(articleDTO);
	}

	ArticleDAO articleDAO = new ArticleDAO();

	public void updateArticle(ArticleDTO articleDTO) {
		articleDAO.updateArticle(articleDTO);
	}

	public List<ArticleDTO> selectArticleByJob(String job) {
		return articleDAO.selectArticleByJob(job);
	}
}
