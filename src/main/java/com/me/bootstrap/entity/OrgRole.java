package com.me.bootstrap.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the tb_org_role database table.
 * 
 */
@Entity
@Table(name="tb_org_role")
public class OrgRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private int priority;

	@Column(name="role_id")
	private BigInteger roleId;

	//bi-directional many-to-one association to Orgnization
    @ManyToOne
	@JoinColumn(name="organization_id")
	private Orgnization orgnization;

    public OrgRole() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public BigInteger getRoleId() {
		return this.roleId;
	}

	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}

	public Orgnization getOrgnization() {
		return this.orgnization;
	}

	public void setOrgnization(Orgnization orgnization) {
		this.orgnization = orgnization;
	}
	
}