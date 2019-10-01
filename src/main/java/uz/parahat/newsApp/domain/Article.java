package uz.parahat.newsApp.domain;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;
	private String text;
	private LocalDateTime createdDate;
	private LocalDateTime updateDate;
	@OneToMany(targetEntity = User.class)
	private User author;
}
