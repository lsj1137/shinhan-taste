package shinhantaste.view;

import java.util.List;

import shinhantaste.dto.CategoryDTO;

public class CategoryView {
	public static void CategoryMenu(List<CategoryDTO> categoryList) {
		if (categoryList == null || categoryList.isEmpty()) {
			System.out.println("표시할 카테고리 목록이 없습니다.");
			return;
		}

		System.out.println("\n───────────────────────");
		System.out.println("      카테고리 메뉴     ");
		System.out.println("┌─────────────────────┐");
		System.out.printf("│ %-4s │ %-10s │\n", "번호", "종류");
		System.out.println("├─────────────────────┤");
		for (CategoryDTO category : categoryList) {
			System.out.printf("│ %-5d │ %-10s │\n", category.getCategoryId(), category.getCategoryName());
		}
		System.out.println("└─────────────────────┘\n");

	}
}
