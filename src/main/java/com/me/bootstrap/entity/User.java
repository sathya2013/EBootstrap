package com.me.bootstrap.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import com.google.common.collect.Sets;


import java.util.Date;

import java.util.Set;


/**
 * The persistent class for the tb_users database table.
 * 
 */
@Entity
@Table(name="tb_users")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="com.me.bootstrap.entity")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	private String email;

	private String password;

	private String phone;

	private String picture;

	private String realname;

	private String salt;

	private String status;

	private String username;
	
	@Transient
	private String plainPassword;

	//bi-directional many-to-one association to Logs
	@OneToMany(mappedBy="user")
	private Set<Logs> logs;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private Set<UserRole> userRoles =Sets.newHashSet();

	//bi-directional many-to-one association to Orgnization
    @ManyToOne
	@JoinColumn(name="org_id")
	private Orgnization orgnization;

    public User() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Logs> getLogs() {
		return this.logs;
	}

	public void setLogs(Set<Logs> logs) {
		this.logs = logs;
	}
	
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	public Orgnization getOrgnization() {
		return this.orgnization;
	}

	public void setOrgnization(Orgnization orgnization) {
		this.orgnization = orgnization;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	
}