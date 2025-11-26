package shinhantaste.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ArticleDTO {
	@NonNull
	private int articleId;
	@NonNull
	private int categoryId;
	@NonNull
	private String title;
	@NonNull
	private String restaurant;
	@NonNull
	private int rating;
	private String review;
	private int distance;
	@NonNull
	private String password;
	@NonNull
	private Date createdAt;
}
