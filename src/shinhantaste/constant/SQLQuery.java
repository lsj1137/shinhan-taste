package shinhantaste.constant;

public class SQLQuery {
	public static final String SELECT_ARTICLE = """
			SELECT * FROM ARTICLE WHERE article_id = ?
			""";
	public static final String INSERT_ARTICLE = """
			INSERT INTO ARTICLE (category_id, title, restaurant, rating, review, distance, password, created_at)
			VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE)
			""";
	public static final String UPDATE_ARTICLE = """
			update article 
			set category_id=?, title=?, restaurant=?, rating=?, review=?, distance=?
			where article_id = ?
			
""";
	public static final String DELETE_ARTICLE = """
			delete 
			from article
			where article_id = ?
""";
	
	//최신순, 별점순 정렬

	public static final String SELECT_CATEGORY = """
			select category_id,category_name from category order by category_id
			""";

	// 최신순, 별점순 정렬
	public static final String SELECT_ARTICLE_BY_JOB = """
			select *
			from article
			order by %s desc
""";
	

	//특정 카테고리만 조회
	public static final String SELECT_ARTICLE_ONLY_JOB = """
						select * from article where category_id = ?
			""";
}
