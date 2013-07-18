package com.me.bootstrap.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_logs database table.
 * 
 */
@Entity
@Table(name="tb_logs")
public class Logs implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="ip_address")
	private String ipAddress;

	@Column(name="log_level")
	private String logLevel;

	private String message;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="user")
	private User user;

    public Logs() {
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

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLogLevel() {
		return this.logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}