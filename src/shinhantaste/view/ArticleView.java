package shinhantaste.view;

import java.util.List;

import shinhantaste.dto.ArticleDTO;

public class ArticleView {

	public static void print(List<ArticleDTO> articleList) {
		System.out.println("글 번호 | [식당이름] | 제목 | 별점");
        System.out.println("----------------------------------------");

        for (ArticleDTO dto : articleList) {
            System.out.printf("%d | [%s] | %s | %d\n",
                dto.getArticleId(),
                dto.getRestaurant(),
                dto.getTitle(),
                dto.getRating()
            );
            System.out.println();
        }
        System.out.println("----------------------------------------");
	}
	
	public static void printDeleteMenu() {
		System.out.println("삭제할 글을 선택해주세요 >> ");
	}
}
