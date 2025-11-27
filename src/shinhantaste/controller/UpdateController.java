package shinhantaste.controller;

import java.util.Scanner;

import shinhantaste.dto.ArticleDTO;
import shinhantaste.service.ArticleService;

public class UpdateController {

	Scanner sc = new Scanner(System.in);
	ArticleService articleService = new ArticleService();

	public void execute(ArticleDTO articleDTO) {
		// TODO: 비밀번호 검증(공통)
		String data = null;
		System.out.print("식당 이름>> ");
		String restaurant = null;
		data = sc.nextLine().trim();
		if (!data.isEmpty()) {
			restaurant = data;
			articleDTO.setRestaurant(restaurant);
		}
		
		System.out.print("글 제목(최대 30자)>> ");
		String title = null;
		data = sc.nextLine().trim();
		if (!data.isEmpty()) {
			title = data;
			articleDTO.setTitle(title);
		}
		
		System.out.print("카테고리((1) 한식 (2) 중식 (3) 일식 (4) 양식 (5) 기타)>> ");
		Integer categoryId = null;
		data = sc.nextLine().trim();
		if (!data.isEmpty()) {
			categoryId = Integer.parseInt(data);
			articleDTO.setCategoryId(categoryId);
		}
		
		System.out.print("별점>> ");
		Integer rating = null;
		data = sc.nextLine().trim();
		if (!data.isEmpty()) {
			rating = Integer.parseInt(data);
			articleDTO.setRating(rating);
		}
		
		System.out.print("평가(최대 100자, 입력을 끝내려면 Enter 2번)>> ");
		String review = null;
		StringBuilder sb = new StringBuilder();
		while (true) {
			String line = sc.nextLine();
			if (line.isEmpty()) {
				break;
			} else {
				sb.append(line).append("\n");
			}
		}
		if (sb.length() > 0) {
			review = sb.toString().trim();
			articleDTO.setReview(review);
		}
		
		System.out.print("위치(도보 몇 분)>> ");
		Integer distance = null;
		data = sc.nextLine().trim();
		if (!data.isEmpty()) {
			distance = Integer.parseInt(data);
			articleDTO.setDistance(rating);
		}
		
		articleService.updateArticle(articleDTO);
	}
}
