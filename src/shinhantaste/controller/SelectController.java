package shinhantaste.controller;

import java.util.List;
import java.util.Scanner;

import shinhantaste.dto.ArticleDTO;
import shinhantaste.service.ArticleService;
import shinhantaste.util.PrintUtil;
import shinhantaste.view.ArticleView;

public class SelectController {

	Scanner sc = new Scanner(System.in);
	ArticleService articleService = new ArticleService();

	public void execute() {
		standardMenu();
	}

	private void standardMenu() {
		String beforeJob = null;
		sm: while (true) {
			String job = null;
			if (beforeJob==null) {
				PrintUtil.alert("(1) 최신순 (2) 별점순 (3) 한식 (4) 중식 (5) 일식 (6) 양식 (7) 기타 (0) 뒤로가기\n");
				PrintUtil.request("정렬기준 선택");
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
				PrintUtil.alert("없는 번호입니다. 다시 선택해 주세요.\n");
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
			PrintUtil.alert("1.상세 조회  2.정렬기준 변경  0.메인으로\n");
			PrintUtil.request("선택");
			String job = sc.next();

			switch (job) {
			case "1" -> {
				PrintUtil.request("조회할 글 ID 입력");
				int articleId = sc.nextInt();

				ArticleDTO articleDto = articleService.selectArticleDetail(articleId);
				if (articleDto == null) {
					PrintUtil.alert("게시글이 존재하지 않습니다.\n");
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
				break am;
			}
			default -> {
				PrintUtil.alert("잘못된 번호입니다. 다시 입력해주세요.\n");
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
				PrintUtil.request("정말로 삭제하시겠습니까? (Y/N)");
				if (sc.next().toUpperCase().equals("Y")) {
					DeleteController deleteController = new DeleteController();
					PrintUtil.alert(deleteController.execute(articleDTO));
				} else {
					PrintUtil.alert("글 삭제를 취소합니다.\n");
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
				PrintUtil.alert("잘못된 번호입니다. 다시 입력해주세요.\n");
			}
			}
		}
		return goList;
	}

	private void handleArticleMenu() {
		while (true) {
			System.out.println("\n1. 상세 조회   2. 정렬기준 변경   0. 메인으로");
			System.out.print("선택>> ");
			String menu = sc.next();

			if (menu.equals("1")) {
				// 상세 조회 로직
				System.out.print("조회할 글 번호 입력>> ");
				int articleId = sc.nextInt();

				// 서비스에서 글 하나 가져오기 (구현 필요)
				ArticleDTO article = articleService.getArticleDetail(articleId);

				if (article != null) {
					ArticleView.printDetail(article); // 상세 내용 출력
					// 여기서 수정/삭제 메뉴를 또 보여줄 수 있음 (필요하다면)
				} else {
					System.out.println("글이 존재하지 않습니다.");
				}

			} else if (menu.equals("2")) {
				break; // 이 내부 반복문을 빠져나가면 -> 다시 '정렬기준 선택'으로 돌아갑니다.
			} else if (menu.equals("0")) {
				System.exit(0); // 아예 프로그램 종료하거나 메인으로 가는 로직
			} else {
				System.out.println("다시 입력해주세요.");
			}
		}
	}
}
