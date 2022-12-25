package com.example.crud.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "users",
uniqueConstraints = {
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email")
})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotBlank
	private Long id;
	@NotBlank
	private String username;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	@NotBlank
	private Set<Role> role = new HashSet<>();

	public User(String username, String email, String password){
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User(){}


	public User(Long id){
		this.id = id;
	}


	@Override
	public String toString() {
		return "users [id=" + id
				+ ", username=" + username
				+ ", email=" + email
				+ ", password=" + password
				+ ", role=" + role
				+ "]";
	}
}
