package shinhantaste.controller;

import java.util.Scanner;

import shinhantaste.dto.ArticleDTO;
import shinhantaste.service.ArticleService;
import shinhantaste.util.InputChecker;
import shinhantaste.util.PrintUtil;
import shinhantaste.view.ArticleView;

public class UpdateController {

	Scanner sc = new Scanner(System.in);
	ArticleService articleService = new ArticleService();

	public void execute(ArticleDTO prevArticleDTO) {

		ArticleView.getPassword();
		while (true) {
			String curPw = sc.next();
			if (!curPw.isEmpty()) {
				// 비밀번호의 형태가 잘못되었거나, 작성 시 입력한 비밀번호와 다를 때
				if (!InputChecker.validPassword(curPw) || !articleService.checkPassword(prevArticleDTO, curPw)) {
					PrintUtil.alert("비밀번호가 틀립니다.");
					// TODO: 글 상세 페이지로 돌아가기
					return;
				}
				PrintUtil.request("비밀번호를 입력하세요.");
			}

			ArticleDTO articleDTO = new ArticleDTO();
			String data = null;
			PrintUtil.request("식당 이름>> ");
			String restaurant = null;
			data = sc.nextLine().trim();
			if (!data.isEmpty()) {
				restaurant = data;
			} else {
				// 수정 안 할 시, 기존값 불러오기
				restaurant = prevArticleDTO.getRestaurant();
			}
			articleDTO.setRestaurant(restaurant);

			PrintUtil.request("글 제목(최대 30자)>> ");
			String title = null;
			data = sc.nextLine().trim();
			if (!data.isEmpty()) {
				title = data;
			} else {
				title = prevArticleDTO.getTitle();
			}
			articleDTO.setTitle(title);

			PrintUtil.request("카테고리((1) 한식 (2) 중식 (3) 일식 (4) 양식 (5) 기타)>> ");
			Integer categoryId = null;
			data = sc.nextLine().trim();
			if (!data.isEmpty()) {
				categoryId = Integer.parseInt(data);
			} else {
				categoryId = prevArticleDTO.getCategoryId();
			}
			articleDTO.setCategoryId(categoryId);

			PrintUtil.request("별점>> ");
			Integer rating = null;
			data = sc.nextLine().trim();
			if (!data.isEmpty()) {
				rating = Integer.parseInt(data);
			} else {
				rating = prevArticleDTO.getRating();
			}
			articleDTO.setRating(rating);

			PrintUtil.request("평가(최대 100자, 입력을 끝내려면 Enter 2번)>> ");
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
			} else {
				review = prevArticleDTO.getReview();
			}
			articleDTO.setReview(review);

			PrintUtil.request("위치(도보 몇 분)>> ");
			Integer distance = null;
			data = sc.nextLine().trim();
			if (!data.isEmpty()) {
				distance = Integer.parseInt(data);
			} else {
				distance = prevArticleDTO.getDistance();
			}
			articleDTO.setDistance(distance);

			articleService.updateArticle(articleDTO);
		}
	}
}
