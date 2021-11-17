package com.ecommerce.role;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVO {
	
	private Integer id;
	@JsonProperty("role_name")
	private String roleName;
	@JsonProperty("active")
	private boolean isActive;
	
	public Role convertToRole() {
		Role role = new Role();
		role.setId(id);
		role.setActive(isActive);
		role.setRoleName(roleName);
	   return role;	
	}

}
