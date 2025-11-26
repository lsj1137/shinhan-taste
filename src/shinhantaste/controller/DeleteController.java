package shinhantaste.controller;

import java.util.List;
import java.util.Scanner;

import shinhantaste.dto.ArticleDTO;
import shinhantaste.service.ArticleService;
import shinhantaste.view.ArticleView;

public class DeleteController {

	Scanner sc = new Scanner(System.in);
	ArticleService articleService = new ArticleService();

	public void execute(List<ArticleDTO> articleList) {
		ArticleView.printDeleteMenu();
		int deleteId = sc.nextInt();
		for (ArticleDTO articleDTO : articleList) {
			if (articleDTO.getArticleId().equals(deleteId)) {
				String result = articleService.deleteArticle(deleteId);
				System.out.println(result);
			}
		}
	}
}
