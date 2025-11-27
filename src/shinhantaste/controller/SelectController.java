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

	// 카테고리 선택 메뉴
	private void standardMenu() {
		String beforeJob = null;
		sm: while (true) {
			String job = null;
			// 글 상세에서 글 목록으로 돌아오는 경우, 기준이 선택된 상태여야 함
			// 따라서 beforeJob 에 기준을 미리 저장해두었다가 활용함
			if (beforeJob==null) { // 메인메뉴에서 들어가는 경우
				PrintUtil.alert("(1) 최신순 (2) 별점순 (3) 한식 (4) 중식 (5) 일식 (6) 양식 (7) 기타 (0) 뒤로가기\n");
				PrintUtil.request("정렬기준 선택");
				job = sc.next();
			} else { // 글 상세에서 목록으로 돌아온 경우
				job = beforeJob;
				beforeJob = null;
			}

			switch (job) {
			case "1", "2", "3", "4", "5", "6", "7" -> {
				List<ArticleDTO> articleList = articleService.selectArticleByJob(job);
				ArticleView.print(articleList);
				// 아티클 메뉴는 [goMain, goList] 배열을 반환함.
				boolean[] boolList = articleMenu();
				boolean goMain = boolList[0];
				boolean goList = boolList[1];
				if (goMain) {
					// 메인으로 가려면 카테고리 선택 메뉴를 벗어나면 됨.
					break sm;
				} else if (goList) {
					// 글 목록을 보려면 beforeJob에 지금 job을 저장한채로 다시 루프를 돌면 됨.
					beforeJob = job;
				}
			}
			case "0" -> {
				// 메인으로 가려면 카테고리 선택 메뉴를 벗어나면 됨.
				break sm;
			}
			default -> {
				PrintUtil.alert("없는 번호입니다. 다시 선택해 주세요.\n");
			}
			}
		}
	}

	// 글 목록 메뉴
	private boolean[] articleMenu() {
		// 메인으로 갈지, 글 목록으로 갈지 저장하는 불리언 변수
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
					// 글 상세 메뉴는 글 목록으로 갈지 안갈지 결정해서 반환함.
					boolList[1] = detailMenu(articleDto);
					if (boolList[1]) {
						// 글 목록으로 가려면 boolList[1]이 true인 채로
						// articleMenu를 벗어나면 됨.
						break am;
					}
				}
			}
			case "2" -> {
				// 정렬기준 선택으로 이동하려면 boolList[1]이 false인 채로
				// articleMenu를 벗어나면 됨.
				break am;
			}
			case "0" -> {
				// 메인으로 이동하려면 boolList[0]이 true인 채로
				// articleMenu를 벗어나면 됨.
				// standardMenu에서는 이 값을 받아서 break하게 됨.
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

	// 글 상세 메뉴
	private boolean detailMenu(ArticleDTO articleDTO) {
		// 글 목록으로 갈지 결정하는 변수
		boolean goList = false;
		dm: while (true) {
			ArticleView.printDetailMenu();
			String job = sc.next();
			switch (job) {
			case "1" -> {
				// 글 수정 로직
				UpdateController updateController = new UpdateController();
				int result = updateController.execute(articleDTO);
				// -1은 비밀번호 틀림, 그 외 음수는 통신 에러
				// 양수는 삭제에 성공한 데이터 수
				if (result==-1) {
					PrintUtil.alert("비밀번호가 틀립니다.\n");
				} else if (result<1) {
					PrintUtil.alert("수정에 실패했습니다.\n");
					ArticleView.printDetail(articleDTO);
					// 이전 글 그대로 다시 출력
				} else{
					PrintUtil.alert("수정에 성공했습니다.\n");
					ArticleDTO newArticleDto = articleService.selectArticleDetail(articleDTO.getArticleId());
					// 새 글 받아와서 출력
					ArticleView.printDetail(newArticleDto);
					articleDTO = newArticleDto;
				} 
				
			}
			case "2" -> {
				// 글 삭제 로직
				PrintUtil.request("정말로 삭제하시겠습니까? (Y/N)");
				if (sc.next().toUpperCase().equals("Y")) {
					DeleteController deleteController = new DeleteController();
					PrintUtil.alert(deleteController.execute(articleDTO));
				} else {
					PrintUtil.alert("글 삭제를 취소합니다.\n");
				}
				// 글 목록으로 가려면 goList가 true인 채로 detailMenu를 벗어나면 됨.
				// articleMenu에서는 이 값을 받아서 break하게 됨.
				// standardMenu에서는 또 이 값을 받아서 break하게 됨.
				goList = true;
				break dm;
			}
			case "0" -> {
				// 글 목록으로 가려면 goList가 true인 채로 detailMenu를 벗어나면 됨.
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
}
