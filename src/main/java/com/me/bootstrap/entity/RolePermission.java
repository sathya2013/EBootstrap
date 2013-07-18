package com.me.bootstrap.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_role_permission database table.
 * 
 */
@Entity
@Table(name="tb_role_permission")
public class RolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	//bi-directional many-to-one association to Role
    @ManyToOne
	private Role role;

	//bi-directional many-to-one association to Permission
    @ManyToOne
	private Permission permission;

    public RolePermission() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
}