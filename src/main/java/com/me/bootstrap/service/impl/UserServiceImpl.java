/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.ygsoft.security.service.impl.UserServiceImpl.java
 * Class:			UserServiceImpl
 * Date:			2012-8-7
 * Author:			<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.me.bootstrap.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.bootstrap.dao.UserDao;
import com.me.bootstrap.entity.User;
import com.me.bootstrap.exception.ExistedException;
import com.me.bootstrap.exception.ServiceException;
import com.me.bootstrap.service.UserService;
import com.me.bootstrap.shiro.ShiroDbRealm;
import com.me.bootstrap.shiro.ShiroDbRealm.HashPassword;
import com.me.bootstrap.util.Page;
import com.me.bootstrap.util.PageUtils;
import com.me.bootstrap.util.SearchFilter;



/** 
 * 	
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * Version  1.1.0
 * @since   2012-8-7 下午3:14:29 
 */
@Service("userService")
@Transactional(readOnly=true)
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserDao userDao;
	
	@Autowired
	private ShiroDbRealm shiroRealm;
	
	/**  
	 * 构造函数
	 * @param jpaRepository  
	 */ 
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		super((JpaRepository<User, Long>) userDao);
		this.userDao = userDao;
	}
	
	/**
	 * 
	 * @param user
	 * @throws ExistedException  
	 * @see com.ketayao.security.service.UserService#save(com.ketayao.security.entity.main.User)
	 */
	@Transactional
	public void save(User user) throws ExistedException {		
		if (userDao.findByUsername(user.getUsername()) != null) {
			throw new ExistedException("用户添加失败，登录名：" + user.getUsername() + "已存在。");
		}
		
		if (userDao.findByRealname(user.getRealname()) != null) {
			throw new ExistedException("用户添加失败，真实名：" + user.getRealname() + "已存在。");
		}
		
		//设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
		if (StringUtils.isNotBlank(user.getPlainPassword()) && shiroRealm != null) {
			HashPassword hashPassword = shiroRealm.encrypt(user.getPlainPassword());
			user.setSalt(hashPassword.salt);
			user.setPassword(hashPassword.password);
		}
		
		userDao.save(user);
		shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
	}

	/**   
	 * @param user  
	 * @see com.ketayao.security.service.UserService#update(com.ketayao.security.entity.main.User)  
	 */
	@Transactional
	public void update(User user) {
		//if (isSupervisor(user.getId())) {
		//	logger.warn("操作员{},尝试修改超级管理员用户", SecurityUtils.getSubject().getPrincipal());
		//	throw new ServiceException("不能修改超级管理员用户");
		//}
		//设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
		
		if (StringUtils.isNotBlank(user.getPlainPassword()) && shiroRealm != null) {
			HashPassword hashPassword = shiroRealm.encrypt(user.getPlainPassword());
			user.setSalt(hashPassword.salt);
			user.setPassword(hashPassword.password);
		}
		
		userDao.save(user);
		shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
	}

	/**   
	 * @param id  
	 * @see com.ketayao.security.service.UserService#delete(java.lang.Long)  
	 */
	@Transactional
	public void delete(Long id) throws ServiceException {
		if (isSupervisor(id)) {
			logger.warn("操作员{}，尝试删除超级管理员用户", SecurityUtils.getSubject()
					.getPrincipal() + "。");
			throw new ServiceException("不能删除超级管理员用户。");
		}
		userDao.delete(id);
	}

	/**   
	 * @param username
	 * @return  
	 * @see com.ketayao.security.service.UserService#get(java.lang.String)  
	 */
	public User get(String username) {
		return userDao.findByUsername(username);
	}

//	/**   
//	 * @param page
//	 * @param name
//	 * @return  
//	 * @see com.ketayao.security.service.UserService#find(com.ketayao.util.dwz.Page, java.lang.String)  
//	 */
//	public List<User> find(Page page, String name) {
//		org.springframework.data.domain.Page<User> pageUser = 
//				userDao.findByUsernameContaining(name, PageUtils.createPageable(page));
//		PageUtils.injectPageProperties(page, pageUser);
//		return pageUser.getContent();
//	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	@Override
	public org.springframework.data.domain.Page<User> findPage(
			Map<String, String[]> params, Pageable pageable) {
		
		 return userDao.findAll(spec(params),pageable);
	}
	
	private Specification<User> spec(Map<String, String[]> params) {
		Collection<SearchFilter> filters = SearchFilter.parse(params).values();
		Specification<User> sp = SearchFilter.spec(filters, User.class);
		return sp;
	}
}
