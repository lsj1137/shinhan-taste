package shinhantaste.controller;

import java.util.List;
import java.util.Scanner;

import shinhantaste.dto.ArticleDTO;
import shinhantaste.service.ArticleService;
import shinhantaste.view.ArticleView;

public class DeleteController {

	Scanner sc = new Scanner(System.in);
	ArticleService articleService = new ArticleService();

	public String execute(ArticleDTO articleDTO) {
		String result = articleService.deleteArticle(articleDTO.getArticleId());
		return result;
	}
}
