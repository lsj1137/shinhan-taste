package shinhantaste.constant;

public class SQLQuery {
	public static final String SELECT_ARTICLE = """

			""";
	public static final String INSERT_ARTICLE = """
			INSERT INTO ARTICLE (category_id, title, restaurant, rating, review, distance, password, created_at)
			VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE)
			""";
	public static final String UPDATE_ARTICLE = """
			update article 
			set categoryId=?, title=?, restaurant=?, rating=?, review=?, distance=?
			where articleId = ?
			
""";
	public static final String DELETE_ARTICLE = """
			delete 
			from article
			where article_id = ?
""";
	
	//최신순, 별점순 정렬

	public static final String SELECT_CATEGORY = """
			select * from category order by category_id
			""";

	// 최신순, 별점순 정렬
	public static final String SELECT_ARTICLE_BY_JOB = """
			select *
			from article
			order by %s desc
""";
	

	//특정 카테고리만 조회
	public static final String SELECT_ARTICLE_ONLY_JOB = """
						select * from article where categoryId = ?
			""";
}
