package com.me.bootstrap.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Set;


/**
 * The persistent class for the tb_permissions database table.
 * 
 */
@Entity
@Table(name="tb_permissions")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="com.me.bootstrap.entity")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String description;

	private String name;

	@Column(name="short_name")
	private String shortName;

	//bi-directional many-to-one association to Module
    @ManyToOne
    @JoinColumn(name="module_id")
	private Module module;

	//bi-directional many-to-one association to RolePermission
	@OneToMany(mappedBy="permission")
	private Set<RolePermission> rolePermissions;

    public Permission() {
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

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
	public Set<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}
	
}