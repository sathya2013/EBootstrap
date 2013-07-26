package com.me.bootstrap.model;



import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;



@XmlRootElement
public class Node implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String text;
	private String state;
	private Map<String,String> attributes=Maps.newHashMap();
	private Set<Node> children =Sets.newHashSet();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public Set<Node> getChildren() {
		return children;
	}
	public void setChildren(Set<Node> children) {
		this.children = children;
	}
	

}
