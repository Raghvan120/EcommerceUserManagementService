package com.ecommerce.user;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;


import org.hibernate.annotations.ColumnDefault;

import com.ecommerce.role.Role;

import lombok.Data;

import org.jboss.aerogear.security.otp.api.Base32;

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
	@Column(length = 60)
	private String password;
//	@Column(length = 100)
//	private String name;
	@Column(length = 15)
	private String mobileNumber;
	@Column(length = 30)
	private String secret;
	private boolean isUsing2FA;
	private char sex;
	
	private Integer loginAttempts;
	
    @ColumnDefault("false")
	private boolean enabled;
	@ColumnDefault("false")
	private boolean locked;
	
	@ColumnDefault("false")
	private boolean isVerified;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(final Collection<Role> roles) {
		this.roles = roles;
	}
	public User(){
		super();
		this.secret = Base32.random();
		this.enabled = false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((getEmail() == null) ? 0 : getEmail().hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final User user = (User) obj;
		if (!getEmail().equals(user.getEmail())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("User [id=")
				.append(id)
				.append(", userName").append(userName)
				.append(", email").append(email)
				.append(", password").append(password)
				.append(", mobileNumber").append(mobileNumber)
				.append(", secret").append(secret)
				.append(", isUsing2FA").append(isUsing2FA)
				.append(", sex").append(sex)
				.append(", roles").append(roles)
				.append(", enabled").append(enabled)
				.append(",locked").append(locked)
				.append(", isVerified").append(isVerified)
				.append("]");
		return builder.toString();
	}

	public UserVO convertToUserVO() {
		 UserVO user = new UserVO();
		 user.setId(id);
		 user.setDisabled(enabled);
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
