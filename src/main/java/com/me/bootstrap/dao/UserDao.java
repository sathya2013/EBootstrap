package com.me.bootstrap.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.me.bootstrap.entity.User;




public interface UserDao extends JpaRepository<User, Long> {
	// 根据登录名查找用户
	User findByUsername(String Username);
	
	// 根据实名查找用户
	User findByRealname(String realname);
	
	// 根据包含登录名(类似like)查找用户
	Page<User> findByUsernameContaining(String Username, Pageable pageable);
	
	//查找用户列表
	public Page<User> findAll(Specification<User> spec, Pageable pageable);
}