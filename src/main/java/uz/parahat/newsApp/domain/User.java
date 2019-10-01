package uz.parahat.newsApp.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "useer")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
	private String password;
	private String token;
}
