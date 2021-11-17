package com.ecommerce.user;

import com.ecommerce.role.RoleVO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
	
	private Long id;
	@JsonProperty("user_name")
	private String userName;
	private String email;
	private String password;
	@JsonProperty("mobile_number")
	private String mobileNumber;
	private char sex;
	@JsonProperty("login_attempts")
	private Integer loginAttempts;
	private boolean disabled;
	private boolean locked;
	@JsonProperty("verified")
	private boolean isVerified;
	@JsonProperty("role")
	private RoleVO roleVO;
	
	public User convertToUser() {
		 User user = new User();
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
