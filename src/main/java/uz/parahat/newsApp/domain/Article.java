package uz.parahat.newsApp.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Please fill the message")
	@Length(max = 255, message = "Message too long (more than 255)")
	private String title;
	@NotBlank(message = "Please fill the title")
	private String text;
	@NotBlank(message = "Please fill the text")
	private LocalDateTime createdDate;
	private LocalDateTime updateDate;
//	@ManyToOne(fetch = FetchType.EAGER)
//	private User author;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

}
