package com.me.bootstrap.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.Sets;
import java.util.Set;


/**
 * The persistent class for the tb_orgnizations database table.
 * 
 */
@Entity
@Table(name="tb_orgnizations")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="com.me.bootstrap.entity")
public class Orgnization implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@Column(nullable=false, length=64)
	private String name;
	
	@Column(length=255)
	private String description;

	@ManyToOne
	@JoinColumn(name="parent_id")
	private Orgnization parent;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="parent")
	private Set<Orgnization> children =Sets.newHashSet();
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="orgnization")
	private Set<User> users = Sets.newHashSet();

	/**  
	 * 返回 name 的值   
	 * @return name  
	 */
	public String getName() {
		return name;
	}

	/**  
	 * 设置 name 的值  
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * 返回 description 的值   
	 * @return description  
	 */
	public String getDescription() {
		return description;
	}

	/**  
	 * 设置 description 的值  
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**  
	 * 返回 parent 的值   
	 * @return parent  
	 */
	public Orgnization getParent() {
		return parent;
	}

	/**  
	 * 设置 parent 的值  
	 * @param parent
	 */
	public void setParent(Orgnization parent) {
		this.parent = parent;
	}

	/**  
	 * 返回 children 的值   
	 * @return children  
	 */
	public Set<Orgnization> getChildren() {
		return children;
	}

	/**  
	 * 设置 children 的值  
	 * @param children
	 */
	public void setChildren(Set<Orgnization> children) {
		this.children = children;
	}

	/**  
	 * 返回 users 的值   
	 * @return users  
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**  
	 * 设置 users 的值  
	 * @param users
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}