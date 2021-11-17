package com.ecommerce.user;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;

import com.ecommerce.role.Role;

import lombok.Data;

@Entity
@Table(name = "users", indexes = {@Index(columnList = "userName"),
		@Index(columnList = "email"),@Index(columnList = "isVerified"),@Index(columnList = "mobileNumber")},
uniqueConstraints = { @UniqueConstraint(columnNames = {"email","mobileNumber"})})
@Data
public class User implements Serializable {
	
	private static final long serialVersionUID = -589728220955307851L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50)
	private String userName;
	@Column(length = 100)
	private String email;
	private String password;
//	@Column(length = 100)
//	private String name;
	@Column(length = 15)
	private String mobileNumber;
	
	private char sex;
	
	private Integer loginAttempts;
	
    @ColumnDefault("false")
	private boolean disabled;
	@ColumnDefault("false")
	private boolean locked;
	
	@ColumnDefault("false")
	private boolean isVerified;
	
	@ManyToOne(cascade = {CascadeType.MERGE})
	private Role role;
	
	public void addRole(Role role) {
		 this.setRole(role);
	}
	
	public UserVO convertToUserVO() {
		 UserVO user = new UserVO();
		 user.setId(id);
		 user.setDisabled(disabled);
		 user.setEmail(email);
		 user.setLocked(locked);
		 user.setLoginAttempts(loginAttempts);
		 user.setMobileNumber(mobileNumber);
		 user.setPassword(password);
		 user.setSex(sex);
		 user.setUserName(userName);
		 user.setVerified(isVerified);
	    return user;
	}
	
}
