package com.training.model.jpa;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	private UUID roleId;
	private String roleName;
	private Set<User> users;

	public Role() {
	}

	public Role(UUID roleId, String roleName, Set<User> users) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.users = users;
	}

	@Id
	@GeneratedValue
	@Column(name = "role_id", unique = true, nullable = false)
	public UUID getRoleId() {
		return roleId;
	}

	public void setRoleId(UUID roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_name", nullable = false)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
