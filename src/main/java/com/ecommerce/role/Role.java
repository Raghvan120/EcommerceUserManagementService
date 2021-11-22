package com.ecommerce.role;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ecommerce.user.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "roles", indexes = {@Index(columnList = "roleName"), @Index(columnList = "isActive")},
uniqueConstraints = {@UniqueConstraint(columnNames = "roleName")})
@Data
@EqualsAndHashCode(of = {"id"})
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String roleName;
	private boolean isActive;

	@OneToMany(mappedBy = "roles", cascade = {CascadeType.MERGE}, orphanRemoval = true)
	private Set<User> userSet = new HashSet<>();
	
	public RoleVO convertToRoleVO() {
		RoleVO role = new RoleVO();
		role.setId(id);
		role.setActive(isActive);
		role.setRoleName(roleName);
	   return role;	
	}
	
}
