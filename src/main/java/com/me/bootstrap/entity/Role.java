package com.me.bootstrap.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;


/**
 * The persistent class for the tb_roles database table.
 * 
 */
@Entity
@Table(name="tb_roles")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="com.me.bootstrap.entity")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String description;

	private String name;
	
	@ElementCollection
	@CollectionTable(
			name = "tb_role_permission", 
			joinColumns = { @JoinColumn(name = "role_id") }
	)
	@Column(name = "permission_id")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="com.ketayao.security.entity.main")
	private List<String> permissionList = Lists.newArrayList();
	

	//bi-directional many-to-one association to RolePermission
	@OneToMany(mappedBy="role")
	private Set<RolePermission> rolePermissions;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="role")
	private Set<UserRole> userRoles;

    public Role() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}
	
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}
	
}