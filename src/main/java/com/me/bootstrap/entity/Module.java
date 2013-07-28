
 
package com.me.bootstrap.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.google.common.collect.Sets;


/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-2 下午5:36:39 
 */
@Entity
@Table(name="tb_modules")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="com.me.bootstrap.entity")
public class Module  implements Comparable<Module> {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=32)
	private String name;
	
	/**
	 * 模块的入口地址
	 */
	@Column(nullable=false, length=255)
	private String url;
	
	@Column(length=255)
	private String description;
	
	/**
	 * 标志符，用于授权名称（类似module:save）
	 */
	@Column(nullable=false, length=32)
	private String sn;
	
	/**
	 * 模块的排序号,越小优先级越高
	 */
	@Column(length=2)
	private Integer priority = 99;

	@ManyToOne
	@JoinColumn(name="parent_id")
	private Module parent;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="parent")
	@OrderBy("priority ASC")
	private Set<Module> children = Sets.newHashSet();
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="module")
	private Set<Permission> permissions =Sets.newHashSet();

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

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
	 * 返回 url 的值   
	 * @return url  
	 */
	public String getUrl() {
		return url;
	}

	/**  
	 * 设置 url 的值  
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * 返回 priority 的值   
	 * @return priority  
	 */
	public Integer getPriority() {
		return priority;
	}

	/**  
	 * 设置 priority 的值  
	 * @param priority
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**  
	 * 返回 parent 的值   
	 * @return parent  
	 */
	public Module getParent() {
		return parent;
	}

	/**  
	 * 设置 parent 的值  
	 * @param parent
	 */
	public void setParent(Module parent) {
		this.parent = parent;
	}

	/**  
	 * 返回 children 的值   
	 * @return children  
	 */
	public Set<Module> getChildren() {
		return children;
	}

	/**  
	 * 设置 children 的值  
	 * @param children
	 */
	public void setChildren(Set<Module> children) {
		this.children = children;
	}

	/**  
	 * 返回 sn 的值   
	 * @return sn  
	 */
	public String getSn() {
		return sn;
	}

	/**  
	 * 设置 sn 的值  
	 * @param sn
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	/*
	 * 
	 */
	@Override
	public int compareTo(Module m) {
		if (m == null) {
			return -1;
		} else if (m == this) {
			return 0;
		} else if (this.priority < m.getPriority()) {
			return -1;
		} else if (this.priority > m.getPriority()) {
			return 1;
		}

		return 0;	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
