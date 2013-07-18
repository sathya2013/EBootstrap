package com.me.bootstrap.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_user_role database table.
 * 
 */
@Entity
@Table(name="tb_user_role")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private int priority;

	//bi-directional many-to-one association to Role
    @ManyToOne
	private Role role;

	//bi-directional many-to-one association to User
    @ManyToOne
	private User user;

    public UserRole() {
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

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}