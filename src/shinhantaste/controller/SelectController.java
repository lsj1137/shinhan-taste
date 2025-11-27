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
		standardMenu();
	}

	private void standardMenu() {
		String beforeJob = null;
		sm: while (true) {
			String job = null;
			if (beforeJob==null) {
				System.out.println("(1) 최신순 (2) 별점순 (3) 한식 (4) 중식 (5) 일식 (6) 양식 (7) 기타 (0) 뒤로가기");
				System.out.print("정렬기준 선택>> ");
				job = sc.next();
			} else {
				job = beforeJob;
				beforeJob = null;
			}

			switch (job) {
			case "1", "2", "3", "4", "5", "6", "7" -> {
				List<ArticleDTO> articleList = articleService.selectArticleByJob(job);
				ArticleView.print(articleList);
				boolean[] boolList = articleMenu();
				boolean goMain = boolList[0];
				boolean goList = boolList[1];
				if (goMain) {
					break sm;
				} else if (goList) {
					beforeJob = job;
				}
			}
			case "0" -> {
				break sm;
			}
			default -> {
				System.out.println("없는 번호입니다. 다시 선택해 주세요.");
			}
			}
		}
	}

	private boolean[] articleMenu() {
		boolean goMain = false;
		boolean goList = false;
		boolean[] boolList = new boolean[2];
		boolList[0] = goMain;
		boolList[1] = goList;
		am: while (true) {
			System.out.println("1.상세 조회  2.정렬기준 변경  0.메인으로");
			System.out.print("선택>> ");
			String job = sc.next();

			switch (job) {
			case "1" -> {
				System.out.print("조회할 글 ID 입력>> ");
				int articleId = sc.nextInt();

				ArticleDTO articleDto = articleService.selectArticleDetail(articleId);
				if (articleDto == null) {
					System.out.println("게시글이 존재하지 않습니다.");
				} else {
					ArticleView.printDetail(articleDto);
					boolList[1] = detailMenu(articleDto);
					if (boolList[1]) {
						break am;
					}
				}
			}
			case "2" -> {
				// 정렬기준 선택으로 이동
				break am;
			}
			case "0" -> {
				// 메인으로 이동
				boolList[0] = true;
			}
			default -> {
				System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
			}
			}
		}
		return boolList;
	}

	private boolean detailMenu(ArticleDTO articleDTO) {
		boolean goList = false;
		dm: while (true) {
			ArticleView.printDetailMenu();
			String job = sc.next();
			switch (job) {
			case "1" -> {
				// TODO: 글 수정 로직
			}
			case "2" -> {
				System.out.println("정말로 삭제하시겠습니까? (Y/N)");
				if (sc.next().toUpperCase().equals("Y")) {
					DeleteController deleteController = new DeleteController();
					System.out.println(deleteController.execute(articleDTO));
				} else {
					System.out.println("글 삭제를 취소합니다.");
				}
				goList = true;
				break dm;
			}
			case "0" -> {
				// 글 목록으로
				goList = true;
				break dm;
			}
			default -> {
				System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
			}
			}
		}
		return goList;
	}
}
