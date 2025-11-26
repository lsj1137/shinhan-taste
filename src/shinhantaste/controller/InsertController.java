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
			if (title == null) {
				PrintUtil.alert("필수 항목이 입력되지 않았습니다. 입력해주세요");
			} else if (!InputChecker.lengthCheck(title, 30)) {
				PrintUtil.alert("최대 30자입니다. 다시 입력해주세요");
			}

			else {
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
			if (restaurant == null) {
				PrintUtil.alert("필수 항목이 입력되지 않았습니다. 입력해주세요.");
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
		CategoryView.CategoryMenu(categoryList);
		while (true) {
			Integer categoryId = sc.nextInt();
			if (categoryId == null) {
				PrintUtil.alert("필수 항목이 입력되지 않았습니다. 입력해주세요.");
			} else {
				articleDTO.setCategoryId(categoryId);
				break;
			}
		}
		return articleDTO;
	}

	public ArticleDTO getRating(ArticleDTO articleDTO) {
		PrintUtil.request("별점을 입력하세요.(필수)");

		while (true) {
			Integer rating = sc.nextInt();
			if (rating == null) {
				System.out.println("필수 항목이 입력되지 않았습니다. 입력해주세요.");
			} else if (!InputChecker.inRange(rating, 1, 5)) {
				PrintUtil.alert("1~5까지의 숫자를 입력해주세요.");
			} else {
				articleDTO.setRating(rating);
				break;
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
				PrintUtil.alert("100자를 넘길 수 없습니다. 다시 입력해주세요.");
				reviewBuilder = new StringBuilder();
			}
		}
		review = reviewBuilder.toString();
		articleDTO.setReview(review);
		return articleDTO;
	}

	public ArticleDTO getDistance(ArticleDTO articleDTO) {
		PrintUtil.request("위치를 입력해주세요(도보 기준 소요시간:분)");
		int distance = sc.nextInt();
		articleDTO.setDistance(distance);
		return articleDTO;
	}

	public ArticleDTO getPassWord(ArticleDTO articleDTO) {
		PrintUtil.request("비밀번호를 입력해주세요(4자리 숫자, 필수)");
		String password = sc.next();
		while (!InputChecker.validPassword(password)) {
			PrintUtil.request("잘못된 형식입니다. 다시 입력해주세요");
			password = sc.next();
		}
		articleDTO.setPassword(password);
		sc.nextLine();
		return articleDTO;
	}

	private boolean getConfirmation() {
		PrintUtil.request("이대로 글을 생성하시겠습니까? (Y/N)");
		while (true) {
			String input = sc.next().toUpperCase();
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
