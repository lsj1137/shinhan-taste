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

				System.out.println("1.상세 조회  2.정렬기준 변경  0.메인으로");
				System.out.print("작업 선택>> ");
				
				String job2 = sc.next();

				switch (job2) {
				case "1" -> {
					System.out.print("조회할 글 ID 입력>> ");
					int articleId = sc.nextInt();
					
					ArticleDTO articleDto = articleService.selectArticleDetail(articleId);
					ArticleView.printDetail(articleDto);
				}
				case "2" -> {
				}
				case "0" -> {
				}
				}
//				1. 상세 조회
//			    
//			    1.1. 조회할 글 번호 입력:
//			    
//			    ((없는 번호 입력시)) 글이 존재하지 않습니다. ((조회페이지 메뉴만 다시 출력))
//			    
//			2. 정렬기준 변경 ((조회 전페이지로 이동))
//
//			 0. 메인으로
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
