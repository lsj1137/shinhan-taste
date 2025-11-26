package shinhantaste.constant;

public class SQLQuery {
	public static final String SELECT_ARTICLE = """
			
""";
	public static final String INSERT_ARTICLE = """
INSERT INTO ARTICLE (category_id, title, restaurant, rating, review, distance, password, created_at)
VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE)
""";
	public static final String UPDATE_ARTICLE = """
			
""";
	public static final String DELETE_ARTICLE = """
			
""";

}
