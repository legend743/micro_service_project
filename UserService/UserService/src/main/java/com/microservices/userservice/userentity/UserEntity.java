package com.microservices.userservice.userentity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder 
@ToString 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name="userdetails")
public class UserEntity {

	@Id
	private String userId;
	private String name;
	@Column(name = "email")
	@Email @NotNull @UniqueElements  private String email;
	private String about;
	@Column(name="created_at",nullable = false)
	private LocalDateTime createdAt;
	
	@Transient
	
	private List<Rating>ratings=new ArrayList<>();
	
	@Transient
	private List<Hotel> hotel=new ArrayList<>();
	
	
}
