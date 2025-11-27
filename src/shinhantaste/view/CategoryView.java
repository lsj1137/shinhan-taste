package shinhantaste.view;

import java.util.List;

import shinhantaste.dto.CategoryDTO;

public class CategoryView {
	public static String CategoryMenu(List<CategoryDTO> categoryList) {
		if (categoryList == null || categoryList.isEmpty()) {
			System.out.println("표시할 카테고리 목록이 없습니다.");
			return null;
		}

		StringBuilder sb = new StringBuilder(); 

	    sb.append("\n───────────────────────").append("\n");
	    sb.append("      카테고리 메뉴     ").append("\n");
	    sb.append("┌─────────────────────┐").append("\n"); 
	    sb.append(String.format("│ %-4s │ %-10s │\n", "번호", "종류"));
	    sb.append("├─────────────────────┤").append("\n");
	    for (CategoryDTO category : categoryList) {
	        sb.append(String.format("│ %-5d │ %-10s │\n", category.getCategoryId(), category.getCategoryName()));
	    }
	    sb.append("└─────────────────────┘\n");

	    return sb.toString();

	}
}
