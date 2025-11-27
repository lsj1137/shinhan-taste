package shinhantaste.controller;

import java.util.List;
import java.util.Scanner;

import shinhantaste.dto.ArticleDTO;
import shinhantaste.dto.CategoryDTO;
import shinhantaste.service.ArticleService;
import shinhantaste.service.CategoryService;
import shinhantaste.util.InputChecker;
import shinhantaste.util.PrintUtil;
import shinhantaste.view.CategoryView;

public class InsertController {
	Scanner sc = new Scanner(System.in);
	ArticleService articleService = new ArticleService();
	CategoryService categoryService = new CategoryService();

	public void execute() {
		ArticleDTO articleDTO = new ArticleDTO();
		boolean confirmed = false;
		do {
			articleDTO = new ArticleDTO();
			getTitle(articleDTO);
			getRestaurant(articleDTO);
			getCategory(articleDTO);
			getRating(articleDTO);
			getReview(articleDTO);
			getDistance(articleDTO);
			getPassWord(articleDTO);
			confirmed = getConfirmation();
		} while (!confirmed);
		String result = articleService.insertArticle(articleDTO);
		PrintUtil.alert(result);
	}

	public ArticleDTO getTitle(ArticleDTO articleDTO) {
		PrintUtil.request("제목을 입력하세요.(필수)");
		while (true) {
			String title = sc.nextLine();
			if (title.isEmpty()) {
				PrintUtil.alert("필수 항목이 입력되지 않았습니다.\n입력해주세요");
			} else if (!InputChecker.lengthCheck(title, 30)) {
				PrintUtil.alert("최대 30자입니다. 다시 입력해주세요");
			} else {
				articleDTO.setTitle(title);
				break;
			}
		}
		return articleDTO;
	}

	public ArticleDTO getRestaurant(ArticleDTO articleDTO) {
		PrintUtil.request("식당이름을 입력하세요.(필수)");

		while (true) {
			String restaurant = sc.nextLine();
			if (restaurant.isEmpty()) {
				PrintUtil.alert("필수 항목이 입력되지 않았습니다.\n입력해주세요.");
			} else {
				articleDTO.setRestaurant(restaurant);
				break;
			}
		}
		return articleDTO;
	}

	public ArticleDTO getCategory(ArticleDTO articleDTO) {
		PrintUtil.request("카테고리를 입력하세요.(필수)");
		List<CategoryDTO> categoryList = categoryService.selectAll();
		System.out.println(CategoryView.CategoryMenu(categoryList));
		while (true) {
			String categoryId = sc.nextLine();
			if (categoryId.isEmpty()) {
				PrintUtil.alert("필수 항목이 입력되지 않았습니다.\n입력해주세요.");
			} else {
				try {
					Integer categoryIdInt = Integer.parseInt(categoryId);
					if (!InputChecker.inRange(categoryIdInt, 1, 5)) {
						PrintUtil.alert("1~5까지의 숫자를 입력해주세요.");
						continue;
					}
					articleDTO.setCategoryId(categoryIdInt);
					break;
				} catch (NumberFormatException e) {
					PrintUtil.alert("잘못된 입력값입니다.\n다시 입력해주세요.");
				}
			}
		}
		return articleDTO;
	}

	public ArticleDTO getRating(ArticleDTO articleDTO) {
		// TODO: 엔터 한 번 더 입력해야 넘어감
		PrintUtil.request("별점을 입력하세요.(필수)");

		while (true) {
			String rating = sc.nextLine();
			if (rating.isEmpty()) {
				PrintUtil.alert("필수 항목이 입력되지 않았습니다.\n입력해주세요.");
			} else {
				try {
					Integer ratingInt = Integer.parseInt(rating);
					if (!InputChecker.inRange(ratingInt, 1, 5)) {
						PrintUtil.alert("1~5까지의 숫자를 입력해주세요.");
						continue;
					}
					articleDTO.setRating(ratingInt);
					break;
				} catch (NumberFormatException e) {
					PrintUtil.alert("잘못된 입력값입니다.\n다시 입력해주세요.");
				}
			}
		}
		return articleDTO;
	}

	public ArticleDTO getReview(ArticleDTO articleDTO) {
		StringBuilder reviewBuilder = new StringBuilder();
		String review = "";
		String line = null;
		sc.nextLine();
		while (true) {
			PrintUtil.request("평가를 입력하세요(100자 이내, 끝내려면 Enter 2번 입력)\n");
			sc.nextLine();  // 위에 \n 제거
			boolean keepWrite = true;
			while (keepWrite = InputChecker.endReviewInput(line = sc.nextLine())) {
				reviewBuilder.append(line);
				if (keepWrite) {
					reviewBuilder.append("\n");
				}
			}
			if (InputChecker.lengthCheck(review, 100)) {
				break;
			} else {
				PrintUtil.alert("100자를 넘길 수 없습니다.\n다시 입력해주세요.");
				reviewBuilder = new StringBuilder();
			}
		}
		review = reviewBuilder.toString();
		articleDTO.setReview(review);
		return articleDTO;
	}

	public ArticleDTO getDistance(ArticleDTO articleDTO) {
		PrintUtil.request("위치를 입력해주세요(도보 기준 소요시간: 분)");

		while (true) {
			String distance = sc.nextLine();
			if (distance.isEmpty()) {
				// TODO: distance 입력 안 할 때 null 값 insert 가능하도록
				// -> 우선 SQLQuery.INSERT_ARTICLE로는 동적 쿼리 처리가 어려울 것 같아서 -1로 대체 입력
				articleDTO.setDistance(-1);
				break;
			}
			try {
				Integer distanceInt = Integer.parseInt(distance);
				articleDTO.setDistance(distanceInt);
				break;
			} catch (NumberFormatException e) {
				PrintUtil.alert("잘못된 입력값입니다.\n다시 입력해주세요.");
			}
		}

		return articleDTO;
	}

	public ArticleDTO getPassWord(ArticleDTO articleDTO) {
		// TODO: 엔터 한 번 더 입력해야 넘어감
		PrintUtil.request("비밀번호를 입력해주세요(4자리 숫자, 필수)");
		String password = sc.nextLine();
		while (!InputChecker.validPassword(password)) {
			PrintUtil.request("잘못된 형식입니다.\n다시 입력해주세요");
			password = sc.next();
		}
		articleDTO.setPassword(password);
		sc.nextLine();
		return articleDTO;
	}

	private boolean getConfirmation() {
		PrintUtil.request("이대로 글을 생성하시겠습니까? (Y/N)");
		while (true) {
			String input = sc.nextLine().toUpperCase();
			sc.nextLine(); // 버퍼 비우기

			if (input.equals("Y")) {
				return true;
			} else if (input.equals("N")) {
				PrintUtil.alert("글 작성이 취소되었습니다.");
				return false;
			} else {
				PrintUtil.alert("Y 또는 N만 입력해주세요.");
				PrintUtil.request("이대로 글을 생성하시겠습니까? (Y/N)");
			}
		}
	}
}
