package shinhantaste.controller;

import java.util.List;
import java.util.Scanner;

import shinhantaste.dto.ArticleDTO;
import shinhantaste.service.ArticleService;
import shinhantaste.view.ArticleView;

public class SelectController {

	Scanner sc = new Scanner(System.in);
	ArticleService articleService = new ArticleService();

	public void execute() {
		boolean isStop = false;
		while (!isStop) {
			System.out.println("(1) 최신순 (2) 별점순 (3) 한식 (4) 중식 (5) 일식 (6) 양식 (7) 기타 (0) 뒤로가기");
			System.out.print("정렬기준 선택>> ");
			String job = sc.next();

			switch (job) {
			case "1", "2", "3", "4", "5", "6", "7" -> {
				List<ArticleDTO> articleList = articleService.selectArticleByJob(job);
				ArticleView.print(articleList);

				boolean isStop2 = false;
				while (!isStop2) {
					System.out.println("1.상세 조회  2.정렬기준 변경  0.메인으로");
					System.out.print("작업 선택>> ");

					String job2 = sc.next();

					switch (job2) {
					case "1" -> {
						System.out.print("조회할 글 ID 입력>> ");
						int articleId = sc.nextInt();

						ArticleDTO articleDto = articleService.selectArticleDetail(articleId);
						if (articleDto == null) {
							System.out.println("게시글이 존재하지 않습니다.");
							continue;
						}
						ArticleView.printDetail(articleDto);
					}
					case "2" -> {
						// 정렬기준 선택으로 이동
						isStop2 = true;
					}
					case "0" -> {
						// 메인으로 이동
						isStop2 = true;
						isStop = true;
					}
					default -> {
						System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
					}
					}
				}
			}
			case "0" -> {
				isStop = true;
			}
			default -> {
				System.out.println("없는 번호입니다. 다시 선택해 주세요.");
			}
			}
		}
	}
}
