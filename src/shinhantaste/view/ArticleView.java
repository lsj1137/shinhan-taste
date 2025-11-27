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
	
	public static void printDetail(ArticleDTO dto) {
        System.out.println("\n======================");
        System.out.println("글번호 : " + dto.getArticleId());
        System.out.println("───────────────────");
        System.out.println("식당이름 : " + dto.getRestaurant());
        System.out.println("───────────────────");
        System.out.println("제목 : " + dto.getTitle());
        System.out.println("───────────────────");
        System.out.println("평가 : \n" + dto.getReview());
        System.out.println("───────────────────");
        System.out.println("위치 : 도보 " + dto.getDistance() + "분");
        System.out.println("───────────────────");
        System.out.println("카테고리 : " + getCategoryName(dto.getCategoryId()));
        System.out.println("───────────────────");
        System.out.println("별점 : " + dto.getRating());
        System.out.println("======================");
    }
	
	private static String getCategoryName(int id) {
        return switch (id) {
            case 1 -> "한식"; case 2 -> "중식"; case 3 -> "일식";
            case 4 -> "양식"; case 7 -> "기타"; default -> "기타";
        };
    }
}