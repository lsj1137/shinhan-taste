package shinhantaste.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ArticleDTO {
	@NonNull
	private Integer articleId;
	@NonNull
	private Integer categoryId;
	@NonNull
	private String title;
	@NonNull
	private String restaurant;
	@NonNull
	private Integer rating;
	private String review;
	private Integer distance;
	@NonNull
	private String password;
	@NonNull
	private Date createdAt;
}
